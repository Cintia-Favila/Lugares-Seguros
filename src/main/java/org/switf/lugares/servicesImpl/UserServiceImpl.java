package org.switf.lugares.servicesImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.switf.lugares.models.UserModel;
import org.switf.lugares.repositories.UserJpaRepository;
import org.switf.lugares.request.UserRequest;
import org.switf.lugares.response.UserResponse;
import org.switf.lugares.services.UserService;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    private UserJpaRepository userRepository;

    @Override
    public boolean existsById(Integer idUser) {
        return true;
    }

    @Override
    public UserResponse updateUserByID(Integer idUser, UserRequest userRequest) {
        userRepository.findById(idUser).map(userFound -> {
            userFound.setIdUser(idUser);
            userFound.setUsername(userRequest.getUsername());
            userFound.setEmail(userFound.getEmail());
            userFound.setPassword(userFound.getPassword());
            userFound.setComments(userFound.getComments());
            userFound.setPlaces(userFound.getPlaces());
            userFound.setLikeDislike(userFound.getLikeDislike());
            return userRepository.save(userFound);
        });
        UserModel userModel = userRepository.getReferenceById(idUser);
        UserResponse userResponse = new UserResponse();
        userResponse.setIdUser(userModel.getIdUser());
        userResponse.setUsername(userModel.getUsername());
        return userResponse;
    }

    @Override
    public void updatePasswordByID(Integer idUser, UserRequest userRequest) {
        userRepository.findById(idUser).map(userFound -> {
            userFound.setIdUser(idUser);
            userFound.setPassword(passwordEncoder.encode(userRequest.getPassword()));
            userFound.setUsername(userFound.getUsername());
            userFound.setEmail(userFound.getEmail());
            userFound.setComments(userFound.getComments());
            userFound.setPlaces(userFound.getPlaces());
            userFound.setLikeDislike(userFound.getLikeDislike());
            return userRepository.save(userFound);
        });
    }
}



