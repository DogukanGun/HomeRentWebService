package com.dag.homerentwebservice.security.dto;

import com.dag.homerentwebservice.model.dto.dialogbox.button.ButtonActionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DialogBoxContent {
    String title;
    String message;
    List<DialogBoxButtonContent> dialogBoxButtonContents ;

}



