package com.wy.hodgepodges;

import com.alibaba.fastjson.JSONObject;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiCallBackRegisterCallBackRequest;
import com.dingtalk.api.response.OapiCallBackRegisterCallBackResponse;
import com.taobao.api.ApiException;

import java.util.Arrays;

/**
 * @author wy
 * @version V1.0
 * @desc
 * @date 2019-11-29 21:30
 */
public class cfdf extends WebApplicationTests{

    public static void main(String[] args)  {

        JSONObject jsonObject = new JSONObject();
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/call_back/register_call_back");
        OapiCallBackRegisterCallBackRequest request = new OapiCallBackRegisterCallBackRequest();
        request.setUrl("http://test001.vaiwan.com/eventreceive");
        request.setAesKey("1234567890123456789012345678901234567890123");
        request.setToken("123456");
        request.setCallBackTag(Arrays.asList("user_add_org", "user_modify_org", "user_leave_org"));
        try {
            OapiCallBackRegisterCallBackResponse response = client.execute(request,"11222");
        } catch (ApiException e) {
            e.printStackTrace();
        }


    }
}
