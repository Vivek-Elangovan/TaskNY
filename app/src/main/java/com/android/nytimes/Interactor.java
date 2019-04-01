package com.android.nytimes;


public interface Interactor<T> {
    void onSyncData(final T data, final int transactionId);

    void onFailed(final int transactionId, final T data);
}
