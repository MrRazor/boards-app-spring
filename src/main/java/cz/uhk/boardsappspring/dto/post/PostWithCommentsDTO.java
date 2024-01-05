package cz.uhk.boardsappspring.dto.post;

import cz.uhk.boardsappspring.dto.comment.CommentDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class PostWithCommentsDTO extends PostDTO {

    private List<CommentDTO> comments;
}
