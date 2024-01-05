package cz.uhk.boardsappspring.service;

import cz.uhk.boardsappspring.dto.post.NewPostDTO;
import cz.uhk.boardsappspring.dto.post.PostDTO;

import java.util.List;

public interface PostService {
    Long addNewPost(NewPostDTO newPostDTO);
    void updatePost(Long postId, NewPostDTO newPostDTO);
    void removePost(Long postId);
    PostDTO findPost(Long id);
    List<PostDTO> findVisiblePosts();
    List<PostDTO> findVisiblePosts(int pageNumber, int pageSize);
}
