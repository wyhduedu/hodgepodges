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
public class cfdf {
    public static void main(String[] args) throws ApiException {

        JSONObject jsonObject = new JSONObject();
//        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/robot/send?access_token=ba6f07335e953814413e7ad42308f4c7a4cadd77f87a5989f8a2303743763cd5");
//        OapiRobotSendRequest request = new OapiRobotSendRequest();
//        request.setMsgtype("text");
//        OapiRobotSendRequest.Text text = new OapiRobotSendRequest.Text();
//        text.setContent("嗷嗷");
//        request.setText(text);
//        OapiRobotSendRequest.At at = new OapiRobotSendRequest.At();
//        at.setAtMobiles(Arrays.asList("13588852087"));
//        request.setAt(at);

//        request.setMsgtype("link");
//        OapiRobotSendRequest.Link link = new OapiRobotSendRequest.Link();
//        link.setMessageUrl("https://www.dingtalk.com/");
//        link.setPicUrl("");
//        link.setTitle("时代的火车向前开");
//        link.setText("这个即将发布的新版本，创始人xx称它为“红树林”。\n" +
//                "而在此之前，每当面临重大升级，产品经理们都会取一个应景的代号，这一次，为什么是“红树林");
//        request.setLink(link);

//        request.setMsgtype("markdown");
//        OapiRobotSendRequest.Markdown markdown = new OapiRobotSendRequest.Markdown();
//        markdown.setTitle("杭州天气");
//        markdown.setText("#### 杭州天气 @13588852087\n" +
//                "> 9度，西北风1级，空气良89，相对温度73%\n\n" +
//                "> ![screenshot](https://gw.alicdn.com/tfs/TB1ut3xxbsrBKNjSZFpXXcXhFXa-846-786.png)\n"  +
//                "> ###### 10点20分发布 [天气](http://www.thinkpage.cn/) \n");
//        request.setMarkdown(markdown);
//        OapiRobotSendResponse response = client.execute(request);
//        System.out.println(":daszd");

        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/call_back/register_call_back");
        OapiCallBackRegisterCallBackRequest request = new OapiCallBackRegisterCallBackRequest();
        request.setUrl("http://test001.vaiwan.com/eventreceive");
        request.setAesKey("1234567890123456789012345678901234567890123");
        request.setToken("123456");
        request.setCallBackTag(Arrays.asList("user_add_org", "user_modify_org", "user_leave_org"));
        OapiCallBackRegisterCallBackResponse response = client.execute(request,"11222");


    }
}
