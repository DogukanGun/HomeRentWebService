package com.dag.homerentwebservice.base;

import com.dag.homerentwebservice.model.dto.dialogbox.DialogBoxColorType;
import com.dag.homerentwebservice.model.dto.dialogbox.DialogBoxDto;
import com.dag.homerentwebservice.model.dto.dialogbox.DialogBoxType;
import com.dag.homerentwebservice.model.dto.dialogbox.button.DialogBoxButton;
import com.dag.homerentwebservice.model.response.BaseResponse;

import java.util.ArrayList;

public class DialogBoxDtoGenerator {

    static private DialogBoxDtoGenerator dialogBoxDtoGenerator;

    public static DialogBoxDtoGenerator getInstance(){
        if (dialogBoxDtoGenerator == null){
            dialogBoxDtoGenerator = new DialogBoxDtoGenerator();
        }
        return dialogBoxDtoGenerator;
    }

    public <T> BaseResponse<T> generateCommonErrorResponse(){
        ArrayList<DialogBoxButton> dialogBoxButtons = new ArrayList<>();
        dialogBoxButtons.add(
                DialogBoxButtonGenerator
                        .getInstance()
                        .generateGoBackButton("Geri Git")
        );
        return BaseResponse.<T>builder()
                .error(true)
                .dialogBoxDto(
                        DialogBoxDto.builder()
                                .dialogBoxType(DialogBoxType.ERROR)
                                .dialogBoxColorType(DialogBoxColorType.RED)
                                .title("Oppss!!")
                                .message("Bilinmedik bir hata aldık.")
                                .buttonList(dialogBoxButtons)
                                .build()
                )
                .build();
    }

}
