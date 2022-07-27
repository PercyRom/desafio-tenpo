package com.tenpo.desafio.app.util;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.springframework.util.ObjectUtils;

public class StringUtils {
	public static String concatenarCadenas(String delimiter, String... valores) {
		return Arrays.stream(valores).filter(s -> !ObjectUtils.isEmpty(s)).collect(Collectors.joining(delimiter));
	}

}
