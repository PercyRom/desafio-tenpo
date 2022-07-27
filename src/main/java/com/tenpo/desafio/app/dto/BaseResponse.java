package com.tenpo.desafio.app.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer status;
    private String message;

}
