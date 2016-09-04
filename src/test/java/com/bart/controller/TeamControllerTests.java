package com.bart.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.bart.model.Team;
import com.bart.service.TeamService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TeamControllerTests {

  @InjectMocks
  private TeamController teamController;
  @Mock
  private TeamService teamService = Mockito.mock(TeamService.class);
  private MockMvc mockMvc;
  private ObjectMapper mapper = new ObjectMapper();


  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
    this.mockMvc = MockMvcBuilders.standaloneSetup(teamController).build();
  }

  @Test
  public void getTeamsShouldReturn200() throws Exception {
    this.mockMvc.perform(get("/teams")).andDo(print()).andExpect(status().isOk());
  }

  @Test
  public void getTeamsShouldReturnAllTeamsFromService() throws Exception {
    final Collection<Team> teams = new HashSet<>();
    final Team team1 = new Team();
    final Team team2 = new Team();
    team1.setId(1L);
    team2.setId(2L);
    teams.add(team1);
    teams.add(team2);
    when(teamService.getTeams()).thenReturn(teams);

    final MvcResult result =
        this.mockMvc.perform(get("/teams")).andDo(print()).andExpect(status().isOk()).andReturn();

    final Collection<Team> returnedTeams = mapper
        .readValue(result.getResponse().getContentAsString(), new TypeReference<Set<Team>>() {});
    assertEquals(teams, returnedTeams);
  }

  @Test
  public void getTeamNotExistingShouldThrow404() throws Exception {
    this.mockMvc.perform(get("/team/1234")).andDo(print()).andExpect(status().is(404));
  }

  @Test
  public void getTeamShouldReturnCorrectObject() throws Exception {
    final Team team1 = new Team();
    team1.setId(1L);
    when(teamService.getTeam(1L)).thenReturn(team1);

    final MvcResult result = this.mockMvc.perform(get("/team/1")).andDo(print()).andReturn();

    final Team returnedTeam =
        mapper.readValue(result.getResponse().getContentAsString(), Team.class);
    assertEquals(returnedTeam, team1);
  }

  @Test
  public void newTeamShouldBePassedToService() throws Exception {
    final Team team1 = new Team();
    team1.setName("Manchester United");
    team1.setId(1L);
    when(teamService.createTeam(any(Team.class))).thenReturn(team1);

    this.mockMvc.perform(post("/team").contentType(MediaType.APPLICATION_JSON)
        .content(mapper.writeValueAsBytes(team1))).andDo(print()).andExpect(status().isOk());;

    verify(teamService).createTeam(team1);
  }
}
