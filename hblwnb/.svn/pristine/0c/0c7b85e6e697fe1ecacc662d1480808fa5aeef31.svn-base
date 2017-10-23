/**
 * 
 */
package com.vkl.hblw.service.api.video;

import java.sql.Timestamp;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vkl.hblw.common.constant.CustomData;
import com.vkl.hblw.dao.domain.ORIGINALVIDEO;
import com.vkl.hblw.dao.domain.VEHICLELOGIN;
import com.vkl.hblw.dao.persistence.ORIGINALVIDEODao;
import com.vkl.hblw.dao.persistence.VEHICLELOGINDao;
import com.vkl.hblw.dao.persistence.VIDEODao;
import com.vkl.hblw.dao.util.SqlUpdate;

/**
 * @author xcc
 *
 */
@Service
public class VideoServiece {

	private static final Logger log = Logger.getLogger(VideoServiece.class);
	
	@Autowired
	private ORIGINALVIDEODao originalvideoDao;// 原始视频信息
	@Autowired
	private VIDEODao videoDao;// 检验视频信息
	@Autowired
	private VEHICLELOGINDao vehicleloginDao;// 检验视频信息

	/** 
	* @Title: updateUnderpanVideoWhenStart 
	* @Description: 更新底盘视频信息
	* @author xwh
	* @param ccheckcode
	* @param passwaytype
	* @param cchkdevicecode
	* @return    设定文件 
	* @return boolean    返回类型 
	* @throws 
	*/
	public boolean updateUnderpanVideoWhenStart(String ccheckcode, String passwaytype, String cchkdevicecode) {
		try {
			VEHICLELOGIN vehiclelogin = vehicleloginDao.queryByCcheckcode(ccheckcode);
			
			if (null == vehiclelogin) {
				return false;
			}
			ORIGINALVIDEO originalvideo = originalvideoDao.queryOriginalVideoByCcheckcode(ccheckcode, passwaytype);
			if (null == originalvideo) {

				ORIGINALVIDEO jyyssp = new ORIGINALVIDEO();
				jyyssp.setCcheckcode(ccheckcode);
				jyyssp.setCchkdevicecode(cchkdevicecode);
				jyyssp.setVideotype(CustomData.NODECODE_VIDEO_WJ);
				jyyssp.setVideonumber(CustomData.CURSOR_VIDEO_WJ_UNDER);
				jyyssp.setVideostarttime(new Timestamp(System.currentTimeMillis()));
				jyyssp.setPasswaytype(passwaytype);
				originalvideoDao.insert(jyyssp);
			} else {
				originalvideo.setCchkdevicecode(cchkdevicecode);
				originalvideo.setVideostarttime(new Timestamp(System.currentTimeMillis()));
				SqlUpdate sqlUpdate = new SqlUpdate().addColumn(ORIGINALVIDEO.SQL_videostarttime).addColumn(ORIGINALVIDEO.SQL_cchkdevicecode)
						.where(ORIGINALVIDEO.SQL_id);
				originalvideoDao.update(sqlUpdate, originalvideo);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return true;
	}

	
}
