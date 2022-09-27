package com.dag.homerentwebservice.model.dto.account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class LandlordAccountDto {
    private String accountNumber;
    private String bankName;
    private String walletNumber;
}
