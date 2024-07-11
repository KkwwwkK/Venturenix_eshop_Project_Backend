package com.fsse2401.project_backend.api;

import com.fsse2401.project_backend.config.EnvConfig;
import com.fsse2401.project_backend.data.transaction.dto.response.TransactionResponseDto;
import com.fsse2401.project_backend.data.transaction.dto.response.TransactionStatusSuccessDto;
import com.fsse2401.project_backend.service.TransactionService;
import com.fsse2401.project_backend.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")
@CrossOrigin({
        EnvConfig.DEV_BASE_URL,
        EnvConfig.PROD_BASE_URL,
        EnvConfig.PROD_S3_BASE_URL
})
public class TransactionApi {
    private final TransactionService transactionService;

    @Autowired
    public TransactionApi(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PutMapping ("/prepare")
    public TransactionResponseDto postTransaction(JwtAuthenticationToken jwtToken){
        return new TransactionResponseDto(
                transactionService.putTransaction(JwtUtil.getFirebaseUserData(jwtToken))
        );

    }
    @GetMapping("/{tid}")
    public TransactionResponseDto getTransactionByTid(JwtAuthenticationToken jwtToken, @PathVariable Integer tid){
        return new TransactionResponseDto(
                transactionService.getTransactionByTid(JwtUtil.getFirebaseUserData(jwtToken), tid)
        );
    }

    @PatchMapping("/{tid}/pay")
    public TransactionStatusSuccessDto UpdateTrantsactionStatusByTid(JwtAuthenticationToken jwtToken, @PathVariable Integer tid){
        return new TransactionStatusSuccessDto(
                transactionService.UpdateTransactionStatusByTid(JwtUtil.getFirebaseUserData(jwtToken), tid)
        );
    }

    @PatchMapping("/{tid}/finish")
    public TransactionResponseDto UpdateTransactionFinishedByTid(JwtAuthenticationToken jwtToken, @PathVariable Integer tid){
        return new TransactionResponseDto(
                transactionService.UpdateTransactionFinishedByTid(JwtUtil.getFirebaseUserData(jwtToken), tid)
        );
    }
}
