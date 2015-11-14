package com.cy.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.protocol.ExecutionContext;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;


/**
 * http请求工具
 */
public class HttpUtil {
	private final static Log logger = LogFactory.getLog(HttpUtil.class);
	/**
	 * 默认字符�?
	 */
	private static final String defaultCharset = Constant.Charset.UTF8;
	
	/**
	 * 请求失败重试次数
	 */
	private static int retryCount = 3;
	
	// 异常自动恢复处理, 使用HttpRequestRetryHandler接口实现请求的异常恢�?
	private static HttpRequestRetryHandler requestRetryHandler = new HttpRequestRetryHandler() {
		// 自定义的恢复策略
		public boolean retryRequest(IOException exception, int executionCount,
				HttpContext context) {
			// 设置恢复策略，在发生异常时�?将自动重�?�?
			if (executionCount >= retryCount) {
				// Do not retry if over max retry count
				return false;
			}
			if (exception instanceof NoHttpResponseException) {
				// Retry if the server dropped connection on us
				return true;
			}
			if (exception instanceof SSLHandshakeException) {
				// Do not retry on SSL handshake exception
				return false;
			}
			HttpRequest request = (HttpRequest) context
					.getAttribute(ExecutionContext.HTTP_REQUEST);
			boolean idempotent = (request instanceof HttpEntityEnclosingRequest);
			if (!idempotent) {
				// Retry if the request is considered idempotent
				return true;
			}
			return false;
		}
	};
	
	// 使用ResponseHandler接口处理响应，HttpClient使用ResponseHandler会自动管理连接的释放，解决了对连接的释放管理
	private static ResponseHandler<String> responseHandler = new ResponseHandler<String>() {
		// 自定义响应处�?
		public String handleResponse(HttpResponse response)
				throws ClientProtocolException, IOException {
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				String charset = EntityUtils.getContentCharSet(entity) == null ? defaultCharset
						: EntityUtils.getContentCharSet(entity);
				return new String(EntityUtils.toByteArray(entity), charset);
			} else {
				return null;
			}
		}
	};
	
	/**
	 * Get方式提交,URL中包含查询参�? 格式：http://www.g.cn?search=p&name=s.....
	 * 
	 * @param url
	 *            提交地址
	 * @return 响应消息
	 */
	public String get(String url) {
		return get(url, null, null);
	}

	/**
	 * Get方式提交,URL中不包含查询参数, 格式：http://www.g.cn
	 * 
	 * @param url
	 *            提交地址
	 * @param params
	 *            查询参数�? �?值对
	 * @return 响应消息
	 */
	public String get(String url, Map<String, String> params) {
		return get(url, params, null);
	}

	/**
	 * Get方式提交,URL中不包含查询参数, 格式：http://www.g.cn
	 * 
	 * @param url
	 *            提交地址
	 * @param params
	 *            查询参数�? �?值对
	 * @param charset
	 *            参数提交编码�?
	 * @return 响应消息
	 */
	public String get(String url, Map<String, String> params,
			String charset) {
		if (StringUtils.isBlank(url)) {
			return null;
		}
		List<NameValuePair> qparams = getParamsList(params);
		if (qparams != null && qparams.size() > 0) {
			charset = (charset == null ? defaultCharset : charset);
			String formatParams = URLEncodedUtils.format(qparams, charset);
			url = (url.indexOf("?")) < 0 ? (url + "?" + formatParams) : (url
					.substring(0, url.indexOf("?") + 1) + formatParams);
		}
		DefaultHttpClient httpclient = getDefaultHttpClient(charset);
		logger.info("Get请求,url:"+url);
		HttpGet hg = new HttpGet(url);
		// 发�?请求，得到响�?
		String responseStr = null;
		try {
			responseStr = httpclient.execute(hg, responseHandler);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			responseStr = "-11,客户端连接协议错";
		} catch (IOException e) {
			e.printStackTrace();
			responseStr = "-12,IO操作异常";
		} finally {
			abortConnection(hg, httpclient);
		}
		return responseStr;
	}

	/**
	 * Post方式提交,URL中不包含提交参数, 格式：http://www.g.cn
	 * 
	 * @param url
	 *            提交地址
	 * @param content
	 *            提交内容
	 * @return 响应消息
	 */
	public String post(String url, String content) {
		return post(url, content, null);
	}
	
	/**
	 * Post方式提交
	 * @param url 请求地址
	 * @param content 请求内容，可为空
	 * @param charset 字符�?
	 * @return
	 */
	public static String post(String url, String content, String charset) {
		if (StringUtils.isBlank(url)) {
			return null;
		}

		String responseStr = null;
		
		if(charset == null){
			charset = defaultCharset;
		}

		DefaultHttpClient httpclient = getDefaultHttpClient(charset);
		
		ByteArrayEntity entity = null;
		HttpPost hp = null;

		try {
			hp = new HttpPost(url);
			if(!StringUtils.isBlank(content)){
				entity = new ByteArrayEntity(content.getBytes(charset));
				hp.setEntity(entity);
			}
			responseStr = httpclient.execute(hp, responseHandler);
		} catch (IOException e) {
			e.printStackTrace();
			responseStr = "-12,IO操作异常";
		} finally {
			abortConnection(hp, httpclient);
		}
		return responseStr;
	}
	
	/**
	 * Post方式提交,URL中不包含提交参数, 格式：http://www.g.cn
	 * 
	 * @param url
	 *            提交地址
	 * @param params
	 *            提交参数�? �?值对
	 * @return 响应消息
	 */
	public String post(String url, Map<String, String> params) {
		return post(url, params, null);
	}
		
	/**
	 * Post方式提交,URL中不包含提交参数, 格式：http://www.g.cn
	 * 
	 * @param url
	 *            提交地址
	 * @param params
	 *            提交参数�? �?值对
	 * @param charset
	 *            参数提交编码�?
	 * @return 响应消息
	 */
	public String post(String url, Map<String, String> params,
			String charset) {
		if (StringUtils.isBlank(url)) {
			return null;
		}

		if(charset == null){
			charset = defaultCharset;
		}
		
		// 发�?请求，得到响�?
		String responseStr = null;

		// 创建HttpClient实例
		DefaultHttpClient httpclient = getDefaultHttpClient(charset);
		UrlEncodedFormEntity formEntity = null;
		try {
			if (StringUtils.isBlank(charset)) {
				formEntity = new UrlEncodedFormEntity(getParamsList(params));
			} else {
				formEntity = new UrlEncodedFormEntity(getParamsList(params),
						charset);
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			responseStr = "";
		}
		HttpPost hp = new HttpPost(url);
		hp.setEntity(formEntity);

		try {
			responseStr = httpclient.execute(hp, responseHandler);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			responseStr = "-11,客户端连接协议错";
		} catch (IOException e) {
			e.printStackTrace();
			responseStr = "-12,IO操作异常";
		} finally {
			abortConnection(hp, httpclient);
		}
		return responseStr;
	}
	
	

	/**
	 * 获取DefaultHttpClient实例
	 * 
	 * @param charset
	 *            参数编码�? 可空
	 * @return DefaultHttpClient 对象
	 */
	private static DefaultHttpClient getDefaultHttpClient(final String charset) {
		DefaultHttpClient httpclient = new DefaultHttpClient();
		httpclient.getParams().setParameter(
				CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
		// 模拟浏览器，解决�?��服务器程序只允许浏览器访问的问题
		httpclient.getParams().setParameter(CoreProtocolPNames.USER_AGENT,
				"Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1)");
		httpclient.getParams().setParameter(
				CoreProtocolPNames.USE_EXPECT_CONTINUE, Boolean.FALSE);
		httpclient.getParams().setParameter(
				CoreProtocolPNames.HTTP_CONTENT_CHARSET,
				charset == null ? defaultCharset : charset);
//		HttpConnectionParams.setConnectionTimeout(httpclient.getParams(), 1000);
//		HttpConnectionParams.setSoTimeout(httpclient.getParams(), 1000);
		httpclient.setHttpRequestRetryHandler(requestRetryHandler);
		return httpclient;
	}
	
	/**
	 * 释放HttpClient连接
	 * 
	 * @param hrb
	 *            请求对象
	 * @param httpclient
	 *            client对象
	 */
	private static void abortConnection(final HttpRequestBase hrb,
			final HttpClient httpclient) {
		if (hrb != null) {
			hrb.abort();
		}
	//	if (httpclient != null) {
	//		httpclient.getConnectionManager().shutdown();
	//	}
	}
	
	/**
	 * 将传入的�?值对参数转换为NameValuePair参数�?
	 * 
	 * @param paramsMap
	 *            参数�? �?值对
	 * @return NameValuePair参数�?
	 */
	private List<NameValuePair> getParamsList(
			Map<String, String> paramsMap) {
		if (paramsMap == null || paramsMap.size() == 0) {
			return null;
		}
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		for (Map.Entry<String, String> map : paramsMap.entrySet()) {
			params.add(new BasicNameValuePair(map.getKey(), map.getValue()));
		}
		return params;
	}

	public void setRetryCount(int retryCount) {
		this.retryCount = retryCount;
	}
	
	/**    
	 * invokeGetService(这里用一句话描述这个方法的作�?    
	 * @param @param url
	 * @param @param charsetName
	 * @param @return     
	 * @return String
	 * @Exception 异常对象
	*/
	public String invokeGetService(String url, String charsetName, int timeoutms){
		String resStr = "";
		HttpClient httpclient = null;
//		logger.info("request url : " + url);
		try {
			httpclient = new DefaultHttpClient();
			httpclient.getParams().setIntParameter("http.socket.timeout", timeoutms);
			
			HttpGet httpget = new HttpGet(url);
			HttpResponse response = httpclient.execute(httpget);
			HttpEntity resEntity = response.getEntity();
			if (resEntity != null) {
			    InputStream is = resEntity.getContent();
			    resStr = convertStreamToString(is, charsetName);
			}
		} catch (ClientProtocolException e) {
		    logger.error(e.getMessage(), e);
		} catch (IOException e) {
		    logger.error(e.getMessage(), e);
		} finally{   
            //HttpClient的实例不再需要时，降低连接，管理器关闭，以确保立即释放所有系统资�?  
            if(httpclient != null)   
                httpclient.getConnectionManager().shutdown();   
        }
//		logger.debug("interface return result: " + resStr);
		return resStr;
	}
	
	public String convertStreamToString(InputStream is, String charsetName){   
        /*  
         * To convert the InputStream to String we use the BufferedReader.readLine()  
         * method. We iterate until the BufferedReader return null which means  
         * there's no more data to read. Each line will appended to a StringBuilder  
         * and returned as String.  
         */            
        StringBuilder sb = new StringBuilder();       
        try {   
        	BufferedReader reader = new BufferedReader(new InputStreamReader(is, charsetName)); 
        	String line = null;
            while ((line = reader.readLine()) != null) {   
                sb.append(line + "\n");   
            }   
        } catch (IOException e) {   
            logger.info(e.getMessage());   
        } finally {   
            try {   
                is.close();   
            } catch (IOException e) {   
                logger.info(e.getMessage());
            }   
        }       
        return sb.toString();   
    } 
	
	public void PostWithJSON(String url, String json) throws Exception {
        // 将JSON进行UTF-8编码,以便传输中文        
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader(HTTP.CONTENT_TYPE, "application/json");
        
        StringEntity se = new StringEntity(json);
        se.setContentType("text/json");
        se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
        httpPost.setEntity(se);
        httpClient.execute(httpPost);
    }
	
	//以下是wrapClient方法
		/**
		* 获取可信任https链接，以避免不受信任证书出现peer not authenticated异常
		*
		* @param base
		* @return
		*/
		public static HttpClient wrapClient(HttpClient base) {
		try {
			SSLContext ctx = SSLContext.getInstance("TLS");
			X509TrustManager tm = new X509TrustManager() {
				public void checkClientTrusted(X509Certificate[] xcs,
						String string) {
				}
			public void checkServerTrusted(X509Certificate[] xcs,
					String string) {
			}
			public X509Certificate[] getAcceptedIssuers() {
				return null;
			}
			};
			ctx.init(null, new TrustManager[] { tm }, null);
			SSLSocketFactory ssf = new SSLSocketFactory(ctx);
			ssf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
			ClientConnectionManager ccm = base.getConnectionManager();
			SchemeRegistry sr = ccm.getSchemeRegistry();
			sr.register(new Scheme("https", ssf, 443));
			return new DefaultHttpClient(ccm, base.getParams());
			} catch (Exception ex) {
				ex.printStackTrace();
				return null;
		}}
		
		/**
		 * Get方式提交,URL中不包含查询参数, 格式：http://www.g.cn
		 * 
		 * @param url
		 *            提交地址
		 * @param params
		 *            查询参数�? �?值对
		 * @param charset
		 *            参数提交编码�?
		 * @return 响应消息
		 */
		public String getHttps(String url, Map<String, String> params,
				String charset) {
			if (StringUtils.isBlank(url)) {
				return null;
			}
			List<NameValuePair> qparams = getParamsList(params);
			if (qparams != null && qparams.size() > 0) {
				charset = (charset == null ? defaultCharset : charset);
				String formatParams = URLEncodedUtils.format(qparams, charset);
				url = (url.indexOf("?")) < 0 ? (url + "?" + formatParams) : (url
						.substring(0, url.indexOf("?") + 1) + formatParams);
			}
			HttpClient  httpclient = getDefaultHttpClient(charset);
			httpclient =  wrapClient(httpclient);
			logger.info("Get请求,url:"+url);
			HttpGet hg = new HttpGet(url);
			// 发�?请求，得到响�?
			String responseStr = null;
			try {
				responseStr = httpclient.execute(hg, responseHandler);
			} catch (ClientProtocolException e) {
				e.printStackTrace();
				responseStr = "-11,客户端连接协议错误";
			} catch (IOException e) {
				e.printStackTrace();
				responseStr = "-12,IO操作异常";
			} finally {
				abortConnection(hg, httpclient);
			}
			return responseStr;
		}
}
