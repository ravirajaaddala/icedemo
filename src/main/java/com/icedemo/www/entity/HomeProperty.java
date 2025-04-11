
package com.icedemo.www.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

/**
 * 
 */
@Entity
public class HomeProperty {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String streetaddress;
	private String type;
	private String description;
	private String area;
	private Long price;
	
	@ManyToOne
	@JoinColumn(name = "location_id")
	private LocationDetails locationDetails;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStreetaddress() {
		return streetaddress;
	}

	public void setStreetaddress(String streetaddress) {
		this.streetaddress = streetaddress;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public LocationDetails getLocationDetails() {
		return locationDetails;
	}

	public void setLocationDetails(LocationDetails locationDetails) {
		this.locationDetails = locationDetails;
	} 
	
	
}
