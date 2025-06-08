package com.ncommerce.order.service;

import com.ncommerce.order.dao.OrderDao;
import com.ncommerce.order.dto.OrderDto;
import com.ncommerce.order.dto.ProductDto;
import com.ncommerce.order.entity.Order;
import com.ncommerce.order.mapper.OrderMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private RestTemplate restTemplate;

    public OrderDto getOrder(Long id){
        return OrderMapper.entityToDto(orderDao.getOrder(id));
    }

    @Transactional(value = Transactional.TxType.REQUIRED)
    public OrderDto placeOrder(OrderDto orderDto){
        String urlCheck = "http://product-service/api/product/" + orderDto.getProductId();
        ResponseEntity<ProductDto> responseEntity =
                restTemplate.exchange(
                        urlCheck,
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<ProductDto>() {}
                );
//                restTemplate.getForObject(urlCheck, ResponseEntity.class);
        ProductDto productDto = null;
        if(responseEntity.getStatusCode() == HttpStatus.OK){
                productDto = responseEntity.getBody();
        }else{
            throw new RuntimeException(" Product not found..!");
        }

        if(orderDto.getQuantity() > productDto.getStock()){
            throw new RuntimeException(" Purchase quantity exceed..!");
        }
        orderDto.setPrice(orderDto.getQuantity()*productDto.getPrice());
        Order order = OrderMapper.dtoToEntity(orderDto);
        orderDto = OrderMapper.entityToDto(orderDao.placeOrder(order));
        urlCheck = "http://product-service/api/product";
        productDto.setStock(productDto.getStock()-order.getQuantity());
        HttpEntity<ProductDto> request = new HttpEntity<>(productDto);
        ResponseEntity<ProductDto> response =null;
        try {
            response = restTemplate.exchange(
                    urlCheck,
                    HttpMethod.PUT,
                    request,
                    new ParameterizedTypeReference<ProductDto>() {
                    }
            );
        }catch (Exception e){
            throw new RuntimeException("Product Service is down");
        }
        System.out.println(productDto.toString());
//                restTemplate.patchForObject(urlCheck,productDto, ResponseEntity.class);
        if(response.getStatusCode() != HttpStatus.OK){
            throw new RuntimeException("Quantity not updated..!");
        }
        return orderDto;
    }


}
