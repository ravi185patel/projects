package com.ravidpatel.mybookingapp.service;

import com.ravidpatel.mybookingapp.dto.ShowDto;
import com.ravidpatel.mybookingapp.entity.Show;
import com.ravidpatel.mybookingapp.repository.ShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class ShowService {

    @Autowired
    private ShowRepository showRepository;

    public String createShow(ShowDto dto) {
        Show entity = mapToEntity(dto);
        showRepository.save(entity);
        return entity.getShowId();
    }

    public List<String> createShows(List<ShowDto> dtos) {
        List<Show> entities = dtos.stream()
                .map(this::mapToEntity)
                .toList();

        showRepository.saveAll(entities);

        return entities.stream()
                .map(Show::getShowId)
                .toList();
    }

    public ShowDto getShowById(String showId) {
        return mapToDto(showRepository.getShowById(showId));
    }

    public List<ShowDto> searchShows(
            Long movieId,
            Long screenId,
            LocalDate showDate
    ) {
        return showRepository.search(movieId, screenId, showDate)
                .stream()
                .map(this::mapToDto)
                .toList();
    }

    // --------------------
    // Mapping
    // --------------------
    private Show mapToEntity(ShowDto dto) {
        Show entity = new Show();
        entity.setShowId(Objects.nonNull(entity.getShowId()) ? entity.getShowId():UUID.randomUUID().toString());
        entity.setMovieId(dto.getMovieId());
        entity.setScreenId(dto.getScreenId());
        entity.setShowDate(dto.getShowDate());
        entity.setFromTime(dto.getFromTime());
        entity.setToTime(dto.getToTime());
        return entity;
    }

    private ShowDto mapToDto(Show entity) {
        ShowDto dto = new ShowDto();
        dto.setShowId(entity.getShowId());
        dto.setMovieId(entity.getMovieId());
        dto.setScreenId(entity.getScreenId());
        dto.setShowDate(entity.getShowDate());
        dto.setFromTime(entity.getFromTime());
        dto.setToTime(entity.getToTime());
        return dto;
    }
}
