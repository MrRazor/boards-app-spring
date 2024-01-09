package cz.uhk.boardsappspring.service;

import cz.uhk.boardsappspring.dto.user.ChangePasswordUserDTO;
import cz.uhk.boardsappspring.dto.user.InformationUserDTO;
import cz.uhk.boardsappspring.dto.user.LoginUserDTO;

import java.util.List;

public interface UserService {
    void registerUser(LoginUserDTO loginUserDTO);
    void changePassword(ChangePasswordUserDTO changePasswordUserDTO);
    void disableUser(String username);
    InformationUserDTO login(LoginUserDTO loginUserDTO);
    InformationUserDTO getCurrentUser();
    String getCurrentUsername();
    List<String> getCurrentRoles();
}