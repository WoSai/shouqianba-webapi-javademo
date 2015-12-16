package com.wosai.sqb.storemap;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * @author Baishui2004
 */
public class HttpUtil {

    private static Logger log = LoggerFactory.getLogger(HttpUtil.class);

    public static String httpPostWithoutException(String url, String string) {
        String xmlRes = "{}";
        try {
            xmlRes = httpPost(url, string);
        } catch (UnrecoverableKeyException e) {
            log.error("", e);
        } catch (NoSuchAlgorithmException e) {
            log.error("", e);
        } catch (KeyStoreException e) {
            log.error("", e);
        } catch (KeyManagementException e) {
            log.error("", e);
        }
        return xmlRes;
    }

    public static String httpPost(String url, String string) throws UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        String xmlRes = "{}";
        HttpClient client = createSSLClientDefault();
        HttpPost httpost = new HttpPost(url);
        try {
            log.debug("Request string: " + string);
            httpost.setEntity(new StringEntity(string, "UTF-8"));
            HttpResponse response = client.execute(httpost);
            xmlRes = EntityUtils.toString(response.getEntity(), "UTF-8");
            log.debug("Response string: " + xmlRes);
        } catch (ClientProtocolException e) {
            log.error("", e);
        } catch (IOException e) {
            log.error("", e);
        }
        return xmlRes;
    }

    public static CloseableHttpClient createSSLClientDefault() {
        try {
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
                //信任所有
                public boolean isTrusted(X509Certificate[] chain,
                                         String authType) throws CertificateException {
                    return true;
                }
            }).build();
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);
            return HttpClients.custom().setSSLSocketFactory(sslsf).build();
        } catch (KeyManagementException e) {
            log.error("", e);
        } catch (NoSuchAlgorithmException e) {
            log.error("", e);
        } catch (KeyStoreException e) {
            log.error("", e);
        }
        return HttpClients.createDefault();
    }

    public static String doGet(String url,String parameter)
    {
        String uriAPI =url+"?"+parameter ; //"http://XXXXX?str=I+am+get+String";
        String result= "";
        HttpClient client = createSSLClientDefault();
        HttpGet httpRequst = new HttpGet(uriAPI);
        try {

            HttpResponse httpResponse = client.execute(httpRequst);//其中HttpGet是HttpUriRequst的子类
            if(httpResponse.getStatusLine().getStatusCode() == 200)
            {
                HttpEntity httpEntity = httpResponse.getEntity();
                result = EntityUtils.toString(httpEntity);//取出应答字符串
                // 一般来说都要删除多余的字符
                result.replaceAll("\r", "");//去掉返回结果中的"\r"字符，否则会在结果字符串后面显示一个小方格
            }
            else
                httpRequst.abort();
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            result = e.getMessage().toString();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            result = e.getMessage().toString();
        }
        return result;
    }

}
