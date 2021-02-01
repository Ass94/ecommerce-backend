package br.com.devpleno.service.validation;

import javax.validation.Payload;

public @interface ClienteInsert {

	String message() default "Erro de validação";
	
	Class<?>[] groups() default {};
	
	Class<? extends Payload>[] payload() default {};
	
}
