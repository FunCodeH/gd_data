package com.gd.data.utils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.http.*;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;


public class HttpTools {

	private static final CloseableHttpClient httpclient = HttpClientBuilder.create().build();

	/**
	 * 发送HttpGet请求
	 * @param url
	 * @return
	 */
	public static String sendGet(String url, String token) {

		HttpGet httpget = new HttpGet(url);
		httpget.setHeader("Connection", "close");
		CloseableHttpResponse response = null;
		try {
			httpget.addHeader("content-type", "application/json;charset=UTF-8");
			httpget.addHeader("date", new Date().toString());
			httpget.addHeader("server","Apache-Coyote/1.1");
			httpget.addHeader("transfer-encoding","chunked");
			httpget.addHeader("loginToken",token);
			response = httpclient.execute(httpget);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		String result = null;
		try {
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				result = EntityUtils.toString(entity);
			}
		} catch (ParseException | IOException e) {
			e.printStackTrace();
		} finally {
			try {
				response.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 发送HttpPost请求，参数为map
	 * @param url
	 * @param map
	 * @return
	 */
	public static String sendPost(String url, Map<String, String> map) {
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
		for (Map.Entry<String, String> entry : map.entrySet()) {
			formparams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
		}
		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);
		HttpPost httppost = new HttpPost(url);
		httppost.setHeader("Connection", "close");
		httppost.addHeader("content-type", "application/json;charset=UTF-8");
		httppost.addHeader("date", new Date().toString());
		httppost.addHeader("server","Apache-Coyote/1.1");
		httppost.addHeader("loginToken","2157BC59A641705228A76AE4C25754A008E941F2453FEDBFCF9D4097F1BDBF4A17FEE8450E0F825949E2A5B9ACA90FF4E77338EE48F22B45B14CC6BDD5721EDF");
		httppost.setEntity(entity);
		CloseableHttpResponse response = null;
		try {
			response = httpclient.execute(httppost);
		} catch (IOException e) {
			e.printStackTrace();
		}
		HttpEntity entity1 = response.getEntity();
		String result = null;
		try {
			result = EntityUtils.toString(entity1);
		} catch (ParseException | IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 发送HttpPost请求，参数为string
	 * @param url
	 * @param param
	 * @return
	 */
	public static String sendPost(String url, String param, String token) {
		HttpPost httppost = new HttpPost(url);
		httppost.setHeader("Connection", "close");
		httppost.addHeader("content-type", "application/json;charset=UTF-8");
//		httppost.addHeader("date", new Date().toString());
		httppost.addHeader("Accept", "application/json");
		httppost.addHeader("Connection", "keep-alive");
//		httppost.addHeader("server","Apache-Coyote/1.1");
		httppost.addHeader("loginToken",token);
		CloseableHttpResponse response = null;
		try {
			StringEntity stringEntity = new StringEntity(param, Charset.forName("UTF-8"));
			httppost.setEntity(stringEntity);
			response = httpclient.execute(httppost);
		} catch (IOException e) {
			e.printStackTrace();
		}
		HttpEntity entity1 = response.getEntity();
		String result = null;
		try {
			result = EntityUtils.toString(entity1);
		} catch (ParseException | IOException e) {
			e.printStackTrace();
		}finally {
//			try {
//				httpclient.close();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
		}
		return result;
	}

	/**
	 * 发送不带参数的HttpPost请求
	 * @param url
	 * @return
	 */
	public static String sendPost(String url) {
		HttpPost httppost = new HttpPost(url);
		CloseableHttpResponse response = null;
		try {
			response = httpclient.execute(httppost);
		} catch (IOException e) {
			e.printStackTrace();
		}
		HttpEntity entity = response.getEntity();
		String result = null;
		try {
			result = EntityUtils.toString(entity);
		} catch (ParseException | IOException e) {
			e.printStackTrace();
		}
		return result;
	}
}
