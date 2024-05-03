package org.switf.lugares.controllers;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.switf.lugares.request.UserRequest;
import org.switf.lugares.response.UserResponse;
import org.switf.lugares.services.UserService;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserServiceController {

    @Autowired
    public UserService userService;

    @PostMapping(value = "demo")
    public String welcome (){
        return "Welcome from secure endpoint";
    }

    @PatchMapping("/updateUsername/{idUser}")
    public ResponseEntity<String> updateUsername(@PathVariable Integer idUser, @RequestBody UserRequest userRequest) {
        if (userService.existsById(idUser)) {
            UserResponse updateUser = userService.updateUserByID(idUser, userRequest);
            if (updateUser != null) {
                return ResponseEntity.ok("Username Updated: " + updateUser.getUsername());
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update username");
            }
        } else {
            throw new EntityNotFoundException("User not found");
        }
    }

    @PatchMapping("/updatePassword/{idUser}")
    public ResponseEntity<String> updatePassword(@PathVariable Integer idUser, @RequestBody UserRequest userRequest) {
        if (userService.existsById(idUser)) {
            userService.updatePasswordByID(idUser, userRequest);
                return ResponseEntity.ok("Password Updated ");

        } else {
            throw new EntityNotFoundException("User not found");
        }
    }
}
