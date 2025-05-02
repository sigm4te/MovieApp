package com.example.movieapp.navigation;

import com.example.movieapp.ui.fragment.SearchFragment;
import com.example.movieapp.utils.log.Logger;
import com.github.terrakok.cicerone.androidx.FragmentScreen;

public class Screens {

    private static final String SEARCH_SCREEN = "SEARCH_SCREEN";

    public static class SearchScreen extends FragmentScreen {

        public SearchScreen() {
            super(SEARCH_SCREEN, fragmentFactory -> new SearchFragment());
            Logger.logD(null);
        }
    }
}
