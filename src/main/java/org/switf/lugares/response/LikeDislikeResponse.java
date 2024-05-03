package org.switf.lugares.response;
import lombok.Data;

@Data
public class LikeDislikeResponse {

    private Integer idLikeDislike;
    private String liked;
    private Long totalLikes;
    private Long totalDislikes;
    private Double average;
    private String place;
    private String user;
}
