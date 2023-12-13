package com.app.rateme.api.dto;

import java.io.Serializable;


public class UserResponseDto {
    
    private String userName;
	private String email;
	private String firstName;
	private String lastName;
	private String street;
	private String streetNr;
	private String zip;
	private String city;
	private int userId;
	
	
	

	public UserResponseDto(String userName, String email, String firstName, String lastName, String street,
            String streetNr, String zip, String city, int userId) {
        this.userName = userName;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.streetNr = streetNr;
        this.zip = zip;
        this.city = city;
        this.userId = userId;
    }




    @Override
	public String toString() {
		return "UserResponseDto [userName=" + userName + ", email=" + email + ", firstName=" + firstName + ", lastName="
				+ lastName + ", street=" + street + ", streetNr=" + streetNr + ", zip=" + zip + ", city=" + city
				+ ", userId=" + userId + "]";
	}




	public String getUserName() {
		return userName;
	}




	public void setUserName(String userName) {
		this.userName = userName;
	}




	public String getEmail() {
		return email;
	}




	public void setEmail(String email) {
		this.email = email;
	}




	public String getFirstName() {
		return firstName;
	}




	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}




	public String getLastName() {
		return lastName;
	}




	public void setLastName(String lastName) {
		this.lastName = lastName;
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




	public String getZip() {
		return zip;
	}




	public void setZip(String zip) {
		this.zip = zip;
	}




	public String getCity() {
		return city;
	}




	public void setCity(String city) {
		this.city = city;
	}




	public int getUserId() {
		return userId;
	}




	public void setUserId(int userId) {
		this.userId = userId;
	}

 
}
