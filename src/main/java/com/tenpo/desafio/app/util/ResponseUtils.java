package com.tenpo.desafio.app.util;

import com.tenpo.desafio.app.constant.Constant;
import com.tenpo.desafio.app.dto.ResponseDto;
import com.tenpo.desafio.app.dto.ResponseObj;

public class ResponseUtils {

	public static <C> ResponseDto evalResponse(ResponseObj<C> obj) {
		ResponseDto responseDto = new ResponseDto();

		if (Constant.COD_OK.equals(obj.getStatus())) {
			responseDto.setOkData(obj.getData());
		} else if (Constant.COD_ERR.equals(obj.getStatus())) {
			responseDto.setError(obj.getData());
		} else {
			responseDto.setError(obj.getMessage());
		}

		return responseDto;
	}
}
