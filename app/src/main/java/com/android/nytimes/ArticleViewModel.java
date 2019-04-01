package com.android.nytimes;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.android.nytimes.model.ArticleResponse;

/**
 * View Model for Article Activity. This handles the server response and binding the view once the observer called.
 */
public class ArticleViewModel extends BaseViewModel implements Interactor<ArticleResponse> {

    private final MutableLiveData<ArticleResponse> articleResponseMutableLiveData = new MutableLiveData<>();
    private VMListener vmListener;

    public ArticleViewModel(@NonNull Application application) {
        super(application);
    }

    /**
     * Interface initialization
     *
     * @param vmListener VMListener
     */
    void initViewModel(VMListener vmListener) {
        this.vmListener = vmListener;
    }

    /**
     * Server call to get the most popular article
     *
     * @param apiKey String
     */
    void getArticle(String apiKey) {
        AppServiceManager.getInstance().getPopularArticle(this, apiKey);
    }

    @Override
    public void onSyncData(ArticleResponse data, int transactionId) {
        if (transactionId == AppServiceManager.MOST_POPULAR_LIST)
            articleResponseMutableLiveData.setValue(data);
    }

    @Override
    public void onFailed(int transactionId, ArticleResponse data) {
        vmListener.onError();
    }

    MutableLiveData<ArticleResponse> getArticleResponseMutableLiveData() {
        return articleResponseMutableLiveData;
    }

    /**
     * Interface for Activity and View Model communication
     */
    public interface VMListener {
        void onError();
    }
}
