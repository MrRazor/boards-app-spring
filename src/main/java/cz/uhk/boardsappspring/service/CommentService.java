package cz.uhk.boardsappspring.service;

import cz.uhk.boardsappspring.dto.comment.CommentDTO;
import cz.uhk.boardsappspring.dto.comment.NewCommentDTO;

import java.util.List;

public interface CommentService {
    void addNewComment(Long replyPostId, NewCommentDTO newCommentDTO);
    void updateComment(Long commentId, NewCommentDTO newCommentDTO);
    void removeComment(Long commentId);
    List<CommentDTO> findVisibleCommentsByPostId(Long postId);
    List<CommentDTO> findVisibleCommentsByPostId(Long postId, int pageNumber, int pageSize);
}
