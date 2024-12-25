package ba.imad.sis.controllers;

import ba.imad.sis.services.user.DefaultUserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private final DefaultUserService userService;

    public UserController(DefaultUserService userService) {
        this.userService = userService;
    }

}
