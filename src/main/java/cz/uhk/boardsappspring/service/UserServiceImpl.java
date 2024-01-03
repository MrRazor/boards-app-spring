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
        try {
            loginUserDTO.setPassword(encodePassword(loginUserDTO.getPassword()));
            User user = userDTOMapper.loginUserDTOToUser(loginUserDTO);
            Authority authority = new Authority();
            authority.setAuthorityName(Role.USER.getDatabaseName());
            authority.setUsername(user.getUsername());
            authorityDAO.create(authority);
            userDAO.create(user);
        }
        catch (Exception e) {
            throw new IllegalStateException("Failed to create new user, maybe username is already taken");
        }
    }

    @Override
    @Transactional
    public void changePassword(ChangePasswordUserDTO changePasswordUserDTO) {
        try {
            User user = userDAO.findOne(getCurrentUsername());
            if (passwordMatches(changePasswordUserDTO.getOldPassword(), user.getPassword())) {
                user.setPassword(encodePassword(changePasswordUserDTO.getNewPassword()));
            } else {
                throw new IllegalStateException("Wrong current password");
            }
        }
        catch (Exception e) {
            throw new IllegalStateException("Failed to change password, check if old password match");
        }
    }

    @Override
    @Transactional
    public void disableUser(String username) {
        if(getCurrentRoles().contains(Role.ADMIN.getDatabaseName())) {
            try {
                User user = userDAO.findOne(username);
                if (user.isEnabled() && user.getAuthorities().stream().map(Authority::getAuthorityName).noneMatch(authorityName -> authorityName.equals(Role.ADMIN.getDatabaseName()))) {
                    user.setEnabled(false);
                }
            }
            catch (Exception e) {
                throw new IllegalStateException("Failed to find user");
            }
        }
        else {
            throw new IllegalStateException("You are not admin");
        }
    }

    @Override
    public UserDTO login(LoginUserDTO loginUserDTO) {
        User user = userDAO.findUserByUsernameAndPassword(loginUserDTO.getUsername(), loginUserDTO.getPassword());
        return userDTOMapper.userToUserDTO(user);
    }

    @Override
    public UserDTO getCurrentUser() {
        try {
            return userDTOMapper.userToUserDTO(userDAO.findOne(getCurrentUsername()));
        }
        catch (Exception e) {
            throw new IllegalStateException("Failed to find current user information");
        }
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