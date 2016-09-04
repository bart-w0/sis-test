package com.bart.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.joda.time.DateTime;

import com.bart.model.Team;

public class TeamService {

  private final Map<Long, Team> teams = new HashMap<>();

  public Team getTeam(final Long id) {
    return teams.get(id);
  }

  public Collection<Team> getTeams() {
    return teams.values();
  }

  public Team createTeam(final Team team) {
    team.setId(Long.valueOf(teams.size() + 1));
    team.setDateOfCreation(DateTime.now());
    teams.put(team.getId(), team);
    return team;
  }
}
