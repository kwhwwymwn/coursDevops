package edu.esiea.coursDevOps.models;

import java.util.ArrayList;
import java.util.List;

public class Cart {
	private int id;
	private int userId;
	private int quantity;
	private float totalPrice;
	private List<Product> products;
	
	public Cart(int id, int userId, int quantity, float totalPrice) {
		super();
		this.id = id;
		this.userId = userId;
		this.quantity = quantity;
		this.totalPrice = totalPrice;
		this.products = new ArrayList<>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public float getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void addProduct(Product product) {
		if(!this.products.contains(product))
			this.products.add(product);
	}

	public void removeProduct(Product product) {
		if(this.products.contains(product))
			this.products.remove(product);
	}
}
