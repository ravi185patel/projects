package com.ravidpatel.mybookingapp.mapper;

import com.ravidpatel.mybookingapp.entity.Movie;
import com.ravidpatel.mybookingapp.entity.Show;

import java.util.List;

public class ShowMapper {

    List<Show> shows;

    public List<Show> getShows() {
        return shows;
    }

    public void setShows(List<Show> shows) {
        this.shows = shows;
    }
}
