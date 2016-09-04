package com.bart.controller;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bart.exception.NotFoundException;
import com.bart.model.Team;
import com.bart.service.TeamService;

@RestController
public class TeamController {
  private final Logger LOG = LoggerFactory.getLogger(this.getClass());

  @Autowired
  private TeamService teamService;

  @RequestMapping(value = "/team/{id}", method = RequestMethod.GET)
  public Team getTeam(@PathVariable Long id) throws NotFoundException {
    LOG.debug("Processing GET /team/" + id + " request");
    final Team team = teamService.getTeam(id);
    if (team == null) {
      LOG.debug("Team with id: " + id + " not found");
      throw new NotFoundException();
    }
    LOG.debug("Found team with id: " + id);
    return team;
  }

  @RequestMapping(value = "/teams", method = RequestMethod.GET)
  public Collection<Team> getTeams() {
    LOG.debug("Processing GET /teams request");
    return teamService.getTeams();
  }

  @RequestMapping(value = "/team", method = RequestMethod.POST)
  public Team postTeam(@RequestBody Team team) {
    LOG.debug("Processing POST /team request");
    return teamService.createTeam(team);
  }
}
