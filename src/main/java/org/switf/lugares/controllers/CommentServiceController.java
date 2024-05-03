package org.switf.lugares.controllers;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.switf.lugares.request.CommentRequest;
import org.switf.lugares.response.CommentResponse;
import org.switf.lugares.services.CommentService;
import org.switf.lugares.services.PlaceService;
import java.util.List;

@CrossOrigin(origins = "http://localhost/", maxAge = 3600)
@RestController
@RequestMapping("/places")
public class CommentServiceController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private PlaceService placeService;

    @PostMapping("/{idPlace}/newComment")
    public ResponseEntity<CommentResponse> addComment(@PathVariable Integer idPlace, @RequestBody CommentRequest commentRequest) {
        if (placeService.existsById(idPlace)) {
            CommentResponse commentResponse = commentService.createComment(idPlace, commentRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(commentResponse);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{idPlace}/{idComment}")
    public ResponseEntity<String> deleteCommentController(@PathVariable Integer idPlace, @PathVariable Integer idComment) {
        if (placeService.existsById(idPlace)) {
            boolean deleted = commentService.deleteCommentByIdPlace(idComment);
            if (deleted) {
                return ResponseEntity.ok("Comment deleted successfully");
            } else {
                boolean commentExists = commentService.existsById(idComment);
                if (commentExists) {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete place");
                } else {
                    return ResponseEntity.ofNullable("Comment not found");
                }
            }
        }
        return ResponseEntity.ofNullable("Place not found");
    }

    @GetMapping("/{idPlace}/comments")
    public ResponseEntity<List<CommentResponse>> getAllCommentsController(@PathVariable Integer idPlace){
        if (placeService.existsById(idPlace)){
            List<CommentResponse> comments = commentService.getAllCommentsByIdPlace(idPlace);
            if (!comments.isEmpty()){
                return ResponseEntity.ok(comments);
            } else {
               throw new EntityNotFoundException("No comments found for this place");
            }
        }
        return ResponseEntity.notFound().build();
    }

}