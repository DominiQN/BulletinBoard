package bulletinboard.htbeyond.com.bulletinboard.recyclerviewhelpers;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import bulletinboard.htbeyond.com.bulletinboard.R;
import bulletinboard.htbeyond.com.bulletinboard.models.Notice;

public class EndlessNoticeAdapter extends NoticeAdapter {

    private static final int VIEW_TYPE_PROGRESS = 2;

    private boolean mIsAppending;

    public EndlessNoticeAdapter(List<Notice> notices) {
        super(notices);
    }

    public boolean isAppending() {
        return mIsAppending;
    }

    public void setIsAppending(boolean isAppending) {
        mIsAppending = isAppending;
    }

    @Override
    public int getItemCount() {
        return isAppending() ?
                super.getItemCount() + 1 : super.getItemCount();
    }

    @NonNull
    @Override
    public final NoticeHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        RecyclerView.ViewHolder vh;
        if (i == VIEW_TYPE_PROGRESS) {
            View v = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.list_progress_bar, viewGroup, false);
            vh = new ProgressViewHolder(v);
        } else {
            vh = super.onCreateViewHolder(viewGroup, i);
        }
        return (NoticeHolder) vh;
    }

    public static class ProgressViewHolder extends RecyclerView.ViewHolder {
        public ProgressViewHolder(View v) {
            super(v);
        }
    }
}
