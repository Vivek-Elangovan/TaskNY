package com.android.nytimes;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.android.nytimes.databinding.ActivityWebBinding;

public class WebPageActivity extends BaseViewModelActivity<WebPageViewModel> {

    private ActivityWebBinding webBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        webBinding = DataBindingUtil.setContentView(this, R.layout.activity_web);
        webBinding.setViewModel(onCreateViewModel());
    }

    @Override
    protected WebPageViewModel onCreateViewModel() {
        WebPageViewModel viewModel = ViewModelProviders.of(this).get(WebPageViewModel.class);
        viewModel.initViewModel(webBinding, getIntent().getStringExtra("URL"));
        return viewModel;
    }
}
