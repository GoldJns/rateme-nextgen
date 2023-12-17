package com.app.rateme.model;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "rateme_rating")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
public class Rating {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ratingId;

    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
    @JsonIgnoreProperties("ratings")
	private User user;

    @ManyToOne(fetch = FetchType.LAZY)
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
    
}
