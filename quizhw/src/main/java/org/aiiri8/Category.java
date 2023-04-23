package org.aiiri8;

import java.util.Date;

public class Category {
  private int id;
  private String title;
  private Date created_at;
  private Date updated_at;
  private int clues_count;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Date getCreated_at() {
    return created_at;
  }

  public void setCreated_at(Date created_at) {
    this.created_at = created_at;
  }

  public Date getUpdated_at() {
    return updated_at;
  }

  public void setUpdated_at(Date updated_at) {
    this.updated_at = updated_at;
  }

  public int getClues_count() {
    return clues_count;
  }

  public void setClues_count(int clues_count) {
    this.clues_count = clues_count;
  }
}