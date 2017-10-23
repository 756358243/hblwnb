/** 
 * Project Name:hblw_nb 
 * File Name:TGASVMASDATADao.java 
 * Package Name:com.vkl.hblw.dao.persistence 
 * Date:2017年10月13日下午2:27:26 
 * Copyright (c) 2017, chenzhou1025@126.com All Rights Reserved. 
 * 
*/  
  
package com.vkl.hblw.dao.persistence;

import java.util.List;

import com.vkl.hblw.dao.domain.TGASVMASDATA;
import com.vkl.hblw.dao.persistence.base.TGASVMASDATABaseDao;
import com.vkl.hblw.dao.util.SqlSelect;

/** 
 * ClassName:TGASVMASDATADao <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason:   TODO ADD REASON. <br/> 
 * Date:     2017年10月13日 下午2:27:26 <br/> 
 * @author   aiyun 
 * @version   
 * @since    JDK 1.6 
 * @see       
 */
public class TGASVMASDATADao extends TGASVMASDATABaseDao {

	public List<TGASVMASDATA> listByCcheckcode(String ccheckcode) {
		TGASVMASDATA tgasvmasdata = new TGASVMASDATA();
		tgasvmasdata.setCcheckcode(ccheckcode);
		SqlSelect sql = new SqlSelect().where(TGASVMASDATA.SQL_ccheckcode);
		List<TGASVMASDATA> tgasvmasdataList = list(sql, tgasvmasdata);
		return tgasvmasdataList;
	}

}
  