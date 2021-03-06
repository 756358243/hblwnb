/** 
 * Project Name:hblw_nb 
 * File Name:VEHICLELOGINDao.java 
 * Package Name:com.vkl.hblw.dao.persistence 
 * Date:2017年10月13日下午2:47:45 
 * Copyright (c) 2017, chenzhou1025@126.com All Rights Reserved. 
 * 
*/  
  
package com.vkl.hblw.dao.persistence;


import java.sql.Timestamp;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.transaction.annotation.Transactional;

import com.vkl.hblw.common.util.AndroidOut;
import com.vkl.hblw.dao.domain.TPREFACECHECK;
import com.vkl.hblw.dao.domain.VEHICLELOGIN;
import com.vkl.hblw.dao.persistence.base.VEHICLELOGINBaseDao;
import com.vkl.hblw.dao.util.SqlSelect;
import com.vkl.hblw.dao.util.SqlUpdate;

/** 
 * ClassName:VEHICLELOGINDao <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason:   TODO ADD REASON. <br/> 
 * Date:     2017年10月13日 下午2:47:45 <br/> 
 * @author   xwh 
 * @version   
 * @since    JDK 1.8
 * @see       
 */
public class VEHICLELOGINDao extends VEHICLELOGINBaseDao {

	/** 
	* @Title: queryByCnumberplateCnumbertypeCvin 
	* @Description: 查询登录车辆状态信息 
	* @param cnumberplate
	* @param cnumbertype
	* @param cvin
	* @return    设定文件 
	* @return VEHICLELOGIN    返回类型 
	* @throws 
	*/
	public VEHICLELOGIN queryByCnumberplateCnumbertypeCvin(String cnumberplate, String cnumbertype, String cvin) {
		VEHICLELOGIN vehicleLogin = new VEHICLELOGIN();
		vehicleLogin.setCnumberplate(cnumberplate);
		vehicleLogin.setCnumbertype(cnumbertype);
		vehicleLogin.setCvin(cvin);
		SqlSelect sql = new SqlSelect().where(VEHICLELOGIN.SQL_cnumberplate).and(VEHICLELOGIN.SQL_cnumbertype).and(VEHICLELOGIN.SQL_cvin);
		vehicleLogin=load(sql, vehicleLogin);
		return vehicleLogin;
	}
	/** 
	* @Title: queryByCcheckcode 
	* @Description: 根据流水号查询登录车辆记录 
	* @param CCHECKCODE
	* @return    设定文件 
	* @return VEHICLELOGIN    返回类型 
	* @throws 
	*/
	public VEHICLELOGIN queryByCcheckcode(String ccheckcode){
		VEHICLELOGIN vehiclelogin = new VEHICLELOGIN();
		vehiclelogin.setCcheckcode(ccheckcode);
		SqlSelect querySql = new SqlSelect().where(VEHICLELOGIN.SQL_ccheckcode);
		vehiclelogin = load(querySql, vehiclelogin);
		return vehiclelogin;
	}
	
	public Integer updateVehiclelogin(VEHICLELOGIN vehiclelogin){
		Integer rsInt = 0;
		try {
		
		SqlUpdate sql = new SqlUpdate();
		if(vehiclelogin.getAuditpasstime()!=null&&!"".equals(vehiclelogin.getAuditpasstime())){
			sql = sql.addColumn(VEHICLELOGIN.SQL_auditpasstime);
		}
		if(vehiclelogin.getCannulaperson()!=null&&!"".equals(vehiclelogin.getCannulaperson())){
			sql = sql.addColumn(VEHICLELOGIN.SQL_cannulaperson);
		}
		if(vehiclelogin.getCarfueltype()!=null&&!"".equals(vehiclelogin.getCarfueltype())){
			sql = sql.addColumn(VEHICLELOGIN.SQL_carfueltype);
		}
		if(null != vehiclelogin.getCarstate()&&!"".equals(vehiclelogin.getCarstate())){
			sql = sql.addColumn(VEHICLELOGIN.SQL_carstate);
		}
		if(null!=vehiclelogin.getCartel()&&!"".equals(vehiclelogin.getCartel())){
			sql = sql.addColumn(VEHICLELOGIN.SQL_cartel);
		}
		if(null != vehiclelogin.getCcheckcode()&&!"".equals(vehiclelogin.getCcheckcode())){
			sql = sql.addColumn(VEHICLELOGIN.SQL_ccheckcode);
		}
		if(null != vehiclelogin.getCchkdevicecode()&&!"".equals(vehiclelogin.getCchkdevicecode())){
			sql = sql.addColumn(VEHICLELOGIN.SQL_cchkdevicecode);
		}
		if(null != vehiclelogin.getCdriveform()&&!"".equals(vehiclelogin.getCdriveform())){
			sql = sql.addColumn(VEHICLELOGIN.SQL_cdriveform);
		}
		if(null != vehiclelogin.getCdrivercode()&&!"".equals(vehiclelogin.getCdrivercode())){
			sql = sql.addColumn(VEHICLELOGIN.SQL_cdrivercode);
		}
		if(null != vehiclelogin.getCengineno()&&!"".equals(vehiclelogin.getCengineno())){
			sql = sql.addColumn(VEHICLELOGIN.SQL_cengineno);
		}
		if(null != vehiclelogin.getCfueltype()&&!"".equals(vehiclelogin.getCfueltype())){
			sql = sql.addColumn(VEHICLELOGIN.SQL_cfueltype);
		}
		if(null != vehiclelogin.getCharge()&&!"".equals(vehiclelogin.getCharge())){
			sql = sql.addColumn(VEHICLELOGIN.SQL_charge);
		}
		if(null != vehiclelogin.getCheckfueltype()&&!"".equals(vehiclelogin.getCheckfueltype())){
			sql = sql.addColumn(VEHICLELOGIN.SQL_checkfueltype);
		}
		if(null != vehiclelogin.getChecklink()&&!"".equals(vehiclelogin.getChecklink())){
			sql = sql.addColumn(VEHICLELOGIN.SQL_checklink);
		}
		if(null != vehiclelogin.getCheckresult()&&!"".equals(vehiclelogin.getCheckresult())){
			sql = sql.addColumn(VEHICLELOGIN.SQL_checkresult);
		}
		if(null != vehiclelogin.getCheckstate()&&!"".equals(vehiclelogin.getCheckstate())){
			sql = sql.addColumn(VEHICLELOGIN.SQL_checkstate);
		}
		if(null != vehiclelogin.getChecktype()&&!"".equals(vehiclelogin.getChecktype())){
			sql = sql.addColumn(VEHICLELOGIN.SQL_checktype);
		}
		if(null != vehiclelogin.getCnumberplate()&&!"".equals(vehiclelogin.getCnumberplate())){
			sql = sql.addColumn(VEHICLELOGIN.SQL_cnumberplate);
		}
		if(null != vehiclelogin.getCnumbertype()&&!"".equals(vehiclelogin.getCnumbertype())){
			sql = sql.addColumn(VEHICLELOGIN.SQL_cnumbertype);
		}
		if(null != vehiclelogin.getCplatetype()&&!"".equals(vehiclelogin.getCplatetype())){
			sql = sql.addColumn(VEHICLELOGIN.SQL_cplatetype);
		}
		if(null != vehiclelogin.getCpurposeid()&&!"".equals(vehiclelogin.getCpurposeid())){
			sql = sql.addColumn(VEHICLELOGIN.SQL_cpurposeid);
		}
		if(null != vehiclelogin.getCunitname()&&!"".equals(vehiclelogin.getCunitname())){
			sql = sql.addColumn(VEHICLELOGIN.SQL_cunitname);
		}
		if(null != vehiclelogin.getCvehiclecode()&&!"".equals(vehiclelogin.getCvehiclecode())){
			sql = sql.addColumn(VEHICLELOGIN.SQL_cvehiclecode);
		}
		if(null != vehiclelogin.getCvehiclestyle()&&!"".equals(vehiclelogin.getCvehiclestyle())){
			sql = sql.addColumn(VEHICLELOGIN.SQL_cvehiclestyle);
		}
		if(null != vehiclelogin.getCvin()&&!"".equals(vehiclelogin.getCvin())){
			sql = sql.addColumn(VEHICLELOGIN.SQL_cvin);
		}
		if(null != vehiclelogin.getExt_col10()&&!"".equals(vehiclelogin.getExt_col10())){
			sql = sql.addColumn(VEHICLELOGIN.SQL_ext_col10);
		}
		if(null != vehiclelogin.getExternalperson()&&!"".equals(vehiclelogin.getExternalperson())){
			sql = sql.addColumn(VEHICLELOGIN.SQL_externalperson);
		}
		if(null != vehiclelogin.getFirstchecknumber()&&!"".equals(vehiclelogin.getFirstchecknumber())){
			sql = sql.addColumn(VEHICLELOGIN.SQL_firstchecknumber);
		}
		if(null != vehiclelogin.getFuelsupply()&&!"".equals(vehiclelogin.getFuelsupply())){
			sql = sql.addColumn(VEHICLELOGIN.SQL_fuelsupply);
		}
		if(null != vehiclelogin.getFuelway()&&!"".equals(vehiclelogin.getFuelway())){
			sql = sql.addColumn(VEHICLELOGIN.SQL_fuelway);
		}
		if(null != vehiclelogin.getGivecheck()&&!"".equals(vehiclelogin.getGivecheck())){
			sql = sql.addColumn(VEHICLELOGIN.SQL_givecheck);
		}
		if(null != vehiclelogin.getGivecheckremarks()&&!"".equals(vehiclelogin.getGivecheckremarks())){
			sql = sql.addColumn(VEHICLELOGIN.SQL_givecheckremarks);
		}
		if(null != vehiclelogin.getInstitution()&&!"".equals(vehiclelogin.getInstitution())){
			sql = sql.addColumn(VEHICLELOGIN.SQL_institution);
		}
		if(null != vehiclelogin.getMdate()&&!"".equals(vehiclelogin.getMdate())){
			sql = sql.addColumn(VEHICLELOGIN.SQL_mdate);
		}
		if(null != vehiclelogin.getOperationperson()&&!"".equals(vehiclelogin.getOperationperson())){
			sql = sql.addColumn(VEHICLELOGIN.SQL_operationperson);
		}
		if(null != vehiclelogin.getOtherremarks()&&!"".equals(vehiclelogin.getOtherremarks())){
			sql = sql.addColumn(VEHICLELOGIN.SQL_otherremarks);
		}
		if(null != vehiclelogin.getRegisterperson()&&!"".equals(vehiclelogin.getRegisterperson())){
			sql = sql.addColumn(VEHICLELOGIN.SQL_registerperson);
		}
		if(null != vehiclelogin.getRegisterremarks()&&!"".equals(vehiclelogin.getRegisterremarks())){
			sql = sql.addColumn(VEHICLELOGIN.SQL_registerremarks);
		}
		if(null != vehiclelogin.getRegistertime()&&!"".equals(vehiclelogin.getRegistertime())){
			sql = sql.addColumn(VEHICLELOGIN.SQL_registertime);
		}
		if(null != vehiclelogin.getSubmitstate()&&!"".equals(vehiclelogin.getSubmitstate())){
			sql = sql.addColumn(VEHICLELOGIN.SQL_submitstate);
		}
		if(null != vehiclelogin.getWyhwithdraw()&&!"".equals(vehiclelogin.getWyhwithdraw())){
			sql = sql.addColumn(VEHICLELOGIN.SQL_wyhwithdraw);
		}
		sql = sql.where(VEHICLELOGIN.SQL_id).and(TPREFACECHECK.SQL_ccheckcode).and(TPREFACECHECK.SQL_cprefacecheckid);
		AndroidOut.aiyunprintln("sql:-----"+sql+"-----------");
		update(sql, vehiclelogin);
		
		} catch (Exception e) {
			// TODO: handle exception
			return 1;
		}
		return rsInt;
		
		
	}
	

}
  