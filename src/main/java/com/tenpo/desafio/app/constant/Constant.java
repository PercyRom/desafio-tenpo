package com.tenpo.desafio.app.constant;

public class Constant {
	
	public static final Integer COD_OK = 0;
	public static final Integer COD_EMPTY = 1;
	public static final Integer COD_ERR = -1;
	public static final String DATA_OK = "OK";
	public static final String DATA_EMPTY = "NO HAY DATA PARA ESTA CONSULTA";
	
	public static final String MSG_FORMAT_CLIENT_ERROR = "OCURRIO UN ERROR AL %s - [URI: {} - CODIGO: {} - MENSAJE: {} - STATUS: {}]";
	public static final String MSG_FORMAT_INTERNAL_ERROR = "OCURRIO UN ERROR INESPERADO AL CONSUMIR EL SERVICIO - [URL: {} - ERROR: {}]";
	public static final String MSG_MONITOREO = "MONITOREANDO SERVICIO PORCENTAJE: {} | {}";
	public static final String MSG_RETRY_RECOVERY = "RETRY RECOVERY {}";

}
