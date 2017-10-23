/** 
 * Project Name:hblw_nb 
 * File Name:TFREESPEEDDATADao.java 
 * Package Name:com.vkl.hblw.dao.persistence 
 * Date:2017年10月13日下午2:30:09 
 * Copyright (c) 2017, chenzhou1025@126.com All Rights Reserved. 
 * 
*/  
  
package com.vkl.hblw.dao.persistence;

import java.util.List;

import com.vkl.hblw.dao.domain.TFREESPEEDDATA;
import com.vkl.hblw.dao.domain.TGASVMASDATA;
import com.vkl.hblw.dao.persistence.base.TFREESPEEDDATABaseDao;
import com.vkl.hblw.dao.util.SqlSelect;


/** 
* @ClassName: TFREESPEEDDATADao 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author aiyun 
* @date 2017年10月13日 下午2:30:49 
*  
*/
public class TFREESPEEDDATADao extends TFREESPEEDDATABaseDao {

	public List<TFREESPEEDDATA> listByCcheckcode(String ccheckcode) {
		TFREESPEEDDATA tfreespeeddata = new TFREESPEEDDATA();
		tfreespeeddata.setCcheckcode(ccheckcode);
		SqlSelect sql = new SqlSelect().where(TFREESPEEDDATA.SQL_ccheckcode);
		List<TFREESPEEDDATA> tfreespeeddataList = list(sql, tfreespeeddata);
		return tfreespeeddataList;
	}

}
  