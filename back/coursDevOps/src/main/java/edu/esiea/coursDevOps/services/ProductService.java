package edu.esiea.coursDevOps.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.esiea.coursDevOps.models.Product;
import edu.esiea.coursDevOps.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private final ProductRepository repo;
	
	public ProductService(ProductRepository repo) {
		this.repo = repo;
	}
	
	public List<Product> getAll(){
		return repo.findAll();
	}
	
	public Product get(int index) {
		return repo.findById(index).orElseThrow(() -> new RuntimeException("Product not found"));
	}
}
