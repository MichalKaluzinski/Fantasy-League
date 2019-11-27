package com.michalkaluzinski.fantasyleague.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.michalkaluzinski.fantasyleague.dtos.UserDTO;
import com.michalkaluzinski.fantasyleague.dtos.UserLoggedDTO;
import com.michalkaluzinski.fantasyleague.dtos.UserLoginDTO;
import com.michalkaluzinski.fantasyleague.dtos.UserRegistrationDTO;
import com.michalkaluzinski.fantasyleague.exceptions.RestApiException;
import com.michalkaluzinski.fantasyleague.services.UserService;
import io.swagger.annotations.ApiImplicitParam;

@Controller
@RequestMapping("/users")
public class UserController {

  @Autowired private UserService userService;

  @PostMapping
  public ResponseEntity<String> register(@RequestBody UserRegistrationDTO userRegistrationDTO)
      throws RestApiException {
    userService.register(userRegistrationDTO);
    return new ResponseEntity<>("success", HttpStatus.OK);
  }

  @GetMapping("/confirmRegistration")
  public ResponseEntity<String> confirmRegistration(@RequestParam String token) {
    userService.confirmRegistration(token);
    return new ResponseEntity<>("success", HttpStatus.OK);
  }

  @PostMapping("/login")
  public ResponseEntity<UserLoggedDTO> login(@RequestBody UserLoginDTO userLoginDTO) {
    return new ResponseEntity<>(userService.login(userLoginDTO), HttpStatus.OK);
  }

  @ApiImplicitParam(
      name = "Authorization",
      value = "Access Token",
      required = true,
      allowEmptyValue = false,
      paramType = "header",
      dataTypeClass = String.class,
      example = "Bearer access_token")
  @GetMapping("/{id}")
  public ResponseEntity<UserDTO> get(@PathVariable(value = "id") Integer userId) {
    return new ResponseEntity<>(userService.findById(userId), HttpStatus.OK);
  }
}
