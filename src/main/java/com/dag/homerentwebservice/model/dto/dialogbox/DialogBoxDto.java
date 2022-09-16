package com.dag.homerentwebservice.model.dto.dialogbox;

import com.dag.homerentwebservice.model.dto.dialogbox.button.DialogBoxButton;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DialogBoxDto {
    DialogBoxType dialogBoxType;
    DialogBoxColorType dialogBoxColorType;
    Boolean isVisible;
    String title;
    String message;
    String imageUrl;
    Boolean cancelable;
    Boolean showManuel;
    List<DialogBoxButton> buttonList;
}
