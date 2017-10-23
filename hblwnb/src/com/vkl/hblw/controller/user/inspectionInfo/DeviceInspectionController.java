/**
 * 
 */
package com.vkl.hblw.controller.user.inspectionInfo;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;

import org.apache.axis.utils.StringUtils;
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
import com.vkl.hblw.dao.util.PageList;
import com.vkl.hblw.service.user.inspectionInfo.DeviceInspectionService;

/**
 * @author xcc
 *
 */
@Controller
@RequestMapping(value = "/user/deviceInspect")
public class DeviceInspectionController {

	private static final Logger log = Logger.getLogger(DeviceInspectionController.class);

	@Autowired
	private DeviceInspectionService deviceInspectionService;
	
	private String ret(Model model, String view) {
		return "/user/inspect/" + view;
	}
	/*
	 * 检测信息界面首页
	 */
	@RequestMapping("/diviceIndex")
	public String index(Model model, HttpServletRequest req) throws Exception {

		return ret(model, "diviceIndex");

	}

}
