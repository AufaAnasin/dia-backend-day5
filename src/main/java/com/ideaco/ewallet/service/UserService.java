package com.ideaco.ewallet.service;

import com.ideaco.ewallet.dto.EditEmailDTO;
import com.ideaco.ewallet.dto.EditProfilePictureDTO;
import com.ideaco.ewallet.exception.UserNotFoundException;
import com.ideaco.ewallet.model.UserModel;
import com.ideaco.ewallet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FileService fileService;

    public EditEmailDTO editUserEmail(int userId, String newEmail) throws UserNotFoundException {
        Optional<UserModel> userModelOptional = userRepository.findById(userId);
        if(userModelOptional.isEmpty()){
            throw new UserNotFoundException("User not found");
        }

        UserModel userModel = userModelOptional.get();
        userModel.setUserEmail(newEmail);

        userRepository.save(userModel);

        return convertDTO(userModel);
    }

    public EditEmailDTO convertDTO(UserModel userModel){
        EditEmailDTO editEmailDTO = new EditEmailDTO();
        editEmailDTO.setUserId(userModel.getUserId());
        editEmailDTO.setUserEmail(userModel.getUserEmail());
        return editEmailDTO;
    }

    public EditProfilePictureDTO editUserProfilePicture(int userId, MultipartFile newUserPicture) throws UserNotFoundException {
        Optional<UserModel> data = userRepository.findById(userId);
        if (data.isEmpty()) {
            throw new UserNotFoundException("User not found");
        }

        UserModel userModel = data.get();
        String picture = fileService.saveFile(newUserPicture);
        userModel.setUserPicture(picture);
        userRepository.save(userModel);
        return convertEditProfilePictureDTO(userModel);
    }

    public EditProfilePictureDTO convertEditProfilePictureDTO(UserModel userModel) {
        EditProfilePictureDTO editProfilePictureDTO = new EditProfilePictureDTO();
        editProfilePictureDTO.setUserId(userModel.getUserId());
        editProfilePictureDTO.setUserPicture(userModel.getUserPicture());
       return editProfilePictureDTO;
    }
}
