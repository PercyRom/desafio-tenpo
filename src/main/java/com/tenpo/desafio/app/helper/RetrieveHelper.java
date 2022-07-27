package com.tenpo.desafio.app.helper;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.ResolvableType;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import com.tenpo.desafio.app.constant.Constant;
import com.tenpo.desafio.app.dto.BaseResponse;
import com.tenpo.desafio.app.dto.ResponseObj;
import com.tenpo.desafio.app.exception.InternalErrorExceptionHandler;
import com.tenpo.desafio.app.exception.ValidationHttpException;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
public class RetrieveHelper {
	
    private RetrieveHelper() {
        throw new IllegalStateException("RetrieveHelper class");
    }

    public static final String TIPO_CONSULTAR = "CONSULTAR";
    public static final String TIPO_REGISTRAR = "REGISTRAR";
    public static final String TIPO_ACTUALIZAR = "ACTUALIZAR";
    public static final String TIPO_ELIMINAR = "ELIMINAR";

    public static <S, R> ResponseObj<R> post(String uri,S request, Class<R> response) {
        Mono<ResponseObj<R>> responseClientMono = WebClient.create()
                .method(HttpMethod.POST)
                .uri(uri)
                .body(BodyInserters.fromValue(request))
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, clientResponse -> isClientError(clientResponse, uri, TIPO_REGISTRAR))
                .onStatus(HttpStatus::is5xxServerError, clientResponse -> isClientError(clientResponse, uri, TIPO_REGISTRAR))
                .bodyToMono(convertResponseClient(response))
                .onErrorMap(throwable -> isInternalError(uri, throwable));
        return responseClientMono.block();
    }
    
    private static Mono<Throwable> isClientError(ClientResponse clientResponse, String uri, String tipo) {
        return clientResponse.bodyToMono(BaseResponse.class)
                .flatMap(error -> {
                    log.error(String.format(Constant.MSG_FORMAT_CLIENT_ERROR, tipo), uri, error.getStatus(),
                            error.getMessage(), clientResponse.statusCode());
                    return Mono.error(new ValidationHttpException(
                            clientResponse.statusCode(),
                            error.getStatus(),
                            error.getMessage()));
                });
    }
    
    private static <R> ParameterizedTypeReference<ResponseObj<R>> convertResponseClient(Class<R> response) {
        return ParameterizedTypeReference.forType(
                ResolvableType.forClassWithGenerics(ResponseObj.class, response).getType());
    }
    
    private static InternalErrorExceptionHandler isInternalError(String uri, Throwable throwable) {
        log.error(Constant.MSG_FORMAT_INTERNAL_ERROR, uri, throwable.getMessage());
        return new InternalErrorExceptionHandler(throwable.getMessage());
    }

}
