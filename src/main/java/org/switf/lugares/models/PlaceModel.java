package org.switf.lugares.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "places")
public class PlaceModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idPlace;
    private String name;
    private String description;
    @Column(name = "address_state")
    private String addressState;
    @Column(name = "address_city")
    private String addressCity;
    @Column(name = "address_colonia")
    private String addressColonia;
    @Column(name = "address_street")
    private String addressStreet;
    @Column(name = "address_zipcode")
    private String addressZipcode;
    @Column(name = "image")
    private String url;

    @OneToMany(targetEntity = CommentModel.class,fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "place")
    private List<CommentModel> comments;

    @ManyToOne(targetEntity = UserModel.class)
    private UserModel user;

    @OneToMany(targetEntity = LikeDislikeModel.class, fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "place")
    private List<LikeDislikeModel> likeDislike;
}
