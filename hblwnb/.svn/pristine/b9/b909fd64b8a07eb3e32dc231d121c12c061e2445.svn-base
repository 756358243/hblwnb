/**
 * 
 */
package com.vkl.hblw.common.util.FastDFS;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.UUID;

import org.apache.commons.io.IOUtils;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.FileInfo;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageClient1;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;

/**
 * @author xcc
 *
 */
public class FastDFSClient {

	private static String conf_filename = "E:\\fdfs_client.conf";
	private static String local_filename = "E:\\res\\out_di.mp4";
	private TrackerClient trackerClient = null;
	private TrackerServer trackerServer = null;
	private StorageServer storageServer = null;
	// 使用StorageClient1进行上传
	private StorageClient1 storageClient1 = null;

	public FastDFSClient(String conf) throws Exception {
		// 获取classpath路径下配置文件"fdfs_client.conf"的路径
		// conf直接写相对于classpath的位置，不需要写classpath:
		/*
		 * String configPath =
		 * this.getClass().getClassLoader().getResource(conf).getFile();
		 * System.out.println(configPath);
		 */
		ClientGlobal.init(conf);

		trackerClient = new TrackerClient();
		trackerServer = trackerClient.getConnection();
		storageServer = trackerClient.getStoreStorage(trackerServer);
		storageClient1 = new StorageClient1(trackerServer, storageServer);
	}

	public String uploadFile(byte[] file_buff, String file_ext_name) throws Exception {

		String result = storageClient1.upload_file1(file_buff, file_ext_name, null);

		return result;
	}

	public String uploadFile(String local_filename, String file_ext_name) throws Exception {

		String result = storageClient1.upload_file1(local_filename, file_ext_name, null);

		return result;
	}

	public static void testGetFileInfo() {

		try {
			ClientGlobal.init(conf_filename);
			TrackerClient tracker = new TrackerClient();
			TrackerServer trackerServer = tracker.getConnection();
			StorageServer storageServer = null;
			StorageClient storageClient = new StorageClient(trackerServer, storageServer);
			FileInfo fi = storageClient.get_file_info("group1", "M00/00/00/wKgRcFV_08OAK_KCAAAA5fm_sy874.conf");
			System.out.println(fi.getSourceIpAddr());
			System.out.println(fi.getFileSize());
			System.out.println(fi.getCreateTimestamp());
			System.out.println(fi.getCrc32());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void testDownload() {
		try {
			ClientGlobal.init(conf_filename);
			TrackerClient tracker = new TrackerClient();
			TrackerServer trackerServer = tracker.getConnection();
			StorageServer storageServer = null;
			StorageClient storageClient = new StorageClient(trackerServer, storageServer);
			byte[] b = storageClient.download_file("group1", "M00/00/00/wKgRcFV_08OAK_KCAAAA5fm_sy874.conf");
			System.out.println(b);
			IOUtils.write(b, new FileOutputStream("E:/" + UUID.randomUUID().toString() + ".conf"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws Exception {

		FastDFSClient client = new FastDFSClient(conf_filename);
		InputStream fis = new BufferedInputStream(new FileInputStream(local_filename));
		byte[] buffer = new byte[fis.available()];
		fis.read(buffer);
		fis.close();
		
		String result = client.uploadFile(buffer, "mp4");
		System.out.println(result);

		/*testGetFileInfo();*/
	}
}
