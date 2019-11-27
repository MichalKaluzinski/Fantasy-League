package com.michalkaluzinski.fantasyleague.controllers.admin;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.michalkaluzinski.fantasyleague.dtos.TeamAddDTO;
import com.michalkaluzinski.fantasyleague.dtos.TeamDTO;
import com.michalkaluzinski.fantasyleague.exceptions.RestApiException;
import com.michalkaluzinski.fantasyleague.services.TeamService;
import io.swagger.annotations.ApiImplicitParam;

@Controller
@RequestMapping("/admin/teams")
public class AdminTeamController {

  @Autowired private TeamService teamService;

  @ApiImplicitParam(
      name = "Authorization",
      value = "Access Token",
      required = true,
      allowEmptyValue = false,
      paramType = "header",
      dataTypeClass = String.class,
      example = "Bearer access_token")
  @GetMapping("/")
  public ResponseEntity<List<TeamDTO>> findAll() {
    return new ResponseEntity<>(teamService.findAll(), HttpStatus.OK);
  }

  @ApiImplicitParam(
      name = "Authorization",
      value = "Access Token",
      required = true,
      allowEmptyValue = false,
      paramType = "header",
      dataTypeClass = String.class,
      example = "Bearer access_token")
  @PostMapping("/")
  public ResponseEntity<TeamDTO> add(@RequestBody TeamAddDTO teamAddDTO) throws RestApiException {
    return new ResponseEntity<>(teamService.add(teamAddDTO), HttpStatus.OK);
  }
}
