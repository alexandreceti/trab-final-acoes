package com.javaee.acoes.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document
public class Cliente {
	@Id
	private String id;
	//private String id = UUID.randomUUID().toString();
	private String name;
	private String email;
	
//	@DBRef
//	private List<Acao> acoes = new ArrayList<>();
}
