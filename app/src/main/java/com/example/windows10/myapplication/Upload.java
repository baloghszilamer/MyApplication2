package com.example.windows10.myapplication;

public class Upload {
    private String title;
    private String image;
    private String description;

    public Upload() {
        //empty constructor needed
    }

    public Upload(String name, String imageUrl,String description1) {
        if (name.trim().equals("")) {
            name = "No Name";
        }

        title = name;
        image = imageUrl;
        description=description1;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        title = name;
    }

    public String getImage() {
        return image;
    }
    public String getDescription() {
        return description;
    }

    public void setImage(String imageUrl) {
        image = imageUrl;
    }
    public void setDescription(String description1) {
        description = description1;
    }
}