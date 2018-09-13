package bulletinboard.htbeyond.com.bulletinboard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent i = NoticeEditActivity.newIntent(MainActivity.this, 3245644);
        startActivity(i);
    }
}
