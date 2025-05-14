package com.example.movieapp.mvp.view.search_result.list;

import com.example.movieapp.mvp.view.base.list.IItemView;
import com.example.movieapp.mvp.view.search_result.SearchResultItemViewModel;

public interface ISearchResultItemView extends IItemView {
    void setData(SearchResultItemViewModel searchResultItem);
}
