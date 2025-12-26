package com.ravidpatel.mybookingapp.service;

import com.ravidpatel.mybookingapp.dto.TheaterDto;
import com.ravidpatel.mybookingapp.entity.Theater;
import com.ravidpatel.mybookingapp.repository.TheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TheaterService {

    @Autowired
    private TheaterRepository theaterRepository;


    public String save(TheaterDto dto){
        Theater theater = mapToEntity(dto);
        theater.setTheaterId(UUID.randomUUID().toString());
        theaterRepository.save(theater);
        return theater.getTheaterId();
    }


    public TheaterDto getById(String theaterId) {
        return mapToDto(theaterRepository.getById(theaterId));
    }

    public TheaterDto mapToDto(Theater entity){
        TheaterDto dto = new TheaterDto();
        dto.setTheaterName(entity.getTheaterName());
        dto.setTheaterCity(entity.getTheaterCity());
        dto.setTheaterState(entity.getTheaterState());
        dto.setTheaterPinCode(entity.getTheaterPinCode());
        dto.setTheaterId(entity.getTheaterId());
        return dto;
    }

    public Theater mapToEntity(TheaterDto dto){
        Theater entity = new Theater();
        entity.setTheaterName(dto.getTheaterName());
        entity.setTheaterCity(dto.getTheaterCity());
        entity.setTheaterState(dto.getTheaterState());
        entity.setTheaterPinCode(dto.getTheaterPinCode());
        entity.setTheaterId(dto.getTheaterId());
        return entity;
    }
}
