package com.IPAAS.desafio.controller.dto;

import com.IPAAS.desafio.model.Api;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

public class ApiResponse {
	
	private Long id;
	private Long workspace;
	private String especificacoes;
	private String nome;
	
	public ApiResponse(Api api) {
		this.especificacoes= api.getEspecificacoes();
		this.id = api.getId();
		this.nome = api.getNome();
		this.workspace = api.getWorkspace().getId();
	}
	
	public ApiResponse() {
		
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getWorkspace() {
		return workspace;
	}
	public void setWorkspace(Long workspace) {
		this.workspace = workspace;
	}
	public String getEspecificacoes() {
		return especificacoes;
	}
	public void setEspecificacoes(String especificacoes) {
		this.especificacoes = especificacoes;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public JsonElement toJSON() {
		return new Gson().toJsonTree(this);
	}
}
