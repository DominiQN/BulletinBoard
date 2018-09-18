package bulletinboard.htbeyond.com.bulletinboard.fragments;

import android.content.Intent;
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

import java.util.List;

import bulletinboard.htbeyond.com.bulletinboard.NoticeActivity;
import bulletinboard.htbeyond.com.bulletinboard.NoticeEditActivity;
import bulletinboard.htbeyond.com.bulletinboard.R;
import bulletinboard.htbeyond.com.bulletinboard.models.Notice;
import bulletinboard.htbeyond.com.bulletinboard.models.NoticeStorage;
import bulletinboard.htbeyond.com.bulletinboard.recyclerviewhelpers.EndlessRecyclerOnScrollListener;
import bulletinboard.htbeyond.com.bulletinboard.recyclerviewhelpers.NoticeAdapter;
import bulletinboard.htbeyond.com.bulletinboard.network.NoticeListJSONWrapper;
import bulletinboard.htbeyond.com.bulletinboard.network.NoticeService;
import bulletinboard.htbeyond.com.bulletinboard.network.RetrofitService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NoticeListFragment extends Fragment {

    private static final String TAG = "NoticeListFragment";
    private static final String KEY_PAGE_NUM
            = "bulletinboard.htbeyond.com.bulletinboard.fragments.page_num";
    private static final String KEY_LAST
            = "bulletinboard.htbeyond.com.bulletinboard.fragments.last"

    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;\
    private EndlessRecyclerOnScrollListener mEndlessRecyclerOnScrollListener;
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
        mLinearLayoutManager = new LinearLayoutManager(getActivity());

        mEndlessRecyclerOnScrollListener =
                new EndlessRecyclerOnScrollListener() {
                    @Override
                    public void onLoadMore(int current_page) {
                        load
                    }
                }

        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int visibleItemCount = recyclerView.getChildCount();
                int totalItemCount = mLinearLayoutManager.getItemCount();
                int firstVisibleItem = mLinearLayoutManager.findFirstVisibleItemPosition();

                if (mLoading) {
                    int diffCurrentFromPrevious = totalItemCount - previousItemCount;

                    // check if current total is greater than previous (diff should be greater than 1, for considering placeholder)
                    // and if current total is equal to the total in server
                    if ((diffCurrentFromPrevious > 1) ||
                            totalItemCount >= mTotalEntries) {
                        mLoading = false;
                        previousItemCount = totalItemCount;
                    }
                } else {

                    if (totalItemCount >= mTotalEntries) {
                        // do nothing, we've reached the end of the list
                    } else {
                        // check if the we've reached the end of the list,
                        // and if the total items is less than the total items in the server
                        if ((firstVisibleItem + visibleItemCount) >= totalItemCount &&
                                totalItemCount < mTotalEntries) {
                            onLoadMore(++current_page);

                            mLoading = true;
                            previousItemCount = totalItemCount;
                        }
                    }
                }

            }
        });

        if (savedInstanceState != null) {
            mCurrentPageNumber = savedInstanceState.getInt(KEY_PAGE_NUM);
            mIsLast = savedInstanceState.getBoolean(KEY_LAST);
        }
        mCurrentPageNumber = 0;

        getNotices(30, 0, true);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    @Override
    public void onStart() {
        super.onStart();
        mIsLast = false;
        mCurrentPageNumber = 0;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_notice_list_item_post:
                Intent i = NoticeEditActivity.newIntent(getActivity(), NoticeEditActivity.CREATE_NOTICE);
                startActivity(i);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_notice_list, menu);
    }

    private void updateUI() {
        List<Notice> notices = NoticeStorage.getInstance(getActivity()).getNotices();

        if (mAdapter == null) {
            mAdapter = new NoticeAdapter(notices);
            mRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.setNotices(notices);
            mAdapter.notifyDataSetChanged();
        }
    }

    public void getNotices(int pageSize, int pageNum, final boolean isRefresh) {

        Call<JSONObject> res = RetrofitService.getInstance(getActivity()).getService()
                .getNotices(getString(R.string.access_token) , pageSize, pageNum, NoticeService.MODE_FIND_ALL);
        res.enqueue(new Callback<JSONObject>() {
            @Override
            public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
                Log.d(TAG, "getNotices() called" + response.toString());
                if (response.isSuccessful()) {
                    NoticeListJSONWrapper jsonWrapper = new NoticeListJSONWrapper(response.body());
                    NoticeStorage storage = NoticeStorage.getInstance(getActivity());
                    if (isRefresh) {
                        storage.setNotices(jsonWrapper.getNotices());
                    } else {
                        if (jsonWrapper.isLast()) {
                            mIsLast = true;
                            return;
                        }
                        mCurrentPageNumber++;
                        storage.appendNotices(jsonWrapper.getNotices());
                    }

                    updateUI();
                }
            }

            @Override
            public void onFailure(Call<JSONObject> call, Throwable t) {
                Log.e(TAG, "getNotices() called" + t.getMessage());
                showFailToast();
            }
        });
    }
}
