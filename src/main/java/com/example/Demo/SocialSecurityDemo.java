package com.example.Demo;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:社保
 * @author kx
 * @version v1.2.0
 */
public class SocialSecurityDemo extends AbstractCredit {

    //业务参数
    public static final String method = "api.socialsecurity.get";//请求接口
    public static final String bizType = "socialsecurity";//业务类型
    public static final String callBackUrl = "";//回调地址

    public static final String username = "admin";//用户名---需客户指定
    public static final String password = "123456";//密码---需客户指定
    public static final String area = "3100";//地区码---需客户指定
    public static final String realName = "";//真实姓名-非必填---需客户指定
    public static final String otherInfo = "";//其他信息-非必填---需客户指定

    public static void main(String[] args) throws Exception {

        //启动服务
        SocialSecurityDemo service  = new SocialSecurityDemo();
        service.process();

    }

	public void process() throws Exception{
        System.out.println("开始获取社保信息");

        try {

            //提交受理请求对象
            List<BasicNameValuePair> reqParam = new ArrayList<BasicNameValuePair>();
            reqParam.add(new BasicNameValuePair("apiKey", apiKey));//API授权
            reqParam.add(new BasicNameValuePair("version", version));//调用的接口版本
            reqParam.add(new BasicNameValuePair("callBackUrl", callBackUrl));//callBackUrl
            reqParam.add(new BasicNameValuePair("method", method));//接口名称

            reqParam.add(new BasicNameValuePair("username", username));//账号
            reqParam.add(new BasicNameValuePair("password",  new String(Base64.encodeBase64(password.getBytes("UTF-8")))));//密码
            reqParam.add(new BasicNameValuePair("area", area));//地区
            reqParam.add(new BasicNameValuePair("realName", realName));//真实姓名
            reqParam.add(new BasicNameValuePair("otherInfo", otherInfo));//其他信息
            reqParam.add(new BasicNameValuePair("sign", getSign(reqParam)));//请求参数签名

            //提交受理请求
          //  doProcess(reqParam);

        }catch (Exception ex){
            System.out.println("开始获取社保信息异常：" + ex);
        }
	}


    /**
     * 获取业务类型
     */
    @Override
    public String getBizType(){
        return bizType;
    }
}
