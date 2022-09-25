package com.dag.homerentwebservice.service.formcontent;

import com.dag.homerentwebservice.model.entity.pagecontent.FormContent;
import com.dag.homerentwebservice.model.entity.pagecontent.TextField;
import com.dag.homerentwebservice.repository.FormContentRepository;
import com.dag.homerentwebservice.repository.TextFieldRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class FormContentEntityService {
    private final FormContentRepository formContentRepository;
    private final TextFieldRepository textFieldRepository;

    public FormContent saveFormContent(FormContent formContent){
        return formContentRepository.save(formContent);
    }

    public TextField saveTextField(TextField textField){
        return textFieldRepository.save(textField);
    }

    public List<TextField> getTextFieldsOfFromContent(int formId) throws IllegalArgumentException{
        return textFieldRepository.findAllByFormId(formId);
    }

    public FormContent getFormContent(int formId){
        return formContentRepository.findById(formId)
                .orElseThrow(()->new IllegalArgumentException(""));
    }
}
