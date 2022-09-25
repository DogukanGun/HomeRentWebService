package com.dag.homerentwebservice.service.home;

import com.dag.homerentwebservice.base.DialogBoxDtoGenerator;
import com.dag.homerentwebservice.model.dto.dialogbox.DialogBoxDto;
import com.dag.homerentwebservice.model.dto.home.HomeDto;
import com.dag.homerentwebservice.model.dto.home.UserHomeRelationDto;
import com.dag.homerentwebservice.model.entity.home.Home;
import com.dag.homerentwebservice.model.entity.home.UserHomeRelation;
import com.dag.homerentwebservice.model.entity.home.HomeFilterStatus;
import com.dag.homerentwebservice.model.enums.HomeStatus;
import com.dag.homerentwebservice.model.request.home.CreateHomeRequest;
import com.dag.homerentwebservice.model.request.home.CreateUserHomeRelation;
import com.dag.homerentwebservice.model.request.home.UpdateHomeStatusRequest;
import com.dag.homerentwebservice.model.response.BaseResponse;
import com.dag.homerentwebservice.repository.HomeRepository;
import com.dag.homerentwebservice.repository.UserHomeRelationRepository;
import com.dag.homerentwebservice.security.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.rmi.UnexpectedException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.dag.homerentwebservice.model.mapper.HomeMapper.HOME_MAPPER;
import static com.dag.homerentwebservice.model.mapper.UserHomeRelationMapper.USER_HOME_RELATION_MAPPER;

@Service
@RequiredArgsConstructor
public class HomeService {

    private final HomeRepository homeRepository;
    private final AuthenticationService authenticationService;
    private final UserHomeRelationRepository userHomeRelationRepository;

    public BaseResponse<HomeDto> createHome(CreateHomeRequest createHomeRequest){
        try {
            Home home = HOME_MAPPER.createHome(createHomeRequest);
            home = homeRepository.save(home);
            CreateUserHomeRelation createUserHomeRelation = CreateUserHomeRelation
                    .builder()
                    .homeId(home.getId())
                    .userId(authenticationService.getCurrentCustomerId())
                    .status(HomeStatus.Selling.name())
                    .build();
            UserHomeRelation userHomeRelation = USER_HOME_RELATION_MAPPER.createUserHomeRelationMapper(createUserHomeRelation);
            userHomeRelationRepository.save(userHomeRelation);
            return BaseResponse.<HomeDto>builder()
                    .data(HOME_MAPPER.convertToHomeDto(home))
                    .error(false)
                    .build();
        }catch (Exception e){
            return DialogBoxDtoGenerator.getInstance().generateCommonErrorResponse();
        }
    }

    public BaseResponse<UserHomeRelationDto> changeHomeStatus(UpdateHomeStatusRequest updateHomeStatusRequest){
        try {
            UserHomeRelation userHomeRelation = userHomeRelationRepository
                    .findByHomeIdAndUserId(updateHomeStatusRequest.getHomeId(),authenticationService.getCurrentCustomerId())
                    .orElseThrow(()->new UnexpectedException(""));
            userHomeRelation.setStatus(updateHomeStatusRequest.getStatus().name());
            return BaseResponse.<UserHomeRelationDto>builder()
                    .data(
                            USER_HOME_RELATION_MAPPER
                                    .convertToUserHomeRelation(userHomeRelationRepository.save(userHomeRelation))
                    )
                    .error(false)
                    .build();
        }catch (UnexpectedException unexpectedException){
            return DialogBoxDtoGenerator.getInstance().generateCommonErrorResponse();
        }
    }

    public BaseResponse<List<HomeDto>> getAllHomes(){
        return  BaseResponse.<List<HomeDto>>builder()
                .data(HOME_MAPPER.convertToHomeDtoList(homeRepository.findAll()))
                .error(false)
                .build();

    }

    public BaseResponse<List<HomeDto>> filterHomes(int filterStatusId){
        List<HomeFilterStatus> homeFilterStatuses = Arrays.stream(HomeFilterStatus.values()).filter(filterIndex ->
                filterIndex.id == filterStatusId
        ).collect(Collectors.toList());
        if (homeFilterStatuses.size() != 1){
            return DialogBoxDtoGenerator.getInstance().generateCommonErrorResponse();
        }
        List<HomeDto> allHomes = HOME_MAPPER.convertToHomeDtoList(homeRepository.findAll());
        HomeFilterHelper homeFilterHelper = HomeFilterHelper.getInstance();
        return BaseResponse.<List<HomeDto>>builder()
                .data(homeFilterHelper.getFilteredHomes(
                        homeFilterStatuses.get(0),
                        allHomes
                ))
                .error(false)
                .build();
    }

    public BaseResponse<HomeDto> getHome(int homeId){
        Home requestedHome;
        try {
            requestedHome = homeRepository.findById(homeId).orElseThrow(()->new UnexpectedException(""));
            UserHomeRelation userHomeRelation = userHomeRelationRepository
                    .findByHomeIdAndUserId(requestedHome.getId(),authenticationService.getCurrentCustomerId())
                    .orElseThrow(() -> new UnexpectedException(""));
            if (!userHomeRelation.getStatus().equals(HomeStatus.Sold.name()))
                return BaseResponse.<HomeDto>builder()
                        .data(HOME_MAPPER.convertToHomeDto(requestedHome))
                        .error(false)
                        .build();
            else
                return DialogBoxDtoGenerator.getInstance().generateCommonErrorResponse();

        }catch (UnexpectedException unexpectedException){
            return DialogBoxDtoGenerator.getInstance().generateCommonErrorResponse();
        }
    }

}
