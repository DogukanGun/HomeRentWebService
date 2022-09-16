package com.dag.homerentwebservice.model.dto.dialogbox.button;

import com.dag.homerentwebservice.base.KeyValuePair;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ButtonRequestModel {
    String url;
    ButtonRequestModel method;
    List<KeyValuePair<String,Object>> body;
    String bodyAsJsonString;
}
