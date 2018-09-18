package bulletinboard.htbeyond.com.bulletinboard.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONObject;

import java.util.List;

import bulletinboard.htbeyond.com.bulletinboard.R;
import bulletinboard.htbeyond.com.bulletinboard.models.Notice;
import bulletinboard.htbeyond.com.bulletinboard.models.NoticeStorage;
import bulletinboard.htbeyond.com.bulletinboard.recyclerviewhelpers.EndlessNoticeAdapter;
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
            = "bulletinboard.htbeyond.com.bulletinboard.fragments.last";

    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;\
    private EndlessRecyclerOnScrollListener mEndlessRecyclerOnScrollListener;
    private EndlessNoticeAdapter mAdapter;
    private int mCurrentPageNumber;
    private boolean mIsLast;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.addOnScrollListener(new EndlessRecyclerOnScrollListener(mLinearLayoutManager) {
            @Override
            public void onLoadMore(int current_page) {
                    mAdapter = (EndlessNoticeAdapter) mRecyclerView.getAdapter();
                    if (mAdapter != null) {
                        mAdapter.setIsAppending(true);
                        mAdapter.notifyItemInserted(mAdapter.getItemCount());


                        Call<JSONObject> res = RetrofitService.getInstance(getActivity()).getService()
                                .getNotices(getString(R.string.access_token) , NoticeService.NORMAL_SIZE, current_page, NoticeService.MODE_FIND_ALL);
                        res.enqueue(new Callback<JSONObject>() {
                            @Override
                            public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
                                Log.d(TAG, "getNotices() called" + response.toString());
                                if (response.isSuccessful()) {
                                    NoticeListJSONWrapper jsonWrapper = new NoticeListJSONWrapper(response.body());
                                    NoticeStorage storage = NoticeStorage.getInstance(getActivity());
                                    if (jsonWrapper.isLast()) {
                                        mIsLast = true;
                                        return;
                                    }
                                    mAdapter.addAll(jsonWrapper.getNotices());
                                }

                            }

                            @Override
                            public void onFailure(Call<JSONObject> call, Throwable t) {

                            }
                        });
                    }
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
        updateUI();
    }

    @Override
    public void onStart() {
        super.onStart();
        mIsLast = false;
        mCurrentPageNumber = 0;
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

    public void getNotices(int pageSize, int pageNum) {

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
                        mA
                        storage.appendNotices(jsonWrapper.getNotices());
                    }
//
//                    updateUI();
                }
            }

            @Override
            public void onFailure(Call<JSONObject> call, Throwable t) {
                Log.e(TAG, "getNotices() called" + t.getMessage());
            }
        });
    }
}
