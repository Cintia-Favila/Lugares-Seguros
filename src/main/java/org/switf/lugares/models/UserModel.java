package org.switf.lugares.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = "username")})
public class UserModel implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idUser;
    @Column(nullable = false)
    private String username;
    private String email;
    private String password;
    @OneToMany(targetEntity = PlaceModel.class,fetch = FetchType.LAZY, mappedBy = "user")
    private List<PlaceModel> places;

    @OneToMany(targetEntity = CommentModel.class,fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "user")
    private List<CommentModel> comments;

    @OneToMany(targetEntity = LikeDislikeModel.class,fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "user")
    private List<LikeDislikeModel> likeDislike;



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
