package com.dag.homerentwebservice.model.request.formcontent;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateFormContentRequest {

    private int formId;

    private String pageName;

    private String title;
}
