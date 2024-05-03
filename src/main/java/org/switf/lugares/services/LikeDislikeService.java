package org.switf.lugares.services;

import org.switf.lugares.request.LikeDislikeRequest;
import org.switf.lugares.response.LikeDislikeResponse;

public interface LikeDislikeService {

    LikeDislikeResponse addLike(Integer idPlace, LikeDislikeRequest liked);

    LikeDislikeResponse addDisLike(Integer idPlace, LikeDislikeRequest likeDislikeRequest);


    Boolean deleteLikeForPlace(Integer idPlace, boolean liked, Integer idLikeDislike);

    Boolean deleteDislikeForPlace(Integer idPlace, boolean liked, Integer idLikeDislike);

    LikeDislikeResponse getTotalLikesandDislikesForPlace(Integer idPlace);

    boolean existsById(Integer idLikeDislike);
}
