package bulletinboard.htbeyond.com.bulletinboard.interfaces;

import java.util.ArrayList;

import bulletinboard.htbeyond.com.bulletinboard.network.NoticeRepo;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface BulletinApiinterface {
    @Headers({"Accept: application/json"})
    @GET("bdms/noticeBoard/searchPost")
    Call<ArrayList<NoticeRepo>> searchNotice(
            @Field("pageSize") int pageSize
            , @Field("pageNum") int pageNum
            , @Field("searchMode") int searchMode);


    @Headers({"Accept: application/json"})
    @GET("bdms/noticeBoard/searchPost")
    Call<NoticeRepo> getNotice(
            @Field("noticeNum") int noticeNum);


    @POST("bdms/noticeBoard/searchPost")
    Call<NoticeRepo> postNotice(
            @Field("noticeTitle") String noticeTitle
            , @Field("noticeContent") String noticeContent);

    @POST("bdms/noticeBoard/searchPost")
    Call<NoticeRepo> patchNotice(
            @Field("noticeNum") int noticeNum
            , @Field("noticeTitle") String noticeTitle
            , @Field("noticeContent") String noticeContent);

    @DELETE("bdms/noticeBoard/deletePost")
    Call<NoticeRepo> deleteNotice(
            @Field("noticeNum") int noticeNum);

    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://192.168.1.122:8444/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
