package com.vkl.hblw.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.log4j.Logger;

import com.vkl.hblw.common.constant.SystemInfo;

public class HttpUtil {

	private static final Logger log = Logger.getLogger(HttpUtil.class);

	/**
	 * http post 数据 ，内容为 json 字符串
	 * 
	 * @param url
	 * @param jsonStr
	 * @return
	 */
	public static String postHttp(String url, String jsonStr) {

		StringBuilder sb = new StringBuilder();
		RequestConfig.Builder requestBuilder = RequestConfig.custom();
		requestBuilder = requestBuilder.setConnectTimeout(10 * 1000); // 10秒超时
		requestBuilder = requestBuilder.setConnectionRequestTimeout(15 * 1000);

		HttpClientBuilder builder = HttpClientBuilder.create();
		builder.setDefaultRequestConfig(requestBuilder.build());
		HttpClient httpclient = builder.build();
		HttpPost httpPost = new HttpPost(url);

		CloseableHttpResponse response = null;
		try {
			httpPost.setEntity(new StringEntity(jsonStr, "utf-8"));
			response = (CloseableHttpResponse) httpclient.execute(httpPost);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				InputStream instream = entity.getContent();
				try {
					BufferedReader br = null;
					String line;
					try {
						br = new BufferedReader(new InputStreamReader(instream, "utf-8"));
						while ((line = br.readLine()) != null) {
							sb.append(line);
						}
					} catch (IOException e) {
						e.printStackTrace();
					} finally {
						if (br != null) {
							try {
								br.close();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					}

				} finally {
					instream.close();
				}
			}
		} catch (ClientProtocolException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} finally {
			try {
				response.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}

	/**
	 * http post 传入多个表单数据
	 * 
	 * @param url
	 * @param nvps
	 * @return
	 * @throws IOException 
	 */
	public static String postHttp(String url, List<NameValuePair> nvps) throws IOException {

		StringBuilder sb = new StringBuilder();
		RequestConfig.Builder requestBuilder = RequestConfig.custom();
		requestBuilder = requestBuilder.setConnectTimeout(10 * 1000);
		requestBuilder = requestBuilder.setConnectionRequestTimeout(15 * 1000);

		HttpClientBuilder builder = HttpClientBuilder.create();
		builder.setDefaultRequestConfig(requestBuilder.build());
		HttpClient httpclient = builder.build();
		HttpPost httpPost = new HttpPost(url);

		CloseableHttpResponse response = null;
		try {

			// System.out.println(new UrlEncodedFormEntity(nvps).toString());
			httpPost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));
			response = (CloseableHttpResponse) httpclient.execute(httpPost);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				InputStream instream = entity.getContent();
				try {
					BufferedReader br = null;

					String line;
					try {
						br = new BufferedReader(new InputStreamReader(instream, "utf-8"));
						while ((line = br.readLine()) != null) {
							sb.append(line);
						}
					} catch (IOException e) {
						log.error(SystemInfo.LOG_CODE_ERROR, e);
						throw e;
					} finally {
						if (br != null) {
							try {
								br.close();
							} catch (IOException e) {
								log.error(SystemInfo.LOG_CODE_ERROR, e);
								throw e;
							}
						}
					}

				} finally {
					instream.close();
				}
			}
		} catch (ClientProtocolException e1) {
			log.error(SystemInfo.LOG_CODE_ERROR, e1);
			throw e1;
		} catch (IOException e1) {
			log.error(SystemInfo.LOG_CODE_ERROR, e1);
			throw e1;
		} finally {
			try {
				response.close();
			} catch (IOException e) {
				log.error(SystemInfo.LOG_CODE_ERROR, e);
				throw e;
			}
		}
		return sb.toString();
	}

	/**
	 * http get
	 * 
	 * @param url
	 * @return
	 */
	public static String getHttp(String url) {
		StringBuilder sb = new StringBuilder();
		RequestConfig.Builder requestBuilder = RequestConfig.custom();
		requestBuilder = requestBuilder.setConnectTimeout(10 * 1000);
		requestBuilder = requestBuilder.setConnectionRequestTimeout(15 * 1000);

		HttpClientBuilder builder = HttpClientBuilder.create();
		builder.setDefaultRequestConfig(requestBuilder.build());
		HttpClient httpclient = builder.build();
		HttpGet httpget = new HttpGet(url);
		CloseableHttpResponse response = null;
		try {
			response = (CloseableHttpResponse) httpclient.execute(httpget);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				InputStream instream = entity.getContent();
				try {
					BufferedReader br = null;

					String line;
					try {
						br = new BufferedReader(new InputStreamReader(instream, "utf-8"));
						while ((line = br.readLine()) != null) {
							sb.append(line);
						}
					} catch (IOException e) {
						log.error(SystemInfo.LOG_CODE_ERROR, e);
					} finally {
						if (br != null) {
							try {
								br.close();
							} catch (IOException e) {
								log.error(SystemInfo.LOG_CODE_ERROR, e);
							}
						}
					}

				} finally {
					instream.close();
				}
			}
		} catch (ClientProtocolException e1) {
			log.error(SystemInfo.LOG_CODE_ERROR, e1);
		} catch (IOException e1) {
			log.error(SystemInfo.LOG_CODE_ERROR, e1);
		} finally {
			try {
				response.close();
			} catch (IOException e) {
				log.error(SystemInfo.LOG_CODE_ERROR, e);
			}
		}
		return sb.toString();
	}

	public static void clearCookie(HttpServletRequest request, HttpServletResponse response, String path,
			String excludeCookie) {
		Cookie[] cookies = request.getCookies();
		try {
			for (int i = 0; i < cookies.length; i++) {
				String name = cookies[i].getName();

				if (StringUtils.startsWith(name, excludeCookie)) {
					continue;
				}

				Cookie cookie = new Cookie(name, null);
				cookie.setMaxAge(0);
				cookie.setPath(path);// 根据你创建cookie的路径进行填写
				response.addCookie(cookie);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	/**
	 * http post MultipartEntityBuilder 传入多个表单数据
	 * 
	 * @param url
	 * @param nvps
	 * @return
	 */
	public static String postHttp2(String url, List<NameValuePair> nvps) {

		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost(url);
		StringBuilder s = new StringBuilder();
		try {
			MultipartEntityBuilder multipartEntity = MultipartEntityBuilder.create();
			for (NameValuePair temp : nvps) {
				StringBody body = new StringBody(temp.getValue(), Charset.forName("UTF-8"));
				multipartEntity.addPart(temp.getName(), body);
			}

			HttpEntity reqEntity = multipartEntity.build();

			httppost.setEntity(reqEntity);
			log.info("执行: " + httppost.getRequestLine());
			/*getMultipart(reqEntity);*/

			HttpResponse response = httpclient.execute(httppost);
			log.info("statusCode is " + response.getStatusLine().getStatusCode());
			HttpEntity resEntity = response.getEntity();
			log.info(response.getStatusLine());
			if (resEntity != null) {
				log.info("返回长度: " + resEntity.getContentLength());
				log.info("返回类型: " + resEntity.getContentType());
				InputStream in = resEntity.getContent();

				BufferedReader reader = new BufferedReader(new InputStreamReader(resEntity.getContent(), "utf-8"));

				String result = "";
				while ((result = reader.readLine()) != null) {
					s = s.append(result);
				}

				System.out.println("in is " + s.toString());

			}
			if (resEntity != null) {
				resEntity.consumeContent();
			}

			return s.toString();
		} catch (Exception e) {

			log.error(SystemInfo.LOG_CODE_ERROR, e);
		}

		return null;
	}

/*	public static void getMultipart(HttpEntity reqEntity) throws IOException {

		ByteArrayOutputStream out = new ByteArrayOutputStream((int) reqEntity.getContentLength());

		// write content to stream
		reqEntity.writeTo(out);

		// either convert stream to string
		String string = out.toString();

		// or convert stream to bytes
		byte[] bytes = out.toByteArray();

		log.info("HttpEntity：" + string);

	}*/
}
