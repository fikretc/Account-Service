package account.controller;

import account.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Controller
public class AuthenticationController {

    //POST api/auth/signup
    @PostMapping (value = "/api/auth/signup", produces="application/json")
    public ResponseEntity signupEndpoint ( @Valid @RequestBody User user) {
        return ResponseEntity.status(HttpStatus.OK).body(user.new UserView());
    }



}
