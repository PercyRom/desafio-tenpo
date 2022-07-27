package com.tenpo.desafio.app.dto;

import com.tenpo.desafio.app.constant.Constant;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResponseDto {
	private Integer codigo;

	private String mensaje;

	private Object data;

	public ResponseDto(Integer codigo, String mensaje, Object data) {
		this.codigo = codigo;
		this.mensaje = mensaje;
		this.data = data;
	}

	public void setOkData(Object data) {
		setCodigo(Constant.COD_OK);
		setMensaje(Constant.DATA_OK);
		setData(data);
	}

	public void setError(Object data) {
		setCodigo(Constant.COD_ERR);
		setMensaje(Constant.DATA_EMPTY);
		setData(data);
	}
	
	public void setError(String msgErr) {
		setCodigo(Constant.COD_ERR);
		setMensaje(msgErr);
		setData(new Object());
	}
}
