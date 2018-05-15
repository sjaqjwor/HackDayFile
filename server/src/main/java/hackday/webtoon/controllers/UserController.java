package hackday.webtoon.controllers;

import hackday.webtoon.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/users")
    public ResponseEntity<?> getUsersByRole() {
        return new ResponseEntity<>(userService.selectAll(), HttpStatus.OK);
    }
}
