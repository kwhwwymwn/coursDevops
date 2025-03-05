package edu.esiea.coursDevOps.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import edu.esiea.coursDevOps.models.Product;
import edu.esiea.coursDevOps.repository.ProductRepository;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {
	@Mock
	private ProductRepository repo;
	
	@InjectMocks
	private ProductService service;
	
	@Test
	void testGetOne() {
		Product mockedProduct = new Product(1,"name","description","/src/img.png",10.99f,0.055f);
		
		// Simuler le comportement du repository
        when(repo.findById(1)).thenReturn(Optional.of(mockedProduct));

        // Exécuter la méthode à tester
        Product product = service.get(1);

        // Vérifier que le résultat est correct
        assertNotNull(product);
        assertEquals(mockedProduct.getId(), product.getId());
        assertEquals(mockedProduct.getName(), product.getName());
        assertEquals(mockedProduct.getDescription(), product.getDescription());
        assertEquals(mockedProduct.getImgUrl(), product.getImgUrl());
        assertEquals(mockedProduct.getBasePrice(), product.getBasePrice());
        assertEquals(mockedProduct.getTva(), product.getTva());


        // Vérifier que la méthode du repository a bien été appelée une fois
        verify(repo, times(1)).findById(1);
	}

	@Test
	void testGetAll() {
		List<Product> mockedList = new ArrayList<>();
		mockedList.add(new Product(1,"name","description","/src/img.png",10.99f,0.055f));
		mockedList.add(new Product(2,"name2","description2","/src/img2.png",20.99f,0.2f));
		
		// Simuler le comportement du repository
        when(repo.findAll()).thenReturn(mockedList);

        // Exécuter la méthode à tester
        List<Product> products = service.getAll();

        // Vérifier que le résultat est correct
        assertNotNull(products);
        assertEquals(mockedList.size(), products.size());
        assertEquals(mockedList.getFirst().getId(), products.getFirst().getId());

        // Vérifier que la méthode du repository a bien été appelée une fois
        verify(repo, times(1)).findAll();
	}
	

	@Test
	void testAdd() {
		Product mockedProduct = new Product(1,"name","description","/src/img.png",10.99f,0.055f);
		
		// Simuler le comportement du repository
        when(repo.save(mockedProduct)).thenReturn(mockedProduct);

        // Exécuter la méthode à tester
        Product product = service.add(mockedProduct);

        // Vérifier que le résultat est correct
        assertNotNull(product);
        assertEquals(mockedProduct.getId(), product.getId());
        assertEquals(mockedProduct.getName(), product.getName());
        assertEquals(mockedProduct.getDescription(), product.getDescription());
        assertEquals(mockedProduct.getImgUrl(), product.getImgUrl());
        assertEquals(mockedProduct.getBasePrice(), product.getBasePrice());
        assertEquals(mockedProduct.getTva(), product.getTva());


        // Vérifier que la méthode du repository a bien été appelée une fois
        verify(repo, times(1)).save(mockedProduct);
	}
	
	@Test
	void testDelete() {
		Product mockedProduct = new Product(1,"name","description","/src/img.png",10.99f,0.055f);

        // Exécuter la méthode à tester
        service.delete(mockedProduct);

        // Vérifier que la méthode du repository a bien été appelée une fois
        verify(repo, times(1)).delete(mockedProduct);
	}
}
