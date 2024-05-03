package org.switf.lugares.services;


import org.switf.lugares.request.CommentRequest;
import org.switf.lugares.response.CommentResponse;

import java.util.List;


public interface CommentService {

    CommentResponse createComment(Integer idPlace, CommentRequest comment);


    Boolean deleteCommentByIdPlace(Integer idComment);

    List<CommentResponse> getAllCommentsByIdPlace(Integer idPlace);

    boolean existsById(Integer idComment);
}
