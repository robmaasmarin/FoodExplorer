package com.cidead.pmdm.foodexplorer.data.model;

import com.google.gson.annotations.SerializedName;

public class MealDetail {

    @SerializedName("idMeal")
    private String id;

    @SerializedName("strMeal")
    private String name;

    @SerializedName("strMealThumb")
    private String image;

    @SerializedName("strInstructions")
    private String instructions;

    @SerializedName("strIngredient1")
    private String ingredient1;
    @SerializedName("strIngredient2")
    private String ingredient2;
    @SerializedName("strIngredient3")
    private String ingredient3;

    public String getName() { return name; }
    public String getImage() { return image; }
    public String getInstructions() { return instructions; }

    public String getIngredients() {
        StringBuilder sb = new StringBuilder();
        if (ingredient1 != null && !ingredient1.isEmpty()) sb.append("• ").append(ingredient1).append("\n");
        if (ingredient2 != null && !ingredient2.isEmpty()) sb.append("• ").append(ingredient2).append("\n");
        if (ingredient3 != null && !ingredient3.isEmpty()) sb.append("• ").append(ingredient3).append("\n");
        return sb.toString();
    }
}
