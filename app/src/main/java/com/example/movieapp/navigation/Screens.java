package com.example.movieapp.navigation;

import android.os.Bundle;

import com.example.movieapp.ui.fragment.SearchPageFragment;
import com.example.movieapp.ui.fragment.SearchResultFragment;
import com.example.movieapp.utils.log.Logger;
import com.github.terrakok.cicerone.androidx.FragmentScreen;

public class Screens {

    public static final String QUERY_STRING_ARG = "QUERY";

    private static final String SEARCH_PAGE_SCREEN = "SEARCH_PAGE_SCREEN";
    private static final String SEARCH_RESULT_SCREEN = "SEARCH_RESULT_SCREEN";

    public static class SearchPageScreen extends FragmentScreen {

        public SearchPageScreen() {
            super(SEARCH_PAGE_SCREEN, fragmentFactory -> new SearchPageFragment());
            Logger.logD(null);
        }
    }

    public static class SearchResultScreen extends FragmentScreen {

        public SearchResultScreen(String query) {
            super(SEARCH_RESULT_SCREEN, fragmentFactory -> {
                SearchResultFragment searchResultFragment = new SearchResultFragment();
                Bundle args = new Bundle();
                args.putString(QUERY_STRING_ARG, query);
                searchResultFragment.setArguments(args);
                return searchResultFragment;
            });
            Logger.logD(String.format("query = %s", query));
        }
    }
}
