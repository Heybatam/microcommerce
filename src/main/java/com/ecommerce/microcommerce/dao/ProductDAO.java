package com.ecommerce.microcommerce.dao;

import com.ecommerce.microcommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductDAO extends JpaRepository<Product, Integer> {

/*
findAll : renvoie la liste complète de tous les produits ;

findById : renvoie un produit par son Id ;

save : ajoute un produit.
*/

    public List<Product> findAll();
    public Product findById(int id);
    public Product save(Product product);
    public void removeById(int id);

}
