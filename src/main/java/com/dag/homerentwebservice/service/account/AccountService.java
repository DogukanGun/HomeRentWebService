package com.dag.homerentwebservice.service.account;

import com.dag.homerentwebservice.model.dto.account.LandlordAccountDto;
import com.dag.homerentwebservice.model.entity.account.LandlordAccount;
import com.dag.homerentwebservice.model.request.landlordaccount.CreateLandlordAccountRequest;
import com.dag.homerentwebservice.model.response.BaseResponse;
import com.dag.homerentwebservice.repository.LandlordAccountRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.dag.homerentwebservice.model.mapper.LandlordMapper.LANDLORD_MAPPER;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final LandlordAccountRepository landlordAccountRepository;

    public BaseResponse<LandlordAccountDto> saveLandlordAccount(CreateLandlordAccountRequest createLandlordAccountRequest){
        LandlordAccount landlordAccount = LANDLORD_MAPPER.createLandlordAccount(createLandlordAccountRequest);
        landlordAccount = landlordAccountRepository.save(landlordAccount);
        return BaseResponse.<LandlordAccountDto>builder()
                .error(false)
                .data(LANDLORD_MAPPER.convertToLandlordAccountDto(landlordAccount))
                .build();
    }
}
