package org.switf.lugares.servicesImpl;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.switf.lugares.models.LikeDislikeModel;
import org.switf.lugares.models.PlaceModel;
import org.switf.lugares.repositories.LikeDislikeJpaRepository;
import org.switf.lugares.repositories.PlaceJpaRepository;
import org.switf.lugares.request.LikeDislikeRequest;
import org.switf.lugares.response.LikeDislikeResponse;
import org.switf.lugares.services.LikeDislikeService;

import java.util.Optional;

@Service
public class LikeDislikeServiceImpl implements LikeDislikeService {

    @Autowired
    private LikeDislikeJpaRepository likeDislikeRepository;
    @Autowired
    private PlaceJpaRepository placeRepository;

    @Override
    public LikeDislikeResponse addLike(Integer idPlace, LikeDislikeRequest likeDislikeRequest) {

        PlaceModel placeModel = placeRepository.findById(idPlace)
                .orElseThrow(() -> new EntityNotFoundException("Place not found"));

        LikeDislikeModel likeDislikeModel = new LikeDislikeModel();
        likeDislikeModel.setIdLikeDislike(null);
        likeDislikeModel.setLiked(likeDislikeRequest.getLiked());
        likeDislikeModel.setPlace(placeModel);
        //likeDislikeModel.setUser(null);

        LikeDislikeModel savedLikeDislike = likeDislikeRepository.save(likeDislikeModel);
        LikeDislikeResponse likeDislikeResponse = new LikeDislikeResponse();

        likeDislikeResponse.setIdLikeDislike(savedLikeDislike.getIdLikeDislike());
        likeDislikeResponse.setLiked(savedLikeDislike.isLiked() ? "Like" : "Dislike");
        likeDislikeResponse.setTotalLikes(null);
        likeDislikeResponse.setTotalDislikes(null);
        likeDislikeResponse.setAverage(null);
        likeDislikeResponse.setPlace(savedLikeDislike.getPlace().getIdPlace().toString());
        likeDislikeResponse.setUser(null);

        return likeDislikeResponse;
    }


    @Override
    public LikeDislikeResponse addDisLike(Integer idPlace, LikeDislikeRequest likeDislikeRequest) {

        PlaceModel placeModel = placeRepository.findById(idPlace)
                .orElseThrow(() -> new EntityNotFoundException("Place not found"));

        LikeDislikeModel likeDislikeModel = new LikeDislikeModel();
        likeDislikeModel.setIdLikeDislike(null);
        likeDislikeModel.setLiked(likeDislikeRequest.getLiked());
        likeDislikeModel.setPlace(placeModel);
        //likeDislikeModel.setUser(null);

        LikeDislikeModel savedLikeDislike = likeDislikeRepository.save(likeDislikeModel);
        LikeDislikeResponse likeDislikeResponse = new LikeDislikeResponse();

        likeDislikeResponse.setIdLikeDislike(savedLikeDislike.getIdLikeDislike());
        likeDislikeResponse.setLiked(savedLikeDislike.isLiked() ? "Like" : "Dislike");
        likeDislikeResponse.setTotalLikes(null);
        likeDislikeResponse.setTotalDislikes(null);
        likeDislikeResponse.setAverage(null);
        likeDislikeResponse.setPlace(savedLikeDislike.getPlace().getIdPlace().toString());
        likeDislikeResponse.setUser(null);

        return likeDislikeResponse;
    }

    @Override
    public Boolean deleteLikeForPlace(Integer idPlace, boolean liked, Integer idLikeDislike) {
        Optional<LikeDislikeModel> likeDislikeModelOptional = likeDislikeRepository.findById(idLikeDislike);
        if (likeDislikeModelOptional.isPresent()) {
            PlaceModel placeModel = placeRepository.getReferenceById(idPlace);
            likeDislikeRepository.deleteDislikeByIdAndPlace(placeModel, liked, idLikeDislike);
            return true;
        }
        return false;
    }

    @Override
    public Boolean deleteDislikeForPlace(Integer idPlace, boolean liked, Integer idLikeDislike) {
        Optional<LikeDislikeModel> likeDislikeModelOptional = likeDislikeRepository.findById(idLikeDislike);
        if (likeDislikeModelOptional.isPresent()) {
            PlaceModel placeModel = placeRepository.getReferenceById(idPlace);
            likeDislikeRepository.deleteDislikeByIdAndPlace(placeModel, liked, idLikeDislike);
            return true;
        }
        return false;
    }

    @Override
    public LikeDislikeResponse getTotalLikesandDislikesForPlace(Integer idPlace) {

        PlaceModel placeModel = placeRepository.findById(idPlace)
                .orElseThrow(() -> new EntityNotFoundException("Place not found"));

        Long totalLikes = likeDislikeRepository.countLikesByPlaceAndIsLike(placeModel, true);
        Long totalDislikes = likeDislikeRepository.countDislikesByPlaceAndIsLike(placeModel, false);

        LikeDislikeResponse likeDislikeResponse = new LikeDislikeResponse();
        likeDislikeResponse.setTotalLikes(totalLikes);
        likeDislikeResponse.setTotalDislikes(totalDislikes);
        likeDislikeResponse.setPlace(placeModel.getIdPlace().toString());

        return likeDislikeResponse;
    }



    @Override
    public boolean existsById(Integer idLikeDislike) {
        Optional<LikeDislikeModel> likeDislikeModelOptional = likeDislikeRepository.findById(idLikeDislike);
        return likeDislikeModelOptional.isPresent(); // Devuelve true si el comentario existe, false si no existe
    }

}
