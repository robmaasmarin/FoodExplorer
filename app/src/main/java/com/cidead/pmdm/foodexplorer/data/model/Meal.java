package com.cidead.pmdm.foodexplorer.data.model;

import com.google.gson.annotations.SerializedName;

public class Meal {
    @SerializedName("idMeal")
    private String id;

    @SerializedName("strMeal")
    private String name;

    @SerializedName("strMealThumb")
    private String thumb;

    public String getId() { return id; }
    public String getName() { return name; }
    public String getThumb() { return thumb; }
}
