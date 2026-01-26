package com.cidead.pmdm.foodexplorer.data.model;

public class FavoriteMeal {
    private String id;
    private String name;
    private String thumb;

    public FavoriteMeal(String id, String name, String thumb) {
        this.id = id;
        this.name = name;
        this.thumb = thumb;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getThumb() { return thumb; }
}
