/**
 * 
 */
package com.vkl.hblw.controller.api.autoPrint;

import java.sql.Timestamp;
import java.text.DecimalFormat;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.axis.utils.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vkl.common.util.StrUtil;
import com.vkl.common.util.Util;
import com.vkl.hblw.dao.domain.RESULTSREPORT;
import com.vkl.hblw.dao.domain.TPREFACECHECK;
import com.vkl.hblw.dao.domain.VEHICLELOGIN;
import com.vkl.hblw.dao.persistence.ORIGINALVIDEODao;
import com.vkl.hblw.dao.persistence.RESULTSREPORTDao;
import com.vkl.hblw.dao.persistence.TPREFACECHECKDao;
import com.vkl.hblw.dao.persistence.VEHICLELOGINDao;
import com.vkl.hblw.setting.config.APIDataConfig;



/**
 * 
* @ClassName: AutoPrintController 
* @Description: 智能打印
* @author xieyong
* @date 2017年10月19日 下午12:01:02 
*
 */
@Controller
@RequestMapping(value = "/api/autoPrint")
public class AutoPrintController {

	private static final Logger log = Logger.getLogger(AutoPrintController.class);

	/**外检信息*/
	@Autowired
	private TPREFACECHECKDao tprefacecheckDao;
	/**检验结果信息*/
	@Autowired
	private RESULTSREPORTDao resultsreportDao;
	/**车辆状态信息*/
	@Autowired
	private VEHICLELOGINDao vehicleloginDao;
	/**原始视频信息*/
	@Autowired
	private ORIGINALVIDEODao originalvideoDao;
	/**环保接口字段对照*/
	@Autowired
	private APIDataConfig apiDataConfig;
	@Autowired
	private Util util;
	
	private String ret(Model model, String view) {
		return "/user/print/" + view;
	}
	
	/**
	 * @author xcc 打印界面
	 */
	@RequestMapping("/order")
	public String dataList(Model model, HttpServletRequest req) throws Exception {
        /**获取流水号*/
		String ccheckcode = StrUtil.getParamStr(req, "ccheckcode", "");
		/**外观检验流水号*/
		String cprefacecheckid=StrUtil.getParamStr(req, "cprefacecheckid","");

		if (StringUtils.isEmpty(ccheckcode)) {
			log.error("StringUtils.isEmpty(ccheckcode)");
			return "/error";
		}
		/**车辆状态信息*/
		VEHICLELOGIN vehiclelogin = vehicleloginDao.queryByCcheckcode(ccheckcode);
		/**TPREFACECHECK 外检信息表*/
		TPREFACECHECK tprefacecheck = tprefacecheckDao.queryByCprefacecheckid(cprefacecheckid);
		/**检验结果*/
		RESULTSREPORT resultsreport = resultsreportDao.queryByCcheckcode(ccheckcode);
		model.addAttribute("vehiclelogin", vehiclelogin);
		model.addAttribute("tprefacecheck", tprefacecheck);
		model.addAttribute("resultsreport", resultsreport);

		return ret(model, "order");

	}
}