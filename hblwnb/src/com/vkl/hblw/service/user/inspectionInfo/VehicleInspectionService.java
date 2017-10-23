/**
 * 
 */
package com.vkl.hblw.service.user.inspectionInfo;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.vkl.hblw.common.constant.SystemInfo;
import com.vkl.hblw.dao.domain.VEHICLELOGIN;
import com.vkl.hblw.dao.persistence.TPREFACECHECKDao;
import com.vkl.hblw.dao.persistence.VEHICLELOGINDao;
import com.vkl.hblw.dao.util.PageList;
import com.vkl.hblw.dao.util.SqlSelect;

/**
 * @author xcc
 *
 */
@Service
public class VehicleInspectionService {

	private static final Logger log = Logger.getLogger(VehicleInspectionService.class);
	
	@Autowired
	private VEHICLELOGINDao vehicleloginDao;// 检验登记信息
	@Autowired
	private TPREFACECHECKDao tprefacecheckDao;// 外检信息

	/*
	 * 检测信息界面首页
	 */
	public PageList<VEHICLELOGIN> pageListByCondition(String cnumberplate, String dcheckbegindate, String dcheckenddate,
			int currentPage, String cchkdevicecode, String institution, String checktype, String checkmethod,
			String checkstate, String checklink) {
		PageList<VEHICLELOGIN> pageList = new PageList<VEHICLELOGIN>();
		pageList.setCurrentPage(currentPage);
		pageList.setPageSize(10);
		try {

			Map<String, String> map = new HashMap<String, String>();
			// 组装筛选用户名称的SQL语句
			SqlSelect select = new SqlSelect();
			select.where(" 1=1 ");
			if (!StringUtils.isEmpty(cnumberplate)) {
				map.put("hphm", "%" + cnumberplate + "%");
				select.and(VEHICLELOGIN.FLD_cnumberplate + " like :CNUMBERPLATE ");
			}
			if (!StringUtils.isEmpty(cchkdevicecode)) {
				map.put("CCHKDEVICECODE", cchkdevicecode);
				select.and(VEHICLELOGIN.SQL_cchkdevicecode);
			}
			// 组装筛选时间范围的SQL语句
			if (!StringUtils.isEmpty(dcheckbegindate)) {
				select.and(VEHICLELOGIN.FLD_registertime + ">= to_date('" + dcheckenddate + "', 'yyyy-MM-dd hh24:mi:ss') ");
			}
			if (!StringUtils.isEmpty(dcheckenddate)) {
				select.and(VEHICLELOGIN.FLD_registertime + "<= to_date('" + dcheckenddate + "', 'yyyy-MM-dd hh24:mi:ss') ");
			}

			// 检测方法
			if (!StringUtils.isEmpty(checkmethod)) {
				map.put("CHECKMETHOD", checkmethod);
				select.and(VEHICLELOGIN.SQL_ext_col10);
			}
			// 检测结果
			if (!StringUtils.isEmpty(institution)) {
				map.put("INSTITUTION", institution);
				select.and(VEHICLELOGIN.SQL_institution);
			}
			// 检测类别
			if (!StringUtils.isEmpty(checktype)) {
				map.put("CHECKTYPE", checktype);
				select.and(VEHICLELOGIN.SQL_checktype);
			}
			// 检测状态
			if (!StringUtils.isEmpty(checkstate)) {
				map.put("CHECKSTATE", checkstate);
				select.and(VEHICLELOGIN.SQL_checkstate);
			}
			// 审验环节
			if (!StringUtils.isEmpty(checklink)) {
				map.put("CHECKLINK", checklink);
				select.and(VEHICLELOGIN.SQL_checklink);
			}
			select.orderBy(" order by " + VEHICLELOGIN.FLD_registertime + " desc ");
			vehicleloginDao.pageListByMap(pageList, true, select, map);
		} catch (Exception e) {
			log.error(SystemInfo.LOG_CODE_ERROR, e);
			return null;
		}

		return pageList;
	}
	}

	

