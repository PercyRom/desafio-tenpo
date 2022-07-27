package com.tenpo.desafio.app.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ResponseObj<T> extends BaseResponse {

	private static final long serialVersionUID = 1L;
    private T data;

}
