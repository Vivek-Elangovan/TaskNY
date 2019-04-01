package com.android.nytimes;

import android.app.Application;
import android.support.annotation.NonNull;

import com.android.nytimes.databinding.ActivityWebBinding;

public class WebPageViewModel extends BaseViewModel {

    public WebPageViewModel(@NonNull Application application) {
        super(application);
    }

    void initViewModel(ActivityWebBinding webBinding, String url) {
        webBinding.webView.loadUrl(url);
    }
}
