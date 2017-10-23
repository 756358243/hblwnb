/**
 * 
 */
package com.vkl.hblw.common.constant;

import java.util.Map;
import java.util.TreeMap;

/**
 * @author xcc
 *
 */
public class CustomData {

	/**
	 * dlxx登入信息中的车辆检验状态常量  
	 */
	public static final String DLXX_STATUS_QUARANTINE = "1";// 已登记待检
	public static final String DLXX_STATUS_VISUAL = "2";// 已外检
	public static final String DLXX_STATUS_PROCESS = "3";// 已上传检验过程
	public static final String DLXX_STATUS_RESULT = "4";// 已上传检验结果
	public static final String DLXX_STATUS_SUBMIT = "5";// 已提交
	public static final String DLXX_STATUS_RETIREMENT = "6";// 已退办

	/**
	 * 检验方法
	 */
	public static final String JYFF_SDS = "1";// 双怠速法
	public static final String JYFF_JYST = "3";// 简易瞬态工况法
	public static final String JYFF_JZJS = "4";// 加载减速工况法
	public static final String JYFF_ZYJSBTG = "5";// 不透光烟度法
	


	/**
	 * 照片nodecode编号
	 */
	public static final String NODECODE_PICTURE_DJ = "1001";// 登记模块代码
	public static final String NODECODE_PICTURE_WJ = "1002";// 外检模块代码
	public static final String NODECODE_PICTURE_SX = "1004";// 上线检模块代码
	/**
	 * 照片cursor编号
	 */
	public static final String CURSOR_PICTURE_DJ_XSZ = "01";
	public static final String CURSOR_PICTURE_DJ_SFZ = "04";
	public static final String CURSOR_PICTURE_DJ_WTZM = "05";
	public static final String CURSOR_PICTURE_DJ_BGJCFFZM = "06";
	public static final String CURSOR_PICTURE_DJ_KZZM = "07";
	public static final String CURSOR_PICTURE_DJ_WXZM = "08";
	public static final String CURSOR_PICTURE_DJ_CZWTZM = "09";
	public static final String CURSOR_PICTURE_DJ_JYWTHT = "10";
	
	public static final String CURSOR_PICTURE_WJ_FRONT = "01";
	public static final String CURSOR_PICTURE_WJ_BACK = "02";
	public static final String CURSOR_PICTURE_WJ_CJH = "03";
	public static final String CURSOR_PICTURE_WJ_FDJH = "04";
	public static final String CURSOR_PICTURE_WJ_YBPLC = "05";
	public static final String CURSOR_PICTURE_WJ_UNDER = "06";
	
	public static final String CURSOR_PICTURE_SX_FRONT = "01";
	public static final String CURSOR_PICTURE_SX_BACK = "02";
	
	/**
	 * 视频nodecode编号
	 */
	public static final String NODECODE_VIDEO_WJ = "1002";// 外检模块代码
	public static final String NODECODE_VIDEO_SX = "1004";// 上线检模块代码
	/**
	 * 视频cursor编号
	 */
	public static final String CURSOR_VIDEO_WJ_FRONT = "01";
	public static final String CURSOR_VIDEO_WJ_BACK = "02";
	public static final String CURSOR_VIDEO_WJ_UNDER = "03";
	public static final String CURSOR_VIDEO_SX_FRONT = "04";
	public static final String CURSOR_VIDEO_SX_BACK = "01";
	public static final String CURSOR_VIDEO_SX_MOVE = "02";
	/**
	 * 视频是否下载编号
	 */
	public static final String VIDEO_FLAG_UNDOWNLOAD = "0";
	public static final String VIDEO_FLAG_ERROR = "1";
	public static final String VIDEO_FLAG_DOWNLOADED = "2";
	
	/**
	 * 监控配置
	 */
	public static final String JKPZ_WJ_FRONT = "2";// 外检前
	public static final String JKPZ_WJ_BACK = "3";// 外检后
	public static final String JKPZ_WJ_UNDER = "4";// 外检底盘
	public static final String JKPZ_SX_FRONT = "0";// 上线前
	public static final String JKPZ_SX_BACK = "1";// 上线后
	public static final String JKPZ_SX_MOVE = "5";// 上线移动
	
	public final static Map<String, String> SP_MONITOR_CONFIG = new TreeMap<String, String>();

	static {
		
		//视频对应视频配置通道
		SP_MONITOR_CONFIG.put(NODECODE_VIDEO_WJ + CURSOR_VIDEO_WJ_FRONT, JKPZ_WJ_FRONT);
		SP_MONITOR_CONFIG.put(NODECODE_VIDEO_WJ + CURSOR_VIDEO_WJ_BACK, JKPZ_WJ_BACK);
		SP_MONITOR_CONFIG.put(NODECODE_VIDEO_WJ + CURSOR_VIDEO_WJ_UNDER, JKPZ_WJ_UNDER);
		SP_MONITOR_CONFIG.put(NODECODE_VIDEO_SX + CURSOR_VIDEO_SX_FRONT, JKPZ_SX_FRONT);
		SP_MONITOR_CONFIG.put(NODECODE_VIDEO_SX + CURSOR_VIDEO_SX_BACK, JKPZ_SX_BACK);
		SP_MONITOR_CONFIG.put(NODECODE_VIDEO_SX + CURSOR_VIDEO_SX_MOVE, JKPZ_SX_MOVE);
	}
	
	public final static Map<String, String> ZP_MONITOR_CONFIG = new TreeMap<String, String>();
	static {
		//照片对应视频配置通道
		ZP_MONITOR_CONFIG.put(NODECODE_PICTURE_WJ + CURSOR_PICTURE_WJ_UNDER, JKPZ_WJ_UNDER);
		ZP_MONITOR_CONFIG.put(NODECODE_PICTURE_SX + CURSOR_PICTURE_SX_FRONT, JKPZ_SX_FRONT);
		ZP_MONITOR_CONFIG.put(NODECODE_PICTURE_SX + CURSOR_PICTURE_SX_BACK, JKPZ_SX_BACK);
		
	}
	/**
	 * 汇银上传视频接口 标记字段
	 */
	public static final String VIDEO_API_FLAG_START = "1";// 开始
	public static final String VIDEO_API_FLAG_END = "2";// 结束
	
	/**
	 * 登录t_dlxx表中 charge收费字段
	 */
	public static final String DLXX_CHARGE_UNPAY = "0";// 未收费
	public static final String DLXX_CHARGE_PAYED = "1";// 已收费
	/**
	 *  判定结果
	 */
	public static final String JCJG_TRUE = "1";// 合格
	public static final String JCJG_FALSE = "0";// 不合格
	
	/**
	 * 登录TJYSCJG表中 字段
	 */
	public static final String TJYSCJG_FLAG_PICTURE = "zp";
	public static final String TJYSCJG_FLAG_VIDEO = "sp";
	
	public final static Map<String, String> TJYSCJG_FIELD_CONFIG = new TreeMap<String, String>();
	/*static {
		TJYSCJG_FIELD_CONFIG.put(TJYSCJG.FLD_dlxxsc, TJYSCJG.SQL_dlxxsc);
		TJYSCJG_FIELD_CONFIG.put(TJYSCJG.FLD_wjxxsc, TJYSCJG.SQL_wjxxsc);
		TJYSCJG_FIELD_CONFIG.put(TJYSCJG.FLD_jygcxxsc, TJYSCJG.SQL_jygcxxsc);
		TJYSCJG_FIELD_CONFIG.put(TJYSCJG.FLD_jyjgxxsc, TJYSCJG.SQL_jyjgxxsc);
		TJYSCJG_FIELD_CONFIG.put(TJYSCJG.FLD_jyxxtj, TJYSCJG.SQL_jyxxtj);
		
		TJYSCJG_FIELD_CONFIG.put(TJYSCJG_FLAG_PICTURE + NODECODE_PICTURE_DJ + CURSOR_PICTURE_DJ_XSZ, TJYSCJG.SQL_djzp01);
		TJYSCJG_FIELD_CONFIG.put(TJYSCJG_FLAG_PICTURE + NODECODE_PICTURE_DJ + CURSOR_PICTURE_DJ_SFZ, TJYSCJG.SQL_djzp04);
		TJYSCJG_FIELD_CONFIG.put(TJYSCJG_FLAG_PICTURE + NODECODE_PICTURE_DJ + CURSOR_PICTURE_DJ_WTZM, TJYSCJG.SQL_djzp05);
		TJYSCJG_FIELD_CONFIG.put(TJYSCJG_FLAG_PICTURE + NODECODE_PICTURE_DJ + CURSOR_PICTURE_DJ_BGJCFFZM, TJYSCJG.SQL_djzp06);
		TJYSCJG_FIELD_CONFIG.put(TJYSCJG_FLAG_PICTURE + NODECODE_PICTURE_DJ + CURSOR_PICTURE_DJ_KZZM, TJYSCJG.SQL_djzp07);
		TJYSCJG_FIELD_CONFIG.put(TJYSCJG_FLAG_PICTURE + NODECODE_PICTURE_DJ + CURSOR_PICTURE_DJ_WXZM, TJYSCJG.SQL_djzp08);
		TJYSCJG_FIELD_CONFIG.put(TJYSCJG_FLAG_PICTURE + NODECODE_PICTURE_DJ + CURSOR_PICTURE_DJ_CZWTZM, TJYSCJG.SQL_djzp09);
		TJYSCJG_FIELD_CONFIG.put(TJYSCJG_FLAG_PICTURE + NODECODE_PICTURE_DJ + CURSOR_PICTURE_DJ_JYWTHT, TJYSCJG.SQL_djzp10);
		
		TJYSCJG_FIELD_CONFIG.put(TJYSCJG_FLAG_PICTURE + NODECODE_PICTURE_WJ + CURSOR_PICTURE_WJ_FRONT, TJYSCJG.SQL_wjzp01);
		TJYSCJG_FIELD_CONFIG.put(TJYSCJG_FLAG_PICTURE + NODECODE_PICTURE_WJ + CURSOR_PICTURE_WJ_BACK, TJYSCJG.SQL_wjzp02);
		TJYSCJG_FIELD_CONFIG.put(TJYSCJG_FLAG_PICTURE + NODECODE_PICTURE_WJ + CURSOR_PICTURE_WJ_CJH, TJYSCJG.SQL_wjzp03);
		TJYSCJG_FIELD_CONFIG.put(TJYSCJG_FLAG_PICTURE + NODECODE_PICTURE_WJ + CURSOR_PICTURE_WJ_FDJH, TJYSCJG.SQL_wjzp04);
		TJYSCJG_FIELD_CONFIG.put(TJYSCJG_FLAG_PICTURE + NODECODE_PICTURE_WJ + CURSOR_PICTURE_WJ_YBPLC, TJYSCJG.SQL_wjzp05);
		TJYSCJG_FIELD_CONFIG.put(TJYSCJG_FLAG_PICTURE + NODECODE_PICTURE_WJ + CURSOR_PICTURE_WJ_UNDER, TJYSCJG.SQL_wjzp06);
		
		TJYSCJG_FIELD_CONFIG.put(TJYSCJG_FLAG_PICTURE + NODECODE_PICTURE_SX + CURSOR_PICTURE_SX_FRONT, TJYSCJG.SQL_sxzp01);
		TJYSCJG_FIELD_CONFIG.put(TJYSCJG_FLAG_PICTURE + NODECODE_PICTURE_SX + CURSOR_PICTURE_SX_BACK, TJYSCJG.SQL_sxzp02);
		
		TJYSCJG_FIELD_CONFIG.put(TJYSCJG_FLAG_VIDEO + NODECODE_VIDEO_WJ + CURSOR_VIDEO_WJ_FRONT, TJYSCJG.SQL_wjsp01);
		TJYSCJG_FIELD_CONFIG.put(TJYSCJG_FLAG_VIDEO + NODECODE_VIDEO_WJ + CURSOR_VIDEO_WJ_BACK, TJYSCJG.SQL_wjsp02);
		TJYSCJG_FIELD_CONFIG.put(TJYSCJG_FLAG_VIDEO + NODECODE_VIDEO_WJ + CURSOR_VIDEO_WJ_UNDER, TJYSCJG.SQL_wjsp03);
		
		TJYSCJG_FIELD_CONFIG.put(TJYSCJG_FLAG_VIDEO + NODECODE_VIDEO_SX + CURSOR_VIDEO_SX_FRONT, TJYSCJG.SQL_sxsp04);
		TJYSCJG_FIELD_CONFIG.put(TJYSCJG_FLAG_VIDEO + NODECODE_VIDEO_SX + CURSOR_VIDEO_SX_BACK, TJYSCJG.SQL_sxsp01);
		TJYSCJG_FIELD_CONFIG.put(TJYSCJG_FLAG_VIDEO + NODECODE_VIDEO_SX + CURSOR_VIDEO_SX_MOVE, TJYSCJG.SQL_sxsp02);
		
	}*/
	
	/**
	 *  用户权限
	 */
	
	public static final String USER_PRIVILEGE_NORMAL = "0";// 普通用户
	public static final String USER_PRIVILEGE_ADMIN = "1";// 管理员用户
	
	/**
	 *  燃料种类
	 */	
	public static final String RLZL_GASOLINE = "A";// 普通用户
	
	
	public static final String PICTURE_W_TYPE = "1";// 外检图片
	public static final String PICTURE_S_TYPE = "2";// 上传检测图片
	
	
	public static final String VIDEO_W_TYPE = "1";// 外检视频
	public static final String VIDEO_S_TYPE = "2";// 上传检测视频
	
	public static final String VIDEO_W_TIMES= "2";// 外检视频插入次数
	
	public static final String VIDEO_CODE1 = "1";// 视频编号
	public static final String VIDEO_CODE2 = "2";// 视频编号
	public static final String VIDEO_CODE3 = "3";// 视频编号
	public static final String VIDEO_CODE4 = "4";// 视频编号
	public static final String VIDEO_CODE5= "5";// 视频编号
	public static final String VIDEO_CODE6 = "6";// 视频编号
	public static final String VIDEO_CODE7 = "7";// 视频编号
	public static final String VIDEO_CODE8 = "8";// 视频编号
	

	
}
