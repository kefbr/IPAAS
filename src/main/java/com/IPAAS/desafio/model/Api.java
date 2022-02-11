package com.IPAAS.desafio.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.IPAAS.desafio.controller.dto.ApiRequest;

@Entity
public class Api {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "workspace_id")
	private Workspace workspace;
	private String especificacoes;
	private String nome;
	
	public Api requestConverter(ApiRequest request) {
		var api = new Api();
		api.setEspecificacoes(request.getEspecificacao());
		api.setNome(request.getNome());
		return api;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Workspace getWorkspace() {
		return workspace;
	}
	public void setWorkspace(Workspace workspace) {
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
	
}
