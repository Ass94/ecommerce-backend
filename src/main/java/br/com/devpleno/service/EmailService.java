package br.com.devpleno.service;

import org.springframework.mail.SimpleMailMessage;

import br.com.devpleno.domain.Pedido;

public interface EmailService {
	
	void sendOrderConfirmationEmail(Pedido pedido);
	
	void sendEmail(SimpleMailMessage msg);
	
}
