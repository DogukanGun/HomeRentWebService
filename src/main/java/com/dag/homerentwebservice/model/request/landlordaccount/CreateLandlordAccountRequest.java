package com.dag.homerentwebservice.model.request.landlordaccount;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CreateLandlordAccountRequest {
    private String accountNumber;
    private String bankName;
    private String walletNumber;
}
