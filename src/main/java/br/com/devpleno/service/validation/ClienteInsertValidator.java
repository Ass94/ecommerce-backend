package br.com.devpleno.service.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.devpleno.domain.Cliente;
import br.com.devpleno.domain.enums.TipoCliente;
import br.com.devpleno.dto.ClienteNewDTO;
import br.com.devpleno.repositories.ClienteRepository;
import br.com.devpleno.resources.exceptions.FieldMessage;
import br.com.devpleno.service.validation.utils.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO>{
	
	@Autowired
	private ClienteRepository repo;

	@Override
	public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		
		if(objDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCodigo()) && !BR.isValidCpf(objDto.getCpfCnpj())) {
			list.add(new FieldMessage("cpfCnpj", "CPF inválido"));
		}
		
		if(objDto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCodigo()) && !BR.isValidCnpj(objDto.getCpfCnpj())) {
			list.add(new FieldMessage("cpfCnpj", "CNPJ inválido"));
		}
		
		Cliente aux = repo.findByEmail(objDto.getEmail());
		if(aux != null) {
			list.add(new FieldMessage("email", "E-mail inexistente"));
		}
		
		for(FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
				.addConstraintViolation();
		}
		return false;
	}
	
	

}
