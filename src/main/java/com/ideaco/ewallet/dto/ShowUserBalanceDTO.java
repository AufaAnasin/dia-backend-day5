package com.ideaco.ewallet.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
@Data
@Setter
@Getter

public class ShowUserBalanceDTO {
    private int userId;
    private String userName;
    private int balance;

}
