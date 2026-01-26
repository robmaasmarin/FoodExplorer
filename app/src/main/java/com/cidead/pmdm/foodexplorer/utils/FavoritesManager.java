package com.cidead.pmdm.foodexplorer.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.cidead.pmdm.foodexplorer.data.model.FavoriteMeal;
import com.cidead.pmdm.foodexplorer.data.model.Meal;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FavoritesManager {
    private static final String PREFS_NAME = "favorites_prefs";
    private static final String KEY_FAVORITES = "favorites";

    private static SharedPreferences getPrefs(Context context) {
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    public static void toggleFavorite(Context context, FavoriteMeal meal) {
        SharedPreferences prefs = getPrefs(context);
        Gson gson = new Gson();

        Map<String, String> favorites = new HashMap<>();
        for (String json : prefs.getStringSet(KEY_FAVORITES, new HashSet<>())) {
            FavoriteMeal fm = gson.fromJson(json, FavoriteMeal.class);
            favorites.put(fm.getId(), json);
        }

        if (favorites.containsKey(meal.getId())) {
            favorites.remove(meal.getId());
        } else {
            favorites.put(meal.getId(), gson.toJson(meal));
        }

        prefs.edit()
                .putStringSet(KEY_FAVORITES, new HashSet<>(favorites.values()))
                .apply();
    }



    public static boolean isFavorite(Context context, String mealId) {
        Gson gson = new Gson();
        for (String json : getPrefs(context).getStringSet(KEY_FAVORITES, new HashSet<>())) {
            FavoriteMeal meal = gson.fromJson(json, FavoriteMeal.class);
            if (meal.getId().equals(mealId)) return true;
        }
        return false;
    }

    // Devuelve lista de IDs favoritos
    public static List<FavoriteMeal> getFavoriteMeals(Context context) {
        Set<String> set =
                getPrefs(context).getStringSet(KEY_FAVORITES, new HashSet<>());
        List<FavoriteMeal> list = new ArrayList<>();
        Gson gson = new Gson();
        for (String json : set) {
            list.add(gson.fromJson(json, FavoriteMeal.class));
        }
        return list;
    }



}
