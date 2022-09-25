package com.dag.homerentwebservice.controller;

import com.dag.homerentwebservice.model.dto.pagecontent.FormContentDto;
import com.dag.homerentwebservice.model.request.formcontent.CreateFormContentRequest;
import com.dag.homerentwebservice.model.response.BaseResponse;
import com.dag.homerentwebservice.service.formcontent.FormContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/formcontent")
@RequiredArgsConstructor
public class FormContentController {
    private final FormContentService formContentService;

    @PostMapping("/create")
    public BaseResponse<FormContentDto> createFormContentDto(@RequestBody CreateFormContentRequest createFormContentRequest){
        return formContentService.createFormContent(createFormContentRequest);
    }

}
