package com.android.nytimes;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public abstract class BaseViewModelActivity<VM extends BaseViewModel> extends AppCompatActivity implements LoaderHelper {

    protected abstract VM onCreateViewModel();

    protected Dialog dialog = null;

    @TargetApi(Build.VERSION_CODES.KITKAT)
    protected void toolBarSetUp(Toolbar toolbar) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public void showProgress(Context context) {
        if (dialog == null) {
            dialog = new Dialog(context, R.style.CustomProgressTheme);
            dialog.setContentView(R.layout.custom_progress);
            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();
        }
    }

    @Override
    public void dismissProgress() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
        dialog = null;
    }

}
