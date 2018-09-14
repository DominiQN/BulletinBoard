package bulletinboard.htbeyond.com.bulletinboard.network;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import javax.net.ssl.HttpsURLConnection;

public class HttpRequest extends Service{

    private String mainUrl = "https://192.168.1.122:8444/bdms/noticeBoard";
    private String access_token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJkb2xkYXJpIiwidXNlcl9kZXRhaWwiOnsib2F1dGhfdXNlcl9pbmZvIjp7Im1lbWJlcklkIjoiZG9sZGFyaSIsIm5hbWUiOiLshJzshJ3qtZAiLCJiaXJ0aGRheSI6IjE5ODMtMDktMTMiLCJob21lUGhvbmVOdW1iZXIiOiIyMjIyMiIsImNlbGxQaG9uZU51bWJlciI6ImRvbGRhcmkiLCJlbWFpbCI6ImNoaW5nb29AaGFubWFpbC5jb20iLCJnZW5kZXIiOm51bGwsImF1dGhvcml6ZWREYXRlIjpudWxsfX0sInNjb3BlIjpbInRydXN0IiwicmVhZCIsIndyaXRlIl0sImp0aSI6IjhkYjZhOTNiLWU5YzktNGIyNS04ODNlLWE4Y2M4YTZhZTlkNyIsImNsaWVudF9pZCI6ImJjZ19zZXJ2ZXIifQ.Y0JumkQxgpF5r9Vwx91i6nnL44O6PR4wxbzSsHZjHE0";
    private HttpsTrustAll mHttpsTrustAll = new HttpsTrustAll();

    public class MyBinder extends Binder {
        public HttpRequest getService() {
            return HttpRequest.this;
        }
    }

    IBinder mBinder = new MyBinder();

    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    public String HttpRequestService (String request){

        switch(){
            case : 0 {

            }
        }

    }




    public String searchRequest(String mode, String keyword) {

        String result ="keyword";

        try {

            StringBuilder stringBuilder = new StringBuilder(mainUrl + "/searchNoticePost");
            stringBuilder.append("?access_token=");
            stringBuilder.append(URLEncoder.encode(access_token, "UTF-8"));
            stringBuilder.append("&searchMode=");
            stringBuilder.append(URLEncoder.encode("0", "UTF-8"));
            stringBuilder.append("&pageNum=");
            stringBuilder.append(URLEncoder.encode("0", "UTF-8"));
            stringBuilder.append("&pageSize=");
            stringBuilder.append(URLEncoder.encode("10", "UTF-8"));

            URL url = new URL(stringBuilder.toString());

            mHttpsTrustAll.trustAllHosts();

            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept-Charset", "UTF-8");
            connection.setRequestProperty("Context_Type", "application/x-www-form-urlencoded;charset=UTF-8");
            connection.setDoInput(true);
            connection.setHostnameVerifier(mHttpsTrustAll.DO_NOT_VERIFY);

            InputStream is = connection.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String readLine = null;

            while ((readLine = br.readLine()) != null) {
                result = result.concat(readLine);
            }

            br.close();

        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return result;

    }

    private void getRequest(String keyword){


    }

    private void postRequest(String keyword){

    }

    private void deleteRequest(String keyword){

    }

    private void patchRequest(String keyword){

    }
}