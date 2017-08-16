package com.example.Utils;

import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

/**
 * Created by lewis on 2016/7/2.
 */
public class HttpClientConnectionManagerFactory {

    public static HttpClientConnectionManager createHttpClientConnectionManager(){
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory> create()
                .register("https", TrustSSLConnectionSocketFactory.createSSLConnSocketFactory())
                .register("http", new PlainConnectionSocketFactory())
                .build();
        PoolingHttpClientConnectionManager cm =new PoolingHttpClientConnectionManager(socketFactoryRegistry);
        cm.setMaxTotal(400);
        cm.setDefaultMaxPerRoute(50);
        return cm;
    }
}