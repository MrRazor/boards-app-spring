package cz.uhk.boardsappspring.dto;

import cz.uhk.boardsappspring.persistence.entity.Comment;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class PostWithCommentsDTO extends PostDTO {

    private List<Comment> comments;
}
