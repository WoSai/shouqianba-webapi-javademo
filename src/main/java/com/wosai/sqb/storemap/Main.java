package com.wosai.sqb.storemap;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by ycc on 15/12/15.
 */
public class Main {

    public static void main(String []args){
        String vendor_sn = "";                              //需要联系收钱吧技术支持申请获得
        String vendor_key = "";                             //需要联系收钱吧技术支持申请获得
        String appid="";                                    //需要登录收钱吧服务商平台选择相应业务场景生成
        String code = "";                                   //需联系收钱吧提供激活码
        String api_domain = "https://api.shouqianba.com";

        HttpProxy hp = new HttpProxy(api_domain);
        JSONObject result = hp.activate(vendor_sn,vendor_key,appid,code); //激活
        if(result != null){
            try{
                String terminal_sn = result.getString("terminal_sn");
                String terminal_key = result.getString("terminal_key");

                JSONObject terminal = hp.checkin(terminal_sn, terminal_key); //签到
                if(terminal != null) {
                    String new_terminal_sn = terminal.getString("terminal_sn");
                    String new_terminal_key = terminal.getString("terminal_key");

                    hp.pay(new_terminal_sn, new_terminal_key);
                    hp.refund(new_terminal_sn, new_terminal_key);
                    hp.query(new_terminal_sn, new_terminal_key);
                    hp.cancel(new_terminal_sn, new_terminal_key);
                    hp.revoke(new_terminal_sn, new_terminal_key);
                    hp.precreate(new_terminal_sn,new_terminal_key);
                }
            }catch (JSONException e){
                e.printStackTrace();
            }
        }

    }

}

