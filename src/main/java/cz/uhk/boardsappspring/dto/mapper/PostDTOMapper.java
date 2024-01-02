package cz.uhk.boardsappspring.dto.mapper;

import cz.uhk.boardsappspring.dto.PostDTO;
import cz.uhk.boardsappspring.dto.PostWithCommentsDTO;
import cz.uhk.boardsappspring.persistence.entity.Post;
import org.mapstruct.Mapper;

@Mapper
public interface PostDTOMapper {
    PostDTO postToPostDTO(Post post);
    Post postDTOToPost(PostDTO postDTO);
    PostWithCommentsDTO postToPostWithCommentsDTO(Post post);
    Post postWithCommentsDTOToPost(PostWithCommentsDTO postWithCommentsDTO);
}
