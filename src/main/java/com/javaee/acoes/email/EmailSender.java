package com.javaee.acoes.email;

import com.javaee.acoes.email.EmailConfig;

public class EmailSender {
	
	public static void main(String[] args) {
		final String fromEmail = "alexandre@example.com";
		final String password = "senha";
		final String toEmail = "alexandre@example.com";
		
		System.out.println("Initializing email send");
		
		EmailConfig config = new EmailConfig();
		
		config.sendEmail(fromEmail, password, toEmail, "teste de email", "Email body");
	}
}
