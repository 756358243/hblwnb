/**
 * 
 */
package com.vkl.hblw.common.util.hikvision;

import java.io.File;
import java.util.UUID;

import org.apache.log4j.Logger;

import com.sun.jna.NativeLong;
import com.vkl.hblw.common.constant.SystemInfo;
import com.vkl.hblw.common.util.file.FileOperate;
import com.vkl.hblw.common.util.hikvision.HCNetSDK.NET_DVR_JPEGPARA;

/**
 * @author xcc 摄像机抓拍照片
 */
public class CameraCapture {

	private static final Logger log = Logger.getLogger(CameraCapture.class);

	private String deviceIP;
	private int devicePort;
	private String username;
	private String password;
	private HCNetSDK hCNetSDK;
	private NativeLong lUserID;

	public static void main(String args[]) {

		for(int i = 0; i < 100; i++){
		CameraCapture ca = new CameraCapture("172.16.10.169", 8000, "admin", "admin12345");
		String picpath = "C:\\Users\\VKL\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\picture\\";
		String picPropath = picpath + "tes_02_12412125";
		
		boolean result = ca.capture(picpath, picPropath, "test1234.jpeg");
		System.out.print(result);
		}
		/*log.error("relust:" + hCNetSDK.NET_DVR_GetLastError());*/
	}

	public CameraCapture(String deviceIP, int devicePort, String username, String password) {
		this.deviceIP = deviceIP;
		this.devicePort = devicePort;
		this.username = username;
		this.password = password;
		hCNetSDK = HCNetSDK.INSTANCE;
	}

	/**
	 * 抓拍照片
	 * 
	 * @param
	 * 
	 */
	public boolean capture(String picRootPath, String picPrePath, String uploadFileName) {

		try {
			init();
			setConnect();

			if (Login()) {

				NET_DVR_JPEGPARA jpeginfo = new NET_DVR_JPEGPARA();
				jpeginfo.wPicQuality = 2;
				jpeginfo.wPicSize = 0;

				NativeLong tup = new NativeLong(1L);

				File fileDirc = new File(picPrePath);
				if (!fileDirc.exists() && !fileDirc.isDirectory()) {
					fileDirc.mkdir();
				}

				String uploadPath = picPrePath + "\\" + uploadFileName;

				UUID uuid = UUID.randomUUID();
				String tempFilePath = picRootPath + uuid.toString() + ".jpeg";
				if (hCNetSDK.NET_DVR_CaptureJPEGPicture(lUserID, tup, jpeginfo, tempFilePath)) {
					FileOperate.copyFile(tempFilePath, uploadPath);
					FileOperate.delFile(tempFilePath);
					hCNetSDK.NET_DVR_Logout_V30(lUserID);
					hCNetSDK.NET_DVR_Cleanup();
				}else{
					log.error("hCNetSDK error:" + hCNetSDK.NET_DVR_GetLastError());
					hCNetSDK.NET_DVR_Logout_V30(lUserID);
					hCNetSDK.NET_DVR_Cleanup();
					return false;
				}
				
			} else {
				return false;
			}
		} catch (Exception e) {
			log.error(SystemInfo.LOG_CODE_ERROR, e);
			return false;
		}
		
		return true;
	}

	/**
	 * 
	 * 初始化
	 * 
	 * @param
	 */
	private void init() {

		boolean initSuc = hCNetSDK.NET_DVR_Init();
		if (initSuc != true) {
			log.error("摄像机设备初始化失败");
		}
	}

	/**
	 * 
	 * 设置连接事件与重连时间
	 * 
	 * @param
	 */

	private void setConnect() {
		hCNetSDK.NET_DVR_SetConnectTime(2000, 1);
		hCNetSDK.NET_DVR_SetReconnect(10000, true);
	}

	/**
	 * 
	 * 注册摄像机设备
	 * 
	 * @param
	 */

	public Boolean Login() {
		HCNetSDK.NET_DVR_DEVICEINFO_V30 m_strDeviceInfo;// 设备信息
		m_strDeviceInfo = new HCNetSDK.NET_DVR_DEVICEINFO_V30();

		lUserID = hCNetSDK.NET_DVR_Login_V30(deviceIP, (short) devicePort, username, password,
				m_strDeviceInfo);

		long userID = lUserID.longValue();
		if (userID < 0) {
			log.error("Login Failed! Error number：" + hCNetSDK.NET_DVR_GetLastError());

			hCNetSDK.NET_DVR_Cleanup();
			return false;
		} else {
			log.info("Login Successfully!");
			return true;
		}

	}
}
