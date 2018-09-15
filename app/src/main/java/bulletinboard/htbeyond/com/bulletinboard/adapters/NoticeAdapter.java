package bulletinboard.htbeyond.com.bulletinboard.adapters;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import bulletinboard.htbeyond.com.bulletinboard.NoticeActivity;
import bulletinboard.htbeyond.com.bulletinboard.R;
import bulletinboard.htbeyond.com.bulletinboard.models.Notice;

public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.NoticeHolder> {

    private List<Notice> mNotices;

    public class NoticeHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public Notice mNotice;
        public TextView mTitle;
        public TextView mWriter;
        public TextView mDate;
        public TextView mViews;


        public NoticeHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);
            mTitle = (TextView) itemView.findViewById(R.id.list_item_title);
            mWriter = (TextView) itemView.findViewById(R.id.list_item_writer_name);
            mDate = (TextView) itemView.findViewById(R.id.list_item_edit_date);
            mViews = (TextView) itemView.findViewById(R.id.list_item_views);
        }

        @Override
        public void onClick(View v) {
            Intent i = NoticeActivity.newIntent(itemView.getContext(), mNotice.getNoticeId());
            itemView.getContext().startActivity(i);
        }
    }


    public NoticeAdapter(List<Notice> notices) {
        mNotices = notices;
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
        noticeHolder.mViews.setText("views" + notice.getNoticeId());
        if (notice.isHighlighted()) {
            noticeHolder.mTitle.setTypeface(null, Typeface.BOLD);
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
