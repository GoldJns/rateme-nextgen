package com.app.rateme.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Rating {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long ratingId;

	private String text;
	private int stars;
	private LocalDateTime createdAt;

	private Poi poi;
	private User user;
	
	private byte[] image;

    public Rating(){

    };
    
    public Rating(long ratingId, String text, int stars, LocalDateTime createdAt, Poi poi, User user) {
        this.ratingId = ratingId;
        this.text = text;
        this.stars = stars;
        this.createdAt = createdAt;
        this.poi = poi;
        this.user = user;
    }

    public Rating(long ratingId, String text, int stars, LocalDateTime createdAt) {
        this.ratingId = ratingId;
        this.text = text;
        this.stars = stars;
        this.createdAt = createdAt;
    }





    public long getRatingId() {
        return ratingId;
    }

    public void setRatingId(long ratingId) {
        this.ratingId = ratingId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Poi getPoi() {
        return poi;
    }

    public void setPoi(Poi poi) {
        this.poi = poi;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (ratingId ^ (ratingId >>> 32));
        result = prime * result + ((text == null) ? 0 : text.hashCode());
        result = prime * result + stars;
        result = prime * result + ((createdAt == null) ? 0 : createdAt.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Rating other = (Rating) obj;
        if (ratingId != other.ratingId)
            return false;
        if (text == null) {
            if (other.text != null)
                return false;
        } else if (!text.equals(other.text))
            return false;
        if (stars != other.stars)
            return false;
        if (createdAt == null) {
            if (other.createdAt != null)
                return false;
        } else if (!createdAt.equals(other.createdAt))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Rating [ratingId=" + ratingId + ", text=" + text + ", stars=" + stars + ", createdAt=" + createdAt
                + ", poi=" + poi + ", user=" + user + "]";
    }	

    
}
