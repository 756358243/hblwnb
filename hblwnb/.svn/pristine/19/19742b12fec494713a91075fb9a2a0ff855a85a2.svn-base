/**
 * 
 */
package com.vkl.hblw.controller.user.statistics;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vkl.common.json.JsonResult;
import com.vkl.common.json.JsonUtil;
import com.vkl.common.util.StrUtil;
import com.vkl.hblw.common.constant.SystemInfo;
import com.vkl.hblw.common.result.user.count.ResultCarTypeCount;
import com.vkl.hblw.common.result.user.count.ResultCountSum;
import com.vkl.hblw.common.result.user.count.ResultPersonWorkload;
import com.vkl.hblw.common.util.GsonUtil;
import com.vkl.hblw.controller.user.systemLog.OperationLogController;
import com.vkl.hblw.dao.domain.TPREFACECHECK;
import com.vkl.hblw.dao.domain.VEHICLELOGIN;
import com.vkl.hblw.dao.util.PageList;
import com.vkl.hblw.service.user.statistics.StatisticsService;

/**
 * 
* @ClassName: StatisticsController 
* @Description: 统计信息
* @author xieyong
* @date 2017年10月17日 上午10:41:01 
*
 */
@Controller
@RequestMapping(value = "/user/statistics")
public class StatisticsController {
	
	private static final Logger log = Logger.getLogger(OperationLogController.class);

	@Resource
	private StatisticsService statisticsService;
	
	private String ret(Model model, String view) {
		return "/user/statistics/" + view;
	}
	/**
	 * 
	 * 车辆检测统计页面
	 * 
	 * @author xcc
	 */
	@RequestMapping("/countIndex")
	public String index(Model model, HttpServletRequest req) throws Exception {
		return ret(model, "countIndex");
	}
	/**
	 * 
	 * 人员业务量统计页面
	 * 
	 * @author xcc
	 */
	@RequestMapping("/workload")
	public String countPersor(Model model, HttpServletRequest req) throws Exception {
		return ret(model, "workload");
	}
	/**
	 * 
	 * 金钱统计页面
	 * 
	 * @author xcc
	 */
	@RequestMapping("/moneyCountIndex")
	public String moneyCountIndex(Model model, HttpServletRequest req) throws Exception {
		return ret(model, "moneyCountIndex");
	}
    /**检测车辆统计页面*/
	@RequestMapping("/vehicle")
	public String vehicle(Model model, HttpServletRequest req) throws Exception {
		return ret(model, "vehicle");
	}

	/**
	 * 
	* @Title: countTest 
	* @Description: 最近7天检测
	* @param model
	* @param req
	* @return
	* @throws Exception
	 */
	@RequestMapping("/countTest")
	public ResponseEntity<String> countTest(Model model, HttpServletRequest req) throws Exception {
		/**类型 day year*/
		String timeType = StrUtil.getParamStr(req, "timeType", "");
		String startTime = StrUtil.getParamStr(req, "startTime", "");
		String endTime = StrUtil.getParamStr(req, "endTime", "");
		if (startTime == null || "".equals(startTime)) {
			startTime = "";
			if (timeType == null && "".equals(timeType)) {
				timeType = "day";
			}
		}
		if (endTime == null || "".equals(endTime)) {
			endTime = "";
			if (timeType == null && "".equals(timeType)) {
				timeType = "day";
			}

		}
		System.out.println("zuizhong______________________>" + timeType + "++++++++++++++++++++>");
		String timeAllDay = StrUtil.getParamStr(req, "timeAllDay", "");
		if (timeAllDay == null || "".equals(timeAllDay)) {
			timeAllDay = "0";
		}
		System.out.println("______________________>" + timeAllDay + "++++++++++++++++++++>");
		JsonResult result = new JsonResult();
		Map<Integer, Integer> rsMap = statisticsService.countInspectionQuantity("", startTime, endTime, "", timeType,
				"");
		List<ResultCountSum> rslist = new ArrayList<ResultCountSum>();
		for (Integer key : rsMap.keySet()) {
			ResultCountSum uc = new ResultCountSum();
			Integer value = rsMap.get(key);
			uc.setKey(key);
			uc.setSum(value);
			System.out.println("Key = " + key + ", Value = " + value);
			rslist.add(uc);
		}
		result.setAttach(rslist);
		System.out.println("__________________________________<" + rsMap + "____________________________>");
		return JsonUtil.toResponse(result);
	}
	/**
	 * 
	* @Title: countByCondtion 
	* @Description: 检测车辆信息统计
	* @param model
	* @param req
	* @return
	 */
	@RequestMapping("/countByCondtion")
	public ResponseEntity<String> countByCondtion(Model model, HttpServletRequest req) {
		String person = StrUtil.getParamStr(req, "person", "");
		String personType = StrUtil.getParamStr(req, "personType", "");
		String startTime = StrUtil.getParamStr(req, "startTime", "");
		String endTime = StrUtil.getParamStr(req, "endTime", "");
		List<ResultPersonWorkload> rsList = statisticsService.countByCondtion("", person, personType, startTime,
				endTime, "");
		JsonResult result = new JsonResult();
		result.setAttach(rsList);
		System.out.println("__________________________________<" + rsList + "____________________________>");
		return JsonUtil.toResponse(result);
	}
	
	/**
	 * 
	* @Title: countCarType 
	* @Description: 对车辆的类型分类进行统计分析 扇形统计图
	* @param model
	* @param req
	* @return json数据 echart所需数据格式
	 */
	@RequestMapping("/countCarType")
	public ResponseEntity<String> countCarType(Model model, HttpServletRequest req) {
		String startTime = StrUtil.getParamStr(req, "startTime", "");
		String endTime = StrUtil.getParamStr(req, "endTime", "");
		/**
		 * cvehiclestyle 车辆类型 VEHICLELOGIN表名 registertime登记时间 
		 */
		List<ResultCarTypeCount> rsList = statisticsService.countCarTypeByTime(startTime, endTime, "cvehiclestyle", "VEHICLELOGIN",
				"registertime");
		JsonResult result = new JsonResult();
		result.setAttach(rsList);
		return JsonUtil.toResponse(rsList);
	}
	/**
	 * 
	* @Title: countTestType 
	* @Description: 对检验类别进行统计分析 扇形统计图
	* @param model
	* @param req
	* @return
	 */
	@RequestMapping("/countTestType")
	public ResponseEntity<String> countTestType(Model model, HttpServletRequest req) {
		String startTime = StrUtil.getParamStr(req, "startTime", "");
		String endTime = StrUtil.getParamStr(req, "endTime", "");
		/**
		 * checktype检测类别  registertime登记时间 VEHICLELOGIN表名
		 */
		List<ResultCarTypeCount> rsList = statisticsService.countCarTypeByTime(startTime, endTime, "checktype", "VEHICLELOGIN",
				"registertime");
		JsonResult result = new JsonResult();
		result.setAttach(rsList);
		return JsonUtil.toResponse(rsList);

	}
	/**
	 * 
	* @Title: countPersonWorkload 
	* @Description: 统计人员工作量
	* @param model
	* @param req
	* @return
	* @throws Exception
	 */
	@RequestMapping("/countPersonWorkload")
	public ResponseEntity<String> countPersonWorkload(Model model, HttpServletRequest req) throws Exception {

		int page = StrUtil.getParamInt(req, "page", 1);
		/**岗位类别：引车员 外检员*/
		String rylx = StrUtil.getParamStr(req, "rylx", "");
		String startTime = StrUtil.getParamStr(req, "startTime", "");
		String endTime = StrUtil.getParamStr(req, "endTime", "");
		/**是否收费*/
		String charged = StrUtil.getParamStr(req, "charged", "");
		/**检测类型*/
		String checktype = StrUtil.getParamStr(req, "checktype", "");
		/**检测结果*/
		String checkresult = StrUtil.getParamStr(req, "checkresult", "");

		JsonResult result = new JsonResult();
		if (StringUtils.isEmpty(rylx)) {
			result.setCode(JsonResult.CODE_ERROR);
			result.setInfo("参数为空");
			return JsonUtil.toResponse(result);
		}
		try {
			PageList<ResultPersonWorkload> list = statisticsService.countPersonWorkload(checkresult, 
					startTime, endTime, charged, checktype, checkresult, page);
			result.setAttach(list); 
		} catch (Exception e) { 
			log.error(SystemInfo.LOG_CODE_ERROR, e);
			result.setCode(JsonResult.CODE_ERROR);
			result.setInfo(SystemInfo.API_CODE_EXCEPTION);
			return JsonUtil.toResponse(result);
		}
		return JsonUtil.toResponse(result);
	}

	/**
	 * 
	* @Title: vehicleList 
	* @Description: 统计车辆信息 (用于第三个菜单)
	* @param model
	* @param req
	* @return
	* @throws Exception
	 */
	@RequestMapping("/vehicleList")
	public ResponseEntity<String> vehicleList(Model model, HttpServletRequest req) throws Exception {
		/**号牌号码*/
		String cnumberplate = StrUtil.getParamStr(req, "cnumberplate", "");
		String dateBegin = StrUtil.getParamStr(req, "dateBegin", "");
		String dateEnd = StrUtil.getParamStr(req, "dateEnd", "");
		/**使用性质*/
		String cpurposeid = StrUtil.getParamStr(req, "cpurposeid", "");
		/**检测类型*/
		String checktype = StrUtil.getParamStr(req, "checktype", "");
		/**检测结果*/
		String checkresult = StrUtil.getParamStr(req, "checkresult", "");
		/**检验方法*/
		String ext_col10 = StrUtil.getParamStr(req, "ext_col10", "");
		/**是否收费*/
		String charge = StrUtil.getParamStr(req, "charge", "");

		JsonResult result = new JsonResult(); 
		
		try { 
			PageList<VEHICLELOGIN> list = statisticsService.queryVehicleListByCondition(cnumberplate,
					dateBegin, dateEnd, cpurposeid, checkresult, checktype, ext_col10, charge);
			
			if(list.getTotalSize() > 10000){
				result.setCode(JsonResult.CODE_ERROR);
				result.setInfo("数据超过10000条");
				return JsonUtil.toResponse(result);
			}
			result.setAttach(list);
		} catch (Exception e) { 
			log.error(SystemInfo.LOG_CODE_ERROR, e);
			result.setCode(JsonResult.CODE_ERROR);
			result.setInfo(SystemInfo.API_CODE_EXCEPTION);
			return JsonUtil.toResponse(result);
		}
		String jsonResult = GsonUtil.toJson(result, "yyyy-MM-dd HH:mm:ss");
		return JsonUtil.toResponse(jsonResult);
	}
}
