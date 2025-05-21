package com.example.movieapp.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieapp.R;
import com.example.movieapp.mvp.presenter.search_result.list.ISearchResultListPresenter;
import com.example.movieapp.mvp.view.search_result.SearchResultItemViewModel;
import com.example.movieapp.mvp.view.search_result.list.ISearchResultItemView;
import com.example.movieapp.utils.image.GlideImageLoader;
import com.example.movieapp.utils.image.IImageLoader;
import com.example.movieapp.utils.log.Logger;

public class SearchResultAdapter extends RecyclerView.Adapter<SearchResultAdapter.ViewHolder> {

    private final ISearchResultListPresenter presenter;
    private static final IImageLoader<ImageView> imageLoader = new GlideImageLoader();

    public SearchResultAdapter(ISearchResultListPresenter presenter) {
        this.presenter = presenter;
        Logger.logV(null);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View searchResultItemView = inflater.inflate(R.layout.item_search_result, parent, false);
        return new ViewHolder(searchResultItemView);
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

    public static class ViewHolder extends RecyclerView.ViewHolder implements ISearchResultItemView {
        TextView titleView;
        ImageView imageView;
        TextView typeView;
        TextView yearView;
        int position;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleView = itemView.findViewById(R.id.tv_search_result_item_title);
            imageView = itemView.findViewById(R.id.iv_search_result_item_poster);
            typeView = itemView.findViewById(R.id.tv_search_result_item_type);
            yearView = itemView.findViewById(R.id.tv_search_result_item_year);
        }

        @Override
        public void setData(SearchResultItemViewModel searchResultItem) {
            titleView.setText(searchResultItem.title);
            typeView.setText(searchResultItem.type);
            yearView.setText(searchResultItem.year);
            imageLoader.loadImage(searchResultItem.imageUrl, imageView);
        }

        @Override
        public int getPos() {
            return position;
        }
    }
}
