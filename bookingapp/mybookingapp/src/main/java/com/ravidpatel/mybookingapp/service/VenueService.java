package com.ravidpatel.mybookingapp.service;

import com.ravidpatel.mybookingapp.dto.*;
import com.ravidpatel.mybookingapp.entity.*;
import com.ravidpatel.mybookingapp.repository.VenueRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class VenueService {

    private final VenueRepository venueRepository;

    public VenueService(VenueRepository venueRepository) {
        this.venueRepository = venueRepository;
    }


    /* ===================== VENUE ===================== */

    public Long createVenue(VenueDto dto) {
        Venue venue = entityIntoDtoVenue(dto);
        return venueRepository.insertVenue(venue);
    }

    public void updateVenue(Long venueId, VenueDto dto) {
        Venue venue = entityIntoDtoVenue(dto);
        int updated = venueRepository.updateVenue(venue);
        if (updated == 0) {
            throw new IllegalArgumentException("Venue not found: " + venueId);
        }
    }

    public VenueDto getVenue(Long venueId) {
        return dtoToEntityVenue(venueRepository.getVenue(venueId));
    }

    /* ===================== CONTENT ===================== */

    public Long createContent(ContentDto dto) {
        Content content = entityIntoDtoContent(dto);
        return venueRepository.insertContent(content);
    }

    public void updateContent(Long contentId, ContentDto dto) {
        Content content = entityIntoDtoContent(dto,contentId);

        int updated = venueRepository.updateContent(content);
        if (updated == 0) {
            throw new IllegalArgumentException("Content not found: " + contentId);
        }
    }

    public ContentDto getContent(Long contentId) {
        return dtoToEntityContent(venueRepository.getContent(contentId));
    }

    /* ===================== SPACE ===================== */

    public Long createSpace(SpaceDto dto) {
        Space space = dtoToEntitySpace(dto);
        return venueRepository.insertSpace(space);
    }

//    public void updateSpaceCapacity(Long spaceId, Integer capacity) {
//        Space space = new Space();
//        space.setSpaceId(spaceId);
//        space.setCapacity(capacity);
//
//        int updated = venueRepository.updateSpace(space);
//        if (updated == 0) {
//            throw new IllegalArgumentException("Space not found: " + spaceId);
//        }
//    }

    public SpaceDto getSpace(Long spaceId) {
        return entityToDtoSpace(venueRepository.getSpace(spaceId));
    }

    /* ===================== SEAT (BULK) ===================== */

    @Transactional
    public void createSeatLayout(Long spaceId, List<SeatDto> seatDtos) {

        if (seatDtos == null || seatDtos.isEmpty()) {
            throw new IllegalArgumentException("Seat list cannot be empty");
        }

        List<Seat> seats = seatDtos.stream()
                .map(dto -> {
                    Seat seat = entityToDtoSeat(dto);
                    seat.setSpaceId(spaceId);
                    return seat;
                })
                .toList();

        venueRepository.insertSeats(seats);
    }

    public List<SeatDto> getSeats(Long spaceId) {
        return venueRepository.getSeatsBySpace(spaceId).stream().map(seat -> entityToDtoSeat(seat)).collect(Collectors.toList());
    }

    /* ===================== SCHEDULE ===================== */

    @Transactional
    public Long createSchedule(ScheduleDto dto) {

        if (!dto.getFromTime().isBefore(dto.getToTime())) {
            throw new IllegalArgumentException("fromTime must be before toTime");
        }

        Schedule schedule = entityIntoDtoSchedule(dto);

        return venueRepository.insertSchedule(schedule);
    }

    public void updateSchedulePrice(Long scheduleId, BigDecimal price) {
        int updated = venueRepository.updateSchedulePrice(scheduleId, price);
        if (updated == 0) {
            throw new IllegalArgumentException("Schedule not found: " + scheduleId);
        }
    }

    public ScheduleDto getSchedule(Long scheduleId) {
        return entityIntoDtoSchedule(venueRepository.getSchedule(scheduleId));
    }

    public Venue entityIntoDtoVenue(VenueDto dto){
        Venue venue = new Venue();
        venue.setVenueName(dto.getVenueName());
        venue.setVenueCity(dto.getVenueCity());
        venue.setVenueType(dto.getVenueType());
        return venue;
    }
    public VenueDto dtoToEntityVenue(Venue entity){
        VenueDto dto = new VenueDto();
        dto.setVenueName(entity.getVenueName());
        dto.setVenueCity(entity.getVenueCity());
        dto.setVenueType(entity.getVenueType());
        return dto;
    }

    public Content entityIntoDtoContent(ContentDto dto) {
        Content content = new Content();
        content.setContentType(dto.getContentType());
        content.setContentTitle(dto.getContentTitle());
        content.setDurationMinutes(dto.getDurationMinutes());
        return content;
    }


    public ContentDto dtoToEntityContent(Content dto) {
        ContentDto content = new ContentDto();
        content.setContentType(dto.getContentType());
        content.setContentTitle(dto.getContentTitle());
        content.setDurationMinutes(dto.getDurationMinutes());
        return content;
    }

    public Content entityIntoDtoContent(ContentDto dto,Long id) {
        Content content = new Content();
        content.setContentId(id);
        content.setContentType(dto.getContentType());
        content.setContentTitle(dto.getContentTitle());
        content.setDurationMinutes(dto.getDurationMinutes());
        return content;
    }

    public SeatDto entityToDtoSeat(Seat entity) {
        SeatDto seat = new SeatDto();
        seat.setSpaceId(entity.getSpaceId());
        seat.setRowLabel(entity.getRowLabel());
        seat.setSeatNo(entity.getSeatNo());
        seat.setSeatType(entity.getSeatType());
        return seat;
    }

    public Seat entityToDtoSeat(SeatDto dto) {
        Seat seat = new Seat();
        seat.setSpaceId(dto.getSpaceId());
        seat.setRowLabel(dto.getRowLabel());
        seat.setSeatNo(dto.getSeatNo());
        seat.setSeatType(dto.getSeatType());
        return seat;
    }

    public Schedule entityIntoDtoSchedule(ScheduleDto dto) {
        Schedule schedule = new Schedule();
        schedule.setContentId(dto.getContentId());
        schedule.setSpaceId(dto.getSpaceId());
        schedule.setScheduledDate(dto.getScheduledDate());
        schedule.setFromTime(dto.getFromTime());
        schedule.setToTime(dto.getToTime());
        schedule.setBasePrice(dto.getBasePrice());
        return schedule;
    }

    public ScheduleDto entityIntoDtoSchedule(Schedule dto) {
        ScheduleDto schedule = new ScheduleDto();
        schedule.setContentId(dto.getContentId());
        schedule.setSpaceId(dto.getSpaceId());
        schedule.setScheduledDate(dto.getScheduledDate());
        schedule.setFromTime(dto.getFromTime());
        schedule.setToTime(dto.getToTime());
        schedule.setBasePrice(dto.getBasePrice());
        return schedule;
    }

    public Space dtoToEntitySpace(SpaceDto dto){
        Space space = new Space();
        space.setVenueId(dto.getVenueId());
        space.setSpaceName(dto.getSpaceName());
        space.setCapacity(dto.getCapacity());
        return space;
    }

    public Space dtoToEntitySpace(SpaceDto dto,Long id){
        Space space = new Space();
        space.setSpaceId(id);
        space.setVenueId(dto.getVenueId());
        space.setSpaceName(dto.getSpaceName());
        space.setCapacity(dto.getCapacity());
        return space;
    }

    public SpaceDto entityToDtoSpace(Space dto,Long id){
        SpaceDto space = new SpaceDto();
        space.setSpaceId(id);
        space.setVenueId(dto.getVenueId());
        space.setSpaceName(dto.getSpaceName());
        space.setCapacity(dto.getCapacity());
        return space;
    }

    public SpaceDto entityToDtoSpace(Space dto){
        SpaceDto space = new SpaceDto();
        space.setVenueId(dto.getVenueId());
        space.setSpaceName(dto.getSpaceName());
        space.setCapacity(dto.getCapacity());
        return space;
    }

}
