package bulletinboard.htbeyond.com.bulletinboard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Calendar;
import java.util.Date;

//TODO: 없애기
import java.util.Random;

import bulletinboard.htbeyond.com.bulletinboard.models.Notice;
import bulletinboard.htbeyond.com.bulletinboard.models.NoticeStorage;
import bulletinboard.htbeyond.com.bulletinboard.models.NoticeStorageTester;

public class MainActivity extends AppCompatActivity {

    Random rnd = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        testMethod();
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
}
