package com.dag.homerentwebservice.security.dto;

import com.dag.homerentwebservice.model.dto.dialogbox.button.ButtonActionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class DialogBoxButtonContent{
    String buttonText;
    ButtonActionType buttonActionType;
}