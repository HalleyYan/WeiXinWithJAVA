package hotpursuit.halley.utils;

import hotpursuit.halley.pojo.BaiduMapResult;
import hotpursuit.halley.pojo.TranslateResult;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.SocketAddress;
import java.net.URI;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;

/**
 * 
 * @author yhq
 * @date 2013-10-21
 */
public class BaiduTranslateUtil {
	/**
	 * 发起http请求获取返回结果
	 * 
	 * @param requestUrl 请求地址
	 * @return
	 */
	public static String httpRequest(String requestUrl,boolean isLocalProxy) {
		
		if(isLocalProxy){
			ProxySelector.setDefault(new ProxySelector() {

		        @Override
		        public void connectFailed(URI uri, SocketAddress sa, IOException ioe) {
		            throw new RuntimeException("Proxy connect failed", ioe);
		        }

		        @Override
		        public List select(URI uri) {
		            return Arrays
		                .asList(new Proxy(Proxy.Type.HTTP,
		                                  new InetSocketAddress("proxy.piccnet.com.cn",3128)));
		        }
		    });
		}
		
		StringBuffer buffer = new StringBuffer();
		try {
			URL url = new URL(requestUrl);
			HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();

			httpUrlConn.setDoOutput(false);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);

			httpUrlConn.setRequestMethod("GET");
			httpUrlConn.connect();

			// 将返回的输入流转换成字符串
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			// 释放资源
			inputStream.close();
			inputStream = null;
			httpUrlConn.disconnect();

		} catch (Exception e) {
		}
		return buffer.toString();
	}

	/**
	 * utf编码
	 * 
	 * @param source
	 * @return
	 */
	public static String urlEncodeUTF8(String source) {
		String result = source;
		try {
			result = java.net.URLEncoder.encode(source, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 翻译（中->英 英->中 日->中 ）
	 * 
	 * @param source
	 * @return
	 */
	public static String translate(String source) {
		String dst = null;

		// 组装查询地址
		String requestUrl = "http://openapi.baidu.com/public/2.0/bmt/translate?client_id=fs7arP46I4UPHADzqpOQcxk5&q={keyWord}&from=auto&to=auto";
		// 对参数q的值进行urlEncode utf-8编码
		requestUrl = requestUrl.replace("{keyWord}", urlEncodeUTF8(source));
		
		
		
		// 查询并解析结果
		try {
			// 查询并获取返回结果
			String json = httpRequest(requestUrl,true);
			System.out.print(json);
			// 通过Gson工具将json转换成TranslateResult对象
			TranslateResult translateResult = new Gson().fromJson(json, TranslateResult.class);
			// 取出translateResult中的译文
			dst = translateResult.getTrans_result().get(0).getDst();
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (null == dst)
			dst = "翻译系统异常，请稍候尝试！";
		return dst;
	}
	
	public static String ipToLocation(String ip) {
		String dst = null;

		// 组装查询地址
		String requestUrl = "http://api.map.baidu.com/location/ip?ak=bkoh88ONPyvQCR8QRnYlS2Uz&ip={keyWord}&coor=bd09ll";
		// 对参数q的值进行urlEncode utf-8编码
		requestUrl = requestUrl.replace("{keyWord}", urlEncodeUTF8(ip));
		
		
		BaiduMapResult baiduResult = null;
		// 查询并解析结果
		try {
			// 查询并获取返回结果
			String json = httpRequest(requestUrl,true);
			System.out.println(json);
			// 通过Gson工具将json转换成BaiduMapResult对象
			baiduResult = new Gson().fromJson(json, BaiduMapResult.class);
			//System.out.print(baiduResult);
			dst = baiduResult.getStatus();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (null == dst)
			dst = "地图系统异常，请稍候尝试！";
		return baiduResult.toString();
	}

	public static void main(String[] args) {
		// 翻译结果：The network really powerful
		//System.out.println(translate("网络真强大"));
		System.out.println(ipToLocation("14.16.1.102"));
	}
}
