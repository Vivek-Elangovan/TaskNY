package com.android.nytimes;

import android.annotation.SuppressLint;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.nytimes.databinding.ArticleItemBinding;
import com.android.nytimes.model.ArticleResult;

import java.util.List;

/**
 * ArticleAdapter is for loading the most viewed article from NY Times.
 */
class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.MyViewHolder> {

    private List<ArticleResult> postList;
    private LayoutInflater layoutInflater;
    private AdapterListener listener;

    class MyViewHolder extends RecyclerView.ViewHolder {

        private final ArticleItemBinding binding;

        MyViewHolder(final ArticleItemBinding itemBinding) {
            super(itemBinding.getRoot());
            this.binding = itemBinding;
        }
    }

    ArticleAdapter(List<ArticleResult> postList, AdapterListener listener) {
        this.postList = postList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        ArticleItemBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.article_item, parent, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.binding.setViewModel(postList.get(position));
        holder.binding.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onClicked(postList.get(position));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    /**
     * Interface for Activity and Adapter communication
     */
    public interface AdapterListener {
        void onClicked(ArticleResult post);
    }
}
