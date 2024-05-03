package org.switf.lugares.response;
import lombok.Data;


@Data
public class CommentResponse {
    private Integer idComment;
    private String comment;
    private String place;
    private String user;
}
