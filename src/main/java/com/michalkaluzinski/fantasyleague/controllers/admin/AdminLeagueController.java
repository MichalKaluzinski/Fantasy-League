package com.michalkaluzinski.fantasyleague.controllers.admin;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.michalkaluzinski.fantasyleague.dtos.LeagueAddDTO;
import com.michalkaluzinski.fantasyleague.dtos.LeagueDTO;
import com.michalkaluzinski.fantasyleague.dtos.ResponseMessageDTO;
import com.michalkaluzinski.fantasyleague.exceptions.RestApiException;
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

  @ApiImplicitParam(
      name = "Authorization",
      value = "Access Token",
      required = true,
      allowEmptyValue = false,
      paramType = "header",
      dataTypeClass = String.class,
      example = "Bearer access_token")
  @PostMapping("/")
  public ResponseEntity<LeagueDTO> add(@RequestBody LeagueAddDTO leagueAddDTO)
      throws RestApiException {
    return new ResponseEntity<>(leagueService.add(leagueAddDTO), HttpStatus.OK);
  }

  @ApiImplicitParam(
      name = "Authorization",
      value = "Access Token",
      required = true,
      allowEmptyValue = false,
      paramType = "header",
      dataTypeClass = String.class,
      example = "Bearer access_token")
  @DeleteMapping("/{leagueId}")
  public ResponseEntity<ResponseMessageDTO> delete(@PathVariable("leagueId") Integer leagueId)
      throws RestApiException {
    leagueService.delete(leagueId);
    ResponseMessageDTO responseMessageDTO = new ResponseMessageDTO();
    responseMessageDTO.setMessage(String.format("League with id = %d deleted.", leagueId));
    return new ResponseEntity<>(responseMessageDTO, HttpStatus.OK);
  }
}
