package bulletinboard.htbeyond.com.bulletinboard.recyclerviewhelpers;

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

    protected static final int VIEW_TYPE_NORMAL = 0;
    protected static final int VIEW_TYPE_EMPTY = 1;

    private List<Notice> mNotices;
    private boolean mIsAppending = false;

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

    @Override
    public int getItemViewType(int position) {
        if (mNotices.isEmpty()) {
            return VIEW_TYPE_EMPTY;
        } else {
            return VIEW_TYPE_NORMAL;
        }
    }

    @NonNull
    @Override
    public NoticeHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        RecyclerView.ViewHolder vh;
        if (i == VIEW_TYPE_EMPTY) {
            View v = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.list_item_notice, viewGroup, false);

            vh = new EmptyViewHolder(v);
        } else {
            vh = createNormalViewHolder(viewGroup, i);
        }

        return (NoticeHolder) vh;
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

//        if (holder instanceof EmptyViewHolder) {
//            // do nothing
//        } else {
//            // Prevent binding of child view holder when List is NULL/Empty
//            if (mDataList != null && !mDataList.isEmpty()) {
//                bindNormalViewHolder(holder, position);
//            }
//        }

    }

    @Override
    public int getItemCount() {
        return mNotices.isEmpty() ? 1 : mNotices.size();
    }

    public void setNotices(List<Notice> notices) {
        mNotices = notices;
    }

    public void addAll(List<Notice> noticeList) {
        mNotices.addAll(noticeList);
        notifyDataSetChanged();
    }

    public void clear() {
        int size = mNotices.size();
        mNotices.clear();
        notifyItemRangeRemoved(0, size);
    }

    protected Notice getItemAt(int position) {
        return mNotices.get(position);
    }

    protected Notice replaceItemAt(int position, Notice newItem) {
        Notice notice = mNotices.set(position, newItem);
        notifyItemChanged(position);
        return notice;
    }

    protected void addItem(int index, Notice newItem) {
        mNotices.add(index, newItem);
        notifyItemInserted(index);
    }

    protected void addItem(Notice newItem) {
        mNotices.add(newItem);
        notifyItemInserted(mNotices.size() - 1);
    }

    protected Notice removeItem(int index) {
        if (mNotices.size() > 0) {
            Notice notice = mNotices.remove(index);
            notifyItemRemoved(index);

            return notice;
        }

        return null;
    }

    protected NoticeHolder createNormalViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_item_notice, viewGroup, false);

        return new NoticeHolder(view);
    };

    protected void bindNormalViewHolder(NoticeHolder holder, int position) {
        Notice notice = mNotices.get(position);

        holder.mNotice = notice;
        holder.mTitle.setText(notice.getTitle());
        holder.mWriter.setText(notice.getWriter());
        holder.mDate.setText(notice.getModifiedDateToString());
        holder.mViews.setText("views" + notice.getNoticeId());
        if (notice.isHighlighted()) {
            holder.mTitle.setTypeface(null, Typeface.BOLD);
        }
    };

    public static class EmptyViewHolder extends RecyclerView.ViewHolder {
        public EmptyViewHolder(View v) {
            super(v);
        }
    }
}
