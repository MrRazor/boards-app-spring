package cz.uhk.boardsappspring.dto;

import cz.uhk.boardsappspring.persistence.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class PostWithCommentsDTO extends PostDTO {

    private List<Comment> comments;
}
