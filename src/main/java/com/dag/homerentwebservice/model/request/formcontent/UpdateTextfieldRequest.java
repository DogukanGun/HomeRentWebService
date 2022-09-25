package com.dag.homerentwebservice.model.request.formcontent;

import com.dag.homerentwebservice.model.enums.TextFieldType;
import lombok.Builder;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@Builder
public class UpdateTextfieldRequest {

    int textfieldId;

    @Enumerated(EnumType.STRING)
    private TextFieldType type;

    private String hint;
}
