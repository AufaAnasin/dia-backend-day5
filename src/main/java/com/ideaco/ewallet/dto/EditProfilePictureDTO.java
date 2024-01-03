package com.ideaco.ewallet.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
public class EditProfilePictureDTO {
    private int userId;
    private String userPicture;

}
