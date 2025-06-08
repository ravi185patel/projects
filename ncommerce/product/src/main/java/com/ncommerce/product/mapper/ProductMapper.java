package com.ncommerce.product.mapper;

import com.ncommerce.product.dto.ProductDto;
import com.ncommerce.product.entity.Product;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductMapper {


    public static Product dtoToEntity(ProductDto productDto){
        Product product = new Product();
        product.setProductName(productDto.getProductName());
        product.setProductCode(productDto.getProductCode());
        product.setPrice(productDto.getPrice());
        product.setStock(productDto.getStock());
        return product;
    }

    public static ProductDto entityToDto(Product product){
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setProductName(product.getProductName());
        productDto.setProductCode(product.getProductCode());
        productDto.setPrice(product.getPrice());
        productDto.setStock(product.getStock());
        return productDto;
    }

    public static List<ProductDto> entityListToDtoList(List<Product> products){
        return products.stream().map(ProductMapper::entityToDto).collect(Collectors.toList());
    }

    public static List<Product> dtoListToEntityList(List<ProductDto> productDto){
        return productDto.stream().map(ProductMapper::dtoToEntity).collect(Collectors.toList());
    }
}
