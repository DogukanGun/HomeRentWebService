package com.dag.homerentwebservice.controller;

import com.dag.homerentwebservice.model.dto.pagecontent.FormContentDto;
import com.dag.homerentwebservice.model.dto.pagecontent.TextFieldDto;
import com.dag.homerentwebservice.model.request.formcontent.CreateFormContentRequest;
import com.dag.homerentwebservice.model.request.formcontent.CreateTextFieldRequest;
import com.dag.homerentwebservice.model.request.formcontent.UpdateFormContentRequest;
import com.dag.homerentwebservice.model.request.formcontent.UpdateTextfieldRequest;
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
    public BaseResponse<FormContentDto> createFormContentDto(@RequestBody CreateFormContentRequest createFormContentRequest) {
        return formContentService.createFormContent(createFormContentRequest);
    }

    @GetMapping("/name/{name}")
    public BaseResponse<FormContentDto> getFormContentByName(@PathVariable("name") String name) {
        return formContentService.getFormContent(name);
    }

    @PostMapping("change/textField/type")
    public BaseResponse<TextFieldDto> changeTextFieldType(@RequestBody UpdateTextfieldRequest updateTextfieldRequest) {
        return formContentService.changeTextFieldType(updateTextfieldRequest);
    }

    @PostMapping("textField/change/hint")
    public BaseResponse<TextFieldDto> changeTextFieldHint(@RequestBody UpdateTextfieldRequest updateTextfieldRequest) {
        return formContentService.changeTextFieldHint(updateTextfieldRequest);
    }

    @PostMapping("form/change/title")
    public BaseResponse<FormContentDto> changeFormContentTitle(@RequestBody UpdateFormContentRequest updateFormContentRequest) {
        return formContentService.changeFormContentTitle(updateFormContentRequest);
    }

    @PostMapping("form/add/textField/{formId}")
    public BaseResponse<FormContentDto> addTextFieldToForm(@PathVariable("formId")int formId,@RequestBody CreateTextFieldRequest createTextFieldRequest) {
        return formContentService.addTextFieldToForm(formId,createTextFieldRequest);
    }
}
