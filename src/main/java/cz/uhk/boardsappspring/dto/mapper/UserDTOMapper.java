package cz.uhk.boardsappspring.dto.mapper;

import cz.uhk.boardsappspring.dto.LoginUserDTO;
import cz.uhk.boardsappspring.dto.UserDTO;
import cz.uhk.boardsappspring.persistence.entity.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserDTOMapper {
    UserDTO userToUserDTO(User user);
    User userDTOToUser(UserDTO userDTO);
    LoginUserDTO userToLoginUserDTO(User user);
    User loginUserDTOToUser(LoginUserDTO loginUserDTO);
}
