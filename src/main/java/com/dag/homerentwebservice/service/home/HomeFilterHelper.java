package com.dag.homerentwebservice.service.home;


import com.dag.homerentwebservice.model.dto.home.HomeDto;
import com.dag.homerentwebservice.model.entity.home.HomeFilterStatus;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class HomeFilterHelper {
    static private HomeFilterHelper homeFilterHelper;

    static public HomeFilterHelper getInstance(){
        if (homeFilterHelper == null){
            homeFilterHelper = new HomeFilterHelper();
        }
        return homeFilterHelper;
    }

    List<HomeDto> getFilteredHomes(HomeFilterStatus homeFilterStatus,List<HomeDto> notFilteredList){
        if (homeFilterStatus == HomeFilterStatus.ASCENDING_BY_NAME){
            return notFilteredList.stream()
                    .sorted(Comparator.comparing(HomeDto::getHomeName))
                    .collect(Collectors.toList());
        }else if (homeFilterStatus == HomeFilterStatus.ASCENDING_BY_PRICE){
            return notFilteredList.stream()
                    .sorted(Comparator.comparing(HomeDto::getPrice))
                    .collect(Collectors.toList());
        }else if (homeFilterStatus == HomeFilterStatus.DESCENDING_BY_NAME){
            return notFilteredList.stream()
                    .sorted(Comparator.comparing(HomeDto::getHomeName).reversed())
                    .collect(Collectors.toList());
        }else if (homeFilterStatus == HomeFilterStatus.DESCENDING_BY_PRICE){
            return notFilteredList.stream()
                    .sorted(Comparator.comparing(HomeDto::getPrice).reversed())
                    .collect(Collectors.toList());
        }
        return null;
    }

}
