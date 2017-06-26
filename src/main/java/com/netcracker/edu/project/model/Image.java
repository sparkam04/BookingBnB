package com.netcracker.edu.project.model;

public class Image extends Model {
    private String imageUrl;
    private String image_description;

    public String getImageDescription() {
        return image_description;
    }

    public void setImageDescription(String image_description) {
        this.image_description = image_description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
