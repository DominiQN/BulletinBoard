package bulletinboard.htbeyond.com.bulletinboard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Calendar;
import java.util.Date;

import bulletinboard.htbeyond.com.bulletinboard.models.Notice;
import bulletinboard.htbeyond.com.bulletinboard.models.NoticeStorage;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        testMethod();
    }

    private void testMethod() {
        Notice n = new Notice();
        Date date  = Calendar.getInstance().getTime();
        n.setTitle("임의제목")
                .setContent("아무런\n내용")
                .setHighlighted(true)
                .setNoticeId(93478)
                .setViews(10)
                .setWriter("Jerson")
                .setFirstDate(date)
                .setModifiedDate(date);
        NoticeStorage.getInstance(this).addNotice(n);

        Intent i = NoticeActivity.newIntent(MainActivity.this, n.getNoticeId());
        startActivity(i);
        }
}
