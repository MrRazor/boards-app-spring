package cz.uhk.boardsappspring.dto.mapper;

import cz.uhk.boardsappspring.dto.CommentDTO;
import cz.uhk.boardsappspring.persistence.entity.Comment;
import org.mapstruct.Mapper;

@Mapper
public interface CommentDTOMapper {
    CommentDTO commentToCommentDTO(Comment comment);
    Comment commentDTOToComment(CommentDTO commentDTO);
}
