package web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.SignUpService;

@RestController
@RequestMapping("/api")
public class UserController {
    private SignUpService signupService;

    public UserController(SignUpService signupService) {
        this.signupService = signupService;
    }

}
