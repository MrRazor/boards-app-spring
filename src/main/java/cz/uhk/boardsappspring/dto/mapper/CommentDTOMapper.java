package cz.uhk.boardsappspring.dto.mapper;

import cz.uhk.boardsappspring.dto.comment.CommentDTO;
import cz.uhk.boardsappspring.dto.comment.NewCommentDTO;
import cz.uhk.boardsappspring.persistence.entity.Comment;
import org.mapstruct.Mapper;

@Mapper
public interface CommentDTOMapper {
    CommentDTO commentToCommentDTO(Comment comment);
    Comment commentDTOToComment(CommentDTO commentDTO);
    NewCommentDTO commentToNewCommentDTO(Comment comment);
    Comment newCommentDTOToComment(NewCommentDTO newCommentDTO);
}
