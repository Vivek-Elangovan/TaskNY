package com.android.nytimes;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.android.nytimes.databinding.ActivityMainBinding;
import com.android.nytimes.model.ArticleResponse;
import com.android.nytimes.model.ArticleResult;

import java.util.List;

public class MainActivity extends BaseViewModelActivity<ArticleViewModel> implements ArticleViewModel.VMListener, ArticleAdapter.AdapterListener {

    private ActivityMainBinding mainBinding;
    private ArticleViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainBinding.setViewModel(onCreateViewModel());
    }

    @Override
    protected ArticleViewModel onCreateViewModel() {
        viewModel = ViewModelProviders.of(this).get(ArticleViewModel.class);
        viewModel.initViewModel(this);
        viewModel.getArticleResponseMutableLiveData().observe(this, new ArticleObserver());
        getList();
        return viewModel;
    }

    @Override
    public void onError() {
        dismissProgress();
        Snackbar snackbar = Snackbar.make(mainBinding.articleList, getString(R.string.text_failed), Snackbar.LENGTH_INDEFINITE);
        snackbar.setAction(getString(R.string.text_retry), new RetryListener());
        snackbar.show();
    }

    @Override
    public void onClicked(ArticleResult articleResult) {
        Intent intent = new Intent(this, WebPageActivity.class);
        intent.putExtra("URL", articleResult.getUrl());
        startActivity(intent);
    }

    private class RetryListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            getList();
        }
    }

    private void getList() {
        if (NetworkUtils.isConnected(this)) {
            showProgress(this);
            viewModel.getArticle(getString(R.string.api_key));
        } else {
            Snackbar snackbar = Snackbar.make(mainBinding.articleList, getString(R.string.text_no_internet), Snackbar.LENGTH_INDEFINITE);
            snackbar.setAction(getString(R.string.text_retry), new RetryListener());
            snackbar.show();
        }
    }

    private class ArticleObserver implements Observer<ArticleResponse> {

        @Override
        public void onChanged(@Nullable ArticleResponse articleResponse) {
            if (articleResponse != null && !articleResponse.getResults().isEmpty()) {
                initArticleList(articleResponse.getResults());
                dismissProgress();
            } else {
                Snackbar snackbar = Snackbar.make(mainBinding.articleList, getString(R.string.text_no_result), Snackbar.LENGTH_INDEFINITE);
                snackbar.show();
            }
        }
    }

    private void initArticleList(List<ArticleResult> results) {
        mainBinding.articleList.setLayoutManager(new LinearLayoutManager(this));
        mainBinding.articleList.setItemAnimator(new DefaultItemAnimator());
        ArticleAdapter mAdapter = new ArticleAdapter(results, this);
        mainBinding.articleList.setAdapter(mAdapter);
    }
}
