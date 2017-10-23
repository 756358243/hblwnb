/**
 * 
 */
package com.vkl.hblw.service.api.pda;

import java.io.File;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import com.vkl.common.json.JsonResult;
import com.vkl.common.json.JsonUtil;
import com.vkl.hblw.common.constant.CustomData;
import com.vkl.hblw.common.constant.ResultDate;
import com.vkl.hblw.common.constant.SystemInfo;
import com.vkl.hblw.common.util.AndroidOut;
import com.vkl.hblw.common.util.FileUtils;
import com.vkl.hblw.common.util.MyDateUtils;
import com.vkl.hblw.common.util.PropertiesUtil;
import com.vkl.hblw.common.util.file.FileOperate;
import com.vkl.hblw.dao.domain.ORIGINALVIDEO;
import com.vkl.hblw.dao.domain.TPREFACECHECK;
import com.vkl.hblw.dao.domain.UPLOADSTATUS;
import com.vkl.hblw.dao.domain.VEHICLELOGIN;
import com.vkl.hblw.dao.domain.VIDEO;
import com.vkl.hblw.dao.persistence.ORIGINALVIDEODao;
import com.vkl.hblw.dao.persistence.PICTUREDao;
import com.vkl.hblw.dao.persistence.TPREFACECHECKDao;
import com.vkl.hblw.dao.persistence.UPLOADSTATUSDao;
import com.vkl.hblw.dao.persistence.VEHICLELOGINDao;
import com.vkl.hblw.dao.persistence.VIDEODao;
import com.vkl.hblw.dao.util.SqlSelect;
import com.vkl.hblw.dao.util.SqlUpdate;

/** 
* @ClassName: PdaService 
* @Description: 有关pda的所有的业务处理方法 
* @author aiyun
* @date 2017年10月19日 下午3:53:58 
*  
*/
@Service
public class PdaService {

	private static final Logger log = Logger.getLogger(PdaService.class);
	
	@Autowired
	private VEHICLELOGINDao vEHICLELOGINDao;// 车辆状态信息信息
	
	@Autowired
	private TPREFACECHECKDao tPREFACECHECKDao;// 外检信息
	
	@Autowired
	private PICTUREDao pICTUREDao;// 图片信息
	
	@Autowired
	private ORIGINALVIDEODao oRIGINALVIDEODao;// 原始视频信息
	
	//UPLOADSTATUS
	@Autowired
	private UPLOADSTATUSDao uPLOADSTATUSDao;// 原始视频信息

	/** 
	* @Title: getQuarantineCars 
	* @Description: 查询所有的待检测车辆，并且可以根据号牌号码和车架号查询 
	* @param CNUMBERPLATE
	* @param CVIN
	* @return    设定文件 
	* return List<VEHICLELOGIN>    返回类型 
	* @throws 
	*/
	public JsonResult getQuarantineCars(String CNUMBERPLATE,String CVIN) {
		JsonResult result = new JsonResult();
		Map<String, String> map = new HashMap<String, String>();
		SqlSelect sql = new SqlSelect().where(VEHICLELOGIN.SQL_checkstate);
		map.put("checkstate", CustomData.DLXX_STATUS_QUARANTINE);
		if(!CNUMBERPLATE.isEmpty()){
			map.put("CNUMBERPLATE", "%" + CNUMBERPLATE + "%");
			sql.and(VEHICLELOGIN.FLD_cnumberplate + " like :CNUMBERPLATE ");
			}
		if(!CVIN.isEmpty()){
			map.put("CVIN", "%" + CVIN + "%");
			sql.and(VEHICLELOGIN.FLD_cvin + " like :CVIN ");
		}
		List<VEHICLELOGIN> listByMap = vEHICLELOGINDao.listByMap(sql, map);
		if (listByMap.isEmpty()) {
			AndroidOut.aiyunprintln("---------"+CNUMBERPLATE);
            result.setCode(1);
			result.setInfo(ResultDate.RS_PDA_VEHICLELOGIN_NULL);
		
		}
		result.setAttach(listByMap);
		return result;
	}
	
	
	/** 
	* @Title: TprefaceCheckStart 
	* @Description: 车辆外检开始接口 
	* @return    设定文件 
	* @return JsonResult    返回类型 
	* @throws 
	*/
	public JsonResult TprefaceCheckStart(String CCHECKCODE){
		JsonResult result = new JsonResult();
		try {
		//1.查询外检信息表里面是否存在数据
		TPREFACECHECK tPREFACECHECK = tPREFACECHECKDao.queryByCcheckcode(CCHECKCODE);
		AndroidOut.aiyunprintln(tPREFACECHECK.getCcheckcode());
		if(tPREFACECHECK == null || "".equals(tPREFACECHECK)){
			log.info(ResultDate.RS_PDA_TPREFACECHECK_NULL);
			result.setCode(1);
			result.setInfo(ResultDate.RS_PDA_TPREFACECHECK_NULL);
			return result;
		}
		//2.更新外检信息表的检查开始时间
		tPREFACECHECK.setVideostarttime(new Timestamp(System.currentTimeMillis()));
		SqlUpdate sql = new SqlUpdate().addColumn(TPREFACECHECK.SQL_videostarttime).where(TPREFACECHECK.SQL_id);
		tPREFACECHECKDao.update(sql, tPREFACECHECK);
		result.setInfo(ResultDate.RS_PDA_SECCESS);
		}catch (Exception e) {
			// TODO: handle exception
			log.error(ResultDate.METHOD_PDA_ERROR, e);
			result.setCode(1);
			result.setInfo(ResultDate.METHOD_PDA_ERROR);
			return result;
		}
		return result;
	}
	
	
	
	/** 
	* @Title: pictureUpload 
	* @Description: pda图片上传方法
	* @param CPREFACECHECKID
	* @param PICTURENUMBER
	* @return    设定文件 
	* @return JsonResult    返回类型 
	* @throws 
	*/
	public JsonResult pictureUpload(MultipartRequest multipartRequest,String CCHECKCODE,String CPREFACECHECKID,String PICTURENUMBER){
		JsonResult result = new JsonResult();
		//1.拼接存取路径
		//修改图片存储路径
		String purl = "VideoCompressUrl.properties"; 
		PropertiesUtil util = new PropertiesUtil(purl);    
		String url = util.get("VIDEOURL");
		String picPath = url + "\\"+MyDateUtils.getNowYear()+"\\"+MyDateUtils.getNowMonth()+"\\"+MyDateUtils.getNowDay()+"\\";
		log.info("picPath:" + picPath);
				
		boolean isExists = FileUtils.existsDirectory(picPath);
		if(isExists){
		log.info("filePath:bu cun zai wenjian mulv....." );
		               
		}else{
		FileUtils.forceDirectory(picPath);
		log.info("filePath:chuangjian  chenggong....." );

		 }
		log.info("filePath:" + picPath);
		//2.保存pda相片
		Map<String, MultipartFile> uploadFiles = multipartRequest.getFileMap();
		if (null == uploadFiles || uploadFiles.size() <= 0) {
			log.info("uploadFiles.size() <= 0");
			return result; 
		}
		
		Set<String> fileNameSet = uploadFiles.keySet();
		Iterator<String> it = fileNameSet.iterator();
		while (it.hasNext()) {

			// 本地存储用户上传的文件
			String itemFileId = it.next();
			MultipartFile multipartFile = uploadFiles.get(itemFileId);
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");

			FileOperate.newFolder(picPath);
			String filepath = picPath;
			String ext = multipartFile.getOriginalFilename()
					.substring(multipartFile.getOriginalFilename().lastIndexOf("."));
			String uploadFileName = CPREFACECHECKID + "_" + PICTURENUMBER + ext;
			String uploadPath = filepath + "\\" + uploadFileName;
            //保存图片
			SavePicture(filepath, uploadFileName, multipartFile);
			//查询登录信息表
			TPREFACECHECK tprefacecheck = tPREFACECHECKDao.queryByCcheckcode(CCHECKCODE);
			//获取当前的时间插入数据库
			//插入图片数据库
			 Integer rsInt = pICTUREDao.insertOrUpdatePicture(CCHECKCODE, CPREFACECHECKID, tprefacecheck.getCvehiclecode(), tprefacecheck.getCunitcode(), CustomData.PICTURE_W_TYPE, PICTURENUMBER, uploadPath, new Timestamp(System.currentTimeMillis()), tprefacecheck.getCnumberplate());
			 if(rsInt == 1){
				 result.setCode(1);
				 result.setInfo(ResultDate.METHOD_insertOrUpdatePicture_PDA_ERROR);
			     return result;
			 }
		}
		return result;
	}

		

		
		
		/** 
		* @Title: SavePicture 
		* @Description: 保存图片
		* @param filepath
		* @param filename
		* @param multipartFile
		* @return    设定文件 
		* @return Boolean    返回类型 
		* @throws 
		*/
		public Boolean SavePicture(String filepath, String filename, MultipartFile multipartFile) {

			log.info(filepath);
			try {
				File fileDirc = new File(filepath);
				if (!fileDirc.exists() && !fileDirc.isDirectory()) {
					fileDirc.mkdir();
				}

				// 保存的上传文件路径
				String uploadPath = filepath + "\\" + filename;
				File uploadFile = new File(uploadPath);
				if (!uploadFile.exists()) {

					uploadFile.createNewFile();
				}
				multipartFile.transferTo(uploadFile);
			} catch (Exception e) {
				log.error(SystemInfo.LOG_CODE_ERROR, e);
				return false;
			}
			return true;
		}

		
		public JsonResult insertUnderpanStart(String CCHECKCODE,String CPREFACECHECKID,String CCHKDEVICECODE,String CUNITCODE){
			JsonResult result = new JsonResult();
			//到时候自己配置通道
			try {
				String PASSWAYTYPE = CustomData.SP_MONITOR_CONFIG.get(CustomData.NODECODE_VIDEO_WJ + CustomData.CURSOR_VIDEO_WJ_UNDER);
				Timestamp startTime = new Timestamp(System.currentTimeMillis()); 
				String  VIDEOTYPE = CustomData.VIDEO_W_TYPE;
				String VIDEONUMBER = CPREFACECHECKID+"-"+CustomData.VIDEO_CODE3;
				String ORVIDEODOWNYESORNO = "0";
				//1.0判断车是否已经登录
			    VEHICLELOGIN vehiclelogin  = vEHICLELOGINDao.queryByCcheckcode(CCHECKCODE);
				if(null == vehiclelogin){
					log.info(ResultDate.RS_PDA_VEHICLELOGIN_NULL);
					result.setCode(1);
					result.setInfo(ResultDate.RS_PDA_VEHICLELOGIN_NULL);
					return result;
				}
				
				//查看数据库是否有记录
				ORIGINALVIDEO oRIGINALVIDEO =  new ORIGINALVIDEO();
				oRIGINALVIDEO = oRIGINALVIDEODao.queryOriginalVideoByCcheckcode(CCHECKCODE,PASSWAYTYPE);
				if(null == oRIGINALVIDEO){
					//数据库没有，则进行重新插入
					ORIGINALVIDEO insertORIGINALVIDEO =  new ORIGINALVIDEO();
					insertORIGINALVIDEO.setCcheckcode(CCHECKCODE);
					insertORIGINALVIDEO.setCchkdevicecode(CCHKDEVICECODE);
					insertORIGINALVIDEO.setCprefacecheckid(CPREFACECHECKID);
					insertORIGINALVIDEO.setCunitcode(CUNITCODE);
					insertORIGINALVIDEO.setOrvideodownyesorno(ORVIDEODOWNYESORNO);
					insertORIGINALVIDEO.setPasswaytype(PASSWAYTYPE);
					insertORIGINALVIDEO.setVideonumber(VIDEONUMBER);
					insertORIGINALVIDEO.setVideostarttime(startTime);
					insertORIGINALVIDEO.setVideotype(VIDEOTYPE);
					oRIGINALVIDEODao.insert(insertORIGINALVIDEO);
				}else{
					//数据库有数据，则直接更新就可以了
					oRIGINALVIDEO.setVideostarttime(startTime);
					oRIGINALVIDEO.setCchkdevicecode(CCHKDEVICECODE);
					SqlUpdate sqlUpdate = new SqlUpdate().addColumn(ORIGINALVIDEO.SQL_videostarttime).addColumn(ORIGINALVIDEO.SQL_cchkdevicecode).where(ORIGINALVIDEO.SQL_id);
				    oRIGINALVIDEODao.update(sqlUpdate, oRIGINALVIDEO);
				}
				
			} catch (Exception e) {
				// TODO: handle exception
				log.info(ResultDate.METHOD_insertUnderpan_PDA_ERROR);
				result.setCode(1);
				result.setInfo(ResultDate.METHOD_insertUnderpan_PDA_ERROR);
			}
			
		    return result;

		}
		
		
		public JsonResult insertUnderpanEnd(String CCHECKCODE){
			JsonResult result = new JsonResult();
			try {
				//1.0判断车是否已经登录
			    VEHICLELOGIN vehiclelogin  = vEHICLELOGINDao.queryByCcheckcode(CCHECKCODE);
				if(null == vehiclelogin){
					log.info(ResultDate.RS_PDA_VEHICLELOGIN_NULL);
					result.setCode(1);
					result.setInfo(ResultDate.RS_PDA_VEHICLELOGIN_NULL);
					return result;
				}
				//查看数据库是否有记录
				String PASSWAYTYPE = CustomData.SP_MONITOR_CONFIG.get(CustomData.NODECODE_VIDEO_WJ + CustomData.CURSOR_VIDEO_WJ_UNDER);
				Timestamp endTime = new Timestamp(System.currentTimeMillis()); 
				ORIGINALVIDEO oRIGINALVIDEO =  new ORIGINALVIDEO();
				oRIGINALVIDEO = oRIGINALVIDEODao.queryOriginalVideoByCcheckcode(CCHECKCODE,PASSWAYTYPE);
				if(null == oRIGINALVIDEO){
					//如果没有底盘视频
					log.info(ResultDate.RS_PDA_ORIGINALVIDEO_NULL);
					result.setCode(1);
					result.setInfo(ResultDate.RS_PDA_ORIGINALVIDEO_NULL);
					return result;
				}else{
					//进行更新底盘视频结束时间
					oRIGINALVIDEO.setVideoendtime(endTime);
					SqlUpdate sqlUpdate = new SqlUpdate().addColumn(ORIGINALVIDEO.SQL_videoendtime).where(ORIGINALVIDEO.SQL_id);
				    oRIGINALVIDEODao.update(sqlUpdate, oRIGINALVIDEO);
				}
				
			} catch (Exception e) {
				// TODO: handle exception
				log.info(ResultDate.METHOD_insertUnderpanEnd_PDA_ERROR);
				result.setCode(1);
				result.setInfo(ResultDate.METHOD_insertUnderpanEnd_PDA_ERROR);
				return result;
			}
			return result;
		}
		
		
		public JsonResult wjxxAllEnd(String jsonData){
			JsonResult result = new JsonResult();
			
			try {
				TPREFACECHECK tprefacecheck = JsonUtil.fromRequest(jsonData, TPREFACECHECK.class);

				//1.0判断车是否已经登录
			    VEHICLELOGIN vlogin  = vEHICLELOGINDao.queryByCcheckcode(tprefacecheck.getCcheckcode());
				if(null == vlogin){
					log.info(ResultDate.RS_PDA_VEHICLELOGIN_NULL);
					result.setCode(1);
					result.setInfo(ResultDate.RS_PDA_VEHICLELOGIN_NULL);
					return result;
				}
				
			    //2.更新外检信息数据库
			    Integer rsInt =	tPREFACECHECKDao.updateTpefacecheck(tprefacecheck);
				if(rsInt > 0){
					log.info(ResultDate.METHOD_updateTpefacecheck_PDA_ERROR);
					result.setCode(1);
					result.setInfo(ResultDate.METHOD_updateTpefacecheck_PDA_ERROR);
				}
			    //3.更新车辆状态表
				VEHICLELOGIN vehiclelogin = new VEHICLELOGIN();
				vehiclelogin.setCcheckcode(tprefacecheck.getCcheckcode());
				vehiclelogin.setCvehiclecode(tprefacecheck.getCvehiclecode());
				vehiclelogin.setCnumberplate(tprefacecheck.getCnumberplate());
				vehiclelogin.setCplatetype(tprefacecheck.getCplatetype());
				vehiclelogin.setCvin(tprefacecheck.getCvin());
				vehiclelogin.setCengineno(tprefacecheck.getCenginemodelno());
				vehiclelogin.setCvehiclestyle(tprefacecheck.getCvehiclestyle());
				vehiclelogin.setCpurposeid(tprefacecheck.getCpurposeid());
				vehiclelogin.setCfueltype(tprefacecheck.getCfueltype());
				vehiclelogin.setCunitname(tprefacecheck.getCunitcode());
				//已外检
				vehiclelogin.setCheckstate(CustomData.DLXX_STATUS_VISUAL);
				vehiclelogin.setFuelsupply(tprefacecheck.getFuelsupply());
				vehiclelogin.setCdriveform(tprefacecheck.getCdriveform());
				Integer rsstatu = vEHICLELOGINDao.updateVehiclelogin(vehiclelogin);
				if(rsstatu > 0){
					log.info(ResultDate.METHOD_updateVehiclelogin_PDA_ERROR);
					result.setCode(1);
					result.setInfo(ResultDate.METHOD_updateVehiclelogin_PDA_ERROR);
				}
	
				//4.更新原始视频表
				Integer ovideoRs = oRIGINALVIDEODao.insertOriginalvideo(tprefacecheck.getCcheckcode());
				if(ovideoRs > 0){
					log.info(ResultDate.METHOD_insertOriginalvideo_PDA_ERROR);
					result.setCode(1);
					result.setInfo(ResultDate.METHOD_insertOriginalvideo_PDA_ERROR);
				}
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			return result;

		}
		
	
}
