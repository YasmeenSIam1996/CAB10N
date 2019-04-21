package com.mj.cab10.model;

public class Payment {
    private String image;
    private String name;
    private String selectedImg;

    public Payment(String image, String name , String selectedImg) {
        this.image = image;
        this.name = name;
        this.selectedImg = selectedImg;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSelectedImg() {
        return selectedImg;
    }

    public void setSelectedImg(String selectedImg) {
        this.selectedImg = selectedImg;
    }
}
