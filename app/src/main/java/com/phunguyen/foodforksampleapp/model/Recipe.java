package com.phunguyen.foodforksampleapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Recipe {

    @SerializedName("publisher")
    @Expose
    private String publisher;

    @SerializedName("publisher_url")
    @Expose
    private String publisherUrl;

    @SerializedName("f2f_url")
    @Expose
    private String f2fUrl;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("recipe_id")
    @Expose
    private String recipeId;

    @SerializedName("image_url")
    @Expose
    private String imageUrl;

    @SerializedName("source_url")
    @Expose
    private String sourceUrl;

    private boolean isFavorited;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(String recipeId) {
        this.recipeId = recipeId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    public boolean isFavorited() {
        return isFavorited;
    }

    public void setFavorited(boolean favorited) {
        isFavorited = favorited;
    }
}
