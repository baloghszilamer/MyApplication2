package com.example.windows10.myapplication;

public class Upload {
    private String mName;
    private String mImageUrl;
    private String mDescription;

    public Upload() {
        //empty constructor needed
    }

    public Upload(String name, String imageUrl,String description) {
        if (name.trim().equals("")) {
            name = "No Name";
        }

        mName = name;
        mImageUrl = imageUrl;
        mDescription=description;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getImageUrl() {
        return mImageUrl;
    }
    public String getIDescription() {
        return mDescription;
    }

    public void setImageUrl(String imageUrl) {
        mImageUrl = imageUrl;
    }
    public void setDescription(String description) {
        mDescription = description;
    }
}