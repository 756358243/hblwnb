package com.vkl.hblw.common.constant;

/** 
* @ClassName: ResultDate 
* @Description: 用于存放所有不理想的返回值 
* @author aiyun
* @date 2017年10月17日 下午2:57:04 
*  
*/
public class ResultDate {
	
	/**
	 * PDA接口返回值常量
	 */
	public static final String RS_PDA_VEHICLELOGIN_NULL = "查询车辆状态表时，没有查到记录...."; //车辆状态表为空 
	public static final String RS_PDA_TPREFACECHECK_NULL = "查询外检信息表时，没有查到记录...."; //外检信息表为空 
	public static final String RS_PDA_SECCESS = "执行成功...."; //成功
	public static final String RS_PDA_CCHKDEVICECODE_NULL = "underpanStart()的CCHKDEVICECODE为null"; //CCHKDEVICECODE
	public static final String RS_PDA_ORIGINALVIDEO_NULL = "underpanEnd()的ORIGINALVIDEO为null"; //CCHKDEVICECODE

	
	
	/**
	 * PDA方法调用异常
	 */
	public static final String METHOD_PDA_ERROR = "外检开始TprefaceCheckStart()执行的时候抛出异常...."; //外检开始 
	public static final String METHOD_insertOrUpdatePicture_PDA_ERROR = "外检开始insertOrUpdatePicture()执行的时候抛出异常...."; //外检开始 
	public static final String METHOD_insertUnderpan_PDA_ERROR = "底盘开始检查insertUnderpanStart()执行的时候抛出异常...."; //底盘开始 
	public static final String METHOD_insertUnderpanEnd_PDA_ERROR = "底盘结束检查insertUnderpanEnd()执行的时候抛出异常...."; //底盘结束 
	public static final String METHOD_updateTpefacecheck_PDA_ERROR = "执行dao层updateTpefacecheck()插入外检表数据的时候抛出异常...."; 
	public static final String METHOD_updateVehiclelogin_PDA_ERROR = "执行dao层updateVehiclelogin()更新车辆状态表数据的时候抛出异常...."; 
    public static final String METHOD_insertOriginalvideo_PDA_ERROR = "执行dao层insertOriginalvideo()更新原始视频表数据的时候抛出异常...."; 

	
}