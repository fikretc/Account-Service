package account.controller;

import account.business.UserInfoService;
import account.entity.UserInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BusinessController {

    @Autowired
    UserInfoService userInfoService;
    private static final Logger logger = LogManager.getLogger(BusinessController.class);

    //GET api/empl/payment/
    @GetMapping (value = "/api/empl/payment", produces="application/json")
    public ResponseEntity paymentList (@AuthenticationPrincipal UserDetails details) {
        UserInfo user1 = userInfoService.loadUserByEmail(details.getUsername());
        logger.debug("GET /api/empl/payment1 " + user1.toDebugString());
        return ResponseEntity.status(HttpStatus.OK).body(user1.new UserView());
    }
}
