/**
 * 
 */
package com.vkl.hblw.common.util.ffmpeg;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.vkl.hblw.common.constant.SystemInfo;

/**
 * @author xcc
 *
 */
public class FFMPEG {

	private static final Logger log = Logger.getLogger(FFMPEG.class);
	
	private String ffmpegPath = "ffmpeg";
	private String path;
	private String tempFilePath;
	private String pictureName;
	private String mergeName;
	private String compressName;
	//压缩比
	private String videoysb;
	
	public static void main(String[] args) {

		File aa = new File("C:\\Users\\Administrator\\Desktop\\F1_1.mp4");
		FFMPEG ffm = new FFMPEG("E:\\这123\\123", "E:\\这123", "123.mp4");
		
		String path1 = ffm.drawingFrame(aa);
		String path2 = ffm.videoMerge(path1);
		ffm.videoCompress(path2,"36");
	}
	public FFMPEG(String tempPath, String filePath, String fileName){
		path = filePath + "\\";
		tempFilePath = tempPath + "\\";
		pictureName = "image-%05d.jpeg";
		mergeName = "merge.mp4";
		compressName = fileName;
	}

	
	
	
	
	//张萍修改压缩构造函数，添加参数压缩比
	public FFMPEG(String tempPath, String filePath, String fileName,String ysb){
		path = filePath + "\\";
		tempFilePath = tempPath + "\\";
		pictureName = "image-%05d.jpeg";
		mergeName = "merge.mp4";
		compressName = fileName;
		videoysb=ysb;
	}

	
	
	
	/**
	 * 视频是否是支持类型
	 * 
	 * @param
	 */
	private boolean isSurpportedType(String type) {
		Pattern pattern = Pattern.compile("(asx|asf|mpg|wmv|3gp|mp4|mov|avi|flv|264|265){1}$", Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(type);
		return matcher.find();
	}

	/**
	 * 视频抓图
	 * 
	 * @param
	 */
	public boolean capture(File sourceFile, String picturePath) {

		String fileName = sourceFile.getName();
		String surffix = fileName.substring(fileName.lastIndexOf(".") + 1);
		if (!isSurpportedType(surffix))
			throw new RuntimeException("unsurpported file type " + surffix);

		List<String> cmdParam = new LinkedList<String>();
		cmdParam.add(ffmpegPath);
		cmdParam.add("-r");
		cmdParam.add("1");
		cmdParam.add("-ss");
		cmdParam.add("00:00:01");
		cmdParam.add("-t");
		cmdParam.add("1");
		cmdParam.add("-i");
		cmdParam.add(sourceFile.getAbsolutePath());			 
		cmdParam.add("-f");
		cmdParam.add("image2");
		cmdParam.add("-y");
		cmdParam.add(picturePath);

		execCmd(cmdParam);
		return true;
	}

	/**
	 * 视频抽帧
	 * 
	 * @param
	 */
	public String drawingFrame(File sourceFile) {

		String fileName = sourceFile.getName();
		String surffix = fileName.substring(fileName.lastIndexOf(".") + 1);
		if (!isSurpportedType(surffix))
			throw new RuntimeException("unsurpported file type " + surffix);

		List<String> cmdParam = new LinkedList<String>();
		cmdParam.add(ffmpegPath);
		cmdParam.add("-i");
		cmdParam.add(sourceFile.getAbsolutePath());
		cmdParam.add("-r");
		cmdParam.add("2");		
		cmdParam.add("-s"); 
		cmdParam.add("1280*720");		 
		cmdParam.add("-f");
		cmdParam.add("image2");
		cmdParam.add("-y");
		cmdParam.add(tempFilePath + pictureName);

		execCmd(cmdParam);
		return tempFilePath + pictureName;
	}

	/**
	 * 视频合成
	 * 
	 * @param
	 */
	public String videoMerge(String filePath) {

		List<String> cmdParam = new LinkedList<String>();
		cmdParam.add(ffmpegPath);
		cmdParam.add("-framerate");
		cmdParam.add("2");
		cmdParam.add("-i");
		cmdParam.add(filePath);
		cmdParam.add("-codec");
		cmdParam.add("copy");
		cmdParam.add("-y");
		cmdParam.add(tempFilePath + mergeName);

		execCmd(cmdParam);
		return tempFilePath + mergeName; 
	}

	/**
	 * 视频压缩
	 * 
	 * @param
	 */
	public String videoCompress(String filePath,String ysb) {

		List<String> cmdParam = new LinkedList<String>();
		cmdParam.add(ffmpegPath);
		cmdParam.add("-i");
		cmdParam.add(filePath);
		cmdParam.add("-vcodec");
		cmdParam.add("libx264");
		cmdParam.add("-preset");
		cmdParam.add("slow");
		cmdParam.add("-crf");
		cmdParam.add(ysb);
		cmdParam.add("-y");
		cmdParam.add(path + compressName);

		execCmd(cmdParam);
		return path + compressName;
	}

	/**
	 * 开启进程执行FFMPEG命令 启动两个线程，一个线程负责读标准输出流，另一个负责读标准错误流
	 * 
	 * @param
	 */
	private void execCmd(List<String> cmd) {

		final ProcessBuilder pb = new ProcessBuilder();
		/* pb.redirectErrorStream(true); */
		pb.command(cmd);

		try {
			final Process p = pb.start();
			InputStream inputStream = p.getInputStream();
			InputStream errorStream = p.getErrorStream();

			// 开启单独的线程来处理输入和输出流，避免缓冲区满导致线程阻塞.
			// 启动两个线程，一个线程负责读标准输出流，另一个负责读标准错误流
			FFmpegInputStream streamErr = new FFmpegInputStream(inputStream);
			Thread errorStreamThread = new Thread(streamErr);
			errorStreamThread.start();

			FFmpegErrorStream streamIn = new FFmpegErrorStream(errorStream);
			Thread inputStreamThread = new Thread(streamIn);
			inputStreamThread.start();

			try {
				p.waitFor();
			} catch (InterruptedException e) {
				log.error(SystemInfo.LOG_CODE_ERROR, e);
				Thread.currentThread().interrupt();
			}
			p.getErrorStream().close();
			p.destroy();

		} catch (IOException e) {
			log.error(SystemInfo.LOG_CODE_ERROR, e);
		}

	}

	private class FFmpegInputStream implements Runnable {

		private InputStream is;

		public FFmpegInputStream(InputStream stream) {
			is = stream;
		}

		public void run() {
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			try {
				String line = null;
				while ((line = br.readLine()) != null) {
					if (line != null) {
						/*System.out.println(line);*/
					}
				}
			} catch (IOException e) {
				log.error(SystemInfo.LOG_CODE_ERROR, e);
			} finally {
				try {
					is.close();
				} catch (IOException e) {
					log.error(SystemInfo.LOG_CODE_ERROR, e);
				}
			}
		}
	}
	private class FFmpegErrorStream implements Runnable {

		private InputStream is;

		public FFmpegErrorStream(InputStream stream) {
			is = stream;
		}

		public void run() {
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			try {
				String line = null;
				while ((line = br.readLine()) != null) {
					if (line != null) {
						/*log.error(line);*/
					}
				}
			} catch (IOException e) {
				log.error(SystemInfo.LOG_CODE_ERROR, e);
			} finally {
				try {
					is.close();
				} catch (IOException e) {
					log.error(SystemInfo.LOG_CODE_ERROR, e);
				}
			}
		}
	}
}