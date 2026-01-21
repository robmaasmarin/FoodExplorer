package com.cidead.pmdm.foodexplorer.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashSet;
import java.util.Set;

public class FavoritesManager {
    private static final String PREFS_NAME = "favorites_prefs";
    private static final String KEY_FAVORITES = "favorites";

    private static SharedPreferences getPrefs(Context context) {
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    public static void toggleFavorite(Context context, String mealId) {
        SharedPreferences prefs = getPrefs(context);
        Set<String> favorites = new HashSet<>(prefs.getStringSet(KEY_FAVORITES, new HashSet<>()));

        if (favorites.contains(mealId)) {
            favorites.remove(mealId);
        } else {
            favorites.add(mealId);
        }

        prefs.edit().putStringSet(KEY_FAVORITES, favorites).apply();
    }

    public static boolean isFavorite(Context context, String mealId) {
        return getPrefs(context)
                .getStringSet(KEY_FAVORITES, new HashSet<>())
                .contains(mealId);
    }
}
