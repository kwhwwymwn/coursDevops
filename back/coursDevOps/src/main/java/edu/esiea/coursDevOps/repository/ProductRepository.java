package edu.esiea.coursDevOps.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.esiea.coursDevOps.models.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
