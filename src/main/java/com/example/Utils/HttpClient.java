package com.example.Utils;


import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.net.URI;
import java.util.List;

/**
 * Created by lewis on 2016/7/2.
 */
public class HttpClient {

    private CloseableHttpClient httpClient;

    public HttpClient(){
        httpClient=HttpClientManager.getHttpClient();
    }

    /**
     * 获取页面
     *
     * @param url
     * @return
     */
    public String getPage(String url){
       return doGet(url,null);
    }

    /**
     * 获取页面
     *
     * @param url
     * @return
     */
    public String getPage(String url,List<BasicNameValuePair> params){
        return doGet(url,params);
    }

    /**
     * 获取页面
     *
     * @param url
     * @return
     */
    public String getPage(String url,HttpMethod httpMethod,List<BasicNameValuePair> params){
        if(HttpMethod.GET==httpMethod){
           return doGet(url,params);
        }else{
           return doPost(url,params);
        }
    }




    /**
     * 获取页面
     *
     * @param url
     * @return
     */
    public BufferedImage getImage(String url){
        BufferedImage bufferedImage=null;
        HttpEntity entity = null;
        CloseableHttpResponse response=null;
        HttpGet method=new HttpGet(url);
        try{
            response=httpClient.execute(method);
            if(response.getStatusLine().getStatusCode()== HttpStatus.SC_OK){
                entity=response.getEntity();
                bufferedImage=ImageIO.read(entity.getContent());
            }
        }catch (Exception ex){
            throw new RuntimeException(ex);
        }
        return bufferedImage;
    }



    /**
     * get 请求
     *
     * @param url
     * @return
     */
    private String doGet(String url,List<BasicNameValuePair> params){
        String result="";
        HttpEntity entity = null;
        CloseableHttpResponse response=null;
        HttpGet method=new HttpGet(url);
        try{
            if(params != null && params.isEmpty()){
                String str = EntityUtils.toString(new UrlEncodedFormEntity(params, Consts.UTF_8.toString()));
                method.setURI(new URI(method.getURI().toString() + "?" + str));
            }
            response=httpClient.execute(method);
            if(response.getStatusLine().getStatusCode()== HttpStatus.SC_OK){
                entity=response.getEntity();
                result= EntityUtils.toString(entity);
            }
        }catch (Exception ex){
            throw new RuntimeException(ex);
        }
        return result;
    }

    /**
     * post 请求
     *
     * @param url
     * @return
     */
    public String doPost(String url,List<BasicNameValuePair> params){
        String result="";
        HttpEntity entity = null;
        CloseableHttpResponse response=null;
        HttpPost method=new HttpPost(url);
        try{
            if(params != null && !params.isEmpty()){
                UrlEncodedFormEntity encodedFormEntity=new UrlEncodedFormEntity(params, Consts.UTF_8.toString());
                method.setEntity(encodedFormEntity);
            }
            response=httpClient.execute(method);
            if(response.getStatusLine().getStatusCode()== HttpStatus.SC_OK){
                entity=response.getEntity();
                result= EntityUtils.toString(entity);
            }
        }catch (Exception ex){
            throw new RuntimeException(ex);
        }
        return result;
    }
}
