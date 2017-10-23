/** 
 * Project Name:hblw_nb 
 * File Name:UPLOADSTATUSDao.java 
 * Package Name:com.vkl.hblw.dao.persistence 
 * Date:2017年10月13日下午2:50:23 
 * Copyright (c) 2017, chenzhou1025@126.com All Rights Reserved. 
 * 
*/  
  
package com.vkl.hblw.dao.persistence;

import com.vkl.hblw.common.util.AndroidOut;
import com.vkl.hblw.dao.domain.UPLOADSTATUS;
import com.vkl.hblw.dao.persistence.base.UPLOADSTATUSBaseDao;
import com.vkl.hblw.dao.util.SqlUpdate;


/** 
* @ClassName: UPLOADSTATUSDao 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author aiyun 
* @date 2017年10月13日 下午2:50:27 
*  
*/
public class UPLOADSTATUSDao extends UPLOADSTATUSBaseDao {

	
	
	/** 
	* @Title: updateUploadStatus 
	* @Description: 更新对应数据库的数据
	* @param uploadstatus
	* @return    设定文件 
	* @return Integer    返回类型 
	* @throws 
	*/
	public Integer updateUploadStatus(UPLOADSTATUS uploadstatus){
		Integer updateRs = 0;
		
		
		
		try {
		SqlUpdate sql = new SqlUpdate();
		if(null != uploadstatus.getCarpicture01()&&!"".equals(uploadstatus.getCarpicture01())){
			sql = sql.addColumn(UPLOADSTATUS.SQL_carpicture01);
		}
		if(null != uploadstatus.getCarpicture02()&&!"".equals(uploadstatus.getCarpicture02())){
			sql = sql.addColumn(UPLOADSTATUS.SQL_carpicture02);
		}
		if(null != uploadstatus.getCarpicture03()&&!"".equals(uploadstatus.getCarpicture03())){
			sql = sql.addColumn(UPLOADSTATUS.SQL_carpicture03);
		}
		if(null != uploadstatus.getCarpicture04()&&!"".equals(uploadstatus.getCarpicture04())){
			sql = sql.addColumn(UPLOADSTATUS.SQL_carpicture04);
		}
		if(null != uploadstatus.getCarpicture05()&&!"".equals(uploadstatus.getCarpicture05())){
			sql = sql.addColumn(UPLOADSTATUS.SQL_carpicture05);
		}
		if(null != uploadstatus.getCarpicture06()&&!"".equals(uploadstatus.getCarpicture06())){
			sql = sql.addColumn(UPLOADSTATUS.SQL_carpicture06);
		}
		if(null != uploadstatus.getCarpicture07()&&!"".equals(uploadstatus.getCarpicture07())){
			sql = sql.addColumn(UPLOADSTATUS.SQL_carpicture07);
		}
		if(null != uploadstatus.getCarpicture08()&&!"".equals(uploadstatus.getCarpicture08())){
			sql = sql.addColumn(UPLOADSTATUS.SQL_carpicture08);
		}
		
		if(null != uploadstatus.getCarpicture09()&&!"".equals(uploadstatus.getCarpicture09())){
			sql = sql.addColumn(UPLOADSTATUS.SQL_carpicture09);
		}
		if(null != uploadstatus.getCarpicture10()&&!"".equals(uploadstatus.getCarpicture10())){
			sql = sql.addColumn(UPLOADSTATUS.SQL_carpicture10);
		}
		if(null != uploadstatus.getCarpicture11()&&!"".equals(uploadstatus.getCarpicture11())){
			sql = sql.addColumn(UPLOADSTATUS.SQL_carpicture11);
		}
		if(null != uploadstatus.getCarpicture12()&&!"".equals(uploadstatus.getCarpicture12())){
			sql = sql.addColumn(UPLOADSTATUS.SQL_carpicture12);
		}
		if(null != uploadstatus.getCarpicture13()&&!"".equals(uploadstatus.getCarpicture13())){
			sql = sql.addColumn(UPLOADSTATUS.SQL_carpicture13);
		}
		if(null != uploadstatus.getCarpicture14()&&!"".equals(uploadstatus.getCarpicture14())){
			sql = sql.addColumn(UPLOADSTATUS.SQL_carpicture14);
		}
		
		if(null != uploadstatus.getCarvideo01()&&!"".equals(uploadstatus.getCarvideo01())){
			sql = sql.addColumn(UPLOADSTATUS.SQL_carvideo01);
		}
		if(null != uploadstatus.getCarvideo02()&&!"".equals(uploadstatus.getCarvideo02())){
			sql = sql.addColumn(UPLOADSTATUS.SQL_carvideo02);
		}
		if(null != uploadstatus.getCarvideo03()&&!"".equals(uploadstatus.getCarvideo03())){
			sql = sql.addColumn(UPLOADSTATUS.SQL_carvideo03);
		}
		if(null != uploadstatus.getCarvideo04()&&!"".equals(uploadstatus.getCarvideo04())){
			sql = sql.addColumn(UPLOADSTATUS.SQL_carvideo04);
		}
		if(null != uploadstatus.getCarvideo05()&&!"".equals(uploadstatus.getCarvideo05())){
			sql = sql.addColumn(UPLOADSTATUS.SQL_carvideo05);
		}
		if(null != uploadstatus.getCarvideo06()&&!"".equals(uploadstatus.getCarvideo06())){
			sql = sql.addColumn(UPLOADSTATUS.SQL_carvideo06);
		}
		if(null != uploadstatus.getCarvideo07()&&!"".equals(uploadstatus.getCarvideo07())){
			sql = sql.addColumn(UPLOADSTATUS.SQL_carvideo07);
		}
		if(null != uploadstatus.getCarvideo08()&&!"".equals(uploadstatus.getCarvideo08())){
			sql = sql.addColumn(UPLOADSTATUS.SQL_carvideo08);
		}
		if(null != uploadstatus.getCarvideo09()&&!"".equals(uploadstatus.getCarvideo09())){
			sql = sql.addColumn(UPLOADSTATUS.SQL_carvideo09);
		}
		if(null != uploadstatus.getCcheckcode()&&!"".equals(uploadstatus.getCcheckcode())){
			sql = sql.addColumn(UPLOADSTATUS.SQL_ccheckcode);
		}
		if(null != uploadstatus.getChecksubmit()&&!"".equals(uploadstatus.getChecksubmit())){
			sql = sql.addColumn(UPLOADSTATUS.SQL_checksubmit);
		}
		if(null != uploadstatus.getCnumberplate()&&!"".equals(uploadstatus.getCnumberplate())){
			sql = sql.addColumn(UPLOADSTATUS.SQL_cnumberplate);
		}
		if(null != uploadstatus.getCnumbertype()&&!"".equals(uploadstatus.getCnumbertype())){
			sql = sql.addColumn(UPLOADSTATUS.SQL_cnumbertype);
		}
		if(null != uploadstatus.getCvehiclecode()&&!"".equals(uploadstatus.getCvehiclecode())){
			sql = sql.addColumn(UPLOADSTATUS.SQL_cvehiclecode);
		}
		if(null != uploadstatus.getOnlineresultstatus()&&!"".equals(uploadstatus.getOnlineresultstatus())){
			sql = sql.addColumn(UPLOADSTATUS.SQL_onlineresultstatus);
		}
		if(null != uploadstatus.getOnlinestatus()&&!"".equals(uploadstatus.getOnlinestatus())){
			sql = sql.addColumn(UPLOADSTATUS.SQL_onlinestatus);
		}
		if(null != uploadstatus.getTprefacecheckstatus()&&!"".equals(uploadstatus.getTprefacecheckstatus())){
			sql = sql.addColumn(UPLOADSTATUS.SQL_tprefacecheckstatus);
		}
		if(null != uploadstatus.getTvehiclestatus()&&!"".equals(uploadstatus.getTvehiclestatus())){
			sql = sql.addColumn(UPLOADSTATUS.SQL_tvehiclestatus);
		}
		sql = sql.where(UPLOADSTATUS.SQL_id).and(UPLOADSTATUS.SQL_ccheckcode);
		
		AndroidOut.aiyunprintln("------------"+sql+"-------");
		update(sql, uploadstatus);
		
		} catch (Exception e) {
			// TODO: handle exception
			return 1;
		}
		return updateRs;
		
	}
	
}
  