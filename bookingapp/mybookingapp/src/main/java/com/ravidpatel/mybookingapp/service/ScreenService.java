package com.ravidpatel.mybookingapp.service;

import com.ravidpatel.mybookingapp.dto.ScreenDto;
import com.ravidpatel.mybookingapp.entity.Screen;
import com.ravidpatel.mybookingapp.repository.ScreenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ScreenService {


    @Autowired
    private ScreenRepository screenRepository;
    public String save(ScreenDto dto) {
        Screen screen = mapToEntity(dto);
        screen.setScreenId(UUID.randomUUID().toString());
        return screenRepository.save(screen);
    }

    public ScreenDto getById(String screenId) {
        return mapToDto(screenRepository.getById(screenId));
    }

    // DTO → Entity
    public Screen mapToEntity(ScreenDto dto) {

        if (dto == null) return null;

        Screen screen = new Screen();
        screen.setScreenId(dto.getScreenId());
        screen.setTheaterId(dto.getTheaterId());
        screen.setScreenNo(dto.getScreenNo());

        return screen;
    }

    // Entity → DTO
    public static ScreenDto mapToDto(Screen entity) {

        if (entity == null) return null;

        ScreenDto dto = new ScreenDto();
        dto.setScreenId(entity.getScreenId());
        dto.setTheaterId(entity.getTheaterId());
        dto.setScreenNo(entity.getScreenNo());

        return dto;
    }
}
