package com.example.sneakers.model;

import java.io.Serializable;

public class Media implements Serializable  {

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


}
