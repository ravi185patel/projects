package com.ravidpatel.mybookingapp.entity;

public class Seat {
 private String seatId;
 private String screenId;
 private String seatNo;
 private String seatType; // enum

 public String getSeatId() {
  return seatId;
 }

 public void setSeatId(String seatId) {
  this.seatId = seatId;
 }

 public String getScreenId() {
  return screenId;
 }

 public void setScreenId(String screenId) {
  this.screenId = screenId;
 }

 public String getSeatNo() {
  return seatNo;
 }

 public void setSeatNo(String seatNo) {
  this.seatNo = seatNo;
 }

 public String getSeatType() {
  return seatType;
 }

 public void setSeatType(String seatType) {
  this.seatType = seatType;
 }
}
