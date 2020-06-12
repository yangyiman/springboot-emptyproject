package com.yym.springboot.common.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.*;

/**
 * https接口调用工具类
 *
 * @author lizj
 */
public class HttpConnect {

    public static final String HTTP_SECURITY_KEY = "q2V4ujDfrGTNr9agDmWPZuSDyq5dkR0d";

    /**
     * http接口调用
     *
     * @param url        接口地址
     * @param jsonParams json格式参数
     * @return
     * @author my
     */
    public synchronized static String doPost(String url, String jsonParams) throws Exception {
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost method = new HttpPost(url);
        httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 60000);// 连接时间
        httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 60000);// 数据传输时间
        HttpPost post = new HttpPost(url);
        StringEntity entity = new StringEntity(jsonParams, "utf-8");// 解决中文乱码问题
        entity.setContentEncoding("UTF-8");
        entity.setContentType("application/json");
        method.setEntity(entity);
        HttpResponse result = httpClient.execute(method);
        String resData = EntityUtils.toString(result.getEntity());
        return resData;
    }

    /**
     * http接口调用
     *
     * @param url  接口地址
     * @return
     * @author my
     */
    public synchronized static String doGet2(String url) throws Exception {
        StringBuffer sb = new StringBuffer("");
        BufferedReader in = null;
        try {
            String content = null;
            // 定义HttpClient
            HttpClient client = new DefaultHttpClient();
            HttpHost proxy = new HttpHost("192.168.125.125",3128);
            RequestConfig requestConfig = RequestConfig.custom()
                    .setProxy(proxy)
                    .setConnectTimeout(10000)
                    .setSocketTimeout(10000)
                    .setConnectionRequestTimeout(3000)
                    .build();

            // 实例化HTTP方法
            HttpGet request = new HttpGet();
            request.setConfig(requestConfig);
            request.setURI(new URI(url));
            HttpResponse response = null;

            response = client.execute(request);


            in = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));

            String line = "";
            String NL = System.getProperty("line.separator");
            while ((line = in.readLine()) != null) {
                sb.append(line + NL);
            }
        } catch (Exception e) {
            LogUtil.info("HttpConnect.doGet is error..",e.getMessage());
            e.printStackTrace();
        } finally {
            if(in!=null){
                in.close();
            }
        }
        return sb.toString();
    }

    /**
     * 传普通表单参数
     *
     * @param url
     * @param params
     * @return
     * @throws Exception
     */
    public synchronized static String doPost(String url, Map<String, String> params) throws Exception {
        Set<String> keys = params.keySet();
        List<BasicNameValuePair> list = new ArrayList<BasicNameValuePair>();
        Iterator<String> i = keys.iterator();
        while (i.hasNext()) {
            String key = i.next();
            BasicNameValuePair basicNameValuePair = new BasicNameValuePair(key, params.get(key));
            list.add(basicNameValuePair);
        }
        HttpClient httpClient = new DefaultHttpClient();
        httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 3000);// 连接时间
        httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 3000);// 数据传输时间

        HttpPost post = new HttpPost(url);
        post.setEntity(new UrlEncodedFormEntity(list));
        HttpResponse httpResponse = httpClient.execute(post);
        HttpEntity entity = httpResponse.getEntity();
        return EntityUtils.toString(entity);
    }

    public static String doGet(String url) {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        // 创建Get请求
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("HTTP_SECURITY_KEY", HTTP_SECURITY_KEY);
        String res = "";
        // 响应模型
        CloseableHttpResponse response = null;
        try {
            // 由客户端执行(发送)Get请求
            response = httpClient.execute(httpGet);
            // 从响应模型中获取响应实体
            HttpEntity responseEntity = response.getEntity();
            if (responseEntity != null) {
                res = EntityUtils.toString(responseEntity);
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // 释放资源
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return res;
    }

    public static String doGet(String url,String cookie) {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        // 创建Get请求
        HttpGet httpGet = new HttpGet(url);
        if (StringUtils.isNotBlank(cookie)){
            httpGet.setHeader("Cookie", cookie);
        }

        String res = "";
        // 响应模型
        CloseableHttpResponse response = null;
        try {
            // 由客户端执行(发送)Get请求
            response = httpClient.execute(httpGet);
            // 从响应模型中获取响应实体
            HttpEntity responseEntity = response.getEntity();
            if (responseEntity != null) {
                res = EntityUtils.toString(responseEntity);
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // 释放资源
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return res;
    }
}