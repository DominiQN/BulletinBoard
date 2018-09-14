package bulletinboard.htbeyond.com.bulletinboard.network;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class HttpsUrlConnection {

    private HttpsTrustAll mHttpsTrustAll = new HttpsTrustAll();
    private String result;

    public String HttpsUrlConnection(String serviceMode, URL url) {

        InputStream is;
        OutputStream os;
        BufferedReader br;
        BufferedWriter bw;

        mHttpsTrustAll.trustAllHosts();

        try {

            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();

            connection.setRequestProperty("Accept-Charset", "UTF-8");
            connection.setRequestProperty("Context_Type", "application/x-www-form-urlencoded;charset=UTF-8");
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setHostnameVerifier(mHttpsTrustAll.DO_NOT_VERIFY);

            switch (serviceMode) {
                case "search":
                case "get":
                    connection.setRequestMethod("GET");

                    is = connection.getInputStream();
                    br = new BufferedReader(new InputStreamReader(is));
                    String readLine = null;

                    while ((readLine = br.readLine()) != null) {
                        result = result.concat(readLine);
                    }

                    br.close();

                    break;
                case "delete":
                    connection.setRequestMethod("DELETE");
                    break;
                case "patch":
                    connection.setRequestMethod("POST");
                    connection.setReadTimeout(10000);
                    connection.setConnectTimeout(15000);

                    List<NameValuePair> params = new ArrayList<NameValuePair>();
                    params.add(new BasicNameValuePair("noticeNum", "300"));
                    params.add(new BasicNameValuePair("noticeTitle", "300"));
                    params.add(new BasicNameValuePair("noticeContent", "300"));

                    os = connection.getOutputStream();
                    bw = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                    bw.write(getQuery(params));
                    bw.flush();
                    bw.close();
                    os.close();

                case "post":
                    //노티스 타이틀 노티스 컨텐트 바디 붙이기
                    connection.setRequestMethod("POST");
                    break;
                default:
                    break;

            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        return null;
    }

    private String setParam(List<NameValuePair> params) throws UnsupportedEncodingException
    {
        StringBuilder result = new StringBuilder();
        boolean first = true;

        for (NameValuePair pair : params)
        {
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(pair.getName(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(pair.getValue(), "UTF-8"));
        }

        return result.toString();
    }
}
