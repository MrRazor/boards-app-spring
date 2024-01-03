package cz.uhk.boardsappspring.dto.model;

import cz.uhk.boardsappspring.dto.user.UserDTO;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
public abstract class AbstractUserContentDTO implements UserContentDTO, Serializable {

    private Long id;
    private UserDTO author;
    private String content;
    private boolean removed;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
