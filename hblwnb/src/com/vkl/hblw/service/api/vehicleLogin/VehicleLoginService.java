/**
 * 
 */
package com.vkl.hblw.service.api.vehicleLogin;



import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vkl.hblw.common.constant.CustomData;
import com.vkl.hblw.common.constant.SystemInfo;
import com.vkl.hblw.dao.domain.TPREFACECHECK;
import com.vkl.hblw.dao.domain.VEHICLELOGIN;
import com.vkl.hblw.dao.persistence.TPREFACECHECKDao;
import com.vkl.hblw.dao.persistence.VEHICLELOGINDao;
import com.vkl.hblw.dao.util.SqlUpdate;


/**
 * @author xcc
 *
 */
@Service
public class VehicleLoginService {

	private static final Logger log = Logger.getLogger(VehicleLoginService.class);
	@Autowired
	private VEHICLELOGINDao vehicleLoginDao;//检验登录信息
	@Autowired
	private TPREFACECHECKDao tprefaceCheckDao;//检验外检信息
	
	
	
	/** 
	* @Title: modifyCheckMethod 
	* @Description: 修改检测方法
	* @param ccheckcode
	* @param checkmethod
	* @return    设定文件 
	* @return boolean    返回类型 
	* @throws 
	*/
	public boolean modifyCheckMethod(String ccheckcode, String checkmethod) {
		try {
			VEHICLELOGIN vehiclelogin = vehicleLoginDao.queryByCcheckcode(ccheckcode);
			if (null == vehiclelogin) {
				return false;
			}
			vehiclelogin.setExt_col10(checkmethod);
			vehicleLoginDao.update(new SqlUpdate().addColumn(VEHICLELOGIN.SQL_ext_col10).where(VEHICLELOGIN.SQL_id), vehiclelogin);
		} catch (Exception e) {
			log.error(SystemInfo.LOG_CODE_ERROR, e);
			return false;
		}
		return true;
	}
	
	/** 
	* @Title: modifyCdriveform 
	* @Description: 修改驱动方式 
	* @param cprefacecheckid
	* @param cdriveform
	* @return    设定文件 
	* @return boolean    返回类型 
	* @throws 
	*/
	public boolean modifyCdriveform(String cprefacecheckid, String cdriveform) {
		try {
			TPREFACECHECK tprefacecheck = tprefaceCheckDao.queryByCprefacecheckid(cprefacecheckid);
			if (null == tprefacecheck) {
				return false;
			}
			tprefacecheck.setCdriveform(cdriveform);
			tprefaceCheckDao.update(new SqlUpdate().addColumn(TPREFACECHECK.SQL_cdriveform).where(TPREFACECHECK.SQL_id), tprefacecheck);
		} catch (Exception e) {
			log.error(SystemInfo.LOG_CODE_ERROR, e);
			return false;
		}
		return true;
	}

	/** 
	* @Title: uploadChragedVehicle 
	* @Description: 提交已收费车辆信息
	* @param cnumberplate 号牌号码
	* @param cnumbertype 号牌种类
	* @param cvin 车辆识别号
	* @return    设定文件 
	* @return boolean    返回类型 
	* @throws 
	*/
	public boolean uploadChragedVehicle(String ccheckcode) {
		try {
			VEHICLELOGIN vehiclelogin =vehicleLoginDao.queryByCcheckcode(ccheckcode);
			if (null==vehiclelogin) {
				return false;
			}
			vehiclelogin.setCharge(CustomData.DLXX_CHARGE_PAYED);
			vehicleLoginDao.update(new SqlUpdate().addColumn(VEHICLELOGIN.SQL_charge).where(VEHICLELOGIN.SQL_id),vehiclelogin);
		} catch (Exception e) {
			log.error(SystemInfo.LOG_CODE_ERROR, e);
			return false;
		}
		return true;
	}


	/** 
	* @Title: modifyCheckFuelType 
	* @Description: 修改车辆检验燃料种类
	* @param ccheckcode 检验流水号
	* @param checkfueltype  检测燃料种类
	* @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	public String modifyCheckFuelType(String ccheckcode, String checkfueltype) {
		try {
			VEHICLELOGIN vehiclelogin = vehicleLoginDao.queryByCcheckcode(ccheckcode);
			if (null == vehiclelogin) {
				return SystemInfo.API_MODIFYJYRLZL_UNFOUND;
			}
			// 只要第一种燃料没有检测结束、提交、退办就可以修改
			if (CustomData.DLXX_STATUS_RESULT.equals(vehiclelogin.getCheckstate())
					|| CustomData.DLXX_STATUS_SUBMIT.equals(vehiclelogin.getCheckstate())
					|| CustomData.DLXX_STATUS_RETIREMENT.equals(vehiclelogin.getCheckstate())) {
				return SystemInfo.API_MODIFYJYRLZL_INCONFORMITY_STATUS;
			}
			vehiclelogin.setCheckfueltype(checkfueltype);
			vehicleLoginDao.update(new SqlUpdate().addColumn(VEHICLELOGIN.SQL_checkfueltype).where(VEHICLELOGIN.SQL_id), vehiclelogin);
		} catch (Exception e) {
			log.error(SystemInfo.LOG_CODE_ERROR, e);
			return SystemInfo.API_CODE_EXCEPTION;
		}
		return SystemInfo.API_MODIFYJYRLZL_SUCCESS;
	}

	/** 
	* @Title: retirement 
	* @Description: 登录车辆退办 
	* @param ccheckcode
	* @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	public String retirement(String ccheckcode) {
		try {
			VEHICLELOGIN vehiclelogin = vehicleLoginDao.queryByCcheckcode(ccheckcode);
			if (null == vehiclelogin) {
				return SystemInfo.API_RETIREMENT_UNFOUND;
			}
			if (CustomData.DLXX_STATUS_SUBMIT.equals(vehiclelogin.getCheckstate())) {
				return SystemInfo.API_RETIREMENT_SUBMITED;
			}
			// 判断是否为双燃料车
//			String[] checkfueltype = vehiclelogin.getCheckfueltype().split(",");
//			if (checkfueltype != null && checkfueltype.length >= 2) {
//				
//				VEHICLELOGIN vehiclelogin_second = vehicleLoginDao.queryByCnumberplateCnumbertypeCvin(cnumberplate, cnumbertype, cvin);
//				if (null == dlxx_second) {
//					return SystemInfo.API_RETIREMENT_SECOND_UNFOUND;
//				}
//				if (CustomData.DLXX_STATUS_SUBMIT.equals(dlxx_second.getStatus())) {
//					return SystemInfo.API_RETIREMENT_SECOND_SUBMITED;
//				}
//				dlxx_second.setStatus(CustomData.DLXX_STATUS_RETIREMENT);
//				tDLXXDao.update(new SqlUpdate().addColumn(TDLXX.SQL_status).where(TDLXX.SQL_id), dlxx_second);
//			}
			vehiclelogin.setCheckstate(CustomData.DLXX_STATUS_RETIREMENT);
			vehicleLoginDao.update(new SqlUpdate().addColumn(VEHICLELOGIN.SQL_checkstate).where(VEHICLELOGIN.SQL_id), vehiclelogin);
		} catch (Exception e) {
			log.error(SystemInfo.LOG_CODE_ERROR, e);
			return SystemInfo.API_CODE_EXCEPTION_RETIREMENT;
		}
		return SystemInfo.API_RETIREMENT_SUCCESS;
	}


	
}
