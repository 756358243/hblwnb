/**
 * 
 */
package com.vkl.hblw.service.user.systemLog;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.vkl.hblw.dao.domain.JOURNAL;
import com.vkl.hblw.dao.persistence.JOURNALDao;
import com.vkl.hblw.dao.util.PageList;
import com.vkl.hblw.dao.util.SqlSelect;

/**
 * 
* @ClassName: OperationLogService 
* @Description: 系统操作日志查询
* @author xieyong
* @date 2017年10月17日 上午10:52:14 
*
 */
@Service
public class OperationLogService {

	private static final Logger log = Logger.getLogger(OperationLogService.class);
	@Autowired
	private JOURNALDao journalDao;
	
	/**
	 * 
	* @Title: querySystemLogByCondition 
	* @Description: 条件查询系统操作日志
	* @param username 用户名称
	* @param startTime 开始时间
	* @param endTime 结束时间
	* @param ip 使用者使用IP
	* @param currentPage 当前页面  默认1
	* @return
	 */
	public PageList<JOURNAL> querySystemLogByCondition(String username, String startTime, String endTime,String ip,
			int currentPage) {
		PageList<JOURNAL> pageList = new PageList<JOURNAL>();
		pageList.setCurrentPage(currentPage);
		pageList.setPageSize(10);
		Map<String, Object> map = new HashMap<String, Object>();
		SqlSelect select = new SqlSelect(); 

		select.where("1=1");
		// 添加处理含有用户名称查询条件的sql语句
		if (!StringUtils.isEmpty(username)) {
			map.put("username", username);
			select.and(JOURNAL.SQL_username);
		}
		// 添加处理含有ip查询条件的sql语句
		if (!StringUtils.isEmpty(ip)) {
			map.put("ip", ip);
			select.and(JOURNAL.SQL_usepersonip);
		}
		if (!StringUtils.isEmpty(startTime)) {

			select.and(JOURNAL.FLD_usetime + ">= to_date('" + startTime + "', 'yyyy-MM-dd hh24:mi:ss') ");
		}
		if (!StringUtils.isEmpty(endTime)) {

			select.and(JOURNAL.FLD_usetime + "<= to_date('" + endTime + "', 'yyyy-MM-dd hh24:mi:ss') ");
		}
		journalDao.pageListByMap(pageList, true, select, map);
		return pageList;

	}
}
