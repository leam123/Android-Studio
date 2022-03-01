package com.example.registration;

public class Items {
        private String picName;
        private int flagImage;

        public Items(String picName, int flagImage) {
            this.picName = picName;
            this.flagImage = flagImage;
        }

        public String getPicName() {
            return picName;
        }

        public int getFlagImage() {
            return flagImage;
        }
}
