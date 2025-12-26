package com.ravidpatel.mybookingapp.dto;

public class TheaterDto {
  private String theaterId;
  private String theaterName;
  private String theaterCity;
  private String theaterState;
  private String theaterPinCode;

  public String getTheaterId() {
    return theaterId;
  }

  public void setTheaterId(String theaterId) {
    this.theaterId = theaterId;
  }

  public String getTheaterName() {
    return theaterName;
  }

  public void setTheaterName(String theaterName) {
    this.theaterName = theaterName;
  }

  public String getTheaterCity() {
    return theaterCity;
  }

  public void setTheaterCity(String theaterCity) {
    this.theaterCity = theaterCity;
  }

  public String getTheaterState() {
    return theaterState;
  }

  public void setTheaterState(String theaterState) {
    this.theaterState = theaterState;
  }

  public String getTheaterPinCode() {
    return theaterPinCode;
  }

  public void setTheaterPinCode(String theaterPinCode) {
    this.theaterPinCode = theaterPinCode;
  }
}
