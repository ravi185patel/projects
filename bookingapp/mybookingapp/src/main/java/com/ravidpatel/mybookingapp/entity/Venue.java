package com.ravidpatel.mybookingapp.entity;

import com.ravidpatel.mybookingapp.constant.VenueType;

public class Venue {
    private Long venueId;
    private String venueName;
    private String venueCity;
    private String venueState;
    private String venuePinCode;
    private VenueType venueType;

    public Long getVenueId() {
        return venueId;
    }

    public void setVenueId(Long venueId) {
        this.venueId = venueId;
    }

    public String getVenueName() {
        return venueName;
    }

    public void setVenueName(String venueName) {
        this.venueName = venueName;
    }

    public String getVenueCity() {
        return venueCity;
    }

    public void setVenueCity(String venueCity) {
        this.venueCity = venueCity;
    }

    public String getVenueState() {
        return venueState;
    }

    public void setVenueState(String venueState) {
        this.venueState = venueState;
    }

    public String getVenuePinCode() {
        return venuePinCode;
    }

    public void setVenuePinCode(String venuePinCode) {
        this.venuePinCode = venuePinCode;
    }

    public VenueType getVenueType() {
        return venueType;
    }

    public void setVenueType(VenueType venueType) {
        this.venueType = venueType;
    }
}
