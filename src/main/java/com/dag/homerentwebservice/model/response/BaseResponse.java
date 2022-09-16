package com.dag.homerentwebservice.model.response;

import com.dag.homerentwebservice.model.dto.dialogbox.DialogBoxDto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BaseResponse<T> {

    DialogBoxDto dialogBoxDto;
    T data;
    Boolean error;
}
