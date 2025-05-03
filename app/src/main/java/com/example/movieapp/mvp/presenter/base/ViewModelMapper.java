package com.example.movieapp.mvp.presenter.base;

import com.example.movieapp.mvp.model.api.dto.SearchResultItem;
import com.example.movieapp.mvp.view.search_result.SearchResultItemViewModel;

public class ViewModelMapper {

    public static SearchResultItemViewModel mapSearchResultItem(SearchResultItem searchResultItem) {
        return new SearchResultItemViewModel(
                searchResultItem.getId(),
                searchResultItem.getTitle(),
                searchResultItem.getType(),
                searchResultItem.getYear(),
                searchResultItem.getImageUrl()
        );
    }
}
