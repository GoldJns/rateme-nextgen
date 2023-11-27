package com.app.rateme.api.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Arrays;

public class RatingDto implements Serializable{

    private long ratingId;
    private int userId;
    private long osmId;
    private String text;
	private int stars;
	private LocalDateTime createdAt;
	private byte[] image;

    public long getRatingId() {
        return ratingId;
    }

    public void setRatingId(long ratingId) {
        this.ratingId = ratingId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public long getOsmId() {
        return osmId;
    }

    public void setOsmId(long osmId) {
        this.osmId = osmId;
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

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
    
    @Override
    public String toString() {
        return "RatingDto [ratingId=" + ratingId + ", userId=" + userId + ", osmId=" + osmId + ", text=" + text
                + ", stars=" + stars + ", createdAt=" + createdAt + ", image=" + Arrays.toString(image) + "]";
    }
}
