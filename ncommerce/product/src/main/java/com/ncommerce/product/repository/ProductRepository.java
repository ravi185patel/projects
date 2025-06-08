package com.ncommerce.product.repository;

import com.ncommerce.product.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends MongoRepository<Product,Long> {
    Optional<Product> findById(String id);
}
