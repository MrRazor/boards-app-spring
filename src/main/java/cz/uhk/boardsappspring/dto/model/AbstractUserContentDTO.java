package cz.uhk.boardsappspring.dto.model;

import cz.uhk.boardsappspring.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class AbstractUserContentDTO implements UserContentDTO, Serializable {

    private Long id;
    private UserDTO author;
    private String content;
    private boolean removed;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
