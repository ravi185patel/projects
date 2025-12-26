package com.ravidpatel.mybookingapp.entity;

import java.time.LocalDateTime;

public class Show {
    private String showId;
    private String movieId;
    private String screenId;
    private LocalDateTime showDate;
    private LocalDateTime fromTime;
    private LocalDateTime toTime;

    public String getShowId() {
        return showId;
    }

    public void setShowId(String showId) {
        this.showId = showId;
    }
}
