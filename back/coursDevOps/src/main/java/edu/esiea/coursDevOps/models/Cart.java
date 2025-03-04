package edu.esiea.coursDevOps.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Cart {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@OneToOne
	@JoinColumn(name = "userId")
	private User user;
	private int quantity;
	private float totalPrice;
	private List<Product> products;
	
	public Cart(int id, User user, int quantity, float totalPrice) {
		super();
		this.id = id;
		this.user = user;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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
