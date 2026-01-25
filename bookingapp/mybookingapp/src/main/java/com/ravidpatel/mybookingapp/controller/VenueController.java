package com.ravidpatel.mybookingapp.controller;

import com.ravidpatel.mybookingapp.dto.*;
import com.ravidpatel.mybookingapp.entity.Space;
import com.ravidpatel.mybookingapp.service.VenueService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/venue")
public class VenueController {

    private final VenueService venueService;

    public VenueController(VenueService venueService) {
        this.venueService = venueService;
    }

    /* ===================== VENUE ===================== */

    @PostMapping("/venues")
    public ResponseEntity<Long> createVenue(@RequestBody VenueDto venueDto) {
        Long venueId = venueService.createVenue(venueDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(venueId);
    }

    @PutMapping("/venues/{venueId}")
    public ResponseEntity<Void> updateVenue(@PathVariable Long venueId,
                                            @RequestBody VenueDto venueDto) {
        venueService.updateVenue(venueId, venueDto);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/venues/{venueId}")
    public ResponseEntity<VenueDto> getVenue(@PathVariable Long venueId) {
        return ResponseEntity.ok(venueService.getVenue(venueId));
    }

    /* ===================== CONTENT ===================== */

    @PostMapping("/contents")
    public ResponseEntity<Long> createContent(@RequestBody ContentDto contentDto) {
        Long contentId = venueService.createContent(contentDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(contentId);
    }

    @PutMapping("/contents/{contentId}")
    public ResponseEntity<Void> updateContent(
            @PathVariable Long contentId,
            @RequestBody ContentDto contentDto
    ) {
        venueService.updateContent(contentId, contentDto);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/contents/{contentId}")
    public ResponseEntity<ContentDto> getContent(@PathVariable Long contentId) {
        return ResponseEntity.ok(venueService.getContent(contentId));
    }

    /* ===================== SPACE ===================== */

    @PostMapping("/spaces")
    public ResponseEntity<Long> createSpace(
            @RequestBody SpaceDto spaceDto
    ) {
        Long spaceId = venueService.createSpace(spaceDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(spaceId);
    }

//    @PutMapping("/spaces/{spaceId}")
//    public ResponseEntity<Void> updateSpaceCapacity(
//            @PathVariable Long spaceId,
//            @RequestBody SpaceDto spaceDto
//    ) {
//        venueService.updateSpaceCapacity(
//                spaceId,
//                request.get("capacity")
//        );
//        return ResponseEntity.noContent().build();
//    }

    @GetMapping("/spaces/{spaceId}")
    public ResponseEntity<SpaceDto> getSpace(
            @PathVariable Long spaceId
    ) {
        return ResponseEntity.ok(
                venueService.getSpace(spaceId)
        );
    }

    /* ===================== SEAT ===================== */

    @PostMapping("/spaces/{spaceId}/seats")
    public ResponseEntity<Void> createSeatLayout(
            @PathVariable Long spaceId,
            @RequestBody List<SeatDto> seats
    ) {
        venueService.createSeatLayout(spaceId, seats);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/spaces/{spaceId}/seats")
    public ResponseEntity<List<SeatDto>> getSeats(
            @PathVariable Long spaceId
    ) {
        return ResponseEntity.ok(
                venueService.getSeats(spaceId)
        );
    }

    /* ===================== SCHEDULE ===================== */

    /* ===================== SCHEDULE ===================== */

    @PostMapping("/schedules")
    public ResponseEntity<Long> createSchedule(
            @RequestBody ScheduleDto scheduleDto
    ) {
        Long scheduleId = venueService.createSchedule(scheduleDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(scheduleId);
    }

//    @PutMapping("/schedules/{scheduleId}/price")
//    public ResponseEntity<Void> updateSchedulePrice(
//            @PathVariable Long scheduleId,
//            @RequestBody ScheduleDto scheduleDto
//    ) {
//        venueService.updateSchedulePrice(
//                scheduleId,
//                new BigDecimal(request.get("price").toString())
//        );
//        return ResponseEntity.noContent().build();
//    }

    @GetMapping("/schedules/{scheduleId}")
    public ResponseEntity<ScheduleDto> getSchedule(
            @PathVariable Long scheduleId
    ) {
        return ResponseEntity.ok(
                venueService.getSchedule(scheduleId)
        );
    }
}
