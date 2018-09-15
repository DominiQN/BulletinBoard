package bulletinboard.htbeyond.com.bulletinboard.models;

import android.text.format.DateFormat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Notice {

    private int mNoticeId;
    private String mTitle;
    private String mContent;
    private Date mFirstDate;
    private Date mModifiedDate;
    private int mViews;
    private String mWriter;
    private boolean mHighlighted;


    public Notice() {

    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Notice)) {
            return false;
        }
        Notice target = (Notice) obj;
        if (mNoticeId == target.mNoticeId) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isHighlighted() {
        return mHighlighted;
    }

    public int getNoticeId() {
        return mNoticeId;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getContent() {
        return mContent;
    }

    public Date getFirstDate() {
        return mFirstDate;
    }

    public String getFirstDateToString() {
        return dateToString(mFirstDate);
    }

    public Date getModifiedDate() {
        return mModifiedDate;
    }

    public String getModifiedDateToString() {
        return dateToString(mModifiedDate);
    }

    public int getViews() {
        return mViews;
    }

    public String getWriter() {
        return mWriter;
    }

    public Notice setNoticeId(int noticeId) {
        mNoticeId = noticeId;
        return this;
    }

    public Notice setTitle(String title) {
        mTitle = title;
        return this;
    }

    public Notice setContent(String content) {
        mContent = content;
        return this;
    }

    public Notice setFirstDate(Date firstDate) {
        mFirstDate = firstDate;
        return this;
    }

    public Notice setModifiedDate(Date modifiedDate) {
        mModifiedDate = modifiedDate;
        return this;
    }

    public Notice setViews(int views) {
        mViews = views;
        return this;
    }

    public Notice setWriter(String writer) {
        mWriter = writer;
        return this;
    }

    public Notice setHighlighted(boolean highlighted) {
        mHighlighted = highlighted;
        return this;
    }

    private String dateToString(Date date) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN_MR2) {
            String formatString = DateFormat.getBestDateTimePattern(Locale.getDefault(), "yyyyMMddhhmm");
            SimpleDateFormat dateFormatter = new SimpleDateFormat(formatString);
            String dateString = dateFormatter.format(date);

            return dateString;
        } else {
            String formatString = "yyyy. MM. dd a hh:mm:ss";
            SimpleDateFormat dateFormatter = new SimpleDateFormat(formatString);
            String dateString = dateFormatter.format(date);

            return dateString;
        }
    }
}
