package com.ideaco.ewallet.controller;

import com.ideaco.ewallet.dto.EditEmailDTO;
import com.ideaco.ewallet.dto.EditProfilePictureDTO;
import com.ideaco.ewallet.exception.UserNotFoundException;
import com.ideaco.ewallet.response.EditEmailResponse;
import com.ideaco.ewallet.response.EditProfileResponse;
import com.ideaco.ewallet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PatchMapping("/{user_id}/profile")
    public ResponseEntity<EditProfileResponse> editProfilePicture(@PathVariable("user_id") int userId,
                                                                  @RequestParam("user_picture")MultipartFile userPicture) {
        EditProfileResponse editProfileResponse = new EditProfileResponse();
        try {
            EditProfilePictureDTO editProfilePictureDTO = userService.editUserProfilePicture(userId, userPicture);

            // for the api response
            editProfileResponse.setSuccess(true);
            editProfileResponse.setMessage("Succesfully change profile picture");
            editProfileResponse.setErrorCode("");
            editProfileResponse.setData(editProfilePictureDTO);
            return ResponseEntity.ok().body(editProfileResponse);
        } catch (UserNotFoundException e) {
            editProfileResponse.setSuccess(false);
            editProfileResponse.setMessage("Failed to change profile picture");
            editProfileResponse.setErrorCode("400");
        } catch (IOException e) {
            editProfileResponse.setSuccess(false);
            editProfileResponse.setMessage("Failed to upload File");
            editProfileResponse.setErrorCode("401");
        }
        return ResponseEntity.badRequest().body(editProfileResponse);

    }

    @PatchMapping("/{user_id}/email")
    public ResponseEntity<EditEmailResponse> editEmail(@PathVariable("user_id") int userId,
                                                       @RequestParam("user_email") String userEmail){
        EditEmailResponse editEmailResponse = new EditEmailResponse();
        try {
            EditEmailDTO editEmailDTO = userService.editUserEmail(userId, userEmail);

            editEmailResponse.setSuccess(true);
            editEmailResponse.setMessage("Successfully changed user email");
            editEmailResponse.setErrorCode("");
            editEmailResponse.setData(editEmailDTO);

            return ResponseEntity.ok().body(editEmailResponse);
        } catch (UserNotFoundException e) {
            editEmailResponse.setSuccess(false);
            editEmailResponse.setMessage("Failed to changed user email");
            editEmailResponse.setErrorCode("400");

            return ResponseEntity.badRequest().body(editEmailResponse);
        }
    }
}
