package com.wosai.sqb.storemap;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class HttpProxy {
    private String api_domain;
    private final static String CHARSET_UTF8 = "utf8";
    HttpProxy(String domain){
        api_domain = domain;
    }
    /**
     * 计算字符串的MD5值
     * @param  signStr:签名字符串
     * @return
     */
    public String getSign(String signStr) {
        try{
            String md5 = MD5Util.encryptMd5(signStr);
            return md5;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return  null ;
        }
    }

    /**
     * 终端激活
     * @param  code:激活码
     * @param  vendor_sn:服务商序列号
     * @param  vendor_key:服务商密钥
     * @return  {terminal_sn:"$终端号",terminal_key:"$终端密钥"}
     */
    public  JSONObject activate(String vendor_sn,String vendor_key,String code){
        String url = api_domain + "/terminal/activate";
        JSONObject params = new JSONObject();
        try{
            params.put("code",code);                                          //激活码
            params.put("type","2");                                           //设备类型可以不提供。默认为"2"
            params.put("os_info","Android 5.0");                              //当前系统信息
            params.put("device_id","50a87771-ca8a-4952-a493-9504c39ab495");   //设备唯一身份ID
            params.put("sdk_version","Python SDK v1.0");	 //SDK版本

            String sign = getSign(params.toString() + vendor_key);
            String result = HttpUtil.httpPost(url, params.toString(),sign,vendor_sn);
            JSONObject retObj = new JSONObject(result);
            String resCode = retObj.get("result_code").toString();
            if(!resCode.equals("200"))
                return null;
            String responseStr = retObj.get("biz_response").toString();
            JSONObject terminal = new JSONObject(responseStr);
            if(terminal.get("terminal_sn")==null || terminal.get("terminal_key")==null)
              return null;
            return  terminal;
        }catch (Exception e){
            return null;
        }
    }

    /**
     * 终端签到
     * @param  terminal_sn:终端号
     * @param  terminal_key:终端密钥
     * @return  {terminal_sn:"$终端号",terminal_key:"$终端密钥"}
     */
    public  JSONObject checkin(String terminal_sn,String terminal_key){
        String url = api_domain + "/terminal/checkin";
        JSONObject params = new JSONObject();
        try{
            params.put("terminal_sn",terminal_sn);                            //终端号
            params.put("device_id","50a87771-ca8a-4952-a493-9504c39ab495");   //设备唯一身份ID
            params.put("os_info","Android 5.0");                              //当前系统信息
            params.put("sdk_version","Python SDK v1.0");	                  //SDK版本
            String sign = getSign(params.toString() + terminal_key);
            String result = HttpUtil.httpPost(url, params.toString(),sign,terminal_sn);
            JSONObject retObj = new JSONObject(result);
            String resCode = retObj.get("result_code").toString();
            if(!resCode.equals("200"))
                return null;
            String responseStr = retObj.get("biz_response").toString();
            JSONObject terminal = new JSONObject(responseStr);
            if(terminal.get("terminal_sn")==null || terminal.get("terminal_key")==null)
                return null;
            return  terminal;
        }catch (Exception e){
            return null;
        }
    }
    /**
     * 付款
     * @param  terminal_sn:终端号
     * @param  terminal_key:终端密钥
     * @return
     */
    public  String pay(String terminal_sn,String terminal_key){
        String url = api_domain + "/upay/v2/pay";
        JSONObject params = new JSONObject();
        try{
            params.put("terminal_sn",terminal_sn);           //终端号
            params.put("client_sn","18348290098298292838");  //商户系统订单号,必须在商户系统内唯一；且长度不超过64字节
            params.put("total_amount","1000");               //交易总金额,以分为单位
            params.put("payway","1");	                     //支付方式,1:支付宝 3:微信 4:百付宝 5:京东钱包
            params.put("dynamic_id","130818341921441147");	 //条码内容
            params.put("subject","Pizza");	                 //交易简介
            params.put("operator","kay");	                 //门店操作员

            String sign = getSign(params.toString() + terminal_key);
            String result = HttpUtil.httpPost(url, params.toString(),sign,terminal_sn);

            return  result;
        }catch (Exception e){
            return null;
        }
    }
    /**
     * 退款
     * @param  terminal_sn:终端号
     * @param  terminal_key:终端密钥
     * @return
     */
    public  String refund(String terminal_sn,String terminal_key){
        String url = api_domain + "/upay/v2/refund";
        JSONObject params = new JSONObject();
        try{
            params.put("terminal_sn",terminal_sn);            //收钱吧终端ID
            params.put("sn","7892259488292938");              //收钱吧系统内部唯一订单号
            params.put("client_sn","18348290098298292838");   //商户系统订单号,必须在商户系统内唯一；且长度不超过64字节
            params.put("refund_amount","1000");               //退款金额
            params.put("refund_request_no","23030349");	      //商户退款所需序列号,表明是第几次退款
            params.put("operator","kay");	                  //门店操作员

            String sign = getSign(params.toString() + terminal_key);
            String result = HttpUtil.httpPost(url, params.toString(),sign,terminal_sn);

            return  result;
        }catch (Exception e){
            return null;
        }
    }

    /**
     * 查询
     * @param  terminal_sn:终端号
     * @param  terminal_key:终端密钥
     * @return
     */
    public  String query(String terminal_sn,String terminal_key){
        String url = api_domain + "/upay/v2/query";
        JSONObject params = new JSONObject();
        try{
            params.put("terminal_sn",terminal_sn);           //终端号
            params.put("sn","7892259488292938");             //收钱吧系统内部唯一订单号
            params.put("client_sn","18348290098298292838");  //商户系统订单号,必须在商户系统内唯一；且长度不超过64字节

            String sign = getSign(params.toString() + terminal_key);
            String result = HttpUtil.httpPost(url, params.toString(),sign,terminal_sn);

            return  result;
        }catch (Exception e){
            return null;
        }
    }
    /**
     * 自动撤单
     * @param  terminal_sn:终端号
     * @param  terminal_key:终端密钥
     * @return
     */
    public  String cancel(String terminal_sn,String terminal_key){
        String url = api_domain + "/upay/v2/cancel";
        JSONObject params = new JSONObject();
        try{
            params.put("terminal_sn",terminal_sn);           //终端号
            params.put("sn","7892259488292938");             //收钱吧系统内部唯一订单号
            params.put("client_sn","18348290098298292838");  //商户系统订单号,必须在商户系统内唯一；且长度不超过64字节

            String sign = getSign(params.toString() + terminal_key);
            String result = HttpUtil.httpPost(url, params.toString(),sign,terminal_sn);

            return  result;
        }catch (Exception e){
            return null;
        }
    }
    /**
     * 手动撤单
     * @param  terminal_sn:终端号
     * @param  terminal_key:终端密钥
     * @return
     */
    public  String revoke(String terminal_sn,String terminal_key){
        String url = api_domain + "/upay/v2/revoke";
        JSONObject params = new JSONObject();
        try{
            params.put("terminal_sn",terminal_sn);           //终端号
            params.put("sn","7892259488292938");             //收钱吧系统内部唯一订单号
            params.put("client_sn","18348290098298292838");  //商户系统订单号,必须在商户系统内唯一；且长度不超过64字节

            String sign = getSign(params.toString() + terminal_key);
            String result = HttpUtil.httpPost(url, params.toString(),sign,terminal_sn);

            return  result;
        }catch (Exception e){
            return null;
        }
    }
    /**
     * 预下单
     * @param  terminal_sn:终端号
     * @param  terminal_key:终端密钥
     * @return
     */
    public  String precreate(String terminal_sn,String terminal_key){
        String url = api_domain + "/upay/v2/precreate";
        JSONObject params = new JSONObject();
        try{
            params.put("terminal_sn",terminal_sn);           //收钱吧终端ID
            params.put("client_sn","18348290098298292838");  //商户系统订单号,必须在商户系统内唯一；且长度不超过32字节
            params.put("total_amount","1000");               //交易总金额
            params.put("payway","1");	                     //支付方式
            params.put("dynamic_id","130818341921441147");	 //条码内容
            params.put("subject","Pizza");	                 //交易简介
            params.put("operator","kay");	                 //门店操作员
            params.put("sub_payway","3");	                 //二级支付方式

            String sign = getSign(params.toString() + terminal_key);
            String result = HttpUtil.httpPost(url, params.toString(),sign,terminal_sn);

            return  result;
        }catch (Exception e){
            return null;
        }
    }

}