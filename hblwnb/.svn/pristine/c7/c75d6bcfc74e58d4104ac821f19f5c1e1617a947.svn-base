/** 
 * Project Name:hblw_nb 
 * File Name:TPREFACECHECKDao.java 
 * Package Name:com.vkl.hblw.dao.persistence 
 * Date:2017年10月13日下午2:26:16 
 * Copyright (c) 2017, chenzhou1025@126.com All Rights Reserved. 
 * 
*/  
  
package com.vkl.hblw.dao.persistence;

import java.sql.Timestamp;

import org.springframework.transaction.annotation.Transactional;

import com.vkl.hblw.common.util.AndroidOut;
import com.vkl.hblw.dao.domain.TPREFACECHECK;
import com.vkl.hblw.dao.persistence.base.TPREFACECHECKBaseDao;
import com.vkl.hblw.dao.util.SqlSelect;
import com.vkl.hblw.dao.util.SqlUpdate;


/** 
* @ClassName: TPREFACECHECKDao 
* @Description:车辆外检信息dao扩展类 
* @author aiyun 
* @date 2017年10月13日 下午2:26:25 
*  
*/
public class TPREFACECHECKDao extends TPREFACECHECKBaseDao {

	
	/** 
	* @Title: queryByCcheckcode 
	* @Description: 根据流水号查询外检信息表
	* @param CCHECKCODE
	* @return    设定文件 
	* @return TPREFACECHECK    返回类型 
	* @throws 
	*/
	public TPREFACECHECK queryByCcheckcode(String CCHECKCODE){
		TPREFACECHECK tprefacecheck = new TPREFACECHECK();
		tprefacecheck.setCcheckcode(CCHECKCODE);
		SqlSelect sql = new SqlSelect().where(TPREFACECHECK.SQL_ccheckcode);
		tprefacecheck = load(sql, tprefacecheck);
		return tprefacecheck;
	}
	/** 
	* @Title: queryByCNUMBERPLATECNUMBERTYPECVIN 
	* @Description: 根据外观检验流水号查询外检信息
	* @param cprefacecheckid
	* @return    设定文件 
	* @return TPREFACECHECK    返回类型 
	* @throws 
	*/
	public TPREFACECHECK queryByCprefacecheckid(String cprefacecheckid) {
		TPREFACECHECK tprefacecheck = new TPREFACECHECK();
		tprefacecheck.setCprefacecheckid(cprefacecheckid);
		SqlSelect sql = new SqlSelect().where(TPREFACECHECK.SQL_cprefacecheckid);
		tprefacecheck = load(sql, tprefacecheck);
		return tprefacecheck;
	}
	
	
	
	/** 
	* @Title: updateTpefacecheck 
	* @Description: 根据条件更新数据 (待测试和改进)
	* @param tprefacecheck
	* @return    设定文件 
	* @return Integer    返回类型 
	* @throws 
	*/
	public Integer updateTpefacecheck(TPREFACECHECK tprefacecheck){
		Integer rsInt = 0;
		//插入结束时间
		try {
		tprefacecheck.setVideoendtime(new Timestamp(System.currentTimeMillis()));
		SqlUpdate sql = new SqlUpdate();
		if(tprefacecheck.getBchkaaae()!=null&&!"".equals(tprefacecheck.getBchkaaae())){
			sql = sql.addColumn(TPREFACECHECK.SQL_bchkaaae);
		}
		if(tprefacecheck.getBchkel()!=null&&!"".equals(tprefacecheck.getBchkel())){
			sql = sql.addColumn(TPREFACECHECK.SQL_bchkel);
		}
		if(tprefacecheck.getBchkislou()!=null&&!"".equals(tprefacecheck.getBchkislou())){
			sql = sql.addColumn(TPREFACECHECK.SQL_bchkislou);
		}
		if(tprefacecheck.getBchkobd()!=null&&!"".equals(tprefacecheck.getBchkobd())){
			sql = sql.addColumn(TPREFACECHECK.SQL_bchkobd);
		}
		if(tprefacecheck.getBchkt()!=null&&!"".equals(tprefacecheck.getBchkt())){
			sql = sql.addColumn(TPREFACECHECK.SQL_bchkt);
		}
		if(tprefacecheck.getBfacecheck()!=null&&!"".equals(tprefacecheck.getBfacecheck())){
			sql = sql.addColumn(TPREFACECHECK.SQL_bfacecheck);
		}
		if(tprefacecheck.getBisavoidcheck()!=null&&!"".equals(tprefacecheck.getBisavoidcheck())){
			sql = sql.addColumn(TPREFACECHECK.SQL_bisavoidcheck);
		}
		if(tprefacecheck.getBisdiankong()!=null&&!"".equals(tprefacecheck.getBisdiankong())){
			sql = sql.addColumn(TPREFACECHECK.SQL_bisdiankong);
		}
		if(tprefacecheck.getBishaveobd()!=null&&!"".equals(tprefacecheck.getBishaveobd())){
			sql = sql.addColumn(TPREFACECHECK.SQL_bishaveobd);
		}
		if(tprefacecheck.getBisobdcomm()!=null&&!"".equals(tprefacecheck.getBisobdcomm())){
			sql = sql.addColumn(TPREFACECHECK.SQL_bisobdcomm);
		}
		if(tprefacecheck.getCaddress()!=null&&!"".equals(tprefacecheck.getCaddress())){
			sql = sql.addColumn(TPREFACECHECK.SQL_caddress);
		}
		if(tprefacecheck.getCadmission()!=null&&!"".equals(tprefacecheck.getCadmission())){
			sql = sql.addColumn(TPREFACECHECK.SQL_cadmission);
		}
		if(tprefacecheck.getCcheckcode()!=null&&!"".equals(tprefacecheck.getCcheckcode())){
			sql = sql.addColumn(TPREFACECHECK.SQL_ccheckcode);
		}
		if(tprefacecheck.getCdriveform()!=null&&!"".equals(tprefacecheck.getCdriveform())){
			sql = sql.addColumn(TPREFACECHECK.SQL_cdriveform);
		}
		if(tprefacecheck.getCenginemodel()!=null&&!"".equals(tprefacecheck.getCenginemodel())){
			sql =sql.addColumn(TPREFACECHECK.SQL_cenginemodel);
		}
		if(tprefacecheck.getCenginemodelno()!=null&&!"".equals(tprefacecheck.getCenginemodelno())){
			sql =sql.addColumn(TPREFACECHECK.SQL_cenginemodelno);
		}
		if(tprefacecheck.getCfueltype()!=null&&!"".equals(tprefacecheck.getCfueltype())){
			sql = sql.addColumn(TPREFACECHECK.SQL_cfueltype);
		}
		if(tprefacecheck.getCgearboxtype()!=null&&!"".equals(tprefacecheck.getCgearboxtype())){
			sql =sql.addColumn(TPREFACECHECK.SQL_cgearboxtype);
			
		}
		if(tprefacecheck.getCnumberplate()!=null&&!"".equals(tprefacecheck.getCnumberplate())){
			sql = sql.addColumn(TPREFACECHECK.SQL_cnumberplate);
		}
		if(tprefacecheck.getCphone()!=null&&!"".equals(tprefacecheck.getCphone())){
			sql = sql.addColumn(TPREFACECHECK.SQL_cphone);
		}
		if(tprefacecheck.getCplatetype()!=null&&!"".equals(tprefacecheck.getCplatetype())){
			sql = sql.addColumn(TPREFACECHECK.SQL_cplatetype);
		}
		if(tprefacecheck.getCprefacecheckid()!=null&&!"".equals(tprefacecheck.getCprefacecheckid())){
			sql = sql.addColumn(TPREFACECHECK.SQL_cprefacecheckid);
		}
		if(tprefacecheck.getCpurposeid()!=null&&!"".equals(tprefacecheck.getCpurposeid())){
			sql = sql.addColumn(TPREFACECHECK.SQL_cpurposeid);
		}
		if(tprefacecheck.getCunitcode()!=null&&!"".equals(tprefacecheck.getCunitcode())){
			sql = sql.addColumn(TPREFACECHECK.SQL_cunitcode);
		}
		if(tprefacecheck.getCuser()!=null&&!"".equals(tprefacecheck.getCuser())){
			sql = sql.addColumn(TPREFACECHECK.SQL_cuser);
		}
		if(tprefacecheck.getCvehiclecode()!=null&&!"".equals(tprefacecheck.getCvehiclecode())){
			sql = sql.addColumn(TPREFACECHECK.SQL_cvehiclecode);;
		}
		if(tprefacecheck.getCvehiclestyle()!=null&&!"".equals(tprefacecheck.getCvehiclestyle())){
			sql = sql.addColumn(TPREFACECHECK.SQL_cvehiclestyle);
		}
		if(tprefacecheck.getCvin()!=null&&!"".equals(tprefacecheck.getCvin())){
			sql = sql.addColumn(TPREFACECHECK.SQL_cvin);
		}
		if(tprefacecheck.getDregister()!=null&&!"".equals(tprefacecheck.getDregister())){
			sql = sql.addColumn(TPREFACECHECK.SQL_dregister);
		}
		if(tprefacecheck.getEgr()!=null&&!"".equals(tprefacecheck.getEgr())){
			sql = sql.addColumn(TPREFACECHECK.SQL_egr);
		}
		if(tprefacecheck.getFuelsupply()!=null&&!"".equals(tprefacecheck.getFuelsupply())){
			sql = sql.addColumn(TPREFACECHECK.SQL_fuelsupply);
		}
		if(tprefacecheck.getHcl()!=null&&!"".equals(tprefacecheck.getHcl())){
			sql = sql.addColumn(TPREFACECHECK.SQL_hcl);
		}
		if(tprefacecheck.getHcltype()!=null&&!"".equals(tprefacecheck.getHcltype())){
			sql = sql.addColumn(TPREFACECHECK.SQL_hcltype);
		}
		if(tprefacecheck.getNcylinder()>=0&&!"".equals(tprefacecheck.getNcylinder())){
			sql = sql.addColumn(TPREFACECHECK.SQL_ncylinder);
		}
		if(tprefacecheck.getNletout()!=null&&!"".equals(tprefacecheck.getNletout())){
			sql = sql.addColumn(TPREFACECHECK.SQL_nletout);
		}
		if(tprefacecheck.getNmaxweight()>=0&&!"".equals(tprefacecheck.getNmaxweight())){
			sql = sql.addColumn(TPREFACECHECK.SQL_nmaxweight);
		}
		if(tprefacecheck.getNodometer()>=0&&!"".equals(tprefacecheck.getNodometer())){
			sql = sql.addColumn(TPREFACECHECK.SQL_nodometer);
		}
		if(tprefacecheck.getNordainpower()>=0&&!"".equals(tprefacecheck.getNordainpower())){
			sql = sql.addColumn(TPREFACECHECK.SQL_nordainpower);
		}
		if(tprefacecheck.getNordainrev()>=0&&!"".equals(tprefacecheck.getNordainrev())){
			sql = sql.addColumn(TPREFACECHECK.SQL_nordainrev);
		}
		if(tprefacecheck.getNstandardid()!=null&&!"".equals(tprefacecheck.getNstandardid())){
			sql = sql.addColumn(TPREFACECHECK.SQL_nstandardid);
		}
		if(tprefacecheck.getNstdweight()>=0&&!"".equals(tprefacecheck.getNstdweight())){
			sql = sql.addColumn(TPREFACECHECK.SQL_nstdweight);
		}
		if(tprefacecheck.getNvehiclemodelid()!=null&&!"".equals(tprefacecheck.getNvehiclemodelid())){
			sql = sql.addColumn(TPREFACECHECK.SQL_nvehiclemodelid);
		}
		if(tprefacecheck.getTg()!=null&&!"".equals(tprefacecheck.getTg())){
			sql = sql.addColumn(TPREFACECHECK.SQL_tg);
		}
		if(tprefacecheck.getVideostarttime()!=null&&!"".equals(tprefacecheck.getVideostarttime())){
			sql = sql.addColumn(TPREFACECHECK.SQL_videostarttime);
		}
		if(tprefacecheck.getVideoendtime()!=null&&!"".equals(tprefacecheck.getVideoendtime())){
			sql = sql.addColumn(TPREFACECHECK.SQL_videoendtime);
		}
		sql = sql.where(TPREFACECHECK.SQL_id).and(TPREFACECHECK.SQL_ccheckcode).and(TPREFACECHECK.SQL_cprefacecheckid);
		AndroidOut.aiyunprintln("sql:-----"+sql+"-----------");
		update(sql, tprefacecheck);
		} catch (Exception e) {
			// TODO: handle exception
			return 1;
		}
		return rsInt;
		
	}
}
  