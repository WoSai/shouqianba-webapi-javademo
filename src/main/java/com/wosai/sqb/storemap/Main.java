package com.wosai.sqb.storemap;

import java.util.HashMap;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Created by ycc on 15/12/15.
 */
public class Main {
    public static void main(String []args){

//三种方法的测试
// //query
//        HttpProxy hp=new HttpProxy();
//        SortedMap<String, String> paramMap=new TreeMap();
//        paramMap.put("wosai_store_id","00000071001200200000046");
//        paramMap.put("wosai_app_id","1001200200000046");
//        paramMap.put("channel_id","14454086076608830");
//        paramMap.put("wosai_order_sn","14454086076608830");
//        paramMap.put("store_owner_order_sn","14454086076608830");
//        String result=hp.queryOrder(paramMap);
//        System.out.print(result);

//Refund
//        HttpProxy hp=new HttpProxy();
//        SortedMap<String, String> paramMap=new TreeMap();
//        paramMap.put("wosai_store_id","00000071001200200000046");
//        paramMap.put("wosai_app_id","1001200200000046");
//        paramMap.put("channel_id","14454086076608830");
//        paramMap.put("wosai_order_sn","14454086076608830");
//        paramMap.put("refund_fee","100");
//        String result=hp.refund(paramMap);
//        System.out.print(result);


//weixinQRCode
        HttpProxy hp=new HttpProxy();
        SortedMap<String, String> paramMap=new TreeMap();
        paramMap.put("wosai_store_id","00000071001200200000046");
        paramMap.put("wosai_app_id","1001200200000046");
        paramMap.put("channel_id","14454086076608830");
        paramMap.put("store_own_order_id","14454086076608830");
        paramMap.put("total_fee","1000");
        paramMap.put("subject","14454086076608830");
        paramMap.put("notify_url","http://sdfs");
        paramMap.put("operator","14454086076608830");
        paramMap.put("remark","100");
        paramMap.put("terminal_id","100");
        paramMap.put("customer_store_id","100");
        paramMap.put("deviceId","100");
        String result=hp.weixinQrCodeOffline(paramMap);
        System.out.print(result);
    }

}

