package cz.uhk.boardsappspring.dto.user;

import lombok.Data;

@Data
public class ChangePasswordUserDTO {
    private String oldPassword;
    private String newPassword;
}
