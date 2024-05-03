package org.switf.lugares.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "comments")
public class CommentModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idComment;
    private String comment;


    @ManyToOne(targetEntity = PlaceModel.class)
    private PlaceModel place;

    @ManyToOne(targetEntity = UserModel.class)
    private UserModel user;

}
