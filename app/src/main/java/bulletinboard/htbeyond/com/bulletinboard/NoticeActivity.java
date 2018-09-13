package bulletinboard.htbeyond.com.bulletinboard;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class NoticeActivity extends AppCompatActivity {

    private static final String EXTRA_NOTICE_NUM =
            "bulletinboard.htbeyond.com.bulletinboard.notice_num";


    public static Intent newIntent(Context packageContext, int noticeNum) {
        Intent i = new Intent(packageContext, NoticeEditActivity.class);
        i.putExtra(EXTRA_NOTICE_NUM, noticeNum);

        return i;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_notice, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case null :
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
