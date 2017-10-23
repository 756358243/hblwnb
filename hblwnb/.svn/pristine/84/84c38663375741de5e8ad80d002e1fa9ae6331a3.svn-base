/**
 * 
 */
package com.vkl.hblw.service.user.systemLog;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vkl.hblw.dao.domain.ERROR;
import com.vkl.hblw.dao.domain.JOURNAL;
import com.vkl.hblw.dao.persistence.ERRORDao;
import com.vkl.hblw.dao.persistence.JOURNALDao;
import com.vkl.hblw.dao.util.PageList;
import com.vkl.hblw.dao.util.SqlSelect;

/**
 * 
* @ClassName: ExceptionLogService 
* @Description: 异常日志查询service
* @author xieyong
* @date 2017年10月17日 上午10:51:53 
*
 */
@Service
public class ExceptionLogService {
	
	private static final Logger log = Logger.getLogger(ExceptionLogService.class);
	
	@Autowired
	private ERRORDao errorDao;
	
	/**
	 * 
	* @Title: pageListByCondition 
	* @Description: 异常日志分页查询
	* @param stratTime
	* @param endTime
	* @param errorUrl
	* @param currentPage
	* @return
	 */
	public PageList<ERROR> queryExpLogByCondition(String stratTime, String endTime, String errorUrl, int currentPage) {
		PageList<ERROR> pageList = new PageList<ERROR>();
		pageList.setCurrentPage(currentPage);
		pageList.setPageSize(10);
		Map<String, Object> map = new HashMap<String, Object>();
		SqlSelect select = new SqlSelect();
		map.put("errorUrl", errorUrl);
		select.where("1=1");
		if (stratTime != null && !"".equals(stratTime)) {
			select.and(ERROR.FLD_errortime + ">= to_date('" + stratTime + "', 'yyyy-MM-dd') ");
		}
		if (endTime != null && !"".equals(endTime)) {
			select.and(ERROR.FLD_errortime + "<= to_date('" + endTime + "', 'yyyy-MM-dd') ");
		}
		if (errorUrl != null && !"".equals(errorUrl)) {
			// 添加处理含有异常访问地址查询条件的sql语句
			select.and(ERROR.SQL_errorurl);
		}
		errorDao.pageListByMap(pageList, true, select, map);
		return pageList;
	}
	/**
	 * 
	* @Title: getErrorById 
	* @Description: 查看异常详细信息
	* @param errorId 主键id
	* @return
	 */
	public ERROR queryErrorById(String errorId){
		return errorDao.loadById(errorId);
	}
}
