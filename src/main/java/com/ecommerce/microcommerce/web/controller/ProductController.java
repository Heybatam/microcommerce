package com.ecommerce.microcommerce.web.controller;


import com.ecommerce.microcommerce.dao.ProductDAO;
import com.ecommerce.microcommerce.model.Product;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductDAO productdao;

    @RequestMapping(value="/Produits", method= RequestMethod.GET)
    public MappingJacksonValue listeProduits(){

        Iterable<Product> produits = productdao.findAll();

        SimpleBeanPropertyFilter monFiltre = SimpleBeanPropertyFilter.serializeAllExcept("prixAchat");

        FilterProvider listeDeNosFiltres = new SimpleFilterProvider().addFilter("monFiltreDynamique", monFiltre);

        MappingJacksonValue produitsFiltre = new MappingJacksonValue(produits);

        produitsFiltre.setFilters(listeDeNosFiltres);

        return produitsFiltre;
    }


    /*@GetMapping(value = "/Produits/{id}")
    public Product afficherUnProduit(@PathVariable int id) {
        return productdao.findById(id);
    }

    //ajouter un produit
    @PostMapping(value = "/Produits")
    public ResponseEntity ajouterProduit(@RequestBody Product product) {
        Product productAdded = productdao.save(product);

        if (productAdded == null){
            return ResponseEntity.noContent().build();
        }

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(productAdded.getId())
                .toUri();

        return ResponseEntity.created(location).build();

    }

    @DeleteMapping(value= "/Produits/{id}")
    public void retirerProduits(@PathVariable int id) throws Exception{
        productdao.removeById(id);
    }*/
}
