package com.javaee.acoes.domain;


import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document
public class Acao {
	
	@Id
	private String id;
	
	private String empresaId;
	private String clienteId;
	private String name;
	private float valorInicial;
	private float valor;
	private Date dtInicial;
	private Date dtAtualizacao;
}
