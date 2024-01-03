package com.ideaco.ewallet.response;

import com.ideaco.ewallet.dto.EditProfilePictureDTO;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EditProfileResponse extends BaseResponse{
    private EditProfilePictureDTO data;
}
