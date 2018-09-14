package bulletinboard.htbeyond.com.bulletinboard.network;

import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;

public class HttpsUrlBuilder {

    private String mainUrl;
    private String access_token;
    private URL resultURL;
    private StringBuilder stringBuilder;

    public HttpsUrlBuilder(){
        mainUrl = "https://192.168.1.122:8444/bdms/noticeBoard";
        access_token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJkb2xkYXJpIiwidXNlcl9kZXRhaWwiOnsib2F1dGhfdXNlcl9pbmZvIjp7Im1lbWJlcklkIjoiZG9sZGFyaSIsIm5hbWUiOiLshJzshJ3qtZAiLCJiaXJ0aGRheSI6IjE5ODMtMDktMTMiLCJob21lUGhvbmVOdW1iZXIiOiIyMjIyMiIsImNlbGxQaG9uZU51bWJlciI6ImRvbGRhcmkiLCJlbWFpbCI6ImNoaW5nb29AaGFubWFpbC5jb20iLCJnZW5kZXIiOm51bGwsImF1dGhvcml6ZWREYXRlIjpudWxsfX0sInNjb3BlIjpbInRydXN0IiwicmVhZCIsIndyaXRlIl0sImp0aSI6IjhkYjZhOTNiLWU5YzktNGIyNS04ODNlLWE4Y2M4YTZhZTlkNyIsImNsaWVudF9pZCI6ImJjZ19zZXJ2ZXIifQ.Y0JumkQxgpF5r9Vwx91i6nnL44O6PR4wxbzSsHZjHE0";
        stringBuilder = new StringBuilder(mainUrl);
    }

    public URL searchBuildUrl(int searchMode, int pageSize, int pageNum) {

        try {
            stringBuilder.append("/searchNoticePost");
            stringBuilder.append("?access_token=");
            stringBuilder.append(URLEncoder.encode(access_token, "UTF-8"));
            stringBuilder.append("&searchMode=");
            stringBuilder.append(URLEncoder.encode(String.valueOf(searchMode), "UTF-8"));
            stringBuilder.append("&pageNum=");
            stringBuilder.append(URLEncoder.encode(String.valueOf(pageSize), "UTF-8"));
            stringBuilder.append("&pageSize=");
            stringBuilder.append(URLEncoder.encode(String.valueOf(pageNum), "UTF-8"));
            URL url = new URL(stringBuilder.toString());
            return url;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }
    public URL getBuildUrl(int noticeNum){
        try {
            stringBuilder.append("/searchNoticePost");
            stringBuilder.append("?access_token=");
            stringBuilder.append(URLEncoder.encode(access_token, "UTF-8"));
            stringBuilder.append("&noticeNum=");
            stringBuilder.append(URLEncoder.encode(String.valueOf(noticeNum), "UTF-8"));
            URL url = new URL(stringBuilder.toString());
            return url;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;

    }

    public URL deleteBuildUrl(int noticeNum){
        try {
            stringBuilder.append("/searchNoticePost");
            stringBuilder.append("?access_token=");
            stringBuilder.append(URLEncoder.encode(access_token, "UTF-8"));
            stringBuilder.append("&noticeNum=");
            stringBuilder.append(URLEncoder.encode(String.valueOf(noticeNum), "UTF-8"));
            URL url = new URL(stringBuilder.toString());
            return url;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;

    }

//    public URL postBuildUrl(){
//        try {
//            stringBuilder.append("/searchNoticePost");
//            stringBuilder.append("?access_token=");
//            stringBuilder.append(URLEncoder.encode(access_token, "UTF-8"));
//            stringBuilder.append("&noticeNum=");
//            stringBuilder.append(URLEncoder.encode(String.valueOf(noticeNum), "UTF-8"));
//            URL url = new URL(stringBuilder.toString());
//            return url;
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//
//        return null;
//
//    }
//
//
//    public URL patchBuildUrl(){
//
//    }
}
