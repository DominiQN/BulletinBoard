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


    public int getNoticeNum() {
        return mNoticeNum;
    }

    public void setNoticeNum(int noticeNum) {
        mNoticeNum = noticeNum;
    }

    public String getNoticeTitle() {
        return mNoticeTitle;
    }

    public void setNoticeTitle(String noticeTitle) {
        mNoticeTitle = noticeTitle;
    }

    public String getNoticeContent() {
        return mNoticeContent;
    }

    public void setNoticeContent(String noticeContent) {
        mNoticeContent = noticeContent;
    }

    public Date getNoticePostDate() {
        return mNoticePostDate;
    }

    public void setNoticePostDate(Date noticePostDate) {
        mNoticePostDate = noticePostDate;
    }

    public Date getNoticeEditDate() {
        return mNoticeEditDate;
    }

    public void setNoticeEditDate(Date noticeEditDate) {
        mNoticeEditDate = noticeEditDate;
    }

    public int getNoticeViewNum() {
        return mNoticeViewNum;
    }

    public void setNoticeViewNum(int noticeViewNum) {
        mNoticeViewNum = noticeViewNum;
    }

    public String getNoticeMemId() {
        return mNoticeMemId;
    }

    public void setNoticeMemId(String noticeMemId) {
        mNoticeMemId = noticeMemId;
    }

    public boolean isHighlighted() {
        return mHighlighted;
    }

    public void setHighlighted(boolean highlighted) {
        mHighlighted = highlighted;
    }
}
