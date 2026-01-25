package com.ravidpatel.mybookingapp.entity;

public class Seat {
  private Long seatId;
  private Long spaceId;
  private String rowLabel;
  private String seatNo;
  private String seatType; // enum

 public Long getSeatId() {
  return seatId;
 }

 public void setSeatId(Long seatId) {
  this.seatId = seatId;
 }

 public Long getSpaceId() {
  return spaceId;
 }

 public void setSpaceId(Long spaceId) {
  this.spaceId = spaceId;
 }

 public String getRowLabel() {
  return rowLabel;
 }

 public void setRowLabel(String rowLabel) {
  this.rowLabel = rowLabel;
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
