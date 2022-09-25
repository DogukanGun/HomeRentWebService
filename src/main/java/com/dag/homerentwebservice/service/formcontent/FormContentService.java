package com.dag.homerentwebservice.service.formcontent;

import com.dag.homerentwebservice.base.DialogBoxButtonGenerator;
import com.dag.homerentwebservice.model.dto.dialogbox.DialogBoxColorType;
import com.dag.homerentwebservice.model.dto.dialogbox.DialogBoxDto;
import com.dag.homerentwebservice.model.dto.dialogbox.DialogBoxType;
import com.dag.homerentwebservice.model.dto.dialogbox.button.DialogBoxButton;
import com.dag.homerentwebservice.model.dto.pagecontent.FormContentDto;
import com.dag.homerentwebservice.model.entity.pagecontent.FormContent;
import com.dag.homerentwebservice.model.entity.pagecontent.TextField;
import com.dag.homerentwebservice.model.enums.FormContentPages;
import com.dag.homerentwebservice.model.enums.TextFieldType;
import com.dag.homerentwebservice.model.request.formcontent.CreateFormContentRequest;
import com.dag.homerentwebservice.model.request.formcontent.CreateTextFieldRequest;
import com.dag.homerentwebservice.model.response.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static com.dag.homerentwebservice.model.mapper.FormContentMapper.FORM_CONTENT_MAPPER;

@Service
@RequiredArgsConstructor
public class FormContentService {

    private final FormContentEntityService formContentEntityService;

    public BaseResponse<FormContentDto> getFormContent(String pageName){
        try {
            FormContent formContent = formContentEntityService.getFormContentByPageName(
                    FormContentPages.valueOf(pageName)
            );
            return BaseResponse.<FormContentDto>builder()
                    .data(FORM_CONTENT_MAPPER
                                    .convertToFormContentDto(formContent))
                    .error(false)
                    .build();
        }catch (Exception e){
            return BaseResponse.<FormContentDto>builder()
                    .error(false)
                    .dialogBoxDto(generateFormNotFoundDialogBox())
                    .build();
        }
    }

    public BaseResponse<FormContentDto> createFormContent(CreateFormContentRequest createFormContentRequest) {
        try {
            FormContentDto formContentDto = createFormContentDto(createFormContentRequest);
            if (formContentDto != null)
                return BaseResponse.<FormContentDto>builder()
                        .data(formContentDto)
                        .error(false)
                        .build();
            else
                return BaseResponse.<FormContentDto>builder()
                        .data(null)
                        .error(true)
                        .dialogBoxDto(generateFormNotFoundDialogBox())
                        .build();
        } catch (Exception e) {
            return BaseResponse.<FormContentDto>builder()
                    .data(null)
                    .error(true)
                    .dialogBoxDto(generateFormNotFoundDialogBox())
                    .build();
        }
    }

    FormContentDto createFormContentDto(CreateFormContentRequest createFormContentRequest) {
        FormContent formContent = FORM_CONTENT_MAPPER.createFormContent(createFormContentRequest);
        formContent = formContentEntityService.saveFormContent(formContent);
        for (CreateTextFieldRequest createTextFieldRequest : createFormContentRequest.getCreateTextFieldRequests()) {
            TextField textField = FORM_CONTENT_MAPPER.createTextField(createTextFieldRequest);
            textField.setFormId(formContent.getId());
            formContentEntityService.saveTextField(textField);
        }
        FormContentDto returnedFormContent = FORM_CONTENT_MAPPER.convertToFormContentDto(
                formContentEntityService.getFormContent(formContent.getId())
        );
        returnedFormContent.setTextFields(
                FORM_CONTENT_MAPPER.convertToTextFieldDtoList(
                        formContentEntityService.getTextFieldsOfFromContent(formContent.getId())
                )
        );
        return returnedFormContent;
    }

    private DialogBoxDto generateFormNotFoundDialogBox() {
        ArrayList<DialogBoxButton> dialogBoxButtons = new ArrayList<>();
        dialogBoxButtons.add(
                DialogBoxButtonGenerator
                        .getInstance()
                        .generateGoBackButton("Go Back")
        );
        return DialogBoxDto.builder()
                .dialogBoxType(DialogBoxType.ERROR)
                .dialogBoxColorType(DialogBoxColorType.RED)
                .title("Oppss!!")
                .message("Form bulunamadı")
                .buttonList(dialogBoxButtons)
                .build();
    }
}
