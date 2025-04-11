
package com.icedemo.www.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

/**
 * 
 */
@Entity
public class LocationDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(nullable = false)
	private String city;
	private String county;
	@Column(name = "zipcode", unique = true, nullable = false)
	private String zipcode;
	private String state;
	private String country;
	
	@OneToMany(mappedBy = "locationDetails")
	private List<HomeProperty> homeProperties;
	
	
	public List<HomeProperty> getHomeProperties() {
		return homeProperties;
	}
	public void setHomeProperties(List<HomeProperty> homeProperties) {
		this.homeProperties = homeProperties;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCounty() {
		return county;
	}
	public void setCounty(String county) {
		this.county = county;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
	
}
