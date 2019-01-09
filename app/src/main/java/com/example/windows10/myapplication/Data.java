package com.example.windows10.myapplication;


public class Data {
    public String title,image,description ;

    public Data() {
    }

    public Data(String title, String image, String description) {
        this.title = title;
        this.image = image;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

//    public String getName() {
//        return name;
//    }
//
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

//    public void setName(String name) {
//        this.name = name;
//    }
//
}
