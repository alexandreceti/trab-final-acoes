package com.javaee.acoes.domain;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Pedido implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String id;
	private String clienteId;
	private Boolean venda;
}
