package bulletinboard.htbeyond.com.bulletinboard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import bulletinboard.htbeyond.com.bulletinboard.models.Notice;

public class NoticeEditActivity extends AppCompatActivity {

    private static final String KEY_TITLE = "title";
    private static final String KEY_CONTENT = "content";
    private static final String KEY_HIGHLIGHT = "highlight";

    private Notice mNotice;
    private TextView mTitleTextView;
    private TextView mContentTextView;
    private CheckBox mHighlightedCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_edit);


        mTitleTextView = (TextView) findViewById(R.id.notice_edit_title);
        mTitleTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void afterTextChanged(Editable s) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mNotice.setNoticeTitle(s.toString());
            }
        });

        mContentTextView = (TextView) findViewById(R.id.notice_edit_content);
        mContentTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void afterTextChanged(Editable s) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mNotice.setNoticeContent(s.toString());
            }
        });
        mHighlightedCheckBox = (CheckBox) findViewById(R.id.notice_edit_highlight);
        mHighlightedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mNotice.setHighlighted(isChecked);
            }
        });

        // savedIntanceState에 저장된 값이 있으면 해당 값을, 저장된 값이없으면 Notice의 값을 보여줌
        mTitleTextView.setText(
                savedInstanceState.getString(KEY_TITLE, mNotice.getNoticeTitle()));
        mContentTextView.setText(
                savedInstanceState.getString(KEY_CONTENT, mNotice.getNoticeTitle()));
        mHighlightedCheckBox.setChecked(
                savedInstanceState.getBoolean(KEY_HIGHLIGHT, mNotice.isHighlighted()));
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
                /*
                 * TODO: 작성된 Notice를 서버에 보내 저장한 후, 서버에 정상 저장 되면 다시 불러오기, 저장되지 않았을 시 오류 메시지 만들기
                 */
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
}
