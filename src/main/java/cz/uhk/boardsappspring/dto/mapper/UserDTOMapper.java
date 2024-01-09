package cz.uhk.boardsappspring.dto.mapper;

import cz.uhk.boardsappspring.dto.user.InformationUserDTO;
import cz.uhk.boardsappspring.dto.user.LoginUserDTO;
import cz.uhk.boardsappspring.persistence.entity.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserDTOMapper {
    InformationUserDTO userToUserDTO(User user);
    User userDTOToUser(InformationUserDTO informationUserDTO);
    LoginUserDTO userToLoginUserDTO(User user);
    User loginUserDTOToUser(LoginUserDTO loginUserDTO);
}