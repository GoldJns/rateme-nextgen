package com.app.rateme.model;

import java.util.Set;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "rateme_user")
public class User {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;

	
	private String username;
	private String mail;
	private String firstname;
	private String lastname;
	private String street;
	private String streetNr;
	private String city;
	private String zip;
	private byte[] passwordhash;
	private byte[] passwordsalt;
	
	@OneToMany(mappedBy = "user")
	Set<Rating> ratings;
    
    public User(){


    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String userName) {
        this.username = userName;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String email) {
        this.mail = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstName) {
        this.firstname = firstName;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastName) {
        this.lastname = lastName;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreetNr() {
        return streetNr;
    }

    public void setStreetNr(String streetNr) {
        this.streetNr = streetNr;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public byte[] getPasswordhash() {
        return passwordhash;
    }

    public void setPasswordhash(byte[] passwordHash) {
        this.passwordhash = passwordHash;
    }

    public byte[] getPasswordsalt() {
        return passwordsalt;
    }

    public void setPasswordsalt(byte[] passwordSalt) {
        this.passwordsalt = passwordSalt;
    }

    public Set<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(Set<Rating> ratings) {
        this.ratings = ratings;
    }

    

}
