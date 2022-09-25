package com.dag.homerentwebservice.model.dto.pagecontent;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TextFieldDto {
    private String hint;
    private String textFieldType;
}
