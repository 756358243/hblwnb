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
import com.vkl.hblw.dao.domain.JOURNAL;
import com.vkl.hblw.dao.util.PageList;
import com.vkl.hblw.service.user.systemLog.ExceptionLogService;
import com.vkl.hblw.service.user.systemLog.OperationLogService;

/**
 * 
* @ClassName: OperationLogController 
* @Description: 系统操作日志
* @author xieyong
* @date 2017年10月17日 上午10:39:58 
*
 */
@Controller
@RequestMapping(value = "/user/operationLog")
public class OperationLogController {

	private static final Logger log = Logger.getLogger(OperationLogController.class);

	@Autowired
	private OperationLogService operationLogService;
	//private 
	private String ret(Model model, String view) {
		return "/user/system/" + view;
	}

	/**
	 * 系统操作日志页面
	 * @author xieyong
	 */
	@RequestMapping("/systemLog")
	public String systemLog(Model model, HttpServletRequest req) throws Exception {
		return ret(model, "systemLog");
	}
	/**
	 * 
	* @Title: systemDataList 
	* @Description: 系统操作日志
	* @param model
	* @param req
	* @return
	 */
	@RequestMapping("/systemDateList")
	public ResponseEntity<String> querySystemDataList(Model model, HttpServletRequest req) {
		int page = StrUtil.getParamInt(req, "page", 1);
		String startTime = StrUtil.getParamStr(req, "stratTime", "");
		String endTime = StrUtil.getParamStr(req, "endTime", "");
		String username=StrUtil.getParamStr(req, "username", "");//用户名称
		String ip=StrUtil.getParamStr(req, "ip", "");//使用者使用IP
		
		JsonResult result = new JsonResult();
		PageList<JOURNAL> list = operationLogService.querySystemLogByCondition(username, startTime, endTime, ip, page);
		result.setAttach(list);
		String jsonResult = GsonUtil.toJson(result, "yyyy-MM-dd HH:mm:ss");
		return JsonUtil.toResponse(jsonResult);
	}
}
