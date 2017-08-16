package com.example.Demo;

import com.example.entity.AbstractEntity;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * @Description:信用卡账单
 * @author kx
 * @version v1.2.0
 */
public class BillDemo extends AbstractCredit {

    //业务参数
    public static final String method = "api.bill.get";//请求接口
    public static final String bizType = "bill";//业务类型
    public static final String callBackUrl = "";//回调地址

    public static  String username = "admin@qq.com";//用户名---需客户指定
    public static  String password = "admin123";//密码---需客户指定
    public static  String billType = "email";//账单类型
    public static  String bankCode = "ALL";//账单银行



    public static Map<String,Object> startService(AbstractEntity s)  {
        Map<String,Object>  json = null;
        try {
            username = s.getUsername();
            password = s.getPassword();
            //启动服务
            BillDemo service  = new BillDemo();
            json = service.process();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return json;
    }

	public Map<String, Object> process() throws Exception{
        System.out.println("开始获取信用卡账单信息");
        Map<String,Object>  json = null;
        try {

            //提交受理请求对象
            List<BasicNameValuePair> reqParam = new ArrayList<BasicNameValuePair>();
            reqParam.add(new BasicNameValuePair("apiKey", apiKey));//API授权
            reqParam.add(new BasicNameValuePair("version", version));//调用的接口版本
            reqParam.add(new BasicNameValuePair("callBackUrl", callBackUrl));//callBackUrl
            reqParam.add(new BasicNameValuePair("method", method));//接口名称

            reqParam.add(new BasicNameValuePair("username", username));//账号
            reqParam.add(new BasicNameValuePair("password",  new String(Base64.encodeBase64(password.getBytes("UTF-8")))));//密码
            reqParam.add(new BasicNameValuePair("billType", billType));//账单类型
            reqParam.add(new BasicNameValuePair("bankCode", bankCode));//账单银行
            reqParam.add(new BasicNameValuePair("sign", getSign(reqParam)));//请求参数签名

            //提交受理请求
         // json = doProcess(reqParam);

        }catch (Exception ex){
            System.out.println("开始获取信用卡账单信息异常：" + ex);
        }
        return json;
	}



    /**
     * 获取业务类型
     */
    @Override
    public String getBizType(){
        return bizType;
    }
}
