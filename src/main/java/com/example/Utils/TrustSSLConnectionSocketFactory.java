package com.example.Utils;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.ssl.SSLContexts;

import javax.net.ssl.SSLContext;
import java.security.cert.CertificateException;

/**
 * Created by lewis on 2016/7/2.
 */
public class TrustSSLConnectionSocketFactory {

    /**
     * ����SSL��ȫ����
     *
     * @return
     */
    public static SSLConnectionSocketFactory createSSLConnSocketFactory() {
        SSLConnectionSocketFactory connectionSocketFactory=null;
        try{
            SSLContext sslcontext = SSLContexts.custom()
                    //���Ե��Է�������֤���У��
                    .loadTrustMaterial(new TrustStrategy() {
                        @Override
                        public boolean isTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                            return true;
                        }
                    }).build();
            connectionSocketFactory = new SSLConnectionSocketFactory(sslcontext, SSLConnectionSocketFactory.getDefaultHostnameVerifier());
        }catch (Exception ex){
            throw new RuntimeException(ex);
        }
       return connectionSocketFactory;
    }
}
