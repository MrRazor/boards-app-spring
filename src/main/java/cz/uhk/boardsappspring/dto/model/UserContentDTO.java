package cz.uhk.boardsappspring.dto.model;

import cz.uhk.boardsappspring.dto.UserDTO;

import java.sql.Timestamp;

public interface UserContentDTO {
    Long getId();

    void setId(Long id);

    UserDTO getAuthor();

    void setAuthor(UserDTO author);

    String getContent();

    void setContent(String content);

    boolean isRemoved();

    void setRemoved(boolean removed);

    Timestamp getCreatedAt();

    void setCreatedAt(Timestamp createdAt);

    Timestamp getUpdatedAt();

    void setUpdatedAt(Timestamp updatedAt);
}
