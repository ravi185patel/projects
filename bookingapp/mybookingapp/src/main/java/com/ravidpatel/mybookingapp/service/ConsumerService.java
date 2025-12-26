package com.ravidpatel.mybookingapp.service;

import com.ravidpatel.mybookingapp.dto.ConsumerDto;
import com.ravidpatel.mybookingapp.entity.Consumer;
import com.ravidpatel.mybookingapp.repository.ConsumerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Repository
public class ConsumerService {

    @Autowired
    private ConsumerRepository consumerRepository;

//    @Autowired
//    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    // SAVE
    public String saveConsumer(ConsumerDto dto) {
        Consumer consumer = mapToEntity(dto);
        consumer.setConsumerId(UUID.randomUUID().toString());
        return consumerRepository.saveConsumer(consumer);
    }

    // GET BY ID
    public ConsumerDto getConsumer(String consumerId) {
          Consumer consumer = consumerRepository.getConsumer(consumerId);
          return mapToDto(consumer);
    }

    public Consumer mapToEntity(ConsumerDto dto){
        Consumer entity = new Consumer();
        entity.setConsumerId(dto.getConsumerId());
        entity.setConsumerName(dto.getConsumerName());
        entity.setConsumerIdentityNo(dto.getConsumerIdentityNo());
        entity.setConsumerPassword(dto.getConsumerPassword());
        return entity;
    }

    public ConsumerDto mapToDto(Consumer entity){
        ConsumerDto dto = new ConsumerDto();
        dto.setConsumerId(entity.getConsumerId());
        dto.setConsumerName(entity.getConsumerName());
        dto.setConsumerIdentityNo(entity.getConsumerIdentityNo());
        dto.setConsumerPassword(entity.getConsumerPassword());
        return dto;
    }
}
