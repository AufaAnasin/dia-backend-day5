package com.ideaco.ewallet.model;

import com.ideaco.ewallet.dto.ShowUserBalanceDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "tab_balance")
public class BalanceModel extends ShowUserBalanceDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "balance_id")
    private int balanceId;
    @Column(name = "user_id")
    private int userId;
    @Column(name = "balance")
    private int balance;
}