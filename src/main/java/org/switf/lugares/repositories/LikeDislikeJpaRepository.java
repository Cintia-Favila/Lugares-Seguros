package org.switf.lugares.repositories;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.switf.lugares.models.LikeDislikeModel;
import org.switf.lugares.models.PlaceModel;

@Transactional
@Repository
public interface LikeDislikeJpaRepository extends JpaRepository<LikeDislikeModel, Integer> {

    @Query("SELECT COUNT(ld) FROM LikeDislikeModel ld WHERE ld.place = :placeModel AND ld.liked = :isLike")
    long countLikesByPlaceAndIsLike(PlaceModel placeModel, boolean isLike);

    @Query("SELECT COUNT(ld) FROM LikeDislikeModel ld WHERE ld.place = :placeModel AND ld.liked = :isLike")
    Long countDislikesByPlaceAndIsLike(PlaceModel placeModel, boolean isLike);

    @Modifying
    @Query("DELETE FROM LikeDislikeModel ld WHERE ld.place = :placeModel AND ld.liked = :liked AND ld.idLikeDislike = :idLikeDislike")
    void deleteLikeByIdAndPlace(PlaceModel placeModel, boolean liked, Integer idLikeDislike);

    @Modifying
    @Query("DELETE FROM LikeDislikeModel ld WHERE ld.place = :placeModel AND ld.liked = :liked AND ld.idLikeDislike = :idLikeDislike")
    void deleteDislikeByIdAndPlace(PlaceModel placeModel, boolean liked, Integer idLikeDislike);
}