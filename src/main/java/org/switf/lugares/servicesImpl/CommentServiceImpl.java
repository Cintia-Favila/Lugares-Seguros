package org.switf.lugares.servicesImpl;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.switf.lugares.models.CommentModel;
import org.switf.lugares.models.PlaceModel;
import org.switf.lugares.repositories.CommentJpaRepository;
import org.switf.lugares.repositories.PlaceJpaRepository;
import org.switf.lugares.request.CommentRequest;
import org.switf.lugares.response.CommentResponse;

import org.switf.lugares.services.CommentService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentJpaRepository commentRepository;
    @Autowired
    private PlaceJpaRepository placeRepository;

    @Override
    public CommentResponse createComment(Integer idPlace, CommentRequest commentRequest) {
        String commentReceived = commentRequest.getComment();
        PlaceModel placeModel = placeRepository.getReferenceById(idPlace);

        CommentModel commentModel = new CommentModel();
        commentModel.setIdComment(null);
        commentModel.setComment(commentReceived);
        commentModel.setPlace(placeModel);
        /*commentModel.setUser(null);*/

        CommentModel newCommentModel = commentRepository.save(commentModel);
        CommentResponse commentResponse = new CommentResponse();
        commentResponse.setIdComment(newCommentModel.getIdComment());
        commentResponse.setComment(newCommentModel.getComment());
        commentResponse.setPlace(newCommentModel.getPlace().getIdPlace().toString());
        /*commentResponse.setUser(newCommentModel.getUser().getIdUser().toString());*/

        return commentResponse;
    }

    @Override
    public Boolean deleteCommentByIdPlace(Integer idComment) {
        Optional<CommentModel> commentModel = commentRepository.findById(idComment);
        if (commentModel.isPresent()) {
            commentRepository.deleteById(idComment);
            return true;
        }
        return false;
    }

    @Override
    public List<CommentResponse> getAllCommentsByIdPlace(Integer idPlace) {

        List<CommentModel> commentModels = placeRepository.getReferenceById(idPlace).getComments();

        List<CommentResponse> results = new ArrayList<>();

        for (CommentModel commentModel : commentModels) {
            CommentResponse commentResponse = new CommentResponse();
            commentResponse.setIdComment(commentModel.getIdComment());
            commentResponse.setComment(commentModel.getComment());
            commentResponse.setPlace(commentModel.getPlace().getIdPlace().toString());
            //commentResponse.setUser(commentModel.getUser().toString());
            results.add(commentResponse);
        }
            return results;
    }

    @Override
    public boolean existsById(Integer idComment) {
        Optional<CommentModel> commentOptional = commentRepository.findById(idComment);
        return commentOptional.isPresent(); // Devuelve true si el comentario existe, false si no existe
    }
}
