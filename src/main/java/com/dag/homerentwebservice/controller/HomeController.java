package com.dag.homerentwebservice.controller;

import com.dag.homerentwebservice.model.dto.home.HomeDto;
import com.dag.homerentwebservice.model.dto.home.UserHomeRelationDto;
import com.dag.homerentwebservice.model.request.home.CreateHomeRequest;
import com.dag.homerentwebservice.model.request.home.UpdateHomeStatusRequest;
import com.dag.homerentwebservice.service.home.HomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/home")
@RequiredArgsConstructor
public class HomeController {

    private final HomeService homeService;

    @PostMapping("/create")
    public HomeDto createHome(@RequestBody CreateHomeRequest createHomeRequest){
        return homeService.createHome(createHomeRequest);
    }

    @PostMapping("/status/change")
    public UserHomeRelationDto createHomeStatus(@RequestBody UpdateHomeStatusRequest updateHomeStatusRequest){
        return homeService.changeHomeStatus(updateHomeStatusRequest);
    }

    @PostMapping("all")
    public List<HomeDto> getAllHomes(){
        return homeService.getAllHomes();
    }

    @PostMapping("{homeId}")
    public HomeDto getHome(@PathVariable int homeId){
        return homeService.getHome(homeId);
    }

    @PostMapping("filter/{filterStatusId}")
    public List<HomeDto> filterHomes(@PathVariable int filterStatusId){
        return homeService.filterHomes(filterStatusId);
    }
}
