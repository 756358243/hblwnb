/**
 * 
 */
package com.vkl.hblw.common.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * @author xcc
 *
 */
public class UploadInfoUtil {

	public static String generateExchangeCode(String sqm) {

		String dqbm = "330101";
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");// 定义格式，不显示毫秒
		Timestamp now = new Timestamp(System.currentTimeMillis());// 获取系统当前时间
		String time = df.format(now);
		String serialNumber = "0000001";
		return dqbm + time + serialNumber + sqm;
	}

	public static String generateRequestTime() {

		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");// 定义格式，不显示毫秒
		Timestamp now = new Timestamp(System.currentTimeMillis());// 获取系统当前时间
		String time = df.format(now);

		return time;
	}
}
