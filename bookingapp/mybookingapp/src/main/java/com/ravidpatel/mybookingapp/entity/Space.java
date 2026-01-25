package com.ravidpatel.mybookingapp.entity;

public class Space {
 private Long spaceId;
 private String spaceName;
 private int capacity;
 private Long venueId;

 public Long getSpaceId() {
  return spaceId;
 }

 public void setSpaceId(Long spaceId) {
  this.spaceId = spaceId;
 }

 public String getSpaceName() {
  return spaceName;
 }

 public void setSpaceName(String spaceName) {
  this.spaceName = spaceName;
 }

 public int getCapacity() {
  return capacity;
 }

 public void setCapacity(int capacity) {
  this.capacity = capacity;
 }

 public Long getVenueId() {
  return venueId;
 }

 public void setVenueId(Long venueId) {
  this.venueId = venueId;
 }
}
