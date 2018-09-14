package bulletinboard.htbeyond.com.bulletinboard.network;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Date;
import java.util.GregorianCalendar;

import bulletinboard.htbeyond.com.bulletinboard.models.Notice;

public class NoticeJSONWrapper {

    private static final String TAG = "NoticeJSONWrapper";

    protected final JSONObject mJSONObject;

    private final String NOTICE_ID = "noticeNum";
    private final String TITLE = "noticeTitle";
    private final String CONTENT = "noticeContent";
    private final String FIRST_DATE = "noticePostDate";
    private final String MODIFIED_DATE = "noticeEditDate";
    private final String VIEWS = "noticeViewNum";
    private final String WRITER = "noticeMemId";
    private final String HIGHLIGHTED = "highlight";
    private final String YEAR = "year";
    private final String MONTH = "month";
    private final String DAY_OF_MONTH = "monthValue";
    private final String HOUR = "hour";
    private final String MINUTE = "minute";


    public NoticeJSONWrapper(JSONObject jsonObject) {
        mJSONObject = jsonObject;
    }

    public Notice getNotice() {
        Notice notice = new Notice();

        int noticeId = 0;
        String title = null;
        String content = null;
        Date firstDate = null;
        Date modifiedDate = null;
        int views = 0;
        String writer = null;
        boolean highlighted = false;

        try {
            noticeId = mJSONObject.getInt(NOTICE_ID);
        } catch (JSONException e) {
            Log.w(TAG, "catched JSONException from NOTICE_ID :\n" + getExceptionToString(e)); }
        try {
            title = mJSONObject.getString(TITLE);
        } catch (JSONException e) {
            Log.w(TAG, "catched JSONException from TITLE :\n" + getExceptionToString(e)); }
        try {
            content = mJSONObject.getString(CONTENT);
        } catch (JSONException e) {
            Log.w(TAG, "catched JSONException from CONTENT :\n" + getExceptionToString(e)); }
        try {
            firstDate = getDate(
                    mJSONObject.getJSONObject(FIRST_DATE));
        } catch (JSONException e) {
            Log.w(TAG, "catched JSONException from FIRST_DATE :\n" + getExceptionToString(e)); }
        try {
            modifiedDate = getDate(
                    mJSONObject.getJSONObject(MODIFIED_DATE));
        } catch (JSONException e) {
            Log.w(TAG, "catched JSONException from MODIFIED_DATE :\n" + getExceptionToString(e));
        }
        try {
            views = mJSONObject.getInt(VIEWS);
        } catch (JSONException e) {
            Log.w(TAG, "catched JSONException from VIEWS :\n" + getExceptionToString(e));
        }
        try {
            writer = mJSONObject.getString(WRITER);
        } catch (JSONException e) {
            Log.w(TAG, "catched JSONException from WRITER :\n" + getExceptionToString(e));
        }
        try {
            highlighted = mJSONObject.getBoolean(HIGHLIGHTED);
        } catch (JSONException e) {
            Log.w(TAG, "catched JSONException from HIGHLIGHTED :\n" + getExceptionToString(e));
        }

        notice.setNoticeId(noticeId)
                .setTitle(title)
                .setContent(content)
                .setFirstDate(firstDate)
                .setModifiedDate(modifiedDate)
                .setViews(views)
                .setWriter(writer)
                .setHighlighted(highlighted);

        return notice;
    }

    private Date getDate(JSONObject localDateTime) throws JSONException {
        int year = localDateTime.getInt(YEAR);
        int month = localDateTime.getInt(MONTH);
        int dayOfMonth = localDateTime.getInt(DAY_OF_MONTH);
        int hour = localDateTime.getInt(HOUR);
        int minute = localDateTime.getInt(MINUTE);

        Date date = new GregorianCalendar(year, month, dayOfMonth, hour, minute)
                .getTime();

        return date;
    }

    private String getExceptionToString(JSONException e) {
        Writer writer = new StringWriter();
        e.printStackTrace(new PrintWriter(writer));
        return writer.toString();
    }

}
