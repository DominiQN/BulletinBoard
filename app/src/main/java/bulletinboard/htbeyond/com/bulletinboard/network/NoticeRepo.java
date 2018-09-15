package bulletinboard.htbeyond.com.bulletinboard.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

class Chronology {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("calendarType")
    @Expose
    private String calendarType;

    public String getId() {
        return id;
    }

    public String getCalendarType() {
        return calendarType;
    }

}

class NoticeEditDate {

    @SerializedName("month")
    @Expose
    private String month;
    @SerializedName("year")
    @Expose
    private Integer year;
    @SerializedName("dayOfMonth")
    @Expose
    private Integer dayOfMonth;
    @SerializedName("dayOfWeek")
    @Expose
    private String dayOfWeek;
    @SerializedName("dayOfYear")
    @Expose
    private Integer dayOfYear;
    @SerializedName("monthValue")
    @Expose
    private Integer monthValue;
    @SerializedName("hour")
    @Expose
    private Integer hour;
    @SerializedName("minute")
    @Expose
    private Integer minute;
    @SerializedName("nano")
    @Expose
    private Integer nano;
    @SerializedName("second")
    @Expose
    private Integer second;
    @SerializedName("chronology")
    @Expose
    private Chronology chronology;

    public String getMonth() {
        return month;
    }

    public Integer getYear() {
        return year;
    }

    public Integer getDayOfMonth() {
        return dayOfMonth;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public Integer getDayOfYear() {
        return dayOfYear;
    }

    public Integer getMonthValue() {
        return monthValue;
    }

    public Integer getHour() {
        return hour;
    }

    public Integer getMinute() {
        return minute;
    }

    public Integer getNano() {
        return nano;
    }

    public Integer getSecond() {
        return second;
    }

    public Chronology getChronology() {
        return chronology;
    }

}

class NoticePostDate {

    @SerializedName("month")
    @Expose
    private String month;
    @SerializedName("year")
    @Expose
    private Integer year;
    @SerializedName("dayOfMonth")
    @Expose
    private Integer dayOfMonth;
    @SerializedName("dayOfWeek")
    @Expose
    private String dayOfWeek;
    @SerializedName("dayOfYear")
    @Expose
    private Integer dayOfYear;
    @SerializedName("monthValue")
    @Expose
    private Integer monthValue;
    @SerializedName("hour")
    @Expose
    private Integer hour;
    @SerializedName("minute")
    @Expose
    private Integer minute;
    @SerializedName("nano")
    @Expose
    private Integer nano;
    @SerializedName("second")
    @Expose
    private Integer second;
    @SerializedName("chronology")
    @Expose
    private Chronology chronology;

    public String getMonth() {
        return month;
    }

    public Integer getYear() {
        return year;
    }

    public Integer getDayOfMonth() {
        return dayOfMonth;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public Integer getDayOfYear() {
        return dayOfYear;
    }

    public Integer getMonthValue() {
        return monthValue;
    }

    public Integer getHour() {
        return hour;
    }

    public Integer getMinute() {
        return minute;
    }

    public Integer getNano() {
        return nano;
    }

    public Integer getSecond() {
        return second;
    }

    public Chronology getChronology() {
        return chronology;
    }

}

public class NoticeRepo {

    @SerializedName("noticeNum")
    @Expose
    private Integer noticeNum;
    @SerializedName("noticeTitle")
    @Expose
    private String noticeTitle;
    @SerializedName("noticeContent")
    @Expose
    private String noticeContent;
    @SerializedName("noticePostDate")
    @Expose
    private NoticePostDate noticePostDate;
    @SerializedName("noticeEditDate")
    @Expose
    private NoticeEditDate noticeEditDate;
    @SerializedName("noticeViewNum")
    @Expose
    private Object noticeViewNum;
    @SerializedName("noticeMemId")
    @Expose
    private Object noticeMemId;
    @SerializedName("noticeAttachedFile")
    @Expose
    private Object noticeAttachedFile;
    @SerializedName("highlight")
    @Expose
    private Boolean highlight;

    public Integer getNoticeNum() {
        return noticeNum;
    }

    public String getNoticeTitle() {
        return noticeTitle;
    }

    public String getNoticeContent() {
        return noticeContent;
    }

    public NoticePostDate getNoticePostDate() {
        return noticePostDate;
    }

    public NoticeEditDate getNoticeEditDate() {
        return noticeEditDate;
    }

    public Object getNoticeViewNum() {
        return noticeViewNum;
    }

    public Object getNoticeMemId() {
        return noticeMemId;
    }

    public Object getNoticeAttachedFile() {
        return noticeAttachedFile;
    }

    public Boolean getHighlight() {
        return highlight;
    }

    @Override
    public String toString() {
        return "NoticeRepo{" +
                "noticeNum=" + noticeNum +
                ", noticeTitle='" + noticeTitle + '\'' +
                ", noticeContent='" + noticeContent + '\'' +
                ", noticePostDate=" + noticePostDate +
                ", noticeEditDate=" + noticeEditDate +
                ", noticeViewNum=" + noticeViewNum +
                ", noticeMemId=" + noticeMemId +
                ", noticeAttachedFile=" + noticeAttachedFile +
                ", highlight=" + highlight +
                '}';
    }
}