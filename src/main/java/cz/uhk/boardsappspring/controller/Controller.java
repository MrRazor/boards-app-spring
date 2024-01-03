package cz.uhk.boardsappspring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @GetMapping("/private")
    public String protectedResource() {
        return "Only admin can access this.";
    }

    @GetMapping("/public")
    public String publicResource() {
        return "Anyone (even without account) can access this.";
    }

}