package com.ncommerce.product.controller;

import com.ncommerce.product.dto.ProductDto;
import com.ncommerce.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable("id") String id){
        return ResponseEntity.ok(productService.getProduct(id));
    }

    @PutMapping
    public ResponseEntity<ProductDto> updateProduct(@RequestBody ProductDto productDto){
        System.out.println("in update "+productDto.toString());
//        throw new RuntimeException(" Service is down");
        return ResponseEntity.ok(productService.updateProduct(productDto));
    }

    @PostMapping
    public ResponseEntity<ProductDto> addProduct(@RequestBody ProductDto productDto){
        System.out.println("in add "+productDto.toString());
        return ResponseEntity.ok(productService.saveProduct(productDto));
    }
}
