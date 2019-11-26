package com.michalkaluzinski.fantasyleague.controllers.admin;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.michalkaluzinski.fantasyleague.dtos.LeagueDTO;
import com.michalkaluzinski.fantasyleague.services.LeagueService;
import io.swagger.annotations.ApiImplicitParam;

@Controller
@RequestMapping("/admin/leagues")
public class AdminLeagueController {

  @Autowired private LeagueService leagueService;

  @ApiImplicitParam(
      name = "Authorization",
      value = "Access Token",
      required = true,
      allowEmptyValue = false,
      paramType = "header",
      dataTypeClass = String.class,
      example = "Bearer access_token")
  @GetMapping("/")
  public ResponseEntity<List<LeagueDTO>> findAll() {
    return new ResponseEntity<>(leagueService.findAll(), HttpStatus.OK);
  }
}
