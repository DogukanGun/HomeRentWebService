package com.dag.homerentwebservice.model.mapper;

import com.dag.homerentwebservice.model.dto.home.HomeDto;
import com.dag.homerentwebservice.model.entity.home.Home;
import com.dag.homerentwebservice.model.request.home.CreateHomeRequest;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HomeMapper {
    HomeMapper HOME_MAPPER = Mappers.getMapper(HomeMapper.class);

    //convert to dto
    HomeDto convertToHomeDto(Home home);

    //create entity
    Home createHome(CreateHomeRequest createHomeRequest);

    List<HomeDto> convertToHomeDtoList(List<Home> homes);
}
