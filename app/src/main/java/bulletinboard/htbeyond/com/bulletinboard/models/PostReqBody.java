package bulletinboard.htbeyond.com.bulletinboard.models;

import java.time.LocalDateTime;

public class PostReqBody {

    private Integer noticeNum;

    private String noticeTitle;

    private String noticeContent;

    private LocalDateTime noticePostDate;

    private LocalDateTime noticeEditDate;

    private Integer noticeViewNum;

    private String noticeMemId;

    private String noticeAttachedFile;

    private boolean highlight;

    public PostReqBody(String noticeTitle, String noticeContent) {
        this.noticeTitle = noticeTitle;
        this.noticeContent = noticeContent;
    }

    public PostReqBody(Integer noticeNum, String noticeTitle, String noticeContent) {
        this.noticeNum = noticeNum;
        this.noticeTitle = noticeTitle;
        this.noticeContent = noticeContent;
    }
}
