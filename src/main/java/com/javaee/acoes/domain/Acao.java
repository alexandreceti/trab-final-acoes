package com.javaee.acoes.domain;


import java.util.Date;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document
public class Acao {
	
	@Id
	private String id = UUID.randomUUID().toString();
	
	private String empresaId = UUID.randomUUID().toString();
	private String clienteId = UUID.randomUUID().toString();
	private String name;
	private float valorInicial;
	private float valor;
	private Date dtInicial;
	private Date dtAtualizacao;
}
