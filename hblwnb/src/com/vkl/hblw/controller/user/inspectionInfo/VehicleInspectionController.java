/**
 * 
 */
package com.vkl.hblw.controller.user.inspectionInfo;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.axis.utils.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vkl.common.json.JsonResult;
import com.vkl.common.json.JsonUtil;
import com.vkl.common.util.StrUtil;
import com.vkl.hblw.common.constant.CustomData;
import com.vkl.hblw.common.util.GsonUtil;
import com.vkl.hblw.dao.domain.PICTURE;
import com.vkl.hblw.dao.domain.TDIESELOILRESULTDATA;
import com.vkl.hblw.dao.domain.TDOUBLEIDEDATA;
import com.vkl.hblw.dao.domain.TFREESPEEDDATA;
import com.vkl.hblw.dao.domain.TGASVMASDATA;
import com.vkl.hblw.dao.domain.TPREFACECHECK;
import com.vkl.hblw.dao.domain.VEHICLELOGIN;
import com.vkl.hblw.dao.persistence.ORIGINALVIDEODao;
import com.vkl.hblw.dao.persistence.PICTUREDao;
import com.vkl.hblw.dao.persistence.TDIESELOILRESULTDATADao;
import com.vkl.hblw.dao.persistence.TDOUBLEIDEDATADao;
import com.vkl.hblw.dao.persistence.TFREESPEEDDATADao;
import com.vkl.hblw.dao.persistence.TGASVMASDATADao;
import com.vkl.hblw.dao.persistence.TPREFACECHECKDao;
import com.vkl.hblw.dao.persistence.UPLOADSTATUSDao;
import com.vkl.hblw.dao.persistence.VEHICLELOGINDao;
import com.vkl.hblw.dao.persistence.VIDEODao;
import com.vkl.hblw.dao.util.PageList;
import com.vkl.hblw.service.user.inspectionInfo.VehicleInspectionService;

/**
 * @author xcc
 * 车辆检测信息
 *
 */
@Controller
@RequestMapping(value = "/user/vehicleInspect")
public class VehicleInspectionController {

	private static final Logger log = Logger.getLogger(VehicleInspectionController.class);

	@Resource
	private VEHICLELOGINDao vehicleloginDao;
	@Autowired
	private TPREFACECHECKDao tprefacecheckDao;// 外检信息
	@Autowired
	private PICTUREDao pictureDao;//图片信息
	@Autowired
	private VehicleInspectionService vehicleInspectionService;
//	@Autowired
//	private TJYJGXXDao tJYJGXXDao;// 检验结果信息
	@Autowired
	private TDOUBLEIDEDATADao tdoubleidedataDao;// 双怠速检验过程信息
	@Autowired
	private TGASVMASDATADao tgasvmasdataDao;// 简易瞬态检验过程信息
	@Autowired
	private TDIESELOILRESULTDATADao tdieseloilresultdataDao;// 加载减速检验过程信息
	@Autowired
	private TFREESPEEDDATADao tfreespeeddataDao;// 自由加速不透光检验过程信息
	@Autowired
	private VIDEODao videoDao;// 检测视频
	
	@Autowired
	private ORIGINALVIDEODao originalvideoDao;// 原始检测视频
	
	
	@Autowired
	private UPLOADSTATUSDao uploadstatusDao;// 检测上传结果
//	@Autowired
//	private SystemCountService systemCountService;// 
	

	private String ret(Model model, String view) {
		return "/user/inspect/" + view;
	}

	/** 
	* @Title: index 
	* @Description: 检测信息界面首页 
	* @param model
	* @param req
	* @return
	* @throws Exception    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	@RequestMapping("/index")
	public String index(Model model, HttpServletRequest req) throws Exception {

		return ret(model, "index");

	}
	
	/** 
	* @Title: dataList 
	* @Description: 查询检测车辆登录信息列表 
	* @param model
	* @param req
	* @return
	* @throws Exception    设定文件 
	* @return ResponseEntity<String>    返回类型 
	* @throws 
	*/
	@RequestMapping("/dataList")
	public ResponseEntity<String> dataList(Model model, HttpServletRequest req) throws Exception {
		int page = StrUtil.getParamInt(req, "page", 1);
		String cnumberplate = StrUtil.getParamStr(req, "CNUMBERPLATE", "");
		String dcheckbegindate = StrUtil.getParamStr(req, "DCHECKBEGINDATE", "");
		String dcheckenddate = StrUtil.getParamStr(req, "DCHECKENDDATE", "");
		String cchkdevicecode = StrUtil.getParamStr(req, "CCHKDEVICECODE", "");
		String institution = StrUtil.getParamStr(req, "INSTITUTION", "");
		String checkmethod = StrUtil.getParamStr(req, "CHECKMETHOD", "");
		String checktype = StrUtil.getParamStr(req, "CHECKTYPE", "");//检测类别
		String checkstate = StrUtil.getParamStr(req, "CHECKSTATE", "");
		String checklink = StrUtil.getParamStr(req, "CHECKLINK", "");
		
		JsonResult result = new JsonResult();
		
		PageList<VEHICLELOGIN> list = vehicleInspectionService.pageListByCondition(cnumberplate, dcheckbegindate, dcheckenddate,
				page, cchkdevicecode, institution, checktype, checkmethod, checkstate, checklink); 
		
		result.setAttach(list);
		String jsonResult = GsonUtil.toJson(result, "yyyy-MM-dd HH:mm:ss");
		return JsonUtil.toResponse(jsonResult);
	
	}
	

	/** 
	* @Title: detail 
	* @Description: 查询检测车辆详情数据
	* @param model
	* @param req
	* @return
	* @throws Exception    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	@RequestMapping("/detail")
	public String detail(Model model, HttpServletRequest req) throws Exception {
		String ccheckcode = StrUtil.getParamStr(req, "CCHECKCODE", "");//检测流水号
		String cprefacecheckid = StrUtil.getParamStr(req, "CPREFACECHECKID", "");//外观检测流水号
		
		if (!StringUtils.isEmpty(ccheckcode)) {
			VEHICLELOGIN vehiclelogin = vehicleloginDao.queryByCcheckcode(ccheckcode);
			TPREFACECHECK tprefacecheck = tprefacecheckDao.queryByCprefacecheckid(cprefacecheckid);
			
			List<PICTURE> pictureDJList = pictureDao.listByCprefacecheckid(cprefacecheckid, CustomData.NODECODE_PICTURE_DJ);
			List<PICTURE> pictureWJList = pictureDao.listByCprefacecheckid(cprefacecheckid, CustomData.NODECODE_PICTURE_WJ);
			List<PICTURE> pictureSXList = pictureDao.listByCprefacecheckid(cprefacecheckid, CustomData.NODECODE_PICTURE_SX);
			

			// 检验过程
			if (CustomData.JYFF_SDS.equals(vehiclelogin.getExt_col10())) {
				List<TDOUBLEIDEDATA> jcgcxxList = tdoubleidedataDao.listByCcheckcode(ccheckcode);
				model.addAttribute("jcgcxx", jcgcxxList);
			} else if (CustomData.JYFF_JYST.equals(vehiclelogin.getExt_col10())) {
				List<TGASVMASDATA> jcgcxxList = tgasvmasdataDao.listByCcheckcode(ccheckcode);
				model.addAttribute("jcgcxx", jcgcxxList);
			} else if (CustomData.JYFF_JZJS.equals(vehiclelogin.getExt_col10())) {
//				List<TDIESELOILRESULTDATA> jcgcxxList = tdieseloilresultdataDao.listByCcheckcode(ccheckcode);
//				model.addAttribute("jcgcxx", jcgcxxList);
			} else if (CustomData.JYFF_ZYJSBTG.equals(vehiclelogin.getExt_col10())) {
				List<TFREESPEEDDATA> jcgcxxList = tfreespeeddataDao.listByCcheckcode(ccheckcode);
				model.addAttribute("jcgcxx", jcgcxxList);
			} 
		}
		return ret(model, "detail");
	}
}
