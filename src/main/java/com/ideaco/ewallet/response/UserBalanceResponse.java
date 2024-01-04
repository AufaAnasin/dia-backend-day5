package com.ideaco.ewallet.response;

import com.ideaco.ewallet.dto.ShowUserBalanceDTO;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserBalanceResponse extends BaseResponse {
    private ShowUserBalanceDTO data;
}
