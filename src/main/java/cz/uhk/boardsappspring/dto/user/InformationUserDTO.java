package cz.uhk.boardsappspring.dto.user;

import cz.uhk.boardsappspring.dto.AuthorityDTO;
import lombok.Data;

import java.util.List;

@Data
public class InformationUserDTO {

    private String username;
    private boolean enabled;
    private List<AuthorityDTO> authorities;
}
