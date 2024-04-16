package me.cl.lingxi.module.main;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;

import me.cl.library.base.BaseFragment;
import me.cl.library.photo.PhotoBrowser;
import me.cl.library.util.ToolbarUtil;
import me.cl.lingxi.R;
import me.cl.lingxi.common.config.Api;
import me.cl.lingxi.common.glide.GlideApp;
import me.cl.lingxi.common.okhttp.OkUtil;
import me.cl.lingxi.common.okhttp.ResultCallback;
import me.cl.lingxi.common.util.GsonUtil;
import me.cl.lingxi.common.util.NetworkUtil;
import me.cl.lingxi.common.util.SPUtil;
import me.cl.lingxi.databinding.HomeFragmentBinding;
import me.cl.lingxi.module.search.SearchActivity;
import okhttp3.Call;

public class HomeFragment extends BaseFragment {

    private HomeFragmentBinding mBinding;

    private static final String TYPE = "type";

    private String mType;

    private String mImageUrl;

    public HomeFragment() {

    }

    public static HomeFragment newInstance(String newsType) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(TYPE, newsType);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mType = getArguments().getString(TYPE);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = HomeFragmentBinding.inflate(inflater, container, false);
        init();
        return mBinding.getRoot();
    }

    private void init() {
        ToolbarUtil.init(mBinding.includeTb.toolbar, getActivity())
                .setTitle(R.string.title_bar_home)
                .setTitleCenter()
                .setMenu(R.menu.search_menu, new Toolbar.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.action_search:
                                gotoSearch();
                                break;
                        }
                        return false;
                    }
                })
                .build();

        initView();
        initData();
    }

    private void initView() {
        mBinding.swipeRefreshLayout.setOnRefreshListener(() -> {
            if (NetworkUtil.isWifiConnected(mBinding.swipeRefreshLayout.getContext())) {
                loadImage();
            } else {
                setError();
            }
        });
        mBinding.wordInfo.setOnLongClickListener(v -> {
            ClipboardManager clipboardManager = (ClipboardManager) mBinding.wordInfo.getContext().getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clipData = ClipData.newPlainText(null, mBinding.wordInfo.getText().toString().trim());
            if (clipboardManager != null) {
                clipboardManager.setPrimaryClip(clipData);
                showToast("已复制");
            }
            return false;
        });
        mBinding.randomImage.setOnClickListener(v -> {
            ArrayList<String> strings = new ArrayList<>();
            strings.add(mImageUrl);
            PhotoBrowser.builder()
                    .setPhotos(strings)
                    .start(requireActivity());
        });
    }

    /**
     * 初始化数据
     */
    private void initData() {
        mBinding.swipeRefreshLayout.setRefreshing(true);
        loadCache();
    }

    private void loadCache() {
        setDefaultData();
        loadImage();
    }

    private void setDefaultData() {
        setRefreshFalse();
        mBinding.imageSource.setVisibility(View.GONE);

        String text = "希望大家能够天天健身运动";
        mBinding.wordInfo.setVisibility(View.VISIBLE);
        mBinding.wordInfo.setText(text);
        String author = "河北工业大学";
        mBinding.wordAuthor.setVisibility(View.VISIBLE);
        mBinding.wordAuthor.setText(author);

        String source = "计213班";
        mBinding.wordSource.setVisibility(View.VISIBLE);
        mBinding.wordSource.setText(source);

    }


    private void loadImage() {
        setRefreshFalse();
        int n = (int) (Math.random() * 2);
        String img = Api.baseUrl + "/homepicture/" + n + ".png";

        if (TextUtils.isEmpty(img)) {
            mBinding.randomImage.setEnabled(false);
        } else {
            mBinding.randomImage.setEnabled(true);
            mImageUrl = img;
            GlideApp.with(this)
                    .load(img)
                    .centerInside()
                    .into(mBinding.randomImage);
        }
    }


    /**
     * 设置异常提示
     */
    private void setError() {
        setRefreshFalse();
        showToast("在未知的边缘试探╮(╯▽╰)╭");
    }

    /**
     * 结束刷新
     */
    private void setRefreshFalse() {
        boolean refreshing = mBinding.swipeRefreshLayout.isRefreshing();
        if (refreshing) {
            mBinding.swipeRefreshLayout.setRefreshing(false);
        }
    }

    /**
     * 前往搜索
     */
    private void gotoSearch() {
        Intent intent = new Intent(getActivity(), SearchActivity.class);
        startActivity(intent);
    }
}
