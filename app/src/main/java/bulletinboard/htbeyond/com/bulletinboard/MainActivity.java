package bulletinboard.htbeyond.com.bulletinboard;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import bulletinboard.htbeyond.com.bulletinboard.network.HttpRequest;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(this, HttpRequest.class);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    }

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0: {
                    TextView tv = (TextView) findViewById(R.id.textView);
                    tv.setText(msg.obj.toString());
                }
                break;
            }
        }
    };

    private HttpRequest mHttpRequest;
    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            mHttpRequest = ((HttpRequest.MyBinder) iBinder).getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            mHttpRequest = null;
        }
    };


//    public void onButton2Clicked(View v) {
//
//        new Thread() {
//            public void run(){
//                String result = mHttpRequest.searchRequest("good");
//                Message msg = Message.obtain(handler, 0, result);
//                handler.sendMessage(msg);
//                }
//        }.start();
//    }
}