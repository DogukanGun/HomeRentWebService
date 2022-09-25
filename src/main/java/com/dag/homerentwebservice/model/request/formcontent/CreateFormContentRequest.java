package com.dag.homerentwebservice.model.request.formcontent;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateFormContentRequest {

    private String pageName;

    private String title;

    private List<CreateTextFieldRequest> createTextFieldRequests;
}
