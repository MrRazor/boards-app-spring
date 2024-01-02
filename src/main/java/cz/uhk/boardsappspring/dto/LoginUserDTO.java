package cz.uhk.boardsappspring.dto;

import lombok.Data;

@Data
public class LoginUserDTO {
    private String username;
    private String password;
}