package edu.esiea.coursDevOps.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.esiea.coursDevOps.models.Cart;
import edu.esiea.coursDevOps.repository.CartRepository;



@Service
public class CartService {
	
	@Autowired
	private final CartRepository repo;
	
	public CartService(CartRepository repo) {
		this.repo = repo;
	}
	
	public List<Cart> getAll(){
		return repo.findAll();
	}
	
}
