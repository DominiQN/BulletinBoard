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
    }

    public Notice setNoticeTitle(String noticeTitle) {
        mNoticeTitle = noticeTitle;
    }

    public Notice setNoticeContent(String noticeContent) {
        mNoticeContent = noticeContent;
    }

    public Notice setNoticePostDate(Date noticePostDate) {
        mNoticePostDate = noticePostDate;
    }

    public Notice setNoticeEditDate(Date noticeEditDate) {
        mNoticeEditDate = noticeEditDate;
    }

    public Notice setNoticeViewNum(int noticeViewNum) {
        mNoticeViewNum = noticeViewNum;
    }

    public Notice setNoticeMemId(String noticeMemId) {
        mNoticeMemId = noticeMemId;
    }

    public Notice setHighlighted(boolean highlighted) {
        mHighlighted = highlighted;
    }
}
