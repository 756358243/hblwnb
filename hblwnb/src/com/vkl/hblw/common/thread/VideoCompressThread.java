/**
 * 
 */
package com.vkl.hblw.common.thread;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author xcc
 *
 */
@Component
@Scope("prototype")
public class VideoCompressThread implements Runnable {

	private static final Logger log = Logger.getLogger(VideoCompressThread.class);

	

	@Override
	public void run() {
	}

}
