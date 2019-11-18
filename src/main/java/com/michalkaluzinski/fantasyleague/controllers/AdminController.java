package com.michalkaluzinski.fantasyleague.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.michalkaluzinski.fantasyleague.dtos.UserDTO;
import com.michalkaluzinski.fantasyleague.services.UserService;
import io.swagger.annotations.ApiImplicitParam;

@Controller
@RequestMapping("/admin")
public class AdminController {

  @Autowired private UserService userService;

  @ApiImplicitParam(
      name = "Authorization",
      value = "Access Token",
      required = true,
      allowEmptyValue = false,
      paramType = "header",
      dataTypeClass = String.class,
      example = "Bearer access_token")
  @GetMapping("/users")
  public ResponseEntity<List<UserDTO>> findAll() {
    return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
  }
}
