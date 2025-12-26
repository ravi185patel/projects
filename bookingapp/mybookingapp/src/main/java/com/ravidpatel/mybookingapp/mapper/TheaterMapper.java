package com.ravidpatel.mybookingapp.mapper;

import com.ravidpatel.mybookingapp.entity.Show;
import com.ravidpatel.mybookingapp.entity.Theater;

import java.util.List;

public class TheaterMapper {

    List<Theater> theaters;

    public List<Theater> getTheaters() {
        return theaters;
    }

    public void setTheaters(List<Theater> theaters) {
        this.theaters = theaters;
    }
}
