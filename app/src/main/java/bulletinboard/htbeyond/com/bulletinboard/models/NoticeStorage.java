package bulletinboard.htbeyond.com.bulletinboard.models;

import android.content.Context;

import java.util.List;

class NoticeStorage {
    private static NoticeStorage sNoticeStorage;

    private List<Notice> mNotices;
    private Context mContext;

    static NoticeStorage getInstance(Context context) {
        if (sNoticeStorage == null) {
            sNoticeStorage = new NoticeStorage(context);
        }
        return sNoticeStorage;
    }

    private NoticeStorage(Context context) {
        mContext = context.getApplicationContext();
    }
}
