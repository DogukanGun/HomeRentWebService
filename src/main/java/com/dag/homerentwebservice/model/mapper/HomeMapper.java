package com.dag.homerentwebservice.model.mapper;

import com.dag.homerentwebservice.model.dto.account.LandlordAccountDto;
import com.dag.homerentwebservice.model.dto.home.HomeDto;
import com.dag.homerentwebservice.model.dto.home.HomeImageDto;
import com.dag.homerentwebservice.model.entity.account.LandlordAccount;
import com.dag.homerentwebservice.model.entity.home.Home;
import com.dag.homerentwebservice.model.entity.home.HomeImage;
import com.dag.homerentwebservice.model.entity.home.facility.Facility;
import com.dag.homerentwebservice.model.request.home.CreateHomeRequest;
import com.dag.homerentwebservice.model.request.home.facility.CreateFacilityRequest;
import com.dag.homerentwebservice.model.request.landlordaccount.CreateLandlordAccountRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HomeMapper {
    HomeMapper HOME_MAPPER = Mappers.getMapper(HomeMapper.class);

    //convert to dto
    HomeDto convertToHomeDto(Home home);
    List<HomeDto> convertToHomeDtoList(List<Home> homes);
    HomeImageDto convertToHomeImageDto(HomeImage homeImage);
    List<HomeImageDto> convertToHomeImageDtoList(List<HomeImage> homeImage);

    //create entity
    Home createHome(CreateHomeRequest createHomeRequest);
    Facility createFacility(CreateFacilityRequest createFacilityRequest);
}
