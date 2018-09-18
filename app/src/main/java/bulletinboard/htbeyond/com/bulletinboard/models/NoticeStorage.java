package bulletinboard.htbeyond.com.bulletinboard.models;

import android.content.Context;

import java.util.List;

public class NoticeStorage {
    private static NoticeStorage sNoticeStorage;

    private List<Notice> mNotices;
    private Context mContext;
    // private HTTP가지고 노는 거 변수

    public static NoticeStorage getInstance(Context context) {
        if (sNoticeStorage == null) {
            sNoticeStorage = new NoticeStorage(context);
        }
        // mNotices = 서버에서 일정량? 가져오기?
        return sNoticeStorage;
    }

    private NoticeStorage(Context context) {
        mContext = context.getApplicationContext();
    }

    // 서버에 Notice 추가
    public boolean addNotice(Notice notice) {
        return false;
    }

    public boolean updateNotice(Notice notice) {
        return false;
    }

    public boolean deleteNotice(Notice notice) {
        return false;
    }

    public Notice getNotice(int noticeNum) {
        return null;
    }

    public List<Notice> getNotices() {
        return mNotices;
    }

    public void setNotices(List<Notice> notices) {
        mNotices = notices;
    }

    public void appendNotices(List<Notice> notices) {
        mNotices.addAll(notices);
    }

}
