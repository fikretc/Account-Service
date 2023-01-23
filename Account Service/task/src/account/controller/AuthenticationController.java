package account.controller;

import account.business.UserInfoService;
import account.entity.UserInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Controller
public class AuthenticationController {
    @Autowired
    UserInfoService userInfoService;
    private static final Logger logger = LogManager.getLogger(AuthenticationController.class);

    //POST api/auth/signup
    @PostMapping (value = "/api/auth/signup", produces="application/json")
    public ResponseEntity signupEndpoint ( @Valid @RequestBody UserInfo userInfo) {
        userInfo.setEmail(userInfo.getEmail().toLowerCase());
        UserInfo user1 = userInfoService.loadUserByEmail(userInfo.getEmail());
        if(user1 != null) {
            logger.debug("POST /api/auth/signup user1 User exist!" + " " + user1.getUsername());
            return ResponseEntity.status(400).body("{\n" +
                    "    \"timestamp\": \"data\",\n" +
                    "    \"status\": 400,\n" +
                    "    \"error\": \"Bad Request\",\n" +
                    "    \"message\": \"User exist!\",\n" +
                    "    \"path\": \"/api/auth/signup\"\n" +
                    "}");
        }
        UserInfo savedUser = userInfoService.saveFirstTime(userInfo);
        logger.debug("POST /api/auth/signup user2 " + userInfo.toDebugString());
        return ResponseEntity.status(HttpStatus.OK).body(savedUser.new UserView());
    }



}
