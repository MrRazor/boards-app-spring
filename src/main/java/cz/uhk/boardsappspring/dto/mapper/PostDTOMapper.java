package cz.uhk.boardsappspring.dto.mapper;

import cz.uhk.boardsappspring.dto.post.NewPostDTO;
import cz.uhk.boardsappspring.dto.post.PostDTO;
import cz.uhk.boardsappspring.dto.post.PostWithCommentsDTO;
import cz.uhk.boardsappspring.persistence.entity.Post;
import org.mapstruct.Mapper;

@Mapper
public interface PostDTOMapper {
    PostDTO postToPostDTO(Post post);
    Post postDTOToPost(PostDTO postDTO);
    PostWithCommentsDTO postToPostWithCommentsDTO(Post post);
    Post postWithCommentsDTOToPost(PostWithCommentsDTO postWithCommentsDTO);
    NewPostDTO postToNewPostDTO(Post post);
    Post newPostDTOToPost(NewPostDTO newPostDTO);
}
