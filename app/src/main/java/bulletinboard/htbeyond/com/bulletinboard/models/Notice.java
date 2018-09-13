package bulletinboard.htbeyond.com.bulletinboard.models;

import java.util.Date;

public class Notice {

    private int mNoticeNum;
    private String mNoticeTitle;
    private String mNoticeContent;
    private Date mNoticePostDate;
    private Date mNoticeEditDate;
    private int mNoticeViewNum;
    private String mNoticeMemId;
    private boolean mHighlighted;


    public Notice() {

    }

    public boolean isHighlighted() {
        return mHighlighted;
    }

    public int getNoticeNum() {
        return mNoticeNum;
    }

    public String getNoticeTitle() {
        return mNoticeTitle;
    }

    public String getNoticeContent() {
        return mNoticeContent;
    }

    public Date getNoticePostDate() {
        return mNoticePostDate;
    }

    public Date getNoticeEditDate() {
        return mNoticeEditDate;
    }

    public int getNoticeViewNum() {
        return mNoticeViewNum;
    }

    public String getNoticeMemId() {
        return mNoticeMemId;
    }

    public Notice setNoticeNum(int noticeNum) {
        mNoticeNum = noticeNum;
        return this;
    }

    public Notice setNoticeTitle(String noticeTitle) {
        mNoticeTitle = noticeTitle;
        return this;
    }

    public Notice setNoticeContent(String noticeContent) {
        mNoticeContent = noticeContent;
        return this;
    }

    public Notice setNoticePostDate(Date noticePostDate) {
        mNoticePostDate = noticePostDate;
        return this;
    }

    public Notice setNoticeEditDate(Date noticeEditDate) {
        mNoticeEditDate = noticeEditDate;
        return this;
    }

    public Notice setNoticeViewNum(int noticeViewNum) {
        mNoticeViewNum = noticeViewNum;
        return this;
    }

    public Notice setNoticeMemId(String noticeMemId) {
        mNoticeMemId = noticeMemId;
        return this;
    }

    public Notice setHighlighted(boolean highlighted) {
        mHighlighted = highlighted;
        return this;
    }
}
