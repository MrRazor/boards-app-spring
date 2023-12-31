package cz.uhk.boardsappspring.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class UserDTO implements Serializable {

    private String username;
    private boolean enabled;
    private List<AuthorityDTO> authorities;
}
