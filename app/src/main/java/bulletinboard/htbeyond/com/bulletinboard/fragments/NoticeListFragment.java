package bulletinboard.htbeyond.com.bulletinboard.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONObject;

import bulletinboard.htbeyond.com.bulletinboard.R;
import bulletinboard.htbeyond.com.bulletinboard.models.NoticeStorage;
import bulletinboard.htbeyond.com.bulletinboard.network.NoticeListJSONWrapper;
import bulletinboard.htbeyond.com.bulletinboard.network.NoticeService;
import bulletinboard.htbeyond.com.bulletinboard.network.RetrofitService;
import bulletinboard.htbeyond.com.bulletinboard.recyclerviewhelpers.EndlessRecyclerViewScrollListener;
import bulletinboard.htbeyond.com.bulletinboard.recyclerviewhelpers.NoticeAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NoticeListFragment extends Fragment {

    private static final String TAG = "NoticeListFragment";
    private static final String KEY_PAGE_NUM
            = "bulletinboard.htbeyond.com.bulletinboard.fragments.page_num";
    private static final String KEY_LAST
            = "bulletinboard.htbeyond.com.bulletinboard.fragments.last";

    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;
    private NoticeAdapter mAdapter;
    private int mCurrentPageNumber;
    private boolean mIsLast;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_PAGE_NUM, mCurrentPageNumber);
        outState.putBoolean(KEY_LAST, mIsLast);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notice_list, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.list_recycler_view);

        mAdapter = new NoticeAdapter(NoticeStorage.getInstance(getActivity()).getNotices());
        mRecyclerView.setAdapter(mAdapter);

        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        mRecyclerView.addOnScrollListener(new EndlessRecyclerViewScrollListener(mLinearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                final int currentCount = mAdapter.getItemCount();
                NoticeService noticeService = new NoticeService(getActivity()) {
                    @Override
                    public void onDo() {
                        // onRespose 시 작성해야 할 것

                        mAdapter.notifyItemRangeInserted(currentCount, mAdapter.getItemCount() - 1);
                    }
                };
                noticeService.getNotices(NoticeService.NORMAL_SIZE, page);
            }
        });

        if (savedInstanceState != null) {
            mIsLast = savedInstanceState.getBoolean(KEY_LAST);
        }

        mCurrentPageNumber = 0;

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStart() {
        super.onStart();
        mIsLast = false;
        mCurrentPageNumber = 0;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_notice_list, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_notice_list_item_post:

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
