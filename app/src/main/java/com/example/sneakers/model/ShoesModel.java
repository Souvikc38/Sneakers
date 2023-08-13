package com.example.sneakers.model;

public  class ShoesModel {

    private int year;
    private String title;
    private String name;
    private String shoe;
    private String styleid;
    private int retailprice;
    private int tax;
    private String releasedate;
    private Media media;
    private String gender;
    private String colorway;
    private String brand;
    private String id;

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShoe() {
        return shoe;
    }

    public void setShoe(String shoe) {
        this.shoe = shoe;
    }

    public String getStyleid() {
        return styleid;
    }

    public void setStyleid(String styleid) {
        this.styleid = styleid;
    }

    public int getRetailprice() {
        return retailprice;
    }

    public void setRetailprice(int retailprice) {
        this.retailprice = retailprice;
    }

    public String getReleasedate() {
        return releasedate;
    }

    public void setReleasedate(String releasedate) {
        this.releasedate = releasedate;
    }

    public Media getMedia() {
        return media;
    }

    public void setMedia(Media media) {
        this.media = media;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getColorway() {
        return colorway;
    }

    public void setColorway(String colorway) {
        this.colorway = colorway;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ShoesModel{" +
                "year=" + year +
                ", title='" + title + '\'' +
                ", name='" + name + '\'' +
                ", shoe='" + shoe + '\'' +
                ", styleid='" + styleid + '\'' +
                ", retailprice=" + retailprice +
                ", releasedate='" + releasedate + '\'' +
                ", media=" + media +
                ", gender='" + gender + '\'' +
                ", colorway='" + colorway + '\'' +
                ", brand='" + brand + '\'' +
                ", id='" + id + '\'' +
                '}';
    }

    public int getTax() {
        return tax;
    }

    public void setTax(int tax) {
        this.tax = tax;
    }

    public static class Media {
        private String thumburl;
        private String smallimageurl;
        private String imageurl;

        public String getThumburl() {
            return thumburl;
        }

        public void setThumburl(String thumburl) {
            this.thumburl = thumburl;
        }

        public String getSmallimageurl() {
            return smallimageurl;
        }

        public void setSmallimageurl(String smallimageurl) {
            this.smallimageurl = smallimageurl;
        }

        public String getImageurl() {
            return imageurl;
        }

        public void setImageurl(String imageurl) {
            this.imageurl = imageurl;
        }

        @Override
        public String toString() {
            return "Media{" +
                    "thumburl='" + thumburl + '\'' +
                    ", smallimageurl='" + smallimageurl + '\'' +
                    ", imageurl='" + imageurl + '\'' +
                    '}';
        }
    }
}