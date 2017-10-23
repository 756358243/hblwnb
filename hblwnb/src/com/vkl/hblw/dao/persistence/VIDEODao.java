/** 
 * Project Name:hblw_nb 
 * File Name:VIDEODao.java 
 * Package Name:com.vkl.hblw.dao.persistence 
 * Date:2017年10月13日下午2:52:18 
 * Copyright (c) 2017, chenzhou1025@126.com All Rights Reserved. 
 * 
*/  
  
package com.vkl.hblw.dao.persistence;

import com.vkl.hblw.dao.domain.VIDEO;
import com.vkl.hblw.dao.persistence.base.VIDEOBaseDao;
import com.vkl.hblw.dao.util.SqlSelect;


/** 
* @ClassName: VIDEODao 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author aiyun 
* @date 2017年10月13日 下午2:52:22 
*  
*/
public class VIDEODao extends VIDEOBaseDao {

	
	
	/** 
	* @Title: queryByCcheckcode 
	* @Description: 根据流水号查记录
	* @param CCHECKCODE
	* @return    设定文件 
	* @return VIDEO    返回类型 
	* @throws 
	*/
	public VIDEO queryByCcheckcode(String CCHECKCODE){
		VIDEO  video = new VIDEO();
		video.setCcheckcode(CCHECKCODE);
		SqlSelect querySql = new SqlSelect().where(VIDEO.SQL_ccheckcode);
		video = load(querySql, video);
		return video;
	}
	
	
}
  