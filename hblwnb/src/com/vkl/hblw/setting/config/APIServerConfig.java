/**
 * 
 */
package com.vkl.hblw.setting.config;

import java.util.HashMap;

/**
 * @author xcc
 *
 */
public class APIServerConfig {

	String queryAPIAddress;//查询接口地址
	String uploadAPIAddress;//上传接口地址
	String fileAPIAddress;//附件接口地址
	HashMap<String, String> jkidMap;
	 
	public void init() {

	} 
	
	/**
	 * @return the jkidMap
	 */
	public HashMap<String, String> getJkidMap() {
		return jkidMap;
	}
 
	/**
	 * @param jkidMap the jkidMap to set
	 */
	public void setJkidMap(HashMap<String, String> jkidMap) {
		this.jkidMap = jkidMap;
	}

	/**
	 * @return the queryAPIAddress
	 */
	public String getQueryAPIAddress() {
		return queryAPIAddress;
	}
	/**
	 * @param queryAPIAddress the queryAPIAddress to set
	 */
	public void setQueryAPIAddress(String queryAPIAddress) {
		this.queryAPIAddress = queryAPIAddress;
	}
	/**
	 * @return the uploadAPIAddress
	 */
	public String getUploadAPIAddress() {
		return uploadAPIAddress;
	}
	/**
	 * @param uploadAPIAddress the uploadAPIAddress to set
	 */
	public void setUploadAPIAddress(String uploadAPIAddress) {
		this.uploadAPIAddress = uploadAPIAddress;
	}
	/**
	 * @return the fileAPIAddress
	 */
	public String getFileAPIAddress() {
		return fileAPIAddress;
	}
	/**
	 * @param fileAPIAddress the fileAPIAddress to set
	 */
	public void setFileAPIAddress(String fileAPIAddress) {
		this.fileAPIAddress = fileAPIAddress;
	}
	
}
