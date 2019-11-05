package com.michalkaluzinski.fantasyleague.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.michalkaluzinski.fantasyleague.model.User;
import com.michalkaluzinski.fantasyleague.services.UserService;

@Controller
@RequestMapping("/users")
public class UserController {

  @Autowired private UserService userService;

  @GetMapping
  public ResponseEntity<List<User>> findAll() {
    return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<String> register(@RequestBody User user) throws Exception {
    userService.register(user);
    return new ResponseEntity<>("success", HttpStatus.OK);
  }

  @GetMapping("/confirmRegistration")
  public ResponseEntity<String> confirmRegistration(@RequestParam String token) {
    userService.confirmRegistration(token);
    return new ResponseEntity<>("success", HttpStatus.OK);
  }

  @PostMapping("/login")
  public ResponseEntity<String> login(@RequestBody User user) {
    return new ResponseEntity<>(userService.login(user), HttpStatus.OK);
  }
}
