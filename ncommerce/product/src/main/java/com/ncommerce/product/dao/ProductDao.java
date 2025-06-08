package com.ncommerce.product.dao;

import com.ncommerce.product.entity.Product;
import com.ncommerce.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Component
public class ProductDao {

    @Autowired
    private ProductRepository productRepository;

    public Product getProduct(String id){
        return productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
    }

    public Product saveProduct(Product product){
        return productRepository.save(product);
    }

    public boolean saveAllProduct(List<Product> products){
        productRepository.saveAll(products);
        return true;
    }
}
