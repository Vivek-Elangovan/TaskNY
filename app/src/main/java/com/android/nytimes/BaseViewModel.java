package com.android.nytimes;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

abstract class BaseViewModel extends AndroidViewModel {
    BaseViewModel(@NonNull Application application) {
        super(application);
    }
}
