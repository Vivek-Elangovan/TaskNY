package com.android.nytimes;

import android.content.Context;

public interface LoaderHelper {

    void showProgress(Context context);

    void dismissProgress();
}
