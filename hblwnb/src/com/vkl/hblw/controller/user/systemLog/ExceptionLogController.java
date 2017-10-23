/**
 * 
 */
package com.vkl.hblw.controller.user.systemLog;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vkl.common.json.JsonResult;
import com.vkl.common.json.JsonUtil;
import com.vkl.common.util.StrUtil;
import com.vkl.hblw.common.util.GsonUtil;
import com.vkl.hblw.dao.domain.ERROR;
import com.vkl.hblw.dao.util.PageList;
import com.vkl.hblw.service.user.systemLog.ExceptionLogService;

/**
 * 
* @ClassName: ExceptionLogController 
* @Description: 异常日志信息
* @author xieyong
* @date 2017年10月17日 上午10:40:31 
*
 */
@Controller
@RequestMapping(value = "/user/exceptionLog")
public class ExceptionLogController {

	private static final Logger log = Logger.getLogger(ExceptionLogController.class);
	@Autowired
	private ExceptionLogService exceptionLogService;
	private String ret(Model model, String view) {
		return "/user/system/" + view;
	}
	/**
	 * 异常日志页面
	 * @author xieyong
	 */
	@RequestMapping("/exceptionLog")
	public String excedetail(Model model, HttpServletRequest req) throws Exception {
		return ret(model, "exceptionLog");
	}
	/**
	 * 
	* @Title: systemDataList 
	* @Description: 系统异常日志分页查询
	* @param model
	* @param req
	* @return
	 */
	@RequestMapping("/systemDateList")
	public ResponseEntity<String> systemDataList(Model model, HttpServletRequest req) {
		int page = StrUtil.getParamInt(req, "page", 1);
		String stratTime = StrUtil.getParamStr(req, "stratTime", "");
		String endTime = StrUtil.getParamStr(req, "endTime", "");
		String errorUrl = StrUtil.getParamStr(req, "errorUrl", "");
		JsonResult result = new JsonResult();
		PageList<ERROR> list = exceptionLogService.queryExpLogByCondition(stratTime, endTime, errorUrl, page);
		result.setAttach(list);
		String jsonResult = GsonUtil.toJson(result, "yyyy-MM-dd HH:mm:ss");
		return JsonUtil.toResponse(jsonResult);
	}
	/**
	 * 
	* @Title: detail 
	* @Description: 异常详细信息
	* @param model
	* @param req
	* @return 
	 */
	@RequestMapping("/excedetail")
	public String getErrorById(Model model, HttpServletRequest req) {
		String errorId = StrUtil.getParamStr(req, "errorId", "");
		ERROR error = exceptionLogService.queryErrorById(errorId);
		model.addAttribute("error", error);
		return ret(model, "excedetail");
	}
}
