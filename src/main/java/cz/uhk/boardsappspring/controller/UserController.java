package cz.uhk.boardsappspring.controller;

import cz.uhk.boardsappspring.dto.model.ErrorDTO;
import cz.uhk.boardsappspring.dto.user.ChangePasswordUserDTO;
import cz.uhk.boardsappspring.dto.user.LoginUserDTO;
import cz.uhk.boardsappspring.dto.user.UsernameDTO;
import cz.uhk.boardsappspring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/user/")
public class UserController {

    @Autowired
    public UserService userService;

    @PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity registerUser(@RequestBody LoginUserDTO loginUserDTO) {
        try {
            userService.registerUser(loginUserDTO);
            return ResponseEntity.ok("Created user with username: " + loginUserDTO.getUsername() + "!");
        }
        catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(new ErrorDTO(e.getMessage()));
        }
    }

    @PostMapping(value = "/change-password", produces = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity changePassword(@RequestBody ChangePasswordUserDTO changePasswordUserDTO) {
        try {
        userService.changePassword(changePasswordUserDTO);
        return ResponseEntity.ok("Password for user: " + userService.getCurrentUsername() + " successfully changed!");
        }
        catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(new ErrorDTO(e.getMessage()));
        }
    }

    @PostMapping(value = "/disable-user", produces = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity disableUser(@RequestBody UsernameDTO usernameDTO) {
        try {
        userService.disableUser(usernameDTO.getUsername());
        return ResponseEntity.ok("User: " + usernameDTO.getUsername() + " was blocked!");
        }
        catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(new ErrorDTO(e.getMessage()));
        }
    }

    @GetMapping(value = "/current-user", produces = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity getCurrentUser() {
        try {
            return ResponseEntity.ok(userService.getCurrentUser());
        }
        catch (IllegalStateException e) {
            return ResponseEntity.internalServerError().body(new ErrorDTO(e.getMessage()));
        }
    }

}
