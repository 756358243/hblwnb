/** 
 * Project Name:hblw_nb 
 * File Name:RESULTSREPORTDao.java 
 * Package Name:com.vkl.hblw.dao.persistence 
 * Date:2017年10月13日下午2:48:05 
 * Copyright (c) 2017, chenzhou1025@126.com All Rights Reserved. 
 * 
*/  
  
package com.vkl.hblw.dao.persistence;

import java.sql.Timestamp;

import org.springframework.transaction.annotation.Transactional;

import com.vkl.hblw.dao.domain.RESULTSREPORT;
import com.vkl.hblw.dao.domain.TPREFACECHECK;
import com.vkl.hblw.dao.persistence.base.RESULTSREPORTBaseDao;
import com.vkl.hblw.dao.util.SqlSelect;

/** 
 * ClassName:RESULTSREPORTDao <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason:   TODO ADD REASON. <br/> 
 * Date:     2017年10月13日 下午2:48:05 <br/> 
 * @author   aiyun 
 * @version   
 * @since    JDK 1.6 
 * @see       
 */
public class RESULTSREPORTDao extends RESULTSREPORTBaseDao {

	/**
	 * 
	* @Title: queryByCcheckcode 
	* @Description: 获取登记车辆信息
	* @author xy
	* @param ccheckcode 流水号
	* @return
	 */
	@Transactional
	public RESULTSREPORT queryByCcheckcode(String ccheckcode) {
		RESULTSREPORT resultsreport=new RESULTSREPORT();
		//resultsreport.setC
		return resultsreport;
		}
}
  