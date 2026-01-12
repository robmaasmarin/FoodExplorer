package com.cidead.pmdm.foodexplorer.data.model;

import com.google.gson.annotations.SerializedName;

public class Country {
    @SerializedName("strArea")
    private String name;

    public String getName() {
        return name;
    }
}
