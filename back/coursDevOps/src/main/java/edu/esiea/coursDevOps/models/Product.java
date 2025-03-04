package edu.esiea.coursDevOps.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String description;
	private String imgUrl;
	private float basePrice;
	private float tva;
	

	public Product(int id, String name, String description, String imgUrl, float basePrice, float tva) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.imgUrl = imgUrl;
		this.basePrice = basePrice;
		this.tva = tva;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getImgUrl() {
		return imgUrl;
	}


	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}


	public float getBasePrice() {
		return basePrice;
	}


	public void setBasePrice(float basePrice) {
		this.basePrice = basePrice;
	}


	public float getTva() {
		return tva;
	}


	public void setTva(float tva) {
		this.tva = tva;
	}
}
