package edu.esiea.coursDevOps.repository;
import edu.esiea.coursDevOps.models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Integer> {
   
}

