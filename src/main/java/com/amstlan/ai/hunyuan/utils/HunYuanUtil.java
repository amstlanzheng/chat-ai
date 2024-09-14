package com.amstlan.ai.hunyuan.utils;
import com.tencentcloudapi.common.SSEResponseModel;
import com.tencentcloudapi.common.AbstractModel;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.hunyuan.v20230901.HunyuanClient;
import com.tencentcloudapi.hunyuan.v20230901.models.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HunYuanUtil {
    // 需要设置环境变量 TENCENTCLOUD_SECRET_ID，值为示例的 AKIDz8krbsJ5**********mLPx3EXAMPL
    private final static String SECRET_ID = System.getenv("TENCENTCLOUD_SECRET_ID");
    // 需要设置环境变量 TENCENTCLOUD_SECRET_KEY，值为示例的 Gu5t9xGAR***********EXAMPLE
    private final static String SECRET_KEY = System.getenv("TENCENTCLOUD_SECRET_KEY");

    public static String getHunYuan(Message[] messageArray) {
        try{
            // 实例化一个认证对象，入参需要传入腾讯云账户 SecretId 和 SecretKey，此处还需注意密钥对的保密
            // 代码泄露可能会导致 SecretId 和 SecretKey 泄露，并威胁账号下所有资源的安全性。以下代码示例仅供参考，建议采用更安全的方式来使用密钥，请参见：https://cloud.tencent.com/document/product/1278/85305
            // 密钥可前往官网控制台 https://console.cloud.tencent.com/cam/capi 进行获取
            Credential cred = new Credential(SECRET_ID, SECRET_KEY);
            // 实例化一个http选项，可选的，没有特殊需求可以跳过
            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("hunyuan.tencentcloudapi.com");
            // 实例化一个client选项，可选的，没有特殊需求可以跳过
            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);
            // 实例化要请求产品的client对象,clientProfile是可选的
            HunyuanClient client = new HunyuanClient(cred, "", clientProfile);
            // 实例化一个请求对象,每个接口都会对应一个request对象
            ChatCompletionsRequest req = new ChatCompletionsRequest();
            // 不是哥们，免费的还想要什么自行车？
            req.setModel("hunyuan-lite");
            req.setMessages(messageArray);

            // 返回的resp是一个ChatCompletionsResponse的实例，与请求对象对应
            ChatCompletionsResponse resp = client.ChatCompletions(req);
            // 输出json格式的字符串回包
            if (resp.isStream()) { // 流式响应
                for (SSEResponseModel.SSE e : resp) {
                    log.info(e.Data);
                }
            } else { // 非流式响应
                return AbstractModel.toJsonString(resp);
            }
        } catch (TencentCloudSDKException e) {
            log.error(e.toString());
        }
        return null;
    }

}
