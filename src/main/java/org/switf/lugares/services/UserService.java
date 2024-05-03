package org.switf.lugares.services;

import org.switf.lugares.request.UserRequest;
import org.switf.lugares.response.UserResponse;

public interface UserService {
    boolean existsById(Integer idUser);

    UserResponse updateUserByID(Integer idUser, UserRequest userRequest);

    void updatePasswordByID(Integer idUser, UserRequest userRequest);
}
