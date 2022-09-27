package com.dag.homerentwebservice.model.mapper;

import com.dag.homerentwebservice.model.dto.account.LandlordAccountDto;
import com.dag.homerentwebservice.model.entity.account.LandlordAccount;
import com.dag.homerentwebservice.model.request.landlordaccount.CreateLandlordAccountRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface LandlordMapper {

    LandlordMapper LANDLORD_MAPPER = Mappers.getMapper(LandlordMapper.class);

    LandlordAccountDto convertToLandlordAccountDto(LandlordAccount landlordAccount);
    LandlordAccount createLandlordAccount(CreateLandlordAccountRequest createLandlordAccountRequest);

}
