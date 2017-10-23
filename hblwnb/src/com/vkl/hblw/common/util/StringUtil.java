/**
 * 
 */
package com.vkl.hblw.common.util;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

import com.vkl.hblw.common.constant.SystemInfo;

/**
 * @author xcc
 *
 */
public class StringUtil {

	private static final Logger log = Logger.getLogger(StringUtil.class);
	
	public static void main(String args[]) {
		
		String str1 = null;
		String str2 = "sdf";
		if(equal(str1, str2)){
			System.out.println("true");
		}else{
			System.out.println("false");
		}

	}
	/**
	 * 判断两个字符串是否相等
	 */
	public static Boolean equal(Object str1, Object str2) {
		
		try {
			if(StringUtils.isEmpty(str1)){
				
				if(StringUtils.isEmpty(str2)){
					return true;
				}else{
					return (str2.equals(str1));
				}
			}else{
				return (str1.equals(str2));
			}
			
		} catch (Exception e) {
			log.error(SystemInfo.LOG_CODE_ERROR, e);
			throw e;
		}
	}
}
