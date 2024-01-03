package cz.uhk.boardsappspring.service;

import cz.uhk.boardsappspring.dto.mapper.UserDTOMapper;
import cz.uhk.boardsappspring.dto.user.ChangePasswordUserDTO;
import cz.uhk.boardsappspring.dto.user.LoginUserDTO;
import cz.uhk.boardsappspring.dto.user.UserDTO;
import cz.uhk.boardsappspring.persistence.dao.AuthorityDAO;
import cz.uhk.boardsappspring.persistence.dao.UserDAO;
import cz.uhk.boardsappspring.persistence.entity.Authority;
import cz.uhk.boardsappspring.persistence.entity.User;
import cz.uhk.boardsappspring.security.Role;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDTOMapper userDTOMapper;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private AuthorityDAO authorityDAO;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void registerUser(LoginUserDTO loginUserDTO) {
        User user = userDTOMapper.loginUserDTOToUser(loginUserDTO);
        Authority authority = new Authority();
        authority.setAuthorityName(Role.USER.name());
        authority.setUsername(user.getUsername());
        authorityDAO.create(authority);
        userDAO.create(user);
    }

    @Override
    @Transactional
    public void changePassword(ChangePasswordUserDTO changePasswordUserDTO) {
        User user = userDAO.findOne(getCurrentUsername());
        if(passwordMatches(changePasswordUserDTO.getOldPassword(), user.getPassword())) {
            user.setPassword(encodePassword(changePasswordUserDTO.getNewPassword()));
        }
        else {
            throw new IllegalStateException("Wrong current password!");
        }
    }

    @Override
    @Transactional
    public void disableUser(String username) {
        if(getCurrentRoles().contains(Role.ADMIN.name())) {
            User user = userDAO.findOne(username);
            if(user.getAuthorities().stream().map(Authority::getAuthorityName).noneMatch(authorityName -> authorityName.equals(Role.ADMIN.name()))) {
                user.setEnabled(false);
            }
        }
        else {
            throw new IllegalStateException("You are not admin!");
        }
    }

    @Override
    public UserDTO login(LoginUserDTO loginUserDTO) {
        User user = userDAO.findUserByUsernameAndPassword(loginUserDTO.getUsername(), loginUserDTO.getPassword());
        return userDTOMapper.userToUserDTO(user);
    }

    @Override
    public String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    @Override
    public List<String> getCurrentRoles() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
    }

    private boolean passwordMatches(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    private String encodePassword(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

}