package com.michalkaluzinski.fantasyleague;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.michalkaluzinski.fantasyleague.model.User;
import com.michalkaluzinski.fantasyleague.services.UserService;

@Controller
@RequestMapping("/users")
public class UserController {

  @Autowired private UserService userService;

  @GetMapping
  public ResponseEntity<List<User>> findAll() {
    return new ResponseEntity<List<User>>(userService.findAll(), HttpStatus.OK);
  }
}
