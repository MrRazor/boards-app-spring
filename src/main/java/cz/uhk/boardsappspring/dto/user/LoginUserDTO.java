package cz.uhk.boardsappspring.dto.user;

import lombok.Data;

@Data
public class LoginUserDTO {
    private String username;
    private String password;
}