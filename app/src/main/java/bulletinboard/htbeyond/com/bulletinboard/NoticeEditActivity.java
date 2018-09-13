package bulletinboard.htbeyond.com.bulletinboard;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.TextView;

import bulletinboard.htbeyond.com.bulletinboard.models.Notice;
import bulletinboard.htbeyond.com.bulletinboard.models.NoticeStorage;

public class NoticeEditActivity extends AppCompatActivity {

    private static final String EXTRA_NOTICE_NUM =
            "bulletinboard.htbeyond.com.bulletinboard.notice_num";
    private static final String KEY_TITLE = "title";
    private static final String KEY_CONTENT = "content";
    private static final String KEY_HIGHLIGHT = "highlight";
    private static final int CREATE_NOTICE = -1;

    private Notice mNotice;
    private TextView mTitleTextView;
    private TextView mContentTextView;
    private CheckBox mHighlightedCheckBox;

    /**
     *
     * @param packageContext
     * @param noticeNum
     * 만약 새로 Notice를 만들 시, <br/>
     * NoticeEditActivity.CREATE_NOTICE 를 넣으면 된다.
     * @return
     */
    public static Intent newIntent(Context packageContext, int noticeNum) {
        Intent i = new Intent(packageContext, NoticeEditActivity.class);
        i.putExtra(EXTRA_NOTICE_NUM, noticeNum);

        return i;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_edit);

        mNotice = new Notice();
        int noticeNum = getIntent().getIntExtra(EXTRA_NOTICE_NUM, CREATE_NOTICE);

        mTitleTextView = (TextView) findViewById(R.id.notice_edit_title);
        mContentTextView = (TextView) findViewById(R.id.notice_edit_content);
        mHighlightedCheckBox = (CheckBox) findViewById(R.id.notice_edit_highlight);

        // savedIntanceState에 저장된 값이 있으면 해당 값을, 저장된 값이 없으면 Notice의 값을 보여줌
        if (savedInstanceState != null) {
            if (noticeNum != CREATE_NOTICE) {
                Notice notice =
                        NoticeStorage.getInstance(NoticeEditActivity.this).getNotice(noticeNum);
                mTitleTextView.setText(notice.getNoticeTitle());
                mContentTextView.setText(notice.getNoticeContent());
                mHighlightedCheckBox.setChecked(notice.isHighlighted());
            }
        } else {
            mTitleTextView.setText(
                    savedInstanceState.getString(KEY_TITLE, mNotice.getNoticeTitle()));
            mContentTextView.setText(
                    savedInstanceState.getString(KEY_CONTENT, mNotice.getNoticeTitle()));
            mHighlightedCheckBox.setChecked(
                    savedInstanceState.getBoolean(KEY_HIGHLIGHT, mNotice.isHighlighted()));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_notice_edit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_edit:
                sendNotice();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(KEY_TITLE, mNotice.getNoticeTitle());
        outState.putString(KEY_CONTENT, mNotice.getNoticeContent());
        outState.putBoolean(KEY_HIGHLIGHT, mNotice.isHighlighted());
    }

    private void sendNotice() {
        mNotice.setNoticeTitle(mTitleTextView.getText().toString())
                .setNoticeContent(mContentTextView.getText().toString())
                .setHighlighted(mHighlightedCheckBox.isChecked());
        /*
         * TODO: 작성된 Notice를 서버에 보내 저장한 후, 서버에 정상 저장 되면 다시 불러오기, 저장되지 않았을 시 오류 메시지 만들기
         *
         *
         */
    }
}