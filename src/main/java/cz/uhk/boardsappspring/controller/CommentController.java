package cz.uhk.boardsappspring.controller;

import cz.uhk.boardsappspring.dto.comment.NewCommentDTO;
import cz.uhk.boardsappspring.dto.model.ErrorDTO;
import cz.uhk.boardsappspring.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments/")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/new/{id}")
    public ResponseEntity addNewComment(@PathVariable("id") Long replyPostId, @RequestBody NewCommentDTO newCommentDTO) {
        try {
            Long id = commentService.addNewComment(replyPostId, newCommentDTO);
            return ResponseEntity.ok("New comment with id " + id + " created");
        }
        catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(new ErrorDTO(e.getMessage()));
        }
    }

    @PostMapping("/update/{id}")
    public ResponseEntity updateComment(@PathVariable("id") Long commentId, @RequestBody NewCommentDTO newCommentDTO) {
        try {
            commentService.updateComment(commentId, newCommentDTO);
            return ResponseEntity.ok("Comment with id " + commentId + " successfully updated");
        }
        catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(new ErrorDTO(e.getMessage()));
        }
    }

    @PostMapping("/remove/{id}")
    public ResponseEntity removeComment(@PathVariable("id") Long commentId) {
        try {
            commentService.removeComment(commentId);
            return ResponseEntity.ok("Comment with id " + commentId + " successfully removed");
        }
        catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(new ErrorDTO(e.getMessage()));
        }
    }

    @GetMapping("/comment/{id}")
    public ResponseEntity findComment(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(commentService.findComment(id));
        }
        catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(new ErrorDTO(e.getMessage()));
        }
    }

    @GetMapping("/all/{id}")
    public ResponseEntity findVisibleCommentsByPostId(@PathVariable("id") Long postId) {
        try {
            return ResponseEntity.ok(commentService.findVisibleCommentsByPostId(postId));
        }
        catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(new ErrorDTO(e.getMessage()));
        }
    }

    @GetMapping("/all-paged/{id}")
    public ResponseEntity findVisibleCommentsByPostId(@PathVariable("id") Long postId, @RequestParam("page") int pageNumber, @RequestParam("size") int pageSize) {
        try {
            return ResponseEntity.ok(commentService.findVisibleCommentsByPostId(postId, pageNumber, pageSize));
        }
        catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(new ErrorDTO(e.getMessage()));
        }
    }
}
