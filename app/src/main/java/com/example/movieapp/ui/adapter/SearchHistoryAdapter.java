package com.example.movieapp.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieapp.R;
import com.example.movieapp.mvp.presenter.search_page.list.ISearchHistoryListPresenter;
import com.example.movieapp.mvp.view.search_page.list.ISearchHistoryItemView;
import com.example.movieapp.utils.log.Logger;

public class SearchHistoryAdapter extends RecyclerView.Adapter<SearchHistoryAdapter.ViewHolder> {

    private final ISearchHistoryListPresenter presenter;

    public SearchHistoryAdapter(ISearchHistoryListPresenter presenter) {
        this.presenter = presenter;
        Logger.logV(null);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View searchHistoryView = inflater.inflate(R.layout.item_search_history, parent, false);
        return new ViewHolder(searchHistoryView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.position = position;
        holder.itemView.setOnClickListener((view) -> {
            presenter.onItemClick(holder);
        });
        presenter.bindView(holder);
    }

    @Override
    public int getItemCount() {
        return presenter.getCount();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements ISearchHistoryItemView {
        TextView textView;
        int position;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.tv_search_history_item_text);
        }

        @Override
        public void setData(String title) {
            textView.setText(title);
        }

        @Override
        public int getPos() {
            return position;
        }
    }
}
