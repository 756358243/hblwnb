/**
 * 
 */
package com.vkl.hblw.service.api.autoPrint;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.List;

import org.apache.axis.utils.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vkl.common.util.Util;
import com.vkl.hblw.common.bean.api.query.PrintOrder;
import com.vkl.hblw.dao.domain.ORIGINALVIDEO;
import com.vkl.hblw.dao.domain.RESULTSREPORT;
import com.vkl.hblw.dao.domain.TPREFACECHECK;
import com.vkl.hblw.dao.domain.VEHICLELOGIN;
import com.vkl.hblw.dao.persistence.ORIGINALVIDEODao;
import com.vkl.hblw.dao.persistence.RESULTSREPORTDao;
import com.vkl.hblw.dao.persistence.TPREFACECHECKDao;
import com.vkl.hblw.dao.persistence.VEHICLELOGINDao;
import com.vkl.hblw.setting.config.APIDataConfig;

/**
 * @author xcc
 *
 */
@Service
public class AutoPrintService {

	private static final Logger log = Logger.getLogger(AutoPrintService.class);
	/**外检信息*/
	@Autowired
	private TPREFACECHECKDao tprefacecheckDao;
	/**原始视频*/
	@Autowired
	private ORIGINALVIDEODao originalvideoDao;
	/**检验结果*/
	@Autowired
	private RESULTSREPORTDao resultsreportDao;
	/**车辆状态信息*/
	@Autowired
	private VEHICLELOGINDao vehicleloginDao;
	@Autowired
	private APIDataConfig apiDataConfig;
	@Autowired
	private Util util;
	
	/**
	 * 
	* @Title: getOrderInfo 
	* @Description: 获取打印信息
	* @param ccheckcode 流水号
	* @return
	* @throws Exception
	 */
	public PrintOrder getOrderInfo(String ccheckcode) throws Exception {
		/**车辆状态信息*/
		VEHICLELOGIN vehiclelogin=vehicleloginDao.queryByCcheckcode(ccheckcode);
		if (null == vehiclelogin || StringUtils.isEmpty(vehiclelogin.getCvehiclestyle())) {
			log.error("StringUtils.isEmpty(vehiclelogin.getJybgbm())");
			return null;
		}
		/**外检信息*/
		TPREFACECHECK tprefacecheck=tprefacecheckDao.queryByCcheckcode(ccheckcode);
		if (null == tprefacecheck) {
			log.error("null==tprefacecheck");
			return null;
		}
		/**查询所有原始视频的记录，得到所有的时间*/
		List<ORIGINALVIDEO> originalvideos =null ;//originalvideoDao.queryByCcheckcode(ccheckcode);
		if (null == originalvideos) {
			log.error("null == originalvideos");
			return null;
		}  
		Timestamp dpTime = null;
		Timestamp sxTime = null;
		//for (ORIGINALVIDEO video : originalvideos) {
			/**Nodecode视频模块编码   Cursor视频模块内代码*/
			//if("1002".equals(video.getNodecode())&&"03".equals(video.getCursor())){
				/**视频结束时间*/
				//dpTime=video.getVideoendtime();
			//}
			//if("1004".equals(video.getNodecode())&&"04".equals(video.getCursor())){
				/**视频结束时间*/
				//sxTime=video.getVideoendtime();
			//}
		//}
		Timestamp zhTime = null;
		/*if(dpTime.getTime()>=sxTime.getTime()){
			zhTime = dpTime;
		}else{
			zhTime = sxTime;
		}*/
		/**检验结果信息*/
		RESULTSREPORT resultsreport=resultsreportDao.queryByCcheckcode(ccheckcode);
		if (null == resultsreport) {
			log.error("null == resultsreport");
			return null;
		}
		/**湿度*/
		/*if (!StringUtils.isEmpty(resultsreport.getSd())) {
			double sd = Double.parseDouble(resultsreport.getSd()) * 100;
			DecimalFormat df = new DecimalFormat("#.0");
			resultsreport.setSd(df.format(sd));
		}*/
		/**打印结果报告单*/
		PrintOrder order = new PrintOrder();
		order.setCheckmethod(vehiclelogin.getExt_col10());/**检验方法*/
		//order.setInspectionReportCoding();/**检测报告编码*/
		order.setCnumberplate(vehiclelogin.getCnumberplate());/**号牌号码*/
		//order.setAllPersion(vehiclelogin.get);/**所有人*/
		order.setVehicleModel(vehiclelogin.getCvehiclestyle());/**车辆类型*/
		//order.setManufacturerName();/**生产厂商名称*/
		order.setProductionDate(vehiclelogin.getMdate()+"");/**出厂日期*/
		//order.setCurbWeight(vehiclelogin.);/**整备质量*/
		//order.setTotalMass(vehiclelogin.);/**总质量*/
		//order.setSingleAxleLoad(singleAxleLoad);/**单车轴重*/
		order.setIdentifyCode(vehiclelogin.getCvin());/**车辆识别*/
		order.setDrivingModel(apiDataConfig.getQDFSMap().get(tprefacecheck.getCdriveform()));/**驱动方式*/
		order.setGearboxType(apiDataConfig.getBSXXSMap().get(tprefacecheck.getCgearboxtype()));/**变速箱型式 */
		//order.setGears(tprefacecheck.GET);/**档位数*/
		order.setEngineType(tprefacecheck.getCenginemodel());/**发动机类型*/
		//order.setEngineNumber(tprefacecheck.);/**发动机号*/
		//order.setEngineCylinderNum(vehiclelogin.);/**发动机汽缸数*/
		order.setNletout(tprefacecheck.getNletout()+"");/**发动机排量*/
		order.setFuelFeedType(tprefacecheck.getFuelsupply());/**燃料供给系统型式*/
		//order.setMileage(tprefacecheck.get);/**行驶里程数*/
		order.setFuelType(tprefacecheck.getCfueltype());/**燃料种类*/
		//order.setCatalyticConverter(tprefacecheck);/**催化转化器 */
		order.setInletModel(tprefacecheck.getCadmission());/**进气方式*/
		order.setFirstRegisterDate(tprefacecheck.getDregister()+"");/**初次登记时间*/
		
		String registerTime;
		//if(zhTime==null){
			/**上线开始时间*/
			//registerTime = util.showTime(resultsreport.getSxkssj());
		//}else{
			//registerTime = util.showTime(resultsreport.getSxkssj())+"至"+util.showTime(zhTime);
		//}
		//order.setRegisterDate(registerTime);/**登记时间*/
		order.setEnginePower(tprefacecheck.getNordainpower()+"");/**发动机功率*/
		//order.setVehicleBrand(vehiclelogin.get);/**车辆品牌*/
		order.setRegistrar(vehiclelogin.getRegisterperson());/**登记员*/
		order.setOperationPerson(vehiclelogin.getOperationperson());/**操作员*/
		order.setTruckDriver(vehiclelogin.getCdrivercode());/**引车员*/
		
		/*以下是检验报告单里面的信息*/
		
		return null;
		
	}
}
