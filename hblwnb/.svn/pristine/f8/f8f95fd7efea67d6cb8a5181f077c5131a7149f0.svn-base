/**
 * 
 */
package com.vkl.hblw.common.bean.user;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

/**
 * @author xcc
 *
 */
public class UserSessionBean {

	public static final String SESSION_USER_KEY = "sessionUser";
	
	private String userId = null;
	private String userName = null;
	private String loginTime = null;
	private String idCardNo = null;
	private String mobile = null;	
	private String ipAddress = null;
	private String token = null;
	
	private ArrayList<String> myPrivileges = null;

	private final static Gson gson = new Gson();

	public String toString() {
		return gson.toJson(this);
	}

	public boolean havePrivilege(String... privilege) {
		if (myPrivileges == null) {
			return false;
		}
		if (privilege == null) {
			return false;
		}
		for (String s : myPrivileges) {
			for (String s2 : privilege) {
				if (s.equals(s2)) {
					return true;
				}
			}
		}
		return false;
	}

	public static UserSessionBean getMe(HttpSession sess) {
		return (UserSessionBean) sess.getAttribute(SESSION_USER_KEY);
	}

	public static String getLoginTime(HttpSession sess) {
		UserSessionBean userBean = (UserSessionBean) sess
				.getAttribute(SESSION_USER_KEY);
		if (userBean == null) {
			return null;
		} else {
			return userBean.loginTime;
		}
	}

	public static String getUserId(HttpSession sess) {
		UserSessionBean userBean = (UserSessionBean) sess
				.getAttribute(SESSION_USER_KEY);
		if (userBean == null) {
			return null;
		} else {
			return userBean.getUserId();
		}
	}

	public static String getUserName(HttpSession sess) {
		UserSessionBean userBean = (UserSessionBean) sess
				.getAttribute(SESSION_USER_KEY);
		if (userBean == null) {
			return null;
		} else {
			return userBean.getUserName();
		}
	}

	public static String getIpAddress(HttpSession sess) {
		UserSessionBean userBean = (UserSessionBean) sess
				.getAttribute(SESSION_USER_KEY);
		if (userBean == null) {
			return null;
		} else {
			return userBean.getIpAddress();
		}
	}

	
	public static String getIdCardNo(HttpSession sess) {
		UserSessionBean userBean = (UserSessionBean) sess
				.getAttribute(SESSION_USER_KEY);
		if (userBean == null) {
			return null;
		} else {
			return userBean.getIdCardNo();
		}
	}
	
	public static String getMobile(HttpSession sess) {
		UserSessionBean userBean = (UserSessionBean) sess
				.getAttribute(SESSION_USER_KEY);
		if (userBean == null) {
			return null;
		} else {
			return userBean.getMobile();
		}
	}
	
	public String getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}


	public String getIdCardNo() {
		return idCardNo;
	}

	public void setIdCardNo(String idCardNo) {
		this.idCardNo = idCardNo;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public ArrayList<String> getMyPrivileges() {
		return myPrivileges;
	}

	public void setMyPrivileges(ArrayList<String> myPrivileges) {
		this.myPrivileges = myPrivileges;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
