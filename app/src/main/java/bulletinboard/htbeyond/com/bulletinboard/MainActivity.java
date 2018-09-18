package bulletinboard.htbeyond.com.bulletinboard;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.gson.JsonObject;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import bulletinboard.htbeyond.com.bulletinboard.models.Notice;
import bulletinboard.htbeyond.com.bulletinboard.models.NoticeStorage;
import bulletinboard.htbeyond.com.bulletinboard.models.NoticeStorageTester;
import bulletinboard.htbeyond.com.bulletinboard.models.PostReqBody;
import bulletinboard.htbeyond.com.bulletinboard.network.APIClient;
import bulletinboard.htbeyond.com.bulletinboard.network.NoticeRepo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//TODO: 없애기

public class MainActivity extends AppCompatActivity {

    Random rnd = new Random();
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        testMethod();
        
        tv = (TextView) findViewById(R.id.textView);
    }

    private void testMethod() {
        Notice n = new Notice();
        Date date  = Calendar.getInstance().getTime();
        NoticeStorageTester storage = NoticeStorage.getInstance(this);
        for (int i = 0; i < 100; i++)
            storage.addNotice(setRandomNotice());

        Intent i = ListActivity.newIntent(MainActivity.this);
        startActivity(i);
    }

    private Notice setRandomNotice() {
        Notice n = new Notice();
        Date date  = Calendar.getInstance().getTime();
        n.setTitle("임의제목")
                .setContent("아무런\n내용")
                .setHighlighted(true)
                .setNoticeId(rnd.nextInt(10000))
                .setViews(rnd.nextInt(1000))
                .setWriter("Jerson")
                .setFirstDate(date)
                .setModifiedDate(date);
        return n;
    }

    public void searchNotice() {

        Call<JsonObject> res = APIClient.getInstance().getService().getNotices(getString(R.string.access_token) , 10, 0,0);
        res.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                Log.d("Retrofit", response.toString());
                if (response.body() != null) {
                    tv.setText(response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.e("Err", t.getMessage());
            }
        });
    }

    public void getNotice(View view) {

        Call<NoticeRepo> res = APIClient.getInstance().getService().getNotice(getString(R.string.access_token), 250);
        res.enqueue(new Callback<NoticeRepo>() {
            @Override
            public void onResponse(Call<NoticeRepo> call, Response<NoticeRepo> response) {
                Log.d("Retrofit", response.toString());
                if (response.body() != null){
//               reponse.body().toString() ->
                }
            }

            @Override
            public void onFailure(Call<NoticeRepo> call, Throwable t) {
                Log.e("Err", t.getMessage());
            }
        });
    }

    public void postNotice(View view) {

        // TODO: TOAST 띄워주기
        Call<NoticeRepo> res = APIClient.getInstance().getService().postNotice(getString(R.string.access_token),new PostReqBody("0916title", "0916content"));
        res.enqueue(new Callback<NoticeRepo>() {
            @Override
            public void onResponse(Call<NoticeRepo> call, Response<NoticeRepo> response) {
                Log.d("Retrofit", response.toString());
                if (response.body() != null)
                    tv.setText(response.body().toString());
            }

            @Override
            public void onFailure(Call<NoticeRepo> call, Throwable t) {
                Log.e("Err", t.getMessage());
            }
        });
    }

    public void patchNotice(View view) {

        Call<NoticeRepo> res = APIClient.getInstance().getService().updateNotice(getString(R.string.access_token), new PostReqBody(250, "0916patchTItle", "0916patchContent"));
        res.enqueue(new Callback<NoticeRepo>() {
            @Override
            public void onResponse(Call<NoticeRepo> call, Response<NoticeRepo> response) {
                Log.d("Retrofit", response.toString());
                if (response.body() != null)
                    tv.setText(response.body().toString());
            }

            @Override
            public void onFailure(Call<NoticeRepo> call, Throwable t) {
                Log.e("Err", t.getMessage());
            }
        });
    }

    public void deleteNotice(View view) {

        Call<NoticeRepo> res = APIClient.getInstance().getService().deleteNotice(getString(R.string.access_token), 250);
        res.enqueue(new Callback<NoticeRepo>() {
            @Override
            public void onResponse(Call<NoticeRepo> call, Response<NoticeRepo> response) {
                Log.d("Retrofit", response.toString());
                if (response.body() != null)
                    tv.setText(response.body().toString());
            }

            @Override
            public void onFailure(Call<NoticeRepo> call, Throwable t) {
                Log.e("Err", t.getMessage());
            }
        });
    }

}
