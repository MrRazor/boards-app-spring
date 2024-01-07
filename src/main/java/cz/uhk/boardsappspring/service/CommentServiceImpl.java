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
    public Long addNewComment(Long replyPostId, NewCommentDTO newCommentDTO) {
        try {
            Post post = postDAO.findOne(replyPostId);
            if (!post.isRemoved()) {
                Comment comment = commentDTOMapper.newCommentDTOToComment(newCommentDTO);
                comment.setPost(post);
                User userReference = userDAO.getReference(userService.getCurrentUsername());
                comment.setAuthor(userReference);
                return commentDAO.createAndReturnId(comment);
            }
            else {
                throw new IllegalStateException("You cannot add comment, because post with id " + replyPostId + " is already removed");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new IllegalStateException("Failed to create comment for this post - it is possible post is already deleted");
        }
    }

    @Override
    @Transactional
    public void updateComment(Long commentId, NewCommentDTO newCommentDTO) {
        try {
            Comment comment = commentDAO.findOne(commentId);
            String username = comment.getAuthor().getUsername();
            Post post = comment.getPost();
            if (userService.getCurrentUsername().equals(username) && !post.isRemoved() && !comment.isRemoved()) {
                comment.setContent(newCommentDTO.getContent());
            }
            else {
                throw new IllegalStateException("Post/comment is already deleted, or you are incorrect user");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new IllegalStateException("Failed to update comment - it is possible post/comment is already deleted, or you are incorrect user");
        }
    }

    @Override
    @Transactional
    public void removeComment(Long commentId) {
        try {
            Comment comment = commentDAO.findOne(commentId);
            String username = comment.getAuthor().getUsername();
            Post post = comment.getPost();
            if ((userService.getCurrentUsername().equals(username) || userService.getCurrentRoles().contains(Role.ADMIN.getDatabaseName())) && !post.isRemoved() && !comment.isRemoved()) {
                comment.setRemoved(true);
            }
            else {
                throw new IllegalStateException("Post/comment is already deleted, or you are not admin, or author is admin too");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new IllegalStateException("Failed to remove comment - it is possible post/comment is already deleted, or you are not admin, or author is admin too");
        }
    }

    @Override
    public CommentDTO findComment(Long id) {
        try {
            return commentDTOMapper.commentToCommentDTO(commentDAO.findOne(id));
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new IllegalStateException("Failed to find this comment");
        }
    }

    @Override
    public List<CommentDTO> findVisibleCommentsByPostId(Long postId) {
        try {
            if (postDAO.existsById(postId)) {
                return commentDAO.findVisibleCommentsByPostId(postId).stream().map(comment -> commentDTOMapper.commentToCommentDTO(comment)).toList();
            }
            else {
                throw new IllegalStateException("Post doesn't exist");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new IllegalStateException("Failed to find comments for this post, or post doesn't exist");
        }
    }

    @Override
    public List<CommentDTO> findVisibleCommentsByPostId(Long postId, int pageNumber, int pageSize) {
        try {
            if (postDAO.existsById(postId)) {
                return commentDAO.findVisibleCommentsByPostId(postId, pageNumber, pageSize).stream().map(comment -> commentDTOMapper.commentToCommentDTO(comment)).toList();
            }
            else {
                throw new IllegalStateException("Post doesn't exist");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new IllegalStateException("Failed to find comments for this post, or post doesn't exist");
        }
    }
}
