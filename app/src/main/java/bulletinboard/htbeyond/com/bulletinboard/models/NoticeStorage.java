package bulletinboard.htbeyond.com.bulletinboard.models;

import android.content.Context;

import java.util.List;

public class NoticeStorage {
    //TODO: 서버랑 연결되고 난 후 NoticeStorageTester를 NoticeStorage로 바꿔줘야 한다.
    private static NoticeStorageTester sNoticeStorage;

    private List<Notice> mNotices;
    private Context mContext;
    // private HTTP가지고 노는 거 변수

    public static NoticeStorageTester getInstance(Context context) {
        if (sNoticeStorage == null) {
            sNoticeStorage = new NoticeStorageTester(context);
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

    // 몇개 없으면 서버에서 적당량 불러오기
    public List<Notice> getNotices() {
        return null;
    }


}
