package com.example.Demo;

import com.example.entity.AbstractEntity;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:央行征信
 * @author kx
 * @version v1.2.0
 */
public class CreditDemo extends AbstractCredit {

    //业务参数
    public static final String method = "api.credit.get";//请求接口
	public static final String bizType = "credit";//业务类型
    public static final String callBackUrl = "";//回调地址

	public static  String username = "admin";//用户名---需客户指定
	public static  String password = "admin123";//密码---需客户指定
	public static  String middleAuthCode = "123456";//央行征信身份验证码---需客户指定


    public static void startService(AbstractEntity s) {

        try {
            username = s.getUsername();
            password = s.getPassword();
            middleAuthCode = s.getMiddleAuthCode();
            //启动服务
            CreditDemo service = new CreditDemo();
            service.process();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	public void process() throws Exception{
        System.out.println("开始获取央行征信信息");

        try {

            //提交受理请求对象
            List<BasicNameValuePair> reqParam = new ArrayList<BasicNameValuePair>();
            reqParam.add(new BasicNameValuePair("apiKey", apiKey));//API授权
            reqParam.add(new BasicNameValuePair("version", version));//调用的接口版本
            reqParam.add(new BasicNameValuePair("callBackUrl", callBackUrl));//callBackUrl
            reqParam.add(new BasicNameValuePair("method", method));//接口名称

            reqParam.add(new BasicNameValuePair("username", username));//账号
            reqParam.add(new BasicNameValuePair("password",  new String(Base64.encodeBase64(password.getBytes("UTF-8")))));//密码
            reqParam.add(new BasicNameValuePair("middleAuthCode", middleAuthCode));//央行征信身份验证码
            reqParam.add(new BasicNameValuePair("sign", getSign(reqParam)));//请求参数签名

            //提交受理请求
           // doProcess(reqParam);

        }catch (Exception ex){
            System.out.println("开始获取央行征信信息异常：" + ex);
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
