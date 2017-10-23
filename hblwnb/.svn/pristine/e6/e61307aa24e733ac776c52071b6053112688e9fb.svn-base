/**
 * 
 */
package com.vkl.hblw.common.timer;

import java.util.Timer;

import org.springframework.context.ApplicationContext;

/**
 * @author xcc
 *
 */
public class HeartTimer {

	// 时间间隔
	private static final long PERIOD_DAY = 30 * 1000;// 毫秒

	public HeartTimer(ApplicationContext appContext) {

		Timer timer = new Timer();

		HeartTimerTask task = (HeartTimerTask) appContext.getBean("heartTimerTask");
		// 安排指定的任务在指定的时间开始进行重复的固定延迟执行。
		timer.schedule(task, 0, PERIOD_DAY);
	}

}
