package com.bart.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Collection;

import org.junit.Before;
import org.junit.Test;

import com.bart.model.Team;
import com.bart.service.TeamService;

public class TeamServiceTests {

  private TeamService instance;

  @Before
  public void before() {
    instance = new TeamService();
  }


  @Test
  public void getTeamsWhenEmpty() {
    Collection<Team> ret = instance.getTeams();
    assertTrue(ret.isEmpty());
  }

  @Test
  public void getTeamsAfterCreation() {
    final Team team1 = new Team();
    final Team team2 = new Team();
    team1.setName("Manchester United");
    team2.setName("Manchester City");
    instance.createTeam(team1);
    instance.createTeam(team2);
    Collection<Team> ret = instance.getTeams();
    assertEquals(ret.size(), 2);
    assertTrue(ret.contains(team1));
    assertTrue(ret.contains(team2));
  }

  @Test
  public void getTeamAfterCreation() {
    final Team team = new Team();
    team.setName("Manchester United");
    final Team createdTeam = instance.createTeam(team);
    Team ret = instance.getTeam(createdTeam.getId());
    assertEquals(ret.getName(), team.getName());
  }

  @Test
  public void teamCreationPopulatesIdAndDateCreated() {
    final Team team = new Team();
    team.setName("Manchester United");
    final Team createdTeam = instance.createTeam(team);
    assertTrue(createdTeam.getId() != null);
    assertTrue(createdTeam.getDateOfCreation() != null);
  }
}
