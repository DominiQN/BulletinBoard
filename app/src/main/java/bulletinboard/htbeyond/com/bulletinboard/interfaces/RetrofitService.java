package bulletinboard.htbeyond.com.bulletinboard.interfaces;

import com.google.gson.JsonObject;

import bulletinboard.htbeyond.com.bulletinboard.models.PostReqBody;
import bulletinboard.htbeyond.com.bulletinboard.network.NoticeRepo;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RetrofitService {
    @Headers({"Accept: application/json"})
    @GET("bdms/noticeBoard/searchNoticePost")
    Call<JsonObject> searchNotice(
            @Query("access_token") String access_token
            , @Query("pageSize") int pageSize
            , @Query("pageNum") int pageNum
            , @Query("searchMode") int searchMode);


    @Headers({"Accept: application/json"})
    @GET("bdms/noticeBoard/getNoticePost")
    Call<NoticeRepo> getNotice(
            @Query("access_token") String access_token
            , @Query("noticeNum") int noticeNum);


    @POST("bdms/noticeBoard/postNoticePost")
    Call<NoticeRepo> postNotice(
            @Query("access_token") String access_token
            , @Body PostReqBody postReqBody);

    @POST("bdms/noticeBoard/patchNoticePost")
    Call<NoticeRepo> patchNotice(
            @Query("access_token") String access_token
            , @Body PostReqBody postReqBody);

    @DELETE("bdms/noticeBoard/deleteNoticePost")
    Call<NoticeRepo> deleteNotice(
            @Query("access_token") String access_token
            , @Query("noticeNum") int noticeNum);

}
