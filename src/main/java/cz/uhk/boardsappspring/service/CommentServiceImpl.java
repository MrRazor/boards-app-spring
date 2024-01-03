package cz.uhk.boardsappspring.service;

import cz.uhk.boardsappspring.dto.comment.CommentDTO;
import cz.uhk.boardsappspring.dto.comment.NewCommentDTO;
import cz.uhk.boardsappspring.dto.mapper.CommentDTOMapper;
import cz.uhk.boardsappspring.persistence.dao.CommentDAO;
import cz.uhk.boardsappspring.persistence.dao.PostDAO;
import cz.uhk.boardsappspring.persistence.dao.UserDAO;
import cz.uhk.boardsappspring.persistence.entity.Comment;
import cz.uhk.boardsappspring.persistence.entity.Post;
import cz.uhk.boardsappspring.persistence.entity.User;
import cz.uhk.boardsappspring.security.Role;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDAO commentDAO;

    @Autowired
    private PostDAO postDAO;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private UserService userService;

    @Autowired
    private CommentDTOMapper commentDTOMapper;

    @Override
    @Transactional
    public void addNewComment(Long replyPostId, NewCommentDTO newCommentDTO) {
        Post post = postDAO.findOne(replyPostId);
        if(!post.isRemoved()) {
            Comment comment = commentDTOMapper.newCommentDTOToComment(newCommentDTO);
            comment.setPost(post);
            User userReference = userDAO.getReference(userService.getCurrentUsername());
            comment.setAuthor(userReference);
            commentDAO.create(comment);
        }
    }

    @Override
    @Transactional
    public void updateComment(Long commentId, NewCommentDTO newCommentDTO) {
        Comment comment = commentDAO.findOne(commentId);
        String username = comment.getAuthor().getUsername();
        Post post = comment.getPost();
        if(userService.getCurrentUsername().equals(username) && !post.isRemoved() && !comment.isRemoved()) {
            comment.setContent(newCommentDTO.getContent());
        }
    }

    @Override
    @Transactional
    public void removeComment(Long commentId) {
        Comment comment = commentDAO.findOne(commentId);
        String username = comment.getAuthor().getUsername();
        Post post = comment.getPost();
        if((userService.getCurrentUsername().equals(username) || userService.getCurrentRoles().contains(Role.ADMIN.name())) && !post.isRemoved() && !comment.isRemoved()) {
            comment.setRemoved(true);
        }
    }

    @Override
    public List<CommentDTO> findVisibleCommentsByPostId(Long postId) {
        return commentDAO.findVisibleCommentsByPostId(postId).stream().map(comment -> commentDTOMapper.commentToCommentDTO(comment)).toList();
    }

    @Override
    public List<CommentDTO> findVisibleCommentsByPostId(Long postId, int pageNumber, int pageSize) {
        return commentDAO.findVisibleCommentsByPostId(postId, pageNumber, pageSize).stream().map(comment -> commentDTOMapper.commentToCommentDTO(comment)).toList();
    }
}
