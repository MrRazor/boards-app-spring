package cz.uhk.boardsappspring.service;

import cz.uhk.boardsappspring.dto.post.NewPostDTO;
import cz.uhk.boardsappspring.dto.post.PostDTO;
import cz.uhk.boardsappspring.dto.post.PostWithCommentsDTO;

import java.util.List;

public interface PostService {
    Long addNewPost(NewPostDTO newPostDTO);
    void updatePost(Long postId, NewPostDTO newPostDTO);
    void removePost(Long postId);
    PostDTO findPost(Long id);
    PostWithCommentsDTO findPostWithComments(Long id);
    List<PostDTO> findVisiblePosts();
    List<PostDTO> findVisiblePosts(int pageNumber, int pageSize);
    List<PostWithCommentsDTO> findVisiblePostsWithComments();
    List<PostWithCommentsDTO> findVisiblePostsWithComments(int pageNumber, int pageSize);
}
