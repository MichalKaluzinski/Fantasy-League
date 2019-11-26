package com.michalkaluzinski.fantasyleague.controllers.admin;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.michalkaluzinski.fantasyleague.dtos.CustomLeagueDTO;
import com.michalkaluzinski.fantasyleague.services.CustomLeagueService;
import io.swagger.annotations.ApiImplicitParam;

@Controller
@RequestMapping("/admin/customleagues")
public class AdminCustomLeagueController {
  
  @Autowired
  private CustomLeagueService customLeagueService;
  
  @ApiImplicitParam(
      name = "Authorization",
      value = "Access Token",
      required = true,
      allowEmptyValue = false,
      paramType = "header",
      dataTypeClass = String.class,
      example = "Bearer access_token")
  @GetMapping("/")
  public ResponseEntity<List<CustomLeagueDTO>> findAll(){
    return new ResponseEntity<>(customLeagueService.findAll(), HttpStatus.OK);
  }
}
