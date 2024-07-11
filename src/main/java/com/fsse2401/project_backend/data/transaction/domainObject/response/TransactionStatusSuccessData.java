package com.fsse2401.project_backend.data.transaction.domainObject.response;

import com.fsse2401.project_backend.constant.TransactionResult;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionStatusSuccessData {
    private TransactionResult result;

    public TransactionStatusSuccessData(TransactionResult result) {
        this.result = result;
    }

}
