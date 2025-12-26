package com.ravidpatel.mybookingapp.service;

import com.ravidpatel.mybookingapp.dto.SeatDto;
import com.ravidpatel.mybookingapp.entity.Seat;
import com.ravidpatel.mybookingapp.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SeatService {

    @Autowired
    private SeatRepository seatRepository;

    public String save(SeatDto dto) {
        Seat seat = mapToEntity(dto); // validate dto is null ornot
        seat.setScreenId(UUID.randomUUID().toString());
        return seatRepository.save(seat);
    }

    public SeatDto getById(String seatId) {
        return mapToDto(seatRepository.getById(seatId));
    }

    // DTO → Entity
    public Seat mapToEntity(SeatDto dto) {

        if (dto == null) return null;

        Seat seat = new Seat();
        seat.setSeatId(dto.getSeatId());
        seat.setScreenId(dto.getScreenId());
        seat.setSeatNo(dto.getSeatNo());
        seat.setSeatType(dto.getSeatType());

        return seat;
    }

    // Entity → DTO
    public SeatDto mapToDto(Seat entity) {

        if (entity == null) return null;

        SeatDto dto = new SeatDto();
        dto.setSeatId(entity.getSeatId());
        dto.setScreenId(entity.getScreenId());
        dto.setSeatNo(entity.getSeatNo());
        dto.setSeatType(entity.getSeatType());

        return dto;
    }
}
