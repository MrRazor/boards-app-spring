package cz.uhk.boardsappspring.service;

import cz.uhk.boardsappspring.dto.mapper.PostDTOMapper;
import cz.uhk.boardsappspring.dto.post.NewPostDTO;
import cz.uhk.boardsappspring.dto.post.PostDTO;
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
    public Long addNewPost(NewPostDTO newPostDTO) {
        try {
            Post post = postDTOMapper.newPostDTOToPost(newPostDTO);
            post.setAuthor(userDAO.getReference(userService.getCurrentUsername()));
            return postDAO.createAndReturnId(post);
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new IllegalStateException("Failed to create post");
        }
    }

    @Override
    @Transactional
    public void updatePost(Long postId, NewPostDTO newPostDTO) {
        try {
            Post post = postDAO.findOne(postId);
            String username = post.getAuthor().getUsername();
            if (userService.getCurrentUsername().equals(username) && !post.isRemoved()) {
                post.setTitle(newPostDTO.getTitle());
                post.setContent(newPostDTO.getContent());
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new IllegalStateException("Failed to update post");
        }
    }

    @Override
    @Transactional
    public void removePost(Long postId) {
        try {
            Post post = postDAO.findOne(postId);
            String username = post.getAuthor().getUsername();
            if ((userService.getCurrentUsername().equals(username) || userService.getCurrentRoles().contains(Role.ADMIN.getDatabaseName())) && !post.isRemoved()) {
                post.setRemoved(true);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new IllegalStateException("Failed to remove post");
        }
    }

    @Override
    public PostDTO findPost(Long id) {
        try {
            PostDTO postDTO =  postDTOMapper.postToPostDTO(postDAO.findOne(id));
            if (postDTO != null) {
                return postDTO;
            }
            else {
                throw new IllegalStateException("Post doesn't exist");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new IllegalStateException("Failed to find this post");
        }
    }

    @Override
    public List<PostDTO> findVisiblePosts() {
        try {
            return postDAO.findVisiblePosts().stream().map(post -> postDTOMapper.postToPostDTO(post)).toList();
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new IllegalStateException("Failed to find visible posts");
        }
    }

    @Override
    public List<PostDTO> findVisiblePosts(int pageNumber, int pageSize) {
        try {
            return postDAO.findVisiblePosts(pageNumber, pageSize).stream().map(post -> postDTOMapper.postToPostDTO(post)).toList();
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new IllegalStateException("Failed to find visible posts");
        }
    }
}
