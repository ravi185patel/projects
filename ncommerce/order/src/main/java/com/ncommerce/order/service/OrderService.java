package com.ncommerce.order.service;

import com.ncommerce.order.constant.PaymentStatus;
import com.ncommerce.order.dao.OrderDao;
import com.ncommerce.order.dto.OrderDto;
import com.ncommerce.order.dto.PaymentDto;
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
        ProductDto productDto = null;
        if(responseEntity.getStatusCode() == HttpStatus.OK){
                productDto = responseEntity.getBody();
        }else{
            throw new RuntimeException(" Product not found..!");
        }

        if(productDto == null || orderDto.getQuantity() > productDto.getStock()){
            throw new RuntimeException(" Purchase quantity exceed..!");
        }


        orderDto.setPrice(orderDto.getQuantity()*productDto.getPrice());
        Order order = OrderMapper.dtoToEntity(orderDto);
        orderDto = OrderMapper.entityToDto(orderDao.placeOrder(order));

        //payment process
        System.out.println(orderDto.toString());
        PaymentDto paymentDto = new PaymentDto();
        paymentDto.setPayment(orderDto.getPrice());
        paymentDto.setOrderId(orderDto.getId());
        paymentDto.setCustomerId("xyz");
        HttpEntity<PaymentDto> requestPayment = new HttpEntity<>(paymentDto);
        urlCheck = "http://payment-service/api/payment";
        ResponseEntity<PaymentDto> responsePayment =null;
        try {
            responsePayment = restTemplate.exchange(
                    urlCheck,
                    HttpMethod.POST,
                    requestPayment,
                    new ParameterizedTypeReference<PaymentDto>() {
                    }
            );
        }catch (Exception e){
            throw new RuntimeException("Payment got failed....!");
        }

        if(responsePayment.getStatusCode() != HttpStatus.OK || !responsePayment.getBody().getPaymentStatus().equals(PaymentStatus.SUCCESS)){
            throw new RuntimeException("Payment got failed..!");
        }
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
        if(response.getStatusCode() != HttpStatus.OK){
            throw new RuntimeException("Quantity not updated..!");
        }
        //send notification that your order is placed
        return orderDto;
    }


}
