package com.ideaco.ewallet.controller;

import com.ideaco.ewallet.dto.ShowUserBalanceDTO;
import com.ideaco.ewallet.dto.TransferDTO;
import com.ideaco.ewallet.exception.BalanceNotAvailableException;
import com.ideaco.ewallet.exception.UserNotFoundException;
import com.ideaco.ewallet.model.BalanceModel;
import com.ideaco.ewallet.response.TransferResponse;
import com.ideaco.ewallet.response.UserBalanceResponse;
import com.ideaco.ewallet.service.BalanceService;
import com.ideaco.ewallet.service.TransactionService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private BalanceService balanceService;

    // check saldo
    @GetMapping("/{userId}/showBalance")
    public ResponseEntity<UserBalanceResponse> showUserBalance(@PathVariable int userId) {
        UserBalanceResponse userBalanceResponse = new UserBalanceResponse();
        try {
            ShowUserBalanceDTO showUserBalanceDTO = balanceService.showUserBalance(userId);
            userBalanceResponse.setSuccess(true);
            userBalanceResponse.setMessage("Data Fetched");
            userBalanceResponse.setErrorCode("201");
            userBalanceResponse.setData(showUserBalanceDTO);
            return ResponseEntity.ok().body(userBalanceResponse);
        } catch (BalanceNotAvailableException e) {
            userBalanceResponse.setSuccess(false);
            userBalanceResponse.setMessage("Balance not available/not enough balance");
            userBalanceResponse.setErrorCode("101"); // receiver account not found

            return ResponseEntity.badRequest().body(userBalanceResponse);
        }
    }

    @PostMapping("/transfer")
    public ResponseEntity<TransferResponse> transfer(@RequestParam("user_id") int userId,
                                                     @RequestParam("transaction_amount") int transactionAmount,
                                                     @RequestParam("transaction_receiver") int transactionReceiver,
                                                     @RequestParam("transaction_type") String transactionType){
        TransferResponse transferResponse = new TransferResponse();
        try {
            TransferDTO transferDTO = transactionService.transfer(userId, transactionAmount, transactionReceiver, transactionType);
            transferResponse.setSuccess(true);
            transferResponse.setMessage("Successfully transfer balance");
            transferResponse.setErrorCode("");
            transferResponse.setData(transferDTO);

            return ResponseEntity.ok().body(transferResponse);
        } catch (UserNotFoundException e) {
            transferResponse.setSuccess(false);
            transferResponse.setMessage("Failed transfer balance");
            transferResponse.setErrorCode("100"); // receiver account not found

            return ResponseEntity.badRequest().body(transferResponse);
        } catch (BalanceNotAvailableException e) {
            transferResponse.setSuccess(false);
            transferResponse.setMessage("Balance not available/not enough balance");
            transferResponse.setErrorCode("101"); // receiver account not found

            return ResponseEntity.badRequest().body(transferResponse);
        }

    }
}
