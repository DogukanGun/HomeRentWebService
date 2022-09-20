package com.dag.homerentwebservice.service.home;

import com.dag.homerentwebservice.model.dto.home.HomeDto;
import com.dag.homerentwebservice.model.dto.home.UserHomeRelationDto;
import com.dag.homerentwebservice.model.entity.home.Home;
import com.dag.homerentwebservice.model.entity.home.UserHomeRelation;
import com.dag.homerentwebservice.model.entity.home.HomeFilterStatus;
import com.dag.homerentwebservice.model.enums.HomeStatus;
import com.dag.homerentwebservice.model.request.home.CreateHomeRequest;
import com.dag.homerentwebservice.model.request.home.CreateUserHomeRelation;
import com.dag.homerentwebservice.model.request.home.UpdateHomeStatusRequest;
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

    public HomeDto createHome(CreateHomeRequest createHomeRequest){
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
        return HOME_MAPPER.convertToHomeDto(home);
    }

    public UserHomeRelationDto changeHomeStatus(UpdateHomeStatusRequest updateHomeStatusRequest){
        List<HomeStatus> newHomeStatus = Arrays.stream(HomeStatus.values()).filter(homeStatus ->
                homeStatus.key == updateHomeStatusRequest.getStatus()
        ).collect(Collectors.toList());
        if (newHomeStatus.size() != 1){
            // TODO: 20.09.2022 Throw error
            return null;
        }
        try {
            UserHomeRelation userHomeRelation = userHomeRelationRepository
                    .findByHomeIdAndUserId(updateHomeStatusRequest.getHomeId(),authenticationService.getCurrentCustomerId())
                    .orElseThrow(()->new UnexpectedException(""));
            userHomeRelation.setStatus(newHomeStatus.get(0).name());
            return USER_HOME_RELATION_MAPPER.convertToUserHomeRelation(userHomeRelationRepository.save(userHomeRelation));
        }catch (UnexpectedException unexpectedException){
            // TODO: 21.09.2022 Throw error
            return null;
        }
    }

    public List<HomeDto> getAllHomes(){
        return HOME_MAPPER.convertToHomeDtoList(homeRepository.findAll());
    }

    public List<HomeDto> filterHomes(int filterStatusId){
        List<HomeFilterStatus> homeFilterStatuses = Arrays.stream(HomeFilterStatus.values()).filter(filterIndex ->
                filterIndex.id == filterStatusId
        ).collect(Collectors.toList());
        if (homeFilterStatuses.size() != 1){
            // TODO: 20.09.2022 Throw exception
            return null;
        }
        List<HomeDto> allHomes = HOME_MAPPER.convertToHomeDtoList(homeRepository.findAll());
        HomeFilterHelper homeFilterHelper = HomeFilterHelper.getInstance();
        return homeFilterHelper.getFilteredHomes(
                homeFilterStatuses.get(0),
                allHomes
        );
    }

    public HomeDto getHome(int homeId){
        Home requestedHome;
        try {
            requestedHome = homeRepository.findById(homeId).orElseThrow(()->new UnexpectedException(""));
        }catch (UnexpectedException unexpectedException){
            return null;
        }
        try {
            UserHomeRelation userHomeRelation = userHomeRelationRepository
                    .findByHomeIdAndUserId(requestedHome.getId(),authenticationService.getCurrentCustomerId())
                    .orElseThrow(() -> new UnexpectedException(""));
            if (!userHomeRelation.getStatus().equals(HomeStatus.Sold.name())){
                return HOME_MAPPER.convertToHomeDto(requestedHome);
            }
        }catch (UnexpectedException unexpectedException){
            return null;
        }
        return null;
    }
}
