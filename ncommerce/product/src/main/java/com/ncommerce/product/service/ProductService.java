package com.ncommerce.product.service;

import com.ncommerce.product.dao.ProductDao;
import com.ncommerce.product.dto.ProductDto;
import com.ncommerce.product.entity.Product;
import com.ncommerce.product.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductDao productDao;

    public ProductDto getProduct(String id){
        return ProductMapper.entityToDto(productDao.getProduct(id));
    }

    public ProductDto updateProduct(ProductDto productDto){
        Product product = ProductMapper.dtoToEntity(productDto);
        product.setId(productDto.getId());
        return ProductMapper.entityToDto(productDao.saveProduct(product));
    }
    public ProductDto saveProduct(ProductDto productDto){
        Product product = ProductMapper.dtoToEntity(productDto);
        return ProductMapper.entityToDto(productDao.saveProduct(product));
    }

    public boolean saveAllProduct(List<ProductDto> productDto){
        return productDao.saveAllProduct(ProductMapper.dtoListToEntityList(productDto));
    }
}
