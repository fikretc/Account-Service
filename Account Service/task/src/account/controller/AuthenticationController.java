package account.controller;

import account.business.UserInfoService;
import account.entity.PasswordParams;
import account.entity.UpdateInfo;
import account.entity.UserInfo;
import account.security.IAuthenticationFacade;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Controller
public class AuthenticationController implements PasswordParams {
    @Autowired
    UserInfoService userInfoService;

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    private IAuthenticationFacade authenticationFacade;
    private static final Logger logger = LogManager.getLogger(AuthenticationController.class);

    public String currentUserName() {
        final Authentication authentication = authenticationFacade.getAuthentication();
        return authentication.getName();
    }

    //POST api/auth/signup
    @PostMapping (value = "/api/auth/signup", produces = "application/json")
    public ResponseEntity signupEndpoint ( @Valid @RequestBody UserInfo userInfo) {
        logger.debug("POST /api/auth/signup user0 !" + " " + userInfo.getUsername());
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
        String message = checkPasswordFormat(userInfo.getPassword());
        if (message != null) {
            logger.debug("POST api/auth/signup user2 " + userInfo.getPassword() + message);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\n" +
                    "    \"timestamp\": \"data\",\n" +
                    "    \"status\": 400,\n" +
                    "    \"error\": \"Bad Request\",\n" +
                    "    \"message\": \""+ message + "\",\n" +
                    "    \"path\": \"/api/auth/signup\"\n" +
                    "}");
        }
        UserInfo savedUser = userInfoService.saveFirstTime(userInfo);
        logger.debug("POST /api/auth/signup user3 " + userInfo.toDebugString());
        return ResponseEntity.status(HttpStatus.OK).body(savedUser.new UserView());
    }

    //POST api/auth/changepass
    @PostMapping (value = "/api/auth/changepass", produces = "application/json")
    public ResponseEntity changePassword ( @RequestBody UpdateInfo updateInfo) {
        logger.debug("POST api/auth/changepass0 " + updateInfo.getNew_password());
        String message = checkPasswordSecurityRequirements(updateInfo.getNew_password());
        if (message != null) {
            logger.debug("POST api/auth/changepass1 " + updateInfo.getNew_password() + " " + message);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\n" +
                    "    \"timestamp\": \"data\",\n" +
                    "    \"status\": 400,\n" +
                    "    \"error\": \"Bad Request\",\n" +
                    "    \"message\": \""+ message + "\",\n" +
                    "    \"path\": \"/api/auth/changepass\"\n" +
                    "}");
        }
        logger.debug("POST api/auth/changepass2 " + updateInfo.getNew_password() + " updated for user" + currentUserName());
        UserInfo currentUser = userInfoService.loadUserByEmail(currentUserName());
        currentUser.setPassword(updateInfo.getNew_password());
        userInfoService.saveFirstTime(currentUser);
        return ResponseEntity.status(HttpStatus.OK).body("{\n" +
                "    \"email\": \"" + currentUserName() + "\",\n" +
                "    \"status\": \""+ messagePasswordChanged + "\"\n" +
                "}");

    }

    private String checkPasswordSecurityRequirements(String newPassword) {
        String passwordFormat = checkPasswordFormat(newPassword);
        if (passwordFormat != null) return passwordFormat;
        UserInfo currentUser = userInfoService.loadUserByEmail(currentUserName());
        if ( currentUser != null && passwordEncoder.matches(newPassword, currentUser.getPassword())) {
            return messageSamePassword;
        }
        return null;

    }

    public static String checkPasswordFormat(String newPassword) {
        if (newPassword == null || newPassword.length() < minPasswordLength) {
            return messageMinPasswordLength;
        }
        if (breachedPasswords.contains(newPassword)) {
            return messageBreachedPassword;
        }
        return null;
    }

}
