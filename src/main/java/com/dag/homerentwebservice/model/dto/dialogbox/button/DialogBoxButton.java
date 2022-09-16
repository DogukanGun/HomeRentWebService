package com.dag.homerentwebservice.model.dto.dialogbox.button;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DialogBoxButton {
    String text;
    ButtonActionType actionType;
    ButtonNavigationModel buttonNavigationModel ;
    ButtonRequestModel requestModel ;
    Boolean customModel ;
}
