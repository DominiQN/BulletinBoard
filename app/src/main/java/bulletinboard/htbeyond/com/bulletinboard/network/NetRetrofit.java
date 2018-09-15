package bulletinboard.htbeyond.com.bulletinboard.network;

import java.security.cert.CertificateException;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import bulletinboard.htbeyond.com.bulletinboard.interfaces.RetrofitService;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetRetrofit {
    private static NetRetrofit ourInstance = new NetRetrofit();
    public static NetRetrofit getInstance() {
        return ourInstance;
    }
    private NetRetrofit() {
    }

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://192.168.1.122:8444/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(getUnsafeOkHttpClient().build())
            .build();

    RetrofitService service = retrofit.create(RetrofitService.class);

    public RetrofitService getService() {
        return service;
    }

    public static OkHttpClient.Builder getUnsafeOkHttpClient() {
        try {
            final TrustManager[] trustAllCerts = new TrustManager[] {
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };

            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());

            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.sslSocketFactory(sslSocketFactory, (X509TrustManager)trustAllCerts[0]);
            builder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
            return builder;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
