package com.bart.model;

import java.util.List;
import java.util.Objects;

import org.joda.time.DateTime;
import org.springframework.util.ObjectUtils;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Team {

  private Long id;
  private String content;
  private String name;
  private String city;
  private String owner;
  private String competition;
  private List<Player> players;
  @JsonFormat(pattern = "dd-MM-yyyy")
  private DateTime dateOfCreation;


  public Long getId() {
    return id;
  }

  public void setId(final Long id) {
    this.id = id;
  }

  public String getContent() {
    return content;
  }

  public void setContent(final String content) {
    this.content = content;
  }

  public String getName() {
    return name;
  }

  public void setName(final String name) {
    this.name = name;
  }

  public String getCity() {
    return city;
  }

  public void setCity(final String city) {
    this.city = city;
  }

  public String getOwner() {
    return owner;
  }

  public void setOwner(final String owner) {
    this.owner = owner;
  }

  public String getCompetition() {
    return competition;
  }

  public void setCompetition(final String competition) {
    this.competition = competition;
  }

  public List<Player> getPlayers() {
    return players;
  }

  public void setPlayers(final List<Player> players) {
    this.players = players;
  }

  public DateTime getDateOfCreation() {
    return dateOfCreation;
  }

  public void setDateOfCreation(final DateTime dateOfCreation) {
    this.dateOfCreation = dateOfCreation;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (!(obj instanceof Team)) {
      return false;
    }
    final Team other = (Team) obj;
    return ObjectUtils.nullSafeEquals(this.id, other.id)
        && ObjectUtils.nullSafeEquals(this.content, other.content)
        && ObjectUtils.nullSafeEquals(this.name, other.name)
        && ObjectUtils.nullSafeEquals(this.city, other.city)
        && ObjectUtils.nullSafeEquals(this.owner, other.owner)
        && ObjectUtils.nullSafeEquals(this.competition, other.competition)
        && ObjectUtils.nullSafeEquals(this.dateOfCreation, other.dateOfCreation);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id, this.content, this.name, this.city, this.competition,
        this.dateOfCreation);
  }
}
