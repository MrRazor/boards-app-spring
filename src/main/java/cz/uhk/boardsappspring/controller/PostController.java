package cz.uhk.boardsappspring.controller;

import cz.uhk.boardsappspring.dto.model.ErrorDTO;
import cz.uhk.boardsappspring.dto.post.NewPostDTO;
import cz.uhk.boardsappspring.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts/")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/new")
    public ResponseEntity addNewPost(@RequestBody NewPostDTO newPostDTO) {
        try {
            Long id = postService.addNewPost(newPostDTO);
            return ResponseEntity.ok("New post with id " + id + " created");
        }
        catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(new ErrorDTO(e.getMessage()));
        }
    }

    @PostMapping("/update/{id}")
    public ResponseEntity updatePost(@PathVariable("id") Long postId, @RequestBody NewPostDTO newPostDTO) {
        try {
            postService.updatePost(postId, newPostDTO);
            return ResponseEntity.ok("Post with id " + postId + " successfully updated");
        }
        catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(new ErrorDTO(e.getMessage()));
        }
    }

    @PostMapping("/remove/{id}")
    public ResponseEntity removePost(@PathVariable("id") Long postId) {
        try {
            postService.removePost(postId);
            return ResponseEntity.ok("Post with id " + postId + " successfully removed");
        }
        catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(new ErrorDTO(e.getMessage()));
        }
    }

    @GetMapping("/post/{id}")
    public ResponseEntity findPost(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(postService.findPost(id));
        }
        catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(new ErrorDTO(e.getMessage()));
        }
    }

    @GetMapping("/post-comments/{id}")
    public ResponseEntity findPostWithComments(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(postService.findPostWithComments(id));
        }
        catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(new ErrorDTO(e.getMessage()));
        }
    }

    @GetMapping("/all")
    public ResponseEntity findVisiblePosts() {
        try {
            return ResponseEntity.ok(postService.findVisiblePosts());
        }
        catch (IllegalStateException e) {
            return ResponseEntity.internalServerError().body(new ErrorDTO(e.getMessage()));
        }
    }
    @GetMapping("/all-paged")
    public ResponseEntity findVisiblePosts(@RequestParam("page") int pageNumber, @RequestParam("size") int pageSize) {
        try {
            return ResponseEntity.ok(postService.findVisiblePosts(pageNumber, pageSize));
        }
        catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(new ErrorDTO(e.getMessage()));
        }
    }

    @GetMapping("/all-comments")
    public ResponseEntity findVisiblePostsWithComments() {
        try {
            return ResponseEntity.ok(postService.findVisiblePostsWithComments());
        }
        catch (IllegalStateException e) {
            return ResponseEntity.internalServerError().body(new ErrorDTO(e.getMessage()));
        }
    }

    @GetMapping("/all-comments-paged")
    public ResponseEntity findVisiblePostsWithComments(@RequestParam("page") int pageNumber, @RequestParam("size") int pageSize) {
        try {
            return ResponseEntity.ok(postService.findVisiblePostsWithComments(pageNumber, pageSize));
        }
        catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(new ErrorDTO(e.getMessage()));
        }
    }
}
