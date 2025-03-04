package edu.esiea.coursDevOps.models;

public class Product {
	private int id;
	private String name;
	private float basePrice;
	private float tva;
	
	public Product(int id, String name, float basePrice, float tva) {
		super();
		this.id = id;
		this.name = name;
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
