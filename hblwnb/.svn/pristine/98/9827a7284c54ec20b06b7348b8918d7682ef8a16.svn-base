/**
 * 
 */
package com.vkl.hblw.common.timer;

import java.sql.Timestamp;
import java.util.TimerTask;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vkl.hblw.setting.config.InspectionStationConfig;

/**
 * @author xcc
 *
 */
@Service
public class HeartTimerTask extends TimerTask {

	
	@Autowired
	private InspectionStationConfig inspectionStationConfig;

	private static final Logger log = Logger.getLogger(HeartTimerTask.class);

	@Override
	public void run() {
		try {			
			
			String currentTime = new Timestamp(System.currentTimeMillis()).toString();
			log.info("执行当前时间:" + currentTime);
			
			
		} catch (Exception e) {
			log.error(e.getMessage());
			log.error("-------------解析信息发生异常--------------");
		}
	}
	
	
}
