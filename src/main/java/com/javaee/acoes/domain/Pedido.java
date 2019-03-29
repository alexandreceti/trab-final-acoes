package com.javaee.acoes.domain;

import java.io.Serializable;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Pedido implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String id = UUID.randomUUID().toString();
	private String clienteId = UUID.randomUUID().toString();
	private Boolean venda;
}
