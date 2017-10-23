/**
 * 
 */
package com.vkl.hblw.controller.api.video;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.core.task.TaskRejectedException;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vkl.common.json.JsonResult;
import com.vkl.common.json.JsonUtil;
import com.vkl.common.util.StrUtil;
import com.vkl.hblw.common.constant.CustomData;
import com.vkl.hblw.common.constant.SystemInfo;
import com.vkl.hblw.common.thread.SubmitThread;
import com.vkl.hblw.common.thread.VideoCompressThread;
import com.vkl.hblw.service.api.video.VideoServiece;

/**
 * @author xcc
 *
 */
@Controller
@Scope("prototype")
@RequestMapping(value = "/api/video")
public class VideoController {

	private static final Logger log = Logger.getLogger(VideoController.class);
	
	@Resource
	private VideoServiece videoServiece;
	@Resource
	private SubmitThread submitThread;
	@Resource
	private ThreadPoolTaskExecutor threadPool;
	@Resource
	private VideoCompressThread videoCompressThread;
	
	/** 
	* @Title: compress 
	* @Description: 视频压缩接口
	* @author xwh
	* @param req
	* @return
	* @throws Exception    设定文件 
	* @return ResponseEntity<String>    返回类型 
	* @throws 
	*/
	@RequestMapping("/compress")
	public ResponseEntity<String> compress(HttpServletRequest req) throws Exception {

//		String cnumberplate = StrUtil.getParamStr(req, "CNUMBERPLATE", "");//号牌号码
//		String cnumbertype = StrUtil.getParamStr(req, "CNUMBERTYPE", "");//号牌种类
		String ccheckcode = StrUtil.getParamStr(req, "CCHECKCODE", "");//检验流水号
		String cvin = StrUtil.getParamStr(req, "CVIN", "");//车辆识别号
		String passwaytype = StrUtil.getParamStr(req, "PASSWAYTYPE", "");//通道类别PASSWAYTYPE

		// 路径
		String servletpath = req.getServletContext().getRealPath("/");
		String ContextPath = req.getContextPath().substring(1);
		String proPath = servletpath.substring(0, servletpath.lastIndexOf(ContextPath));
		String picPath = proPath + "videoCompress";

		JsonResult result = new JsonResult();
		try{
			result.setCode(JsonResult.CODE_SUCCESS);
//			
//			videoCompressThread.setCCHECKCODE(ccheckcode);
//			videoCompressThread.setCVIN(cvin);
//			videoCompressThread.setPASSWAYTYPE(passwaytype);
//			videoCompressThread.setPath(picPath);
			threadPool.execute(videoCompressThread);
		}catch (TaskRejectedException  e) {
			log.error(SystemInfo.LOG_CODE_ERROR, e);
			Thread.sleep(10000);
		}
		
		
		return JsonUtil.toResponse(result);

	}
	
	/** 
	* @Title: underpanStart 
	* @Description: 底盘视频开始接口
	* @author xwh
	* @param req
	* @return
	* @throws Exception    设定文件 
	* @return ResponseEntity<String>    返回类型 
	* @throws 
	*/
	@RequestMapping("/underpanStart")
	public ResponseEntity<String> underpanStart(HttpServletRequest req) throws Exception {
		//检验流水号
		String ccheckcode = StrUtil.getParamStr(req, "CCHECKCODE", "");
		//检测线编号
		String cchkdevicecode = StrUtil.getParamStr(req, "CCHKDEVICECODE", "");

		log.info("CCHECKCODE:" + ccheckcode);
		log.info("CCHKDEVICECODE:" + cchkdevicecode);
		JsonResult result = new JsonResult();

		if (StringUtils.isEmpty(cchkdevicecode)) {
			result.setCode(JsonResult.CODE_ERROR);
			return JsonUtil.toResponse(result);
		}
		try {
			//视频通道类别
			String passwaytype = CustomData.SP_MONITOR_CONFIG.get(CustomData.NODECODE_VIDEO_WJ + CustomData.CURSOR_VIDEO_WJ_UNDER);
			if (videoServiece.updateUnderpanVideoWhenStart(ccheckcode, passwaytype, cchkdevicecode)) {
				result.setCode(JsonResult.CODE_SUCCESS);
			} else {
				result.setCode(JsonResult.CODE_ERROR);
			}
		} catch (Exception e) {
			log.error(SystemInfo.LOG_CODE_ERROR, e);
			return JsonUtil.toResponse(result);
		}
		return JsonUtil.toResponse(result);

	}
	
	/** 
	* @Title: underpanEnd 
	* @Description: 底盘视频结束接口
	* @author xwh
	* @param req
	* @return
	* @throws Exception    设定文件 
	* @return ResponseEntity<String>    返回类型 
	* @throws 
	*/
	@RequestMapping("/underpanEnd")
	public ResponseEntity<String> underpanEnd(HttpServletRequest req) throws Exception {
			//检验流水号
			String ccheckcode = StrUtil.getParamStr(req, "CCHECKCODE", "");
			//检测线编号
			String cchkdevicecode = StrUtil.getParamStr(req, "CCHKDEVICECODE", "");

			log.info("CCHECKCODE:" + ccheckcode);
			log.info("CCHKDEVICECODE:" + cchkdevicecode);
			JsonResult result = new JsonResult();
			
		if (StringUtils.isEmpty(cchkdevicecode)) {
			result.setCode(JsonResult.CODE_ERROR);
			return JsonUtil.toResponse(result);
		}
		try {
			//视频通道类别
			String passwaytype = CustomData.SP_MONITOR_CONFIG.get(CustomData.NODECODE_VIDEO_WJ + CustomData.CURSOR_VIDEO_WJ_UNDER);
			if (videoServiece.updateUnderpanVideoWhenStart(ccheckcode, passwaytype, cchkdevicecode)) {
				result.setCode(JsonResult.CODE_SUCCESS);
			} else {
				result.setCode(JsonResult.CODE_ERROR);
			}
		} catch (Exception e) {
			log.error(SystemInfo.LOG_CODE_ERROR, e);
			return JsonUtil.toResponse(result);
		}
		return JsonUtil.toResponse(result);
	}
	
	
}
