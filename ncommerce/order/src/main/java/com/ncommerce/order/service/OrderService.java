package com.ncommerce.order.service;

import com.ncommerce.common.constant.EventType;
import com.ncommerce.common.constant.OrderStatus;
import com.ncommerce.common.constant.PaymentStatus;
import com.ncommerce.common.dto.OrderEvent;
import com.ncommerce.common.dto.PaymentDto;
import com.ncommerce.common.dto.ProductDto;
import com.ncommerce.common.eventdto.InventoryFailedEvent;
import com.ncommerce.common.eventdto.InventoryReservedEvent;
import com.ncommerce.common.eventdto.OrderCreatedEvent;
import com.ncommerce.common.eventdto.PaymentFailedEvent;
import com.ncommerce.order.dao.OrderDao;
import com.ncommerce.order.dto.OrderDto;
import com.ncommerce.order.entity.Order;
import com.ncommerce.order.mapper.OrderMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Value("${kafka.topic.name}")
    private String topicName;

    public OrderDto getOrder(String id){
        return OrderMapper.INSTANCE.toDTO(orderDao.getOrder(id));
    }

    @Transactional(value = Transactional.TxType.REQUIRED)
    public OrderDto placeOrder(OrderDto orderDto){ // called Orchestration-based Saga (central Saga orchestrator) Example  where order work as coordinates the workflow.
        /*String urlCheck = "http://product-service/api/product/" + orderDto.getProductId();
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
            throw new RuntimeException("Payment got failed....!"+e);
        }

        if(responsePayment.getStatusCode() != HttpStatus.OK
                || !responsePayment.getBody()
                                   .getPaymentStatus()
                                   .equals(PaymentStatus.PAYMENT_RECEIVED)){
            throw new RuntimeException("Payment got failed..!");
        }

        urlCheck = "http://product-service/api/product";
        productDto.setStock(0);
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

        OrderEvent orderEvent = new OrderEvent();
        orderEvent.setEventType(EventType.ORDER_NOTIFICATION_SENT);
        orderEvent.setStatus(OrderStatus.CONFIRMED);
        orderEvent.setEventId(order.getId());
        kafkaTemplate.send(topicName,orderEvent);
        return orderDto;*/
        return null;
    }
    public OrderDto placeOrderEvent(OrderDto orderDto){
        orderDto.setStatus("PENDING");
        Order order = OrderMapper.INSTANCE.toEntity(orderDto);
        orderDto = OrderMapper.INSTANCE.toDTO(orderDao.placeOrder(order));
        kafkaTemplate.send("orders-topic",new OrderCreatedEvent(String.valueOf(order.getId()),String.valueOf(order.getUserId()),(double)order.getTotalAmount()));
        return orderDto;
    }

    @KafkaListener(topics = "inventory-reserved", groupId = "order-service")
    public void handleInventoryReserved(InventoryReservedEvent event) {
        Order order = orderDao.getOrder(event.getOrderId());
        order.setStatus("COMPLETED");
        orderDao.placeOrder(order);
    }

    @KafkaListener(topics = {"payment-failed", "inventory-failed"}, groupId = "order-service")
    public void handleFailures(Object event) {
        String orderId = null;
        if (event instanceof PaymentFailedEvent e) orderId = e.getOrderId();
        if (event instanceof InventoryFailedEvent e) orderId = e.getOrderId();
        Order order = orderDao.getOrder(orderId);
        order.setStatus("CANCELLED");
        orderDao.placeOrder(order);
    }
//
//
//    @Service
//    public class OrderService {
//
//        private final OrderRepository repo;
//        private final KafkaTemplate<String, Object> kafkaTemplate;
//
//        public OrderService(OrderRepository repo, KafkaTemplate<String, Object> kafkaTemplate) {
//            this.repo = repo;
//            this.kafkaTemplate = kafkaTemplate;
//        }
//
//        public Order createOrder(Order order) {
//            order.setId(UUID.randomUUID().toString());
//            order.setStatus("PENDING");
//            Order saved = repo.save(order);
//            kafkaTemplate.send("order-created", new OrderCreatedEvent(saved.getId(), saved.getUserId(), saved.getAmount()));
//            return saved;
//        }
//
//        @KafkaListener(topics = "inventory-reserved", groupId = "order-service")
//        public void handleInventoryReserved(InventoryReservedEvent event) {
//            Order order = repo.findById(event.getOrderId()).orElseThrow();
//            order.setStatus("COMPLETED");
//            repo.save(order);
//        }
//
//        @KafkaListener(topics = {"payment-failed", "inventory-failed"}, groupId = "order-service")
//        public void handleFailures(Object event) {
//            String orderId = null;
//            if (event instanceof PaymentFailedEvent e) orderId = e.getOrderId();
//            if (event instanceof InventoryFailedEvent e) orderId = e.getOrderId();
//            Order order = repo.findById(orderId).orElseThrow();
//            order.setStatus("CANCELLED");
//            repo.save(order);
//        }
//    }

}
