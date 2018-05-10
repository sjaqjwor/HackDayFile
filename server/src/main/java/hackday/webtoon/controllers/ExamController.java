package hackday.webtoon.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExamController {

    @GetMapping("")
    public ResponseEntity<?> exam(){
        return new ResponseEntity<>("hello", HttpStatus.OK);
    }
}
