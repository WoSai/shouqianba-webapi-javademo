package com.wosai.sqb.storemap;
import org.json.JSONException;
import org.json.JSONObject;

import javax.print.DocFlavor;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;

public class HttpProxy {

    public String wosai_appkey = "6c62baf79d5cac9d7620292820bb46b0";
    public String channel_secret = "8e8b6ac222a3c17b33cfd78bdef8a506123456798115588";
    public String queryOrder = "https://api.test.shouqianba.com/Upay/queryOrder";
    public String cancelOrder="https://api.test.shouqianba.com/Upay/Cancel";
    public String refundMoney="https://api.test.shouqianba.com/Upay/weixinRefund";
    public String weixinQrCode="https://api.test.shouqianba.com/Upay/weixinQrCodeOffline";
    public String alipay="https://api.test.shouqianba.com/Upay/alipay";
   // public String notify_url="";


    public String getSign(SortedMap<String, String> paramMap) {//wosai_store_id,wosai_app_id, channel_id
        StringBuilder sb = new StringBuilder();
        paramMap.put("wosai_appkey", wosai_appkey);
        paramMap.put("channel_secret", channel_secret);
        for (Map.Entry<String, String> entry : paramMap.entrySet()) {
            if (entry.getValue() != null && entry.getValue().length() != 0) {
                sb.append("&").append(entry.toString());
//                try {
//                   sb.append("&").append(entry.getKey()+"="+URLEncoder.encode(entry.getValue(),"utf-8"));
//                } catch (UnsupportedEncodingException e) {
//                    e.printStackTrace();
//                }
            }
        }
        String md5 = "";
      //  System.out.print(sb.substring(1));
        try {
            md5 = MD5Util.encryptMd5(sb.substring(1)).toUpperCase();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return md5;
    }


    public String queryOrder(SortedMap<String, String> paramMap) {//wosai_order_sn   store_owner_order_sn
        String sign="sign="+getSign(paramMap);
        StringBuilder paramter = new StringBuilder();
        for (Map.Entry<String, String> entry : paramMap.entrySet()){
            paramter.append("&"+entry.getKey()+"="+URLEncoder.encode(entry.getValue()));
        }
        String result=HttpUtil.doGet(queryOrder,sign+paramter);
        return result;
    }


    public String cancelOrder(SortedMap<String, String> paramMap){
        String sign="sign="+getSign(paramMap);
        StringBuilder paramter = new StringBuilder();
        for (Map.Entry<String, String> entry : paramMap.entrySet()){
            paramter.append("&"+entry.getKey()+"="+URLEncoder.encode(entry.getValue()));
        }
        String result=HttpUtil.doGet(cancelOrder,sign+paramter);
        return result;
    }


    public String refund(SortedMap<String, String> paramMap){
        String sign="sign="+getSign(paramMap);
        StringBuilder paramter = new StringBuilder();
        for (Map.Entry<String, String> entry : paramMap.entrySet()){
            paramter.append("&"+entry.getKey()+"="+URLEncoder.encode(entry.getValue()));
        }
        String result=HttpUtil.doGet(refundMoney,sign+paramter);
        return result;
    }

    public String weixinQrCodeOffline(SortedMap<String, String> paramMap){
        String sign="sign="+getSign(paramMap);
        StringBuilder paramter = new StringBuilder();
        for (Map.Entry<String, String> entry : paramMap.entrySet()){
            paramter.append("&"+entry.getKey()+"="+URLEncoder.encode(entry.getValue()));
        }
        String result=HttpUtil.doGet(weixinQrCode,sign+paramter);
        return result;
    }
/*
public String notify(String jsonmessage){
        String result=null;
        try {
            JSONObject jsonobject =new  JSONObject(jsonmessage);
            String paramter="&order_sn="+jsonobject.getString("data.order_sn")+
                    "&wosai_store_id="+jsonobject.getString("data.wosai_store_id")+
                    "&status="+jsonobject.getString("data.status")+"&ctime="+jsonobject.getString("data.ctime")
                    +"&order_pay_detail="+jsonobject.getString("data.order_pay_detail")+
                    "&code="+jsonobject.getString("code")+"&msg="+jsonobject.getString("msg");
             result=HttpUtil.doGet(notify_url,paramter);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }
**/


    public String mAlipay(SortedMap<String, String> paramMap){
        String sign="sign="+getSign(paramMap);
        StringBuilder paramter = new StringBuilder();
        for (Map.Entry<String, String> entry : paramMap.entrySet()){
            paramter.append("&"+entry.getKey()+"="+URLEncoder.encode(entry.getValue()));
        }
        String result=HttpUtil.doGet(alipay,sign+paramter);
        return result;

    }


}