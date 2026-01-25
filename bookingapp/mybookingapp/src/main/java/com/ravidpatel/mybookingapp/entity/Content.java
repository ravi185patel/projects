package com.ravidpatel.mybookingapp.entity;

import com.ravidpatel.mybookingapp.constant.ContentType;

public class Content {
 private Long contentId;
 private String contentTitle;
 private int durationMinutes;
 private ContentType contentType;

 public Long getContentId() {
  return contentId;
 }

 public void setContentId(Long contentId) {
  this.contentId = contentId;
 }

 public String getContentTitle() {
  return contentTitle;
 }

 public void setContentTitle(String contentTitle) {
  this.contentTitle = contentTitle;
 }

 public int getDurationMinutes() {
  return durationMinutes;
 }

 public void setDurationMinutes(int durationMinutes) {
  this.durationMinutes = durationMinutes;
 }

 public ContentType getContentType() {
  return contentType;
 }

 public void setContentType(ContentType contentType) {
  this.contentType = contentType;
 }
}
