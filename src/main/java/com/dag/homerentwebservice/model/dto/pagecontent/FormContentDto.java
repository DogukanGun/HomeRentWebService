package com.dag.homerentwebservice.model.dto.pagecontent;

import com.dag.homerentwebservice.model.enums.FormContentPages;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FormContentDto {

    private FormContentPages pageName;

    private String title;

    private List<TextFieldDto> textFields;
}
