/** 
 * Project Name:hblw_nb 
 * File Name:PICTUREDao.java 
 * Package Name:com.vkl.hblw.dao.persistence 
 * Date:2017年10月13日下午2:51:35 
 * Copyright (c) 2017, chenzhou1025@126.com All Rights Reserved. 
 * 
*/  
  
package com.vkl.hblw.dao.persistence;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.vkl.hblw.dao.domain.PICTURE;
import com.vkl.hblw.dao.domain.VEHICLELOGIN;
import com.vkl.hblw.dao.persistence.base.PICTUREBaseDao;
import com.vkl.hblw.dao.util.SqlSelect;
import com.vkl.hblw.dao.util.SqlUpdate;


/** 
* @ClassName: PICTUREDao 
* @Description: 图片表的DAO扩充类 
* @author aiyun 
* @date 2017年10月13日 下午2:51:38 
*  
*/
public class PICTUREDao extends PICTUREBaseDao {

	@Autowired
	private VEHICLELOGINDao vEHICLELOGINDao;// 车辆状态信息信息
	
	
	
	
	
	/** 
	* @Title: insertOrUpdatePicture 
	* @Description: 插入或者更新图片
	* @param CCHECKCODE
	* @param CPREFACECHECKID
	* @param CVEHICLECODE
	* @param CUNITCODE
	* @param PICTURETYPE
	* @param PICTURENUMBER
	* @param PICTUREURL
	* @param PICTUREUPTIME
	* @param CNUMBERPLATE
	* @return    设定文件 
	* @return int    返回类型 
	* @throws 
	*/
	public int insertOrUpdatePicture(String CCHECKCODE,String CPREFACECHECKID,String CVEHICLECODE,String CUNITCODE,String PICTURETYPE,String PICTURENUMBER,String PICTUREURL,Timestamp PICTUREUPTIME,String CNUMBERPLATE){
		PICTURE picture = new PICTURE();
		picture = queryPictureByCCHECKCODE(CCHECKCODE, CPREFACECHECKID, PICTURETYPE, PICTURENUMBER);
		if(null == picture){
			//1.1如果图片表里面没有值，那么查看是否已经登录了
			VEHICLELOGIN vehiclelogin = new VEHICLELOGIN();
			vehiclelogin = vEHICLELOGINDao.queryByCcheckcode(CCHECKCODE);
			if(null == vehiclelogin){
				return 1;
			}
			//1.2登录了的车辆，但是图片数据库没有记录的直接插入数据
			PICTURE insertPIC = new PICTURE();
			insertPIC.setCcheckcode(CCHECKCODE);
			insertPIC.setCprefacecheckid(CPREFACECHECKID);
			insertPIC.setCvehiclecode(CVEHICLECODE);
			insertPIC.setCunitcode(CUNITCODE);
			insertPIC.setPicturetype(PICTURETYPE);
			insertPIC.setPicturenumber(PICTURENUMBER);
			insertPIC.setPictureurl(PICTUREURL);
			insertPIC.setPictureuptime(PICTUREUPTIME);
			//第一次插入的数据设置插入次数为1；
			Integer PICTUREUPLOADTIMES =0;
			insertPIC.setPictureuploadtimes(PICTUREUPLOADTIMES);
			insertPIC.setCnumberplate(CNUMBERPLATE);
			insert(insertPIC);
		}else{
			//2.数据库里面查询到对应的图片记录，则进行更新即可
			picture.setCvehiclecode(CVEHICLECODE);
			picture.setCunitcode(CUNITCODE);
			picture.setPictureurl(PICTUREURL);
			picture.setPictureuptime(PICTUREUPTIME);
			picture.setPictureuploadtimes(picture.getPictureuploadtimes()+1);
			picture.setCnumberplate(CNUMBERPLATE);
			SqlUpdate sqlUpdate = new SqlUpdate().addColumn(PICTURE.SQL_cvehiclecode).addColumn(PICTURE.SQL_cunitcode)
					.addColumn(PICTURE.SQL_pictureurl).addColumn(PICTURE.SQL_pictureuptime).addColumn(PICTURE.SQL_pictureuploadtimes)
					.addColumn(PICTURE.SQL_cnumberplate);
			update(sqlUpdate, picture);
			
		}
		
		return 0;
	}
	
	
	
	
	/** 
	* @Title: queryPictureByCCHECKCODE 
	* @Description: 根据流水号,外观检测号，检测类型，检测编号，查询一张图片信息 
	* @param ccheckcode
	* @return    设定文件 
	* @return PICTURE    返回类型 
	* @throws 
	*/
	public PICTURE queryPictureByCCHECKCODE(String CCHECKCODE,String CPREFACECHECKID,String PICTURETYPE,String PICTURENUMBER){
		PICTURE picture = new PICTURE();
		picture.setCcheckcode(CCHECKCODE);
		picture.setCprefacecheckid(CPREFACECHECKID);
		picture.setPicturetype(PICTURETYPE);
		picture.setPicturenumber(PICTURENUMBER);
		SqlSelect sqlSelect = new SqlSelect().where(PICTURE.SQL_ccheckcode).and(PICTURE.SQL_cprefacecheckid).and(PICTURE.SQL_picturetype).and(PICTURE.SQL_picturenumber);
		picture = load(sqlSelect, picture);
		return picture;
	}




	public List<PICTURE> listByCprefacecheckid(String cprefacecheckid, String picturetypeDj) {
		PICTURE picture = new PICTURE();
		picture.setPicturetype(picturetypeDj);
		SqlSelect sql = new SqlSelect().where(PICTURE.SQL_cprefacecheckid).and(PICTURE.SQL_picturetype);
		List<PICTURE> pictureList = list(sql, picture);
		return pictureList;
	}
	
	
	
	
}
  