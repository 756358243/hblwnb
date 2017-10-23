/**
 * 
 */
package com.vkl.hblw.controller.api.vehicleLogin;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vkl.common.json.JsonResult;
import com.vkl.common.json.JsonUtil;
import com.vkl.common.util.StrUtil;
import com.vkl.hblw.common.constant.SystemInfo;
import com.vkl.hblw.service.api.vehicleLogin.VehicleLoginService;

/**
 * @author xcc 检测登录系统调用接口
 */
@Controller
@RequestMapping(value = "/api/vehicleLogin")
public class VehicleLoginController {

	private static final Logger log = Logger.getLogger(VehicleLoginController.class);

	@Resource
	private VehicleLoginService vehicleLoginService;
	
	
	/** 
	* @Title: modifyCheckMethodAndCdriveform 
	* @Description: 修改检测方式和驱动方式 
	* @param req
	* @return    设定文件 
	* @return ResponseEntity<String>    返回类型 
	* @throws 
	*/
	@RequestMapping("/modifyCheckMethodAndCDriveForm")
	public ResponseEntity<String> modifyCheckMethodAndCDriveForm(HttpServletRequest req)throws Exception{
		String ccheckcode = StrUtil.getParamStr(req, "CCHECKCODE","");//检验流水号	
		String cprefacecheckid = StrUtil.getParamStr(req, "CPREFACECHECKID","");//外观检验流水号	
		String checkmethod = StrUtil.getParamStr(req, "CHECKMETHOD","");//检测方法	
		String cdriveform = StrUtil.getParamStr(req, "CDRIVEFORM","");//驱动方式	
		
		JsonResult result = new JsonResult();
		
		try {
			if (vehicleLoginService.modifyCheckMethod(ccheckcode,checkmethod)
					&&vehicleLoginService.modifyCdriveform(cprefacecheckid,cdriveform)) {
				result.setCode(JsonResult.CODE_SUCCESS);
			} else {
				result.setCode(JsonResult.CODE_ERROR);
				result.setInfo(SystemInfo.API_CODE_ERROR_MODIFY_CHECKMETHOD_AND_CDRIVEFORM);
			}
		} catch (Exception e) {
			log.error(SystemInfo.LOG_CODE_ERROR, e);
			result.setCode(JsonResult.CODE_ERROR);
			result.setInfo(SystemInfo.API_CODE_EXCEPTION);
		}
		return JsonUtil.toResponse(result);
	}
	
	/** 
	* @Title: retirement 
	* @Description: 登录车辆办理退办
	* @param req
	* @return
	* @throws Exception    设定文件 
	* @return ResponseEntity<String>    返回类型 
	* @throws 
	*/
	@RequestMapping("/retirement")
	public ResponseEntity<String> retirement(HttpServletRequest req) throws Exception {
//		String cnumberplate = StrUtil.getParamStr(req, "CNUMBERPLATE","");//号牌号码
//		String cnumbertype = StrUtil.getParamStr(req, "CNUMBERTYPE","");//号牌种类
//		String cvin = StrUtil.getParamStr(req, "CVIN","");//车辆识别号	
		String ccheckcode = StrUtil.getParamStr(req, "CCHECKCODE","");//检验流水号	
		
		JsonResult result = new JsonResult();
		
		try {
			String info = vehicleLoginService.retirement(ccheckcode);
			if (SystemInfo.API_RETIREMENT_SUCCESS.equals(info)) {
				result.setCode(JsonResult.CODE_SUCCESS);

			} else {
				result.setCode(JsonResult.CODE_ERROR);
			}
			result.setInfo(info);
		} catch (Exception e) {
			log.error(SystemInfo.LOG_CODE_ERROR, e);
			result.setCode(JsonResult.CODE_ERROR);
			result.setInfo(SystemInfo.API_CODE_EXCEPTION_RETIREMENT);
		}
		return JsonUtil.toResponse(result);
	}
	
	/** 
	* @Title: modifyCheckFuelType 
	* @Description: 修改检测燃料种类
	* @param req
	* @return    设定文件 
	* @return ResponseEntity<String>    返回类型 
	* @throws 
	*/
	@RequestMapping("/modifyCheckFuelType")
	public ResponseEntity<String> modifyCheckFuelType(HttpServletRequest req)throws Exception{
//		String cnumberplate = StrUtil.getParamStr(req, "CNUMBERPLATE","");//号牌号码
//		String cnumbertype = StrUtil.getParamStr(req, "CNUMBERTYPE","");//号牌种类
//		String cvin = StrUtil.getParamStr(req, "CVIN","");//车辆识别号
		String ccheckcode = StrUtil.getParamStr(req, "CCHECKCODE","");//检验流水号	
		String checkfueltype = StrUtil.getParamStr(req, "CHECKFUELTYPE","");//检测燃料种类
		JsonResult result = new JsonResult();
		try {
			String info = vehicleLoginService.modifyCheckFuelType(ccheckcode, checkfueltype);
			if (SystemInfo.API_MODIFYJYRLZL_SUCCESS.equals(info)) {
				result.setCode(JsonResult.CODE_SUCCESS);

			} else {
				result.setCode(JsonResult.CODE_ERROR);
			}
			result.setInfo(info);
		} catch (Exception e) {
			log.error(SystemInfo.LOG_CODE_ERROR, e);
			result.setCode(JsonResult.CODE_ERROR);
			result.setInfo(SystemInfo.API_CODE_EXCEPTION);
		}
		return JsonUtil.toResponse(result);
	}
	/** 
	* @Title: uploadChragedVehicle 
	* @Description: 提交已收费车辆信息
	* @return    设定文件 
	* @return ResponseEntity<String>    返回类型 
	* @throws 
	*/
	@RequestMapping("/charged")
	public ResponseEntity<String> uploadChragedVehicle(HttpServletRequest req)throws Exception{
//		String cnumberplate = StrUtil.getParamStr(req, "CNUMBERPLATE","");//号牌号码
//		String cnumbertype = StrUtil.getParamStr(req, "CNUMBERTYPE","");//号牌种类
//		String cvin = StrUtil.getParamStr(req, "CVIN","");//车辆识别号
		String ccheckcode = StrUtil.getParamStr(req, "CCHECKCODE","");//检验流水号	
		
		JsonResult result = new JsonResult();
		try {
			if (vehicleLoginService.uploadChragedVehicle(ccheckcode)){
				result.setCode(JsonResult.CODE_SUCCESS);
			}else {
				result.setCode(JsonResult.CODE_ERROR);
				result.setInfo(SystemInfo.API_CODE_ERROR);
			}
		} catch (Exception e) {
			log.error(SystemInfo.LOG_CODE_ERROR, e);
			result.setCode(JsonResult.CODE_ERROR);
			result.setInfo(SystemInfo.API_CODE_EXCEPTION);
		}
		return JsonUtil.toResponse(result);
	}
}