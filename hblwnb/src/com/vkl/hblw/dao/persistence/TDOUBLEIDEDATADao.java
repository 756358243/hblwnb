/** 
 * Project Name:hblw_nb 
 * File Name:TDOUBLEIDEDATADao.java 
 * Package Name:com.vkl.hblw.dao.persistence 
 * Date:2017年10月13日下午2:28:17 
 * Copyright (c) 2017, chenzhou1025@126.com All Rights Reserved. 
 * 
*/  
  
package com.vkl.hblw.dao.persistence;

import java.util.List;

import com.vkl.hblw.dao.domain.TDOUBLEIDEDATA;
import com.vkl.hblw.dao.persistence.base.TDOUBLEIDEDATABaseDao;
import com.vkl.hblw.dao.util.SqlSelect;

/** 
 * ClassName:TDOUBLEIDEDATADao <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason:   TODO ADD REASON. <br/> 
 * Date:     2017年10月13日 下午2:28:17 <br/> 
 * @author   aiyun 
 * @version   
 * @since    JDK 1.6 
 * @see       
 */
public class TDOUBLEIDEDATADao extends TDOUBLEIDEDATABaseDao {

	public List<TDOUBLEIDEDATA> listByCcheckcode(String ccheckcode) {
		TDOUBLEIDEDATA tdoubleidedata = new TDOUBLEIDEDATA();
		tdoubleidedata.setCcheckcode(ccheckcode);
		SqlSelect sql = new SqlSelect().where(TDOUBLEIDEDATA.SQL_ccheckcode);
		List<TDOUBLEIDEDATA> tDoubleidedataList = list(sql, tdoubleidedata);
		return tDoubleidedataList;
	}

}
  