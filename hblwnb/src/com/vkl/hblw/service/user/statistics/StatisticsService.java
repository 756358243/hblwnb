/**
 * 
 */
package com.vkl.hblw.service.user.statistics;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.vkl.hblw.common.constant.SystemInfo;
import com.vkl.hblw.common.result.user.count.ResultCarTypeCount;
import com.vkl.hblw.common.result.user.count.ResultCountSum;
import com.vkl.hblw.common.result.user.count.ResultPersonWorkload;
import com.vkl.hblw.dao.domain.TPREFACECHECK;
import com.vkl.hblw.dao.domain.TVEHICLE;
import com.vkl.hblw.dao.domain.VEHICLELOGIN;
import com.vkl.hblw.dao.persistence.TPREFACECHECKDao;
import com.vkl.hblw.dao.persistence.TVEHICLEDao;
import com.vkl.hblw.dao.persistence.VEHICLELOGINDao;
import com.vkl.hblw.dao.util.PageList;
import com.vkl.hblw.dao.util.SqlSelect;
import com.vkl.hblw.dao.util.SqlUtil;

/**
 * 
* @ClassName: StatisticsService 
* @Description: 统计信息逻辑类
* @author xieyong
* @date 2017年10月20日 下午2:03:35 
*
 */
@Service
public class StatisticsService {

	private static final Logger log = Logger.getLogger(StatisticsService.class);
	@Resource
	private DataSource dataSource;
	/** 检验登记信息*/
	/*@Autowired
	private TPREFACECHECKDao tprefacecheckDao;*/
	/**车辆状态信息表信息*/
	@Autowired
	private VEHICLELOGINDao vehicleloginDao;
	/**
	 * 
	* @Title: countInspectionQuantity 
	* @Description: 根据时间的间隔做每个月，每日，没年的统计(echarts图表)
	* @param userName
	* @param sTime
	* @param eTime
	* @param userType
	* @param timeType 查询类型  年 月 日
	* @param timeAllDay
	* @return
	* @throws ParseException
	 */
	public Map<Integer, Integer> countInspectionQuantity(String userName, String sTime, String eTime, String userType,
			String timeType, String timeAllDay) throws ParseException {
		Date date = null;
		Date edate;
		long b;
		long a = 0;
		if (sTime != null && !"".equals(sTime) && eTime != null && !"".equals(eTime)) {
			date = new SimpleDateFormat("yyyy-MM-dd").parse(sTime);
			edate = new SimpleDateFormat("yyyy-MM-dd").parse(eTime);
			b = edate.getTime() - date.getTime();
			a = b / (24 * 60 * 60 * 1000);
		}
		Map<Integer, Integer> rsMap = new HashMap<Integer, Integer>();
		if (a > 0) {
			Date date1;
			for (int i = 0; i < a; i++) {
				Calendar calendar = new GregorianCalendar();
				calendar.setTime(date);
				calendar.add(calendar.DATE, -1);// 把日期往前减少一天，若想把日期向后推一天则将负数改为正数
				date1 = calendar.getTime();
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				String endTime = formatter.format(date);
				String startTime = formatter.format(date1);
				String sql = "SELECT COUNT(*) as sum FROM TPREFACECHECK WHERE" + " TPREFACECHECK.DREGISTER" + ">= to_date('" + startTime
						+ "', 'yyyy-MM-dd')" + "and  TPREFACECHECK.DREGISTER" + "<= to_date('" + endTime + "', 'yyyy-MM-dd')";
				System.out.println("sql----1: "+sql);
				NamedParameterJdbcTemplate namedJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
				List<ResultCountSum> sum = namedJdbcTemplate.query(sql, new HashMap<String, Object>(),
						new RowMapper<ResultCountSum>() {
							@Override
							public ResultCountSum mapRow(ResultSet rs, int rowNum) throws SQLException {
								ResultCountSum uc = new ResultCountSum();
								uc.setSum(rs.getInt("sum"));
								return uc;
							}
						});
				Integer k = (int) (a - i);
				rsMap.put(k, sum.get(0).getSum());
				date = date1;
			}
			return rsMap;
		}
		if ("day".equals(timeType)) {
			Date datanew = new Date();
			Date date1;
			a = 7;
			for (int i = 0; i < 7; i++) {
				Calendar calendar = new GregorianCalendar();
				calendar.setTime(datanew);
				calendar.add(calendar.DATE, -1);// 把日期往前减少一天，若想把日期向后推一天则将负数改为正数
				date1 = calendar.getTime();
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				String endTime = formatter.format(datanew);
				String startTime = formatter.format(date1);
				System.out.println("时间："+startTime+"  :  "+endTime);
			    String sql = "SELECT COUNT(*) as sum FROM TPREFACECHECK WHERE" + " TPREFACECHECK.DREGISTER" + ">= to_date('" + startTime
						+ "', 'yyyy-MM-dd')" + "and  TPREFACECHECK.DREGISTER" + "<= to_date('" + endTime + "', 'yyyy-MM-dd')";
			    System.out.println("sql----2: "+sql);
			    NamedParameterJdbcTemplate namedJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
				List<ResultCountSum> sum = namedJdbcTemplate.query(sql, new HashMap<String, Object>(),
						new RowMapper<ResultCountSum>() {
							@Override
							public ResultCountSum mapRow(ResultSet rs, int rowNum) throws SQLException {
								ResultCountSum uc = new ResultCountSum();
								uc.setSum(rs.getInt("sum"));
								return uc;
							}
						});
				Integer k = 7 - i;
				rsMap.put(k, sum.get(0).getSum());
				datanew = date1;

			}
			return rsMap;
		}
		if ("".equals(timeType) || timeType == null) {
			Date date1;
			for (int i = 0; i < a; i++) {
				Calendar calendar = new GregorianCalendar();
				calendar.setTime(date);
				calendar.add(calendar.DATE, -1);// 把日期往前减少一天，若想把日期向后推一天则将负数改为正数
				date1 = calendar.getTime();
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				String endTime = formatter.format(date);
				String startTime = formatter.format(date1);
				String sql = "SELECT COUNT(*) as sum FROM TPREFACECHECK WHERE" + " TPREFACECHECK.DREGISTER" + ">= to_date('" + startTime
						+ "', 'yyyy-MM-dd')" + "and  TPREFACECHECK.DREGISTER" + "<= to_date('" + endTime + "', 'yyyy-MM-dd')";
				System.out.println("sql----3: "+sql);
				NamedParameterJdbcTemplate namedJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
				List<ResultCountSum> sum = namedJdbcTemplate.query(sql, new HashMap<String, Object>(),
						new RowMapper<ResultCountSum>() {
							@Override
							public ResultCountSum mapRow(ResultSet rs, int rowNum) throws SQLException {
								ResultCountSum uc = new ResultCountSum();
								uc.setSum(rs.getInt("sum"));
								return uc;
							}
						});
				Integer k = (int) (a - i);
				rsMap.put(k, sum.get(0).getSum());
				date = date1;

			}
			return rsMap;
		}

		if ("month".equals(timeType)) {
			Date datanew = new Date();
			Date date1;
			a = 30;
			for (int i = 0; i < a; i++) {
				Calendar calendar = new GregorianCalendar();
				calendar.setTime(datanew);
				calendar.add(calendar.DATE, -1);// 把日期往前减少一天，若想把日期向后推一天则将负数改为正数
				date1 = calendar.getTime();
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				String endTime = formatter.format(datanew);
				String startTime = formatter.format(date1);
				String sql = "SELECT COUNT(*) as sum FROM TPREFACECHECK WHERE" + " TPREFACECHECK.DREGISTER" + ">= to_date('" + startTime
						+ "', 'yyyy-MM-dd')" + "and  TPREFACECHECK.DREGISTER" + "<= to_date('" + endTime + "', 'yyyy-MM-dd')";
				System.out.println("sql----4: "+sql);
				NamedParameterJdbcTemplate namedJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
				List<ResultCountSum> sum = namedJdbcTemplate.query(sql, new HashMap<String, Object>(),
						new RowMapper<ResultCountSum>() {
							@Override
							public ResultCountSum mapRow(ResultSet rs, int rowNum) throws SQLException {
								ResultCountSum uc = new ResultCountSum();
								uc.setSum(rs.getInt("sum"));
								return uc;
							}
						});
				Integer k = (int) (a - i);
				rsMap.put(k, sum.get(0).getSum());
				datanew = date1;
			}
			return rsMap;
		}
		if ("year".equals(timeType)) {
			Date datanew = new Date();
			Date date1;
			a = 365;
			for (int i = 0; i < a; i++) {
				Calendar calendar = new GregorianCalendar();
				calendar.setTime(datanew);
				calendar.add(calendar.DATE, -1);// 把日期往前减少一天，若想把日期向后推一天则将负数改为正数
				date1 = calendar.getTime();
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				String endTime = formatter.format(datanew);
				String startTime = formatter.format(date1);
				System.out.println(
						"++++++++++++++++++" + startTime + "_______________________" + endTime + "+++++++++++++++");
				String sql = "SELECT COUNT(*) as sum FROM TPREFACECHECK WHERE" + " TPREFACECHECK.DREGISTER" + ">= to_date('" + startTime
						+ "', 'yyyy-MM-dd')" + "and  TPREFACECHECK.DREGISTER" + "<= to_date('" + endTime + "', 'yyyy-MM-dd')";
				System.out.println("sql----5: "+sql);
				NamedParameterJdbcTemplate namedJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
				List<ResultCountSum> sum = namedJdbcTemplate.query(sql, new HashMap<String, Object>(),
						new RowMapper<ResultCountSum>() {
							@Override
							public ResultCountSum mapRow(ResultSet rs, int rowNum) throws SQLException {
								ResultCountSum uc = new ResultCountSum();
								uc.setSum(rs.getInt("sum"));
								return uc;
							}
						});
				Integer k = (int) (a - i);
				rsMap.put(k, sum.get(0).getSum());
				datanew = date1;
			}
			return rsMap;
		}
		return null;
	}
	/**
	 * 
	* @Title: countCarTypeByTime 
	* @Description: 某段时间对车辆类型进行统计分析（可以适用于多张表）
	* @param startTime 
	* @param endTime
	* @param countTableField  需查询字段
	* @param table 表名
	* @param timeField 时间字段
	* @return
	 */
	public List<ResultCarTypeCount> countCarTypeByTime(String startTime, String endTime, String countTableField,
			String table, String timeField) {

		if (countTableField != null && !"".equals(countTableField) && table != null & !"".equals(table)) {
			String sql = "select " + countTableField + ",count(*) as num from " + table + " where 1=1 ";
			if (startTime != null && !"".equals(startTime)) {
				String stimesql = " and " + timeField + ">= to_date('" + startTime + "', 'yyyy-MM-dd')";
				sql = sql + stimesql;
			}
			if (endTime != null && !"".equals(endTime)) {
				String etimesql = " and " + timeField + "<= to_date('" + endTime + "', 'yyyy-MM-dd')";
				sql = sql + etimesql;
			}
			String endsql = " group by " + countTableField;
			sql = sql + endsql;
			System.out.println("_____________sql_______________" + sql );
			NamedParameterJdbcTemplate namedJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
			List<ResultCarTypeCount> allCountList = namedJdbcTemplate.query(sql, new HashMap<String, Object>(),
					new RowMapper<ResultCarTypeCount>() {
						@Override
						public ResultCarTypeCount mapRow(ResultSet rs, int rowNum) throws SQLException {
							ResultCarTypeCount uc = new ResultCarTypeCount();
							if (rs.getString(countTableField) != null && !"".equals(rs.getString(countTableField))) {
								String r, g, b;
								Random random = new Random();
								r = Integer.toHexString(random.nextInt(256)).toUpperCase();
								g = Integer.toHexString(random.nextInt(256)).toUpperCase();
								b = Integer.toHexString(random.nextInt(256)).toUpperCase();

								r = r.length() == 1 ? "0" + r : r;
								g = g.length() == 1 ? "0" + g : g;
								b = b.length() == 1 ? "0" + b : b;
								uc.setColor("#" + r + g + b);
								uc.setLabel(rs.getString(countTableField));
								uc.setData(rs.getString("num"));
							} else {
								String r, g, b;
								Random random = new Random();
								r = Integer.toHexString(random.nextInt(256)).toUpperCase();
								g = Integer.toHexString(random.nextInt(256)).toUpperCase();
								b = Integer.toHexString(random.nextInt(256)).toUpperCase();

								r = r.length() == 1 ? "0" + r : r;
								g = g.length() == 1 ? "0" + g : g;
								b = b.length() == 1 ? "0" + b : b;
								uc.setColor("#" + r + g + b);
								uc.setLabel("统计总数");
								uc.setData(rs.getString("num"));
							}
							return uc;
						}
					});
			return allCountList;

		}

		return null;

	}
	/**
	 * 
	* @Title: queryVehicleListByCondition 
	* @Description: 根据条件查询车辆信息
	* @param cnumberplate 号牌号码
	* @param dateBegin 开始时间	
	* @param dateEnd 结束时间
	* @param cpurposeid 使用性质 
	* @param checkresult 检测结果
	* @param checktype 检测类别
	* @param ext_col10 检测方法
	* @param charge 是否收费
	* @return
	 */
	public PageList<VEHICLELOGIN> queryVehicleListByCondition(String cnumberplate, String dateBegin, String dateEnd, String cpurposeid,
			String checkresult, String checktype, String ext_col10, String charge) {

		PageList<VEHICLELOGIN> pageList = new PageList<VEHICLELOGIN>();
		pageList.setCurrentPage(1);
		pageList.setPageSize(10000);// 设置数量限制，防止内存不足，或者时间过长

		try {

			Map<String, String> map = new HashMap<String, String>();
			// 组装筛选用户名称的SQL语句
			SqlSelect select = new SqlSelect();

			select.where(" 1=1 ");
			select.and(VEHICLELOGIN	.FLD_institution + " is not null ");//检验机构编码
            //号牌号码
			if (!StringUtils.isEmpty(cnumberplate)) {
				map.put("cnumberplate", "%" + cnumberplate + "%");
				select.and(VEHICLELOGIN	.FLD_cnumberplate + " like :cnumberplate ");
			}
			//使用性质
			if (!StringUtils.isEmpty(cpurposeid)) {
				map.put("cpurposeid", cpurposeid);
				select.and(VEHICLELOGIN.SQL_cpurposeid);
			}
			// 组装筛选时间范围的SQL语句 登记时间
			if (!StringUtils.isEmpty(dateBegin)) {
				select.and(VEHICLELOGIN.FLD_registertime + ">= to_date('" + dateBegin + "', 'yyyy-MM-dd hh24:mi:ss') ");
			}
			if (!StringUtils.isEmpty(dateEnd)) {
				select.and(VEHICLELOGIN.FLD_registertime + "<= to_date('" + dateEnd + "', 'yyyy-MM-dd hh24:mi:ss') ");
			}

			// 检测方法
			if (!StringUtils.isEmpty(ext_col10)) {
				map.put("ext_col10", ext_col10);
				select.and(VEHICLELOGIN.SQL_ext_col10);
			}
			// 检测结果
			if (!StringUtils.isEmpty(checkresult)) {
				map.put("checkresult", checkresult);
				select.and(VEHICLELOGIN.SQL_checkresult);
			}
			// 检测类别
			if (!StringUtils.isEmpty(checktype)) {
				map.put("checktype", checktype);
				select.and(VEHICLELOGIN	.SQL_checktype);
			}
			// 是否收费
			if (!StringUtils.isEmpty(charge)) {
				map.put("charge", charge);
				select.and(VEHICLELOGIN	.SQL_charge);
			}

			select.orderBy(" order by " + VEHICLELOGIN.FLD_registertime + " desc ");
			vehicleloginDao.pageListByMap(pageList, true, select, map);
		} catch (Exception e) {
			log.error(SystemInfo.LOG_CODE_ERROR, e);
			throw e;
		}

		return pageList;
	}

	/***
	 * 
	* @Title: countByCondtion 
	* @Description: 根据条件查询所有的统计数据
	* @param monitoringStation
	* @param person
	* @param personType
	* @param startTime
	* @param endTime
	* @param timeType
	* @return
	 */
	public List<ResultPersonWorkload> countByCondtion(String monitoringStation, String person, String personType,
			String startTime, String endTime, String timeType) {
		String sql = ""; 
		try {
			if (personType != null && !"".equals(personType)) {

				if (person != null && !"".equals(person)) {
					if ("cdrivercode".equals(personType)) {
						String cdrivercodesql = "select cdrivercode,count(*)as num from VEHICLELOGIN where 1=1 and cdrivercode='" + person + "'";
						String gsql = " group by " + personType;
						sql = sql + cdrivercodesql;

						if (startTime != null && !"".equals(startTime)) {
							String stimesql = "and VEHICLELOGIN.registertime" + ">= to_date('" + startTime + "', 'yyyy-MM-dd')";
							sql = sql + stimesql;
						}
						if (endTime != null && !"".equals(endTime)) {
							String etimesql = "and VEHICLELOGIN.registertime" + "<= to_date('" + endTime + "', 'yyyy-MM-dd')";
							sql = sql + etimesql;
						}
						sql = sql + gsql;
						System.out.println(
								"__________________________________<<<" + sql + "__________________________>>>");

						NamedParameterJdbcTemplate namedJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
						List<ResultPersonWorkload> allCountList = namedJdbcTemplate.query(sql,
								new HashMap<String, Object>(), new RowMapper<ResultPersonWorkload>() {
									@Override
									public ResultPersonWorkload mapRow(ResultSet rs, int rowNum) throws SQLException {
										ResultPersonWorkload uc = new ResultPersonWorkload();
										if (rs.getString("cdrivercode") != null && !"".equals(rs.getString("cdrivercode"))) {
											uc.setName(rs.getString("cdrivercode"));
											uc.setNum(rs.getString("num"));
										} else {
											uc.setName("allbody");
											uc.setNum(rs.getString("num"));
										}

										return uc;
									}
								});

						return allCountList;

					}
					if ("givecheck".equals(personType)) {
						String givechecksql = "select givecheck,count(*)as num from VEHICLELOGIN where 1=1 and givecheck='" + person + "'";
						String gsql = " group by " + personType;
						sql = givechecksql + sql;

						if (startTime != null && !"".equals(startTime)) {
							String stimesql = " and VEHICLELOGIN.registertime" + ">= to_date('" + startTime + "', 'yyyy-MM-dd')";
							sql = sql + stimesql;
						}
						if (endTime != null && !"".equals(endTime)) {
							String etimesql = " and VEHICLELOGIN.registertime" + "<= to_date('" + endTime + "', 'yyyy-MM-dd')";
							sql = sql + etimesql;
						}
						sql = sql + gsql;
						System.out.println(
								"__________________________________<<<" + sql + "__________________________>>>");

						NamedParameterJdbcTemplate namedJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
						List<ResultPersonWorkload> allCountList = namedJdbcTemplate.query(sql,
								new HashMap<String, Object>(), new RowMapper<ResultPersonWorkload>() {
									@Override
									public ResultPersonWorkload mapRow(ResultSet rs, int rowNum) throws SQLException {
										ResultPersonWorkload uc = new ResultPersonWorkload();
										if (rs.getString("givecheck") != null && !"".equals(rs.getString("givecheck"))) {
											uc.setName(rs.getString("givecheck"));
											uc.setNum(rs.getString("num"));
										} else {
											uc.setName("allbody");
											uc.setNum(rs.getString("num"));
										}
										return uc;
									}
								});

						return allCountList;

					}
					if ("djy".equals(personType)) {
						String djysql = "select djy,count(*)as num from VEHICLELOGIN where 1=1 and djy='" + person + "'";
						String gsql = " group by " + personType;
						sql = sql + djysql;

						if (startTime != null && !"".equals(startTime)) {
							String stimesql = " and VEHICLELOGIN.registertime" + ">= to_date('" + startTime + "', 'yyyy-MM-dd')";
							sql = sql + stimesql;
						}
						if (endTime != null && !"".equals(endTime)) {
							String etimesql = " and VEHICLELOGIN.registertime" + "<= to_date('" + endTime + "', 'yyyy-MM-dd')";
							sql = sql + etimesql;
						}
						sql = sql + gsql;
						System.out.println(
								"__________________________________<<<" + sql + "__________________________>>>");

						NamedParameterJdbcTemplate namedJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
						List<ResultPersonWorkload> allCountList = namedJdbcTemplate.query(sql,
								new HashMap<String, Object>(), new RowMapper<ResultPersonWorkload>() {
									@Override
									public ResultPersonWorkload mapRow(ResultSet rs, int rowNum) throws SQLException {
										ResultPersonWorkload uc = new ResultPersonWorkload();
										if (rs.getString("djy") != null && !"".equals(rs.getString("djy"))) {
											uc.setName(rs.getString("djy"));
											uc.setNum(rs.getString("num"));
										} else {
											uc.setName("allbody");
											uc.setNum(rs.getString("num"));
										}
										return uc;
									}
								});

						return allCountList;

					}
					if ("externalperson".equals(personType)) {
						String externalpersonsql = "select externalperson,count(*)as num from VEHICLELOGIN where 1=1 and externalperson='" + person + "'";
						String gsql = " group by " + personType;
						sql = sql + externalpersonsql;

						if (startTime != null && !"".equals(startTime)) {
							String stimesql = " and VEHICLELOGIN.registertime" + ">= to_date('" + startTime + "', 'yyyy-MM-dd')";
							sql = sql + stimesql;
						}
						if (endTime != null && !"".equals(endTime)) {
							String etimesql = " and VEHICLELOGIN.registertime" + "<= to_date('" + endTime + "', 'yyyy-MM-dd')";
							sql = sql + etimesql;
						}

						sql = sql + gsql;
						System.out.println(
								"__________________________________<<<" + sql + "__________________________>>>");

						NamedParameterJdbcTemplate namedJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
						List<ResultPersonWorkload> allCountList = namedJdbcTemplate.query(sql,
								new HashMap<String, Object>(), new RowMapper<ResultPersonWorkload>() {
									@Override
									public ResultPersonWorkload mapRow(ResultSet rs, int rowNum) throws SQLException {
										ResultPersonWorkload uc = new ResultPersonWorkload();
										if (rs.getString("externalperson") != null && !"".equals(rs.getString("externalperson"))) {
											uc.setName(rs.getString("externalperson"));
											uc.setNum(rs.getString("num"));
										} else {
											uc.setName("allbody");
											uc.setNum(rs.getString("num"));
										}
										return uc;
									}
								});

						return allCountList;

					}
					if ("cgy".equals(personType)) {
						String cgysql = "select cgy,count(*)as num from VEHICLELOGIN where 1=1 and cgy='" + person + "'";
						String gsql = " group by " + personType;
						sql = sql + cgysql;

						if (startTime != null && !"".equals(startTime)) {
							String stimesql = " and VEHICLELOGIN.registertime" + ">= to_date('" + startTime + "', 'yyyy-MM-dd')";
							sql = sql + stimesql;
						}
						if (endTime != null && !"".equals(endTime)) {
							String etimesql = " and VEHICLELOGIN.registertime" + "<= to_date('" + endTime + "', 'yyyy-MM-dd')";
							sql = sql + etimesql;
						}

						sql = sql + gsql;
						System.out.println(
								"__________________________________<<<" + sql + "__________________________>>>");

						NamedParameterJdbcTemplate namedJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
						List<ResultPersonWorkload> allCountList = namedJdbcTemplate.query(sql,
								new HashMap<String, Object>(), new RowMapper<ResultPersonWorkload>() {
									@Override
									public ResultPersonWorkload mapRow(ResultSet rs, int rowNum) throws SQLException {
										ResultPersonWorkload uc = new ResultPersonWorkload();
										if (rs.getString("cgy") != null && !"".equals(rs.getString("cgy"))) {
											uc.setName(rs.getString("cgy"));
											uc.setNum(rs.getString("num"));
										} else {
											uc.setName("allbody");
											uc.setNum(rs.getString("num"));
										}
										return uc;
									}
								});

						return allCountList;

					}
					if ("externalperson".equals(personType)) {
						String externalpersonsql = "select externalperson,count(*)as num from VEHICLELOGIN where 1=1 and externalperson='" + person + "'";
						String gsql = " group by " + personType;
						sql = sql + externalpersonsql;
						if (startTime != null && !"".equals(startTime)) {
							String stimesql = " and VEHICLELOGIN.registertime" + ">= to_date('" + startTime + "', 'yyyy-MM-dd')";
							sql = sql + stimesql;
						}
						if (endTime != null && !"".equals(endTime)) {
							String etimesql = " and VEHICLELOGIN.registertime" + "<= to_date('" + endTime + "', 'yyyy-MM-dd')";
							sql = sql + etimesql;
						}
						sql = sql + gsql;
						System.out.println(
								"__________________________________<<<" + sql + "__________________________>>>");

						NamedParameterJdbcTemplate namedJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
						List<ResultPersonWorkload> allCountList = namedJdbcTemplate.query(sql,
								new HashMap<String, Object>(), new RowMapper<ResultPersonWorkload>() {
									@Override
									public ResultPersonWorkload mapRow(ResultSet rs, int rowNum) throws SQLException {
										ResultPersonWorkload uc = new ResultPersonWorkload();
										if (rs.getString("czy") != null && !"".equals(rs.getString("czy"))) {
											uc.setName(rs.getString("czy"));
											uc.setNum(rs.getString("num"));
										} else {
											uc.setName("allbody");
											uc.setNum(rs.getString("num"));
										}
										return uc;
									}
								});

						return allCountList;

					}

				} else {

					// pseron为空的时候
					System.out.println(
							"__________________________________<<<" + sql + "1111__________________________>>>");
					if ("cdrivercode".equals(personType)) {
						String cdrivercodesql = "select cdrivercode,count(*)as num from VEHICLELOGIN where 1=1 ";
						String gsql = " group by " + personType;
						sql = sql + cdrivercodesql;

						if (startTime != null && !"".equals(startTime)) {
							String stimesql = " and VEHICLELOGIN.registertime" + ">= to_date('" + startTime + "', 'yyyy-MM-dd')";
							sql = sql + stimesql;
						}
						if (endTime != null && !"".equals(endTime)) {
							String etimesql = " and VEHICLELOGIN.registertime" + "<= to_date('" + endTime + "', 'yyyy-MM-dd')";
							sql = sql + etimesql;
						}

						sql = sql + gsql;
						System.out.println(
								"__________________________________<<<" + sql + "__________________________>>>");

						NamedParameterJdbcTemplate namedJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
						List<ResultPersonWorkload> allCountList = namedJdbcTemplate.query(sql,
								new HashMap<String, Object>(), new RowMapper<ResultPersonWorkload>() {
									@Override
									public ResultPersonWorkload mapRow(ResultSet rs, int rowNum) throws SQLException {
										ResultPersonWorkload uc = new ResultPersonWorkload();
										if (rs.getString("cdrivercode") != null && !"".equals(rs.getString("cdrivercode"))) {
											uc.setName(rs.getString("cdrivercode"));
											uc.setNum(rs.getString("num"));
										} else {
											uc.setName("allbody");
											uc.setNum(rs.getString("num"));
										}
										return uc;
									}
								});

						return allCountList;

					}
					if ("givecheck".equals(personType)) {
						String givechecksql = "select givecheck,count(*)as num from VEHICLELOGIN where 1=1 ";
						String gsql = " group by " + personType;
						sql = givechecksql + sql;

						if (startTime != null && !"".equals(startTime)) {
							String stimesql = " and VEHICLELOGIN.registertime" + ">= to_date('" + startTime + "', 'yyyy-MM-dd')";
							sql = sql + stimesql;
						}
						if (endTime != null && !"".equals(endTime)) {
							String etimesql = " and VEHICLELOGIN.registertime" + "<= to_date('" + endTime + "', 'yyyy-MM-dd')";
							sql = sql + etimesql;
						}
						sql = sql + gsql;
						System.out.println(
								"__________________________________<<<" + sql + "__________________________>>>");

						NamedParameterJdbcTemplate namedJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
						List<ResultPersonWorkload> allCountList = namedJdbcTemplate.query(sql,
								new HashMap<String, Object>(), new RowMapper<ResultPersonWorkload>() {
									@Override
									public ResultPersonWorkload mapRow(ResultSet rs, int rowNum) throws SQLException {
										ResultPersonWorkload uc = new ResultPersonWorkload();
										if (rs.getString("givecheck") != null && !"".equals(rs.getString("givecheck"))) {
											uc.setName(rs.getString("givecheck"));
											uc.setNum(rs.getString("num"));
										} else {
											uc.setName("allbody");
											uc.setNum(rs.getString("num"));
										}
										return uc;
									}
								});

						return allCountList;

					}
					if ("djy".equals(personType)) {
						String djysql = "select djy,count(*)as num from VEHICLELOGIN where 1=1  ";
						String gsql = " group by " + personType;
						sql = sql + djysql;

						if (startTime != null && !"".equals(startTime)) {
							String stimesql = " and VEHICLELOGIN.registertime" + ">= to_date('" + startTime + "', 'yyyy-MM-dd')";
							sql = sql + stimesql;
						}
						if (endTime != null && !"".equals(endTime)) {
							String etimesql = " and VEHICLELOGIN.registertime" + "<= to_date('" + endTime + "', 'yyyy-MM-dd')";
							sql = sql + etimesql;
						}
						sql = sql + gsql;
						System.out.println(
								"__________________________________<<<" + sql + "__________________________>>>");

						NamedParameterJdbcTemplate namedJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
						List<ResultPersonWorkload> allCountList = namedJdbcTemplate.query(sql,
								new HashMap<String, Object>(), new RowMapper<ResultPersonWorkload>() {
									@Override
									public ResultPersonWorkload mapRow(ResultSet rs, int rowNum) throws SQLException {
										ResultPersonWorkload uc = new ResultPersonWorkload();
										if (rs.getString("djy") != null && !"".equals(rs.getString("djy"))) {
											uc.setName(rs.getString("djy"));
											uc.setNum(rs.getString("num"));
										} else {
											uc.setName("allbody");
											uc.setNum(rs.getString("num"));
										}
										return uc;
									}
								});

						return allCountList;

					}
					if ("externalperson".equals(personType)) {
						String externalpersonsql = "select externalperson,count(*)as num from VEHICLELOGIN where 1=1";
						String gsql = " group by " + personType;
						sql = sql + externalpersonsql;

						if (startTime != null && !"".equals(startTime)) {
							String stimesql = " and VEHICLELOGIN.registertime" + ">= to_date('" + startTime + "', 'yyyy-MM-dd')";
							sql = sql + stimesql;
						}
						if (endTime != null && !"".equals(endTime)) {
							String etimesql = " and VEHICLELOGIN.registertime" + "<= to_date('" + endTime + "', 'yyyy-MM-dd')";
							sql = sql + etimesql;
						}
						sql = sql + gsql;

						System.out.println(
								"__________________________________<<<" + sql + "__________________________>>>");

						NamedParameterJdbcTemplate namedJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
						List<ResultPersonWorkload> allCountList = namedJdbcTemplate.query(sql,
								new HashMap<String, Object>(), new RowMapper<ResultPersonWorkload>() {
									@Override
									public ResultPersonWorkload mapRow(ResultSet rs, int rowNum) throws SQLException {
										ResultPersonWorkload uc = new ResultPersonWorkload();
										if (rs.getString("externalperson") != null && !"".equals(rs.getString("externalperson"))) {
											uc.setName(rs.getString("externalperson"));
											uc.setNum(rs.getString("num"));
										} else {
											uc.setName("allbody");
											uc.setNum(rs.getString("num"));
										}
										return uc;
									}
								});

						return allCountList;

					}
					if ("cgy".equals(personType)) {
						String cgysql = "select cgy,count(*)as num from VEHICLELOGIN where 1=1 ";
						String gsql = " group by " + personType;
						sql = sql + cgysql;

						if (startTime != null && !"".equals(startTime)) {
							String stimesql = " and VEHICLELOGIN.registertime" + ">= to_date('" + startTime + "', 'yyyy-MM-dd')";
							sql = sql + stimesql;
						}
						if (endTime != null && !"".equals(endTime)) {
							String etimesql = " and VEHICLELOGIN.registertime" + "<= to_date('" + endTime + "', 'yyyy-MM-dd')";
							sql = sql + etimesql;
						}
						sql = sql + gsql;

						System.out.println(
								"__________________________________<<<" + sql + "__________________________>>>");

						NamedParameterJdbcTemplate namedJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
						List<ResultPersonWorkload> allCountList = namedJdbcTemplate.query(sql,
								new HashMap<String, Object>(), new RowMapper<ResultPersonWorkload>() {
									@Override
									public ResultPersonWorkload mapRow(ResultSet rs, int rowNum) throws SQLException {
										ResultPersonWorkload uc = new ResultPersonWorkload();
										if (rs.getString("cgy") != null && !"".equals(rs.getString("cgy"))) {
											uc.setName(rs.getString("cgy"));
											uc.setNum(rs.getString("num"));
										} else {
											uc.setName("allbody");
											uc.setNum(rs.getString("num"));
										}
										return uc;
									}
								});

						return allCountList;

					}
					if ("czy".equals(personType)) {
						String czysql = "select czy,count(*)as num from VEHICLELOGIN where 1=1 ";
						String gsql = " group by " + personType;
						sql = sql + czysql;
						if (startTime != null && !"".equals(startTime)) {
							String stimesql = " and VEHICLELOGIN.registertime" + ">= to_date('" + startTime + "', 'yyyy-MM-dd')";
							sql = sql + stimesql;
						}
						if (endTime != null && !"".equals(endTime)) {
							String etimesql = " and VEHICLELOGIN.registertime" + "<= to_date('" + endTime + "', 'yyyy-MM-dd')";
							sql = sql + etimesql;
						}
						sql = sql + gsql;
						System.out.println(
								"__________________________________<<<" + sql + "__________________________>>>");

						NamedParameterJdbcTemplate namedJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
						List<ResultPersonWorkload> allCountList = namedJdbcTemplate.query(sql,
								new HashMap<String, Object>(), new RowMapper<ResultPersonWorkload>() {
									@Override
									public ResultPersonWorkload mapRow(ResultSet rs, int rowNum) throws SQLException {
										ResultPersonWorkload uc = new ResultPersonWorkload();
										if (rs.getString("czy") != null && !"".equals(rs.getString("czy"))) {
											uc.setName(rs.getString("czy"));
											uc.setNum(rs.getString("num"));
										} else {
											uc.setName("allbody");
											uc.setNum(rs.getString("num"));
										}
										return uc;
									}
								});

						return allCountList;

					}

				}

			}

		} catch (Exception e) {
			log.error(SystemInfo.LOG_CODE_ERROR, e);
			return null;
		}
		return null;
	}
	/**
	 * 
	* @Title: countPersonWorkload 
	* @Description: 统计人员工作量信息
	* @param rylx 类型 验车员 外检员
	* @param startTime
	* @param endTime
	* @param charged 是否收费
	* @param checktype 检测类别
	* @param checkresult 检测结果
	* @param currentPage 当前页面
	* @return
	 */
	@Transactional
	public PageList<ResultPersonWorkload> countPersonWorkload(String rylx, String startTime, String endTime,
			String charged, String checktype, String checkresult, int currentPage) {

		try {
			String rylxCol = "";
			String chargeCol = "";
			String tchargeCol = "";
			String startTimeCol = "";
			String tstartTimeCol = "";
			String endTimeCol = "";
			String tendTimeCol = "";
			String checktypeCol = "";
			String tchecktypeCol = "";
			String checkresultCol = "";
			String tcheckresultCol = "";
			String rystr = "";

			if ("cdrivercode".equals(rylx)) {//验车员
				rylxCol = "t.cdrivercode";
				rystr = " y.cdrivercode=t.cdrivercode ";
			} else if ("externalperson".equals(rylx)) {//外检员
				rylxCol = "t.externalperson";
				rystr = " y.externalperson=t.externalperson ";
			}
			if ("0".equals(charged)) {
				chargeCol = " and y.charge!='1' ";
				tchargeCol = " and t.charge!='1' ";
			} else if ("1".equals(charged)) {
				chargeCol = " and y.charge='1' ";
				tchargeCol = " and t.charge='1' ";
			}
			// 检测类别
			if (!StringUtils.isEmpty(checktype)) {
				checktypeCol = " and y.checktype=" + checktype;
				tchecktypeCol = " and t.checktype=" + checktype;
			}
			// 检测结果
			if (!StringUtils.isEmpty(checkresult)) {
				checkresultCol = " and y.checkresult=" + checkresult;
				tcheckresultCol = " and t.checkresult=" + checkresult;
			}
			// 检测时间
			if (!StringUtils.isEmpty(startTime)) {
				startTimeCol = " and y.registertime " + ">= to_date('" + startTime + "', 'yyyy-MM-dd hh24:mi:ss') ";
				tstartTimeCol = " and t.registertime " + ">= to_date('" + startTime + "', 'yyyy-MM-dd hh24:mi:ss') ";
			}
			if (!StringUtils.isEmpty(endTime)) {
				endTimeCol = " and y.registertime " + "<= to_date('" + endTime + "', 'yyyy-MM-dd hh24:mi:ss') ";
				tendTimeCol = " and t.registertime " + "<= to_date('" + endTime + "', 'yyyy-MM-dd hh24:mi:ss') ";
			}

			String ddsSql = ", (select count(*) from VEHICLELOGIN y where y.checkstate='5'" + chargeCol + startTimeCol
					+ endTimeCol + checktypeCol + checkresultCol + " and y.ext_col10='0' and " + rystr + ") as dds, ";
			String sdsSql = " (select count(*) from VEHICLELOGIN y where y.checkstate='5'" + chargeCol + startTimeCol + endTimeCol
					+ checktypeCol + checkresultCol + " and y.ext_col10='1' and " + rystr + ") as sds, ";
			String wtgkSql = " (select count(*) from VEHICLELOGIN y where y.checkstate='5'" + chargeCol + startTimeCol
					+ endTimeCol + checktypeCol + checkresultCol + " and y.ext_col10='2' and " + rystr + ") as wtgk, ";
			String jystSql = " (select count(*) from VEHICLELOGIN y where y.checkstate='5'" + chargeCol + startTimeCol
					+ endTimeCol + checktypeCol + checkresultCol + " and y.ext_col10='3' and " + rystr + ") as jyst, ";
			String jzjsSql = " (select count(*) from VEHICLELOGIN y where y.checkstate='5'" + chargeCol + startTimeCol
					+ endTimeCol + checktypeCol + checkresultCol + " and y.ext_col10='4' and " + rystr + ") as jzjs, ";
			String btgSql = " (select count(*) from VEHICLELOGIN y where y.checkstate='5'" + chargeCol + startTimeCol + endTimeCol
					+ checktypeCol + checkresultCol + " and y.ext_col10='5' and " + rystr + ") as btg, ";
			String lzydSql = " (select count(*) from VEHICLELOGIN y where y.checkstate='5'" + chargeCol + startTimeCol
					+ endTimeCol + checktypeCol + checkresultCol + " and y.ext_col10='6' and " + rystr + ") as lzyd, ";

			String sql = "select " + rylxCol + ddsSql + sdsSql + wtgkSql + jystSql + jzjsSql + btgSql + lzydSql
					+ " count(*) as num from VEHICLELOGIN t where 1=1 and " + rylxCol + " is not null and t.checkstate='5' "
					+ tchargeCol + tstartTimeCol + tendTimeCol + tchecktypeCol + tcheckresultCol + " group by " + rylxCol;
            System.out.println("sql------------------"+sql);
			PageList<ResultPersonWorkload> pageList = new PageList<ResultPersonWorkload>();
			pageList.setCurrentPage(currentPage);
			pageList.setPageSize(300);

			NamedParameterJdbcTemplate namedJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);

			SqlParameterSource param = new BeanPropertySqlParameterSource(new VEHICLELOGIN());
			StringBuilder sqlSelect = new StringBuilder();
			sqlSelect.append("select count(distinct ");
			sqlSelect.append(rylxCol + ") from VEHICLELOGIN t where ");
			sqlSelect.append(rylxCol + " is not null and t.checkstate='5' " + tchargeCol + tstartTimeCol + tendTimeCol);
			int totalSize = namedJdbcTemplate.queryForObject(sqlSelect.toString(), param, Integer.class);
			pageList.setTotalSize(totalSize);

			List<ResultPersonWorkload> allCountList = namedJdbcTemplate.query(
					SqlUtil.paginationSql(sql, pageList.getFromPos(), pageList.getPageSize()),
					new HashMap<String, Object>(), new RowMapper<ResultPersonWorkload>() {
						@Override
						public ResultPersonWorkload mapRow(ResultSet rs, int rowNum) throws SQLException {
							ResultPersonWorkload uc = new ResultPersonWorkload();
							uc.setName(rs.getString(rylx));
							uc.setDdsNum(rs.getString("dds"));
							uc.setSdsNum(rs.getString("sds"));
							uc.setWtgkNum(rs.getString("wtgk"));
							uc.setJystNum(rs.getString("jyst"));
							uc.setJzjsNum(rs.getString("jzjs"));
							uc.setBtgNum(rs.getString("btg"));
							uc.setLzydNum(rs.getString("lzyd"));
							uc.setNum(rs.getString("num"));
							return uc;
						}
					});
			pageList.setList(allCountList);
			return pageList;

		} catch (Exception e) {
			log.error(SystemInfo.LOG_CODE_ERROR, e);
			return null;
		}

	}

}
