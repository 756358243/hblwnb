package com.vkl.hblw.common.util;

import java.util.concurrent.atomic.AtomicInteger;

import org.aspectj.weaver.patterns.ThisOrTargetAnnotationPointcut;
import org.springframework.beans.factory.annotation.Autowired;

import com.vkl.hblw.dao.domain.VEHICLELOGIN;

/** 
* @ClassName: SerialnumberUtil 
* @Description: 生成流水号工具类
* @author xwh
* @date 2017年10月22日 上午10:24:53 
*  
*/
public class SerialnumberUtil {
	
	public static String getSerialnumber() {
		AtomicInteger at = new AtomicInteger();
        int atint = at.incrementAndGet();
       String serialnumber = String.format("%09d", atint);
       return serialnumber;
	}
}

