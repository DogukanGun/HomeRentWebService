package com.dag.homerentwebservice.model.mapper;

import com.dag.homerentwebservice.model.dto.pagecontent.FormContentDto;
import com.dag.homerentwebservice.model.dto.pagecontent.TextFieldDto;
import com.dag.homerentwebservice.model.entity.pagecontent.FormContent;
import com.dag.homerentwebservice.model.entity.pagecontent.TextField;
import com.dag.homerentwebservice.model.request.formcontent.CreateFormContentRequest;
import com.dag.homerentwebservice.model.request.formcontent.CreateTextFieldRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FormContentMapper {
    FormContentMapper FORM_CONTENT_MAPPER = Mappers.getMapper(FormContentMapper.class);


    FormContentDto convertToFormContentDto(FormContent formContent);

    TextFieldDto convertToTextFieldDto(TextField textField);

    List<TextFieldDto> convertToTextFieldDtoList(List<TextField> textFields);

    TextField createTextField(CreateTextFieldRequest createTextFieldRequest);

    FormContent createFormContent(CreateFormContentRequest createPageContentRequest);

}
