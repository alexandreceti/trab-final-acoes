package com.javaee.acoes.domain;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document
public class Empresa {
	@Id
	private String id = UUID.randomUUID().toString();
	//private String id = UUID.randomUUID().toString();
	private String name;
	private String email;
	
//	@DBRef
//	private List<Acao> acoes = new ArrayList<>();
}
