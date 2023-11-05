package com.app.rateme.api.dto;

import java.io.Serializable;

@SuppressWarnings("serial")
public class PoiDto implements Serializable {




        private double longitude;
        private double latitude;
        private String title;

        public PoiDto()
        {

        }

        public PoiDto(String title, double longitude, double latitude)
        {
            super();
            this.longitude = longitude;
            this.latitude = latitude;
            this.title = title;
        }


        public double getLongitude()
        {
            return longitude;
        }

        public void setLongitude(double longitude)
        {
            this.longitude = longitude;
        }

        public double getLatitude()
        {
            return latitude;
        }

        public void setLatitude(double latitude)
        {
            this.latitude = latitude;
        }

        public String getTitle()
        {
            return title;
        }

        public void setTitle(String title)
        {
            this.title = title;
        }

        @Override
        public String toString()
        {
            return "PoiDto [longitude=" + longitude + ", latitude=" + latitude + ", title=" + title + "]";
        }


    }


