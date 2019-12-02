package com.michalkaluzinski.fantasyleague.controllers.admin;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.michalkaluzinski.fantasyleague.dtos.GameAddDTO;
import com.michalkaluzinski.fantasyleague.dtos.GameDTO;
import com.michalkaluzinski.fantasyleague.exceptions.RestApiException;
import com.michalkaluzinski.fantasyleague.services.GameService;
import io.swagger.annotations.ApiImplicitParam;

@Controller
@RequestMapping("/admins/games")
public class AdminGameController {

  @Autowired private GameService gameService;

  @ApiImplicitParam(
      name = "Authorization",
      value = "Access Token",
      required = true,
      allowEmptyValue = false,
      paramType = "header",
      dataTypeClass = String.class,
      example = "Bearer access_token")
  @GetMapping("/")
  public ResponseEntity<List<GameDTO>> findAll() {
    return new ResponseEntity<>(gameService.findAll(), HttpStatus.OK);
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
  public ResponseEntity<GameDTO> add(@Valid @RequestBody GameAddDTO gameAddDTO)
      throws RestApiException {
    return new ResponseEntity<>(gameService.add(gameAddDTO), HttpStatus.OK);
  }
}
