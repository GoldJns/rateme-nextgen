package com.app.rateme.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "rateme_rating")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) // Ignore these properties during serialization
public class Rating {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long ratingId;

    @ManyToOne
	@JoinColumn(name = "user_id")
    @JsonIgnoreProperties("ratings")
	private User user;

    @ManyToOne
	@JoinColumn(name = "osm_id")
    @JsonIgnoreProperties("ratings")
	private Poi poi;

	private String txt;
	private int stars;
	private LocalDateTime createdAt;
	private byte[] image;

    public Rating(){

    };

    public Rating(long ratingId, User user, Poi poi, String txt, int stars, LocalDateTime createdAt, byte[] image) {
        this.ratingId = ratingId;
        this.user = user;
        this.poi = poi;
        this.txt = txt;
        this.stars = stars;
        this.createdAt = createdAt;
        this.image = image;
    }

    public Rating(long ratingId, String txt, int stars, LocalDateTime createdAt, byte[] image) {
        this.ratingId = ratingId;
        this.txt = txt;
        this.stars = stars;
        this.createdAt = createdAt;
         this.image = image;
    }

    public long getRatingId() {
        return ratingId;
    }

    public void setRatingId(long ratingId) {
        this.ratingId = ratingId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Poi getPoi() {
        return poi;
    }

    public void setPoi(Poi poi) {
        this.poi = poi;
    }
    /* 
    public int getUserId() {
        return user.getUserId();
    }

    public void setUserId(int user) {
        this.user.setUserId(user);
    }

    public long getosmId() {
        return poi.getOsmId();
    }

    public void setosmId(long osmId) {
        this.poi.setOsmId(osmId);
    }
    */
    public String getText() {
        return txt;
    }

    public void setText(String txt) {
        this.txt = txt;
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

    
    
    /*
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
     */
    
}
