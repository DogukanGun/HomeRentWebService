package com.dag.homerentwebservice.controller;

import com.dag.homerentwebservice.model.dto.pagecontent.FormContentDto;
import com.dag.homerentwebservice.model.request.formcontent.CreateFormContentRequest;
import com.dag.homerentwebservice.model.response.BaseResponse;
import com.dag.homerentwebservice.service.formcontent.FormContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/formcontent")
@RequiredArgsConstructor
public class FormContentController {
    private final FormContentService formContentService;

    @PostMapping("/create")
    public BaseResponse<FormContentDto> createFormContentDto(@RequestBody CreateFormContentRequest createFormContentRequest){
        return formContentService.createFormContent(createFormContentRequest);
    }

    @GetMapping("/name/{name}")
    public BaseResponse<FormContentDto> getFormContentByName(@PathVariable("name") String name){
        return formContentService.getFormContent(name);
    }
}
