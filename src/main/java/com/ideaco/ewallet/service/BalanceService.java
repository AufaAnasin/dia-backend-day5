package com.ideaco.ewallet.service;

import com.ideaco.ewallet.dto.ShowUserBalanceDTO;
import com.ideaco.ewallet.exception.BalanceNotAvailableException;
import com.ideaco.ewallet.model.BalanceModel;
import com.ideaco.ewallet.repository.BalanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BalanceService {
    @Autowired
    private BalanceRepository balanceRepository;

    // check user balance service and DTO Convert
    public ShowUserBalanceDTO showUserBalance(int userId) throws BalanceNotAvailableException {
        Optional<BalanceModel> data = balanceRepository.findByUserId(userId);
        if(data.isPresent()) {
            return data.get();
        } else {
            throw new BalanceNotAvailableException("Balance is Zero");
        }
    }
}
