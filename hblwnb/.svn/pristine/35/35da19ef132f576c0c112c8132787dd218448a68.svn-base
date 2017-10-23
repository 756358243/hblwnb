/** 
 * Project Name:hblw_nb 
 * File Name:ORIGINALVIDEODao.java 
 * Package Name:com.vkl.hblw.dao.persistence 
 * Date:2017年10月13日下午2:51:58 
 * Copyright (c) 2017, chenzhou1025@126.com All Rights Reserved. 
 * 
*/  
  
package com.vkl.hblw.dao.persistence;
import com.vkl.hblw.common.constant.CustomData;
import com.vkl.hblw.common.util.AndroidOut;
import com.sun.org.apache.xml.internal.resolver.helpers.PublicId;
import com.vkl.hblw.dao.domain.ORIGINALVIDEO;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.vkl.hblw.dao.domain.ORIGINALVIDEO;
import com.vkl.hblw.dao.domain.TPREFACECHECK;
import com.vkl.hblw.dao.persistence.base.ORIGINALVIDEOBaseDao;
import com.vkl.hblw.dao.util.SqlDelete;
import com.vkl.hblw.dao.util.SqlSelect;


/** 
* @ClassName: ORIGINALVIDEODao 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author aiyun 
* @date 2017年10月13日 下午2:52:28 
*  
*/
public class ORIGINALVIDEODao extends ORIGINALVIDEOBaseDao {

	@Autowired
	private TPREFACECHECKDao tPREFACECHECKDao;// 外检信息
	
	
/** 
* @Title: queryOriginalVideoByCcheckcode 
* @Description: 查询某一个视频 
* @param ccheckcode
* @param passwaytype
* @return    设定文件 
* @return ORIGINALVIDEO    返回类型 
* @throws 
*/
public ORIGINALVIDEO queryOriginalVideoByCcheckcode(String ccheckcode,String passwaytype){
		ORIGINALVIDEO originalvideo = new ORIGINALVIDEO();
		originalvideo.setCcheckcode(ccheckcode);
		originalvideo.setPasswaytype(passwaytype);
		SqlSelect querysql = new SqlSelect().where(ORIGINALVIDEO.SQL_ccheckcode).and(ORIGINALVIDEO.SQL_passwaytype);
		originalvideo = load(querysql, originalvideo);
		return originalvideo;
	}

/**
 * 
* @Title: queryByCcheckcode 
* @Description: 根据流水号查询原始视频信息
* @param ccheckcode 流水号 
* @return 原始视频信息
 */
public ORIGINALVIDEO queryByCcheckcode(String ccheckcode){
	ORIGINALVIDEO originalvideo = new ORIGINALVIDEO();
	originalvideo.setCcheckcode(ccheckcode);
	SqlSelect querysql = new SqlSelect().where(ORIGINALVIDEO.SQL_ccheckcode).and(ORIGINALVIDEO.SQL_passwaytype);
	originalvideo = load(querysql, originalvideo);
	return originalvideo;
}
	
	/** 
	* @Title: insertOriginalvideo 
	* @Description: 用于外检结束后进行的插入原始视频
	* @param CCHECKCODE
	* @return    设定文件 
	* @return Integer    返回类型 
	* @throws 
	*/
	public Integer insertOriginalvideo(String CCHECKCODE){
		Integer rsO = 0;
		try {
			TPREFACECHECK tprefacecheck = tPREFACECHECKDao.queryByCcheckcode(CCHECKCODE);
			//1.0如果有对应编号的视频，那么就删除
		    List<ORIGINALVIDEO> ovideoList = queryAllByCcheckcode(CCHECKCODE,CustomData.VIDEO_W_TYPE);
		    for (ORIGINALVIDEO originalvideo : ovideoList) {
		    	if (CustomData.CURSOR_VIDEO_WJ_FRONT.equals(originalvideo.getVideonumber())
						|| CustomData.CURSOR_VIDEO_WJ_BACK.equals(originalvideo.getVideonumber())){
		    		delete(new SqlDelete().where(ORIGINALVIDEO.SQL_id), originalvideo);
		    	}
			}
		    //2.0插入视频
		    Integer wjTimes = Integer.valueOf(CustomData.VIDEO_W_TIMES);
		    AndroidOut.aiyunprintln("外检视频需要插入的次数："+wjTimes);
		    for (int i = 0; i < wjTimes; i++) {
				ORIGINALVIDEO originalvideo = new ORIGINALVIDEO();
				originalvideo.setCcheckcode(tprefacecheck.getCcheckcode());
				originalvideo.setCprefacecheckid(tprefacecheck.getCprefacecheckid());
				originalvideo.setCunitcode(tprefacecheck.getCunitcode());
				originalvideo.setVideostarttime(tprefacecheck.getVideostarttime());
				originalvideo.setVideoendtime(tprefacecheck.getVideoendtime());
				originalvideo.setVideotype(CustomData.VIDEO_W_TYPE);
				if(0 == i){
					originalvideo.setVideonumber(CustomData.VIDEO_CODE1);
					originalvideo.setVideo(CustomData.SP_MONITOR_CONFIG.get(CustomData.NODECODE_VIDEO_WJ + CustomData.CURSOR_VIDEO_WJ_FRONT));

				}
				if(1 == i){
					originalvideo.setVideonumber(CustomData.VIDEO_CODE2);
					originalvideo.setVideo(CustomData.SP_MONITOR_CONFIG.get(CustomData.NODECODE_VIDEO_WJ + CustomData.CURSOR_VIDEO_WJ_BACK));
				}else{
					AndroidOut.aiyunprintln("如果还有其他视频：联系管理员进行对应通道的配置");
				}
				
				insert(originalvideo);
		    }
		    
		    
		} catch (Exception e) {
			// TODO: handle exception
		}
		return rsO;
		
	}
	
	
	public List<ORIGINALVIDEO> queryAllByCcheckcode(String CCHECKCODE,String VIDEOTYPE){
		ORIGINALVIDEO originalvideo = new ORIGINALVIDEO();
		originalvideo.setCcheckcode(CCHECKCODE);
		originalvideo.setVideotype(VIDEOTYPE);
		SqlSelect querysql = new SqlSelect().where(ORIGINALVIDEO.SQL_ccheckcode).and(ORIGINALVIDEO.SQL_videotype);
		List<ORIGINALVIDEO> originalvideos = list(querysql, originalvideo);
		return originalvideos;
	}
	
	
}

  