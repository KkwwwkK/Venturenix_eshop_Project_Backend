package com.fsse2401.project_backend.data.transaction.dto.response;

import com.fsse2401.project_backend.constant.TransactionResult;
import com.fsse2401.project_backend.data.transaction.domainObject.response.TransactionStatusSuccessData;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionStatusSuccessDto {
    private TransactionResult result;

    public TransactionStatusSuccessDto(TransactionStatusSuccessData data) {
        this.result = data.getResult();
    }

}
