package org.switf.lugares.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.switf.lugares.request.LikeDislikeRequest;
import org.switf.lugares.response.LikeDislikeResponse;
import org.switf.lugares.services.LikeDislikeService;
import org.switf.lugares.services.PlaceService;

@CrossOrigin(origins = "http://localhost/", maxAge = 3600)
@RestController
@RequestMapping("/places")
public class LikeDislikeServiceController {

    @Autowired
    private LikeDislikeService likeDislikeService;

    @Autowired
    private PlaceService placeService;

    @PostMapping("/{idPlace}/like")
    public ResponseEntity<LikeDislikeResponse> addLike(@PathVariable Integer idPlace, @RequestBody LikeDislikeRequest likeDislikeRequest) {
        if (placeService.existsById(idPlace)) {
            LikeDislikeResponse likeDislikeResponse = likeDislikeService.addLike(idPlace, likeDislikeRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(likeDislikeResponse);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/{idPlace}/dislike")
    public ResponseEntity<LikeDislikeResponse> addDisLike(@PathVariable Integer idPlace, @RequestBody LikeDislikeRequest likeDislikeRequest) {
        if (placeService.existsById(idPlace)) {
            LikeDislikeResponse likeDislikeResponse = likeDislikeService.addLike(idPlace, likeDislikeRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(likeDislikeResponse);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/likes/remove")
    public ResponseEntity<String> removeLikeForPlace(@RequestParam("idPlace") Integer idPlace, @RequestParam("isLike") boolean liked, @RequestParam("idLike") Integer idLikeDislike){
        if (placeService.existsById(idPlace)) {
            boolean deleted = likeDislikeService.deleteLikeForPlace(idPlace, liked, idLikeDislike);
            if (deleted) {
                return ResponseEntity.ok("Like deleted successfully");
            } else {
                boolean likeExists = likeDislikeService.existsById(idLikeDislike);
                if (likeExists) {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete like");
                } else {
                    return ResponseEntity.ofNullable("Like not found");
                }
            }
        }
        return ResponseEntity.ofNullable("Place not found");
    }

    @DeleteMapping("/dislikes/remove")
    public ResponseEntity<String> removeDislikeForPlace(@RequestParam("idPlace") Integer idPlace, @RequestParam("isLike") boolean liked, @RequestParam("idLike") Integer idLikeDislike){
        if (placeService.existsById(idPlace)) {
            boolean deleted = likeDislikeService.deleteDislikeForPlace(idPlace, liked, idLikeDislike);
            if (deleted) {
                return ResponseEntity.ok("Dislike deleted successfully");
            } else {
                boolean dislikeExists = likeDislikeService.existsById(idLikeDislike);
                if (dislikeExists) {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete dislike");
                } else {
                    return ResponseEntity.ofNullable("dislike not found");
                }
            }
        }
        return ResponseEntity.ofNullable("Place not found");
    }

    @GetMapping("/{idPlace}/likes")
    public LikeDislikeResponse getTotalLikesForPlace(@PathVariable Integer idPlace){
        return likeDislikeService.getTotalLikesandDislikesForPlace(idPlace);
    }
}
