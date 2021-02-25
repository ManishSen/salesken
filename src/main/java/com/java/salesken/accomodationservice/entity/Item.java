package com.java.salesken.accomodationservice.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table
public class Item {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="rating")
	private int rating ;
	
	@Column(name="category")
	private String category;
	
	@OneToOne(cascade=CascadeType.ALL)
	private Location location;
	
	@Column(name="imageUrl")
	private String imageUrl;
	
	@Column(name="reputation")
	private int reputation;
	
	@Enumerated(EnumType.STRING)
	@Column(name="reputationBadge")
	private ReputationBadge reputationBadge;
	
	@Column(name="price")
	private int price;
	
	@Column(name="availability")
	private int availability;
	
	public Item() {
		
	}
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public int getReputation() {
		return reputation;
	}
	public void setReputation(int reputation) {
		this.reputation = reputation;
	}
	public ReputationBadge getReputationBadge() {
		return reputationBadge;
	}
	public void setReputationBadge(ReputationBadge badge) {
		this.reputationBadge = badge;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getAvailability() {
		return availability;
	}
	public void setAvailability(int availability) {
		this.availability = availability;
	}
	

}
