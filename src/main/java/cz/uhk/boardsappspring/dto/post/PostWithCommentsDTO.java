package cz.uhk.boardsappspring.dto.post;

import cz.uhk.boardsappspring.persistence.entity.Comment;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class PostWithCommentsDTO extends PostDTO {

    private List<Comment> comments;
}
