package cz.uhk.boardsappspring.service;

import cz.uhk.boardsappspring.dto.mapper.PostDTOMapper;
import cz.uhk.boardsappspring.dto.post.NewPostDTO;
import cz.uhk.boardsappspring.dto.post.PostDTO;
import cz.uhk.boardsappspring.dto.post.PostWithCommentsDTO;
import cz.uhk.boardsappspring.persistence.dao.PostDAO;
import cz.uhk.boardsappspring.persistence.dao.UserDAO;
import cz.uhk.boardsappspring.persistence.entity.Post;
import cz.uhk.boardsappspring.security.Role;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostDAO postDAO;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private UserService userService;

    @Autowired
    private PostDTOMapper postDTOMapper;

    @Override
    @Transactional
    public void addNewPost(NewPostDTO newPostDTO) {
        Post post = postDTOMapper.newPostDTOToPost(newPostDTO);
        post.setAuthor(userDAO.getReference(userService.getCurrentUsername()));
        postDAO.create(post);
    }

    @Override
    @Transactional
    public void updatePost(Long postId, NewPostDTO newPostDTO) {
        Post post = postDAO.findOne(postId);
        String username = post.getAuthor().getUsername();
        if (userService.getCurrentUsername().equals(username) && !post.isRemoved()) {
            post.setTitle(newPostDTO.getTitle());
            post.setContent(newPostDTO.getContent());
        }
    }

    @Override
    @Transactional
    public void removePost(Long postId) {
        Post post = postDAO.findOne(postId);
        String username = post.getAuthor().getUsername();
        if ((userService.getCurrentUsername().equals(username) || userService.getCurrentRoles().contains(Role.ADMIN.getDatabaseName())) && !post.isRemoved()) {
            post.setRemoved(true);
        }
    }

    @Override
    public List<PostDTO> findVisiblePosts() {
        return postDAO.findVisiblePosts().stream().map(post -> postDTOMapper.postToPostDTO(post)).toList();
    }

    @Override
    public List<PostDTO> findVisiblePosts(int pageNumber, int pageSize) {
        return postDAO.findVisiblePosts(pageNumber, pageSize).stream().map(post -> postDTOMapper.postToPostDTO(post)).toList();
    }

    @Override
    public List<PostWithCommentsDTO> findVisiblePostsWithComments() {
        return postDAO.findVisiblePosts().stream().map(post -> postDTOMapper.postToPostWithCommentsDTO(post)).toList();
    }

    @Override
    public List<PostWithCommentsDTO> findVisiblePostsWithComments(int pageNumber, int pageSize) {
        return postDAO.findVisiblePosts(pageNumber, pageSize).stream().map(post -> postDTOMapper.postToPostWithCommentsDTO(post)).toList();
    }
}
