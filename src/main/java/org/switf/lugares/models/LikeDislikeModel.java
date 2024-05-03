package org.switf.lugares.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "like_dislike")
public class LikeDislikeModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idLikeDislike;
    private boolean liked;
    @ManyToOne(targetEntity = PlaceModel.class)
    private PlaceModel place;
    @ManyToOne(targetEntity = UserModel.class)
    private UserModel user;
}
