package bulletinboard.htbeyond.com.bulletinboard.recyclerview;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import bulletinboard.htbeyond.com.bulletinboard.NoticeActivity;
import bulletinboard.htbeyond.com.bulletinboard.R;
import bulletinboard.htbeyond.com.bulletinboard.models.Notice;

public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.NoticeHolder> {

    private List<Notice> mNotices;
    FragmentActivity mActivity;




    public class NoticeHolder extends RecyclerView.ViewHolder {

        public Notice mNotice;
        public RelativeLayout mLayout;
        public TextView mTitle;
        public TextView mWriter;
        public TextView mDate;
        public TextView mViews;


        public NoticeHolder(@NonNull final View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = NoticeActivity.newIntent(mActivity, mNotice.getNoticeId());
                    mActivity.startActivity(i);
                }
            });

            mLayout = (RelativeLayout) itemView.findViewById(R.id.list_layout);
            mTitle = (TextView) itemView.findViewById(R.id.list_item_title);
            mWriter = (TextView) itemView.findViewById(R.id.list_item_writer_name);
            mDate = (TextView) itemView.findViewById(R.id.list_item_edit_date);
            mViews = (TextView) itemView.findViewById(R.id.list_item_views);
        }

    }


    public NoticeAdapter(List<Notice> notices, FragmentActivity activity) {
        mNotices = notices;
        mActivity = activity;
    }

    @NonNull
    @Override
    public NoticeHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_item_notice, viewGroup, false);

        return new NoticeHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoticeHolder noticeHolder, int i) {
        Notice notice = mNotices.get(i);

        noticeHolder.mNotice = notice;
        noticeHolder.mTitle.setText(notice.getTitle());
        noticeHolder.mWriter.setText(notice.getWriter());
        noticeHolder.mDate.setText(notice.getModifiedDateToString());
        noticeHolder.mViews.setText(mActivity.getString(R.string.list_item_views, notice.getViews()));
        if (notice.isHighlighted()) {
            noticeHolder.mLayout.setBackgroundColor(ContextCompat.getColor(mActivity, R.color.colorHighlight));
            noticeHolder.mTitle.setTypeface(null, Typeface.BOLD);
            noticeHolder.mTitle.setTextColor(ContextCompat.getColor(mActivity, R.color.colorHighlightText));
        }

    }

    @Override
    public int getItemCount() {
        return mNotices.size();
    }

    public void setNotices(List<Notice> notices) {
        mNotices = notices;
    }


}
