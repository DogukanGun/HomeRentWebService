package com.dag.homerentwebservice.controller;

import com.dag.homerentwebservice.model.dto.home.HomeDto;
import com.dag.homerentwebservice.model.request.home.CreateHomeRequest;
import com.dag.homerentwebservice.service.HomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
@RequiredArgsConstructor
public class HomeController {

    private final HomeService homeService;

    @PostMapping("/create")
    public HomeDto createHome(@RequestBody CreateHomeRequest createHomeRequest){
        return homeService.createHome(createHomeRequest);
    }
}
