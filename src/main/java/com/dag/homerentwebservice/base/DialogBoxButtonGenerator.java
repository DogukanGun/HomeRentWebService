package com.dag.homerentwebservice.base;

import com.dag.homerentwebservice.model.dto.dialogbox.button.ButtonActionType;
import com.dag.homerentwebservice.model.dto.dialogbox.button.DialogBoxButton;

public class DialogBoxButtonGenerator {

    static private DialogBoxButtonGenerator dialogBoxButtonGenerator;

    public static DialogBoxButtonGenerator getInstance(){
        if (dialogBoxButtonGenerator == null)
            dialogBoxButtonGenerator = new DialogBoxButtonGenerator();
        return dialogBoxButtonGenerator;
    }

    public DialogBoxButton generateDismissButton(String buttonText){
        return DialogBoxButton.builder()
                .actionType(ButtonActionType.DISMISS)
                .text(buttonText)
                .build();
    }
    public DialogBoxButton generateGoBackButton(String buttonText){
        return DialogBoxButton.builder()
                .actionType(ButtonActionType.GO_BACK)
                .text(buttonText)
                .build();
    }
    public DialogBoxButton generateRequestButton(String buttonText){
        return DialogBoxButton.builder()
                .actionType(ButtonActionType.REQUEST)
                .text(buttonText)
                .build();
    }
}
