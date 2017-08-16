package com.example.Utils;

import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 * Created by lewis on 2016/7/2.
 */
public class HttpClientManager {

    public static CloseableHttpClient getHttpClient(){
        RequestConfig requestConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.DEFAULT).build();
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(HttpClientConnectionManagerFactory.createHttpClientConnectionManager()).setDefaultRequestConfig(requestConfig).build();
        return httpClient;
    }
}
