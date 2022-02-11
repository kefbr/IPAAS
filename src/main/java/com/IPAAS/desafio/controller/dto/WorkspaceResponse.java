package com.IPAAS.desafio.controller.dto;

import com.IPAAS.desafio.model.Workspace;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

public class WorkspaceResponse {
	private Long id;
	private Long organizacao_id;
	private String nome;
	
	public WorkspaceResponse(Workspace workspace) {
		this.id = workspace.getId();
		if(workspace != null)
			this.organizacao_id = workspace.getOrganizacao().getId();
		this.nome = workspace.getNome();
	}
	public WorkspaceResponse() {
		
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getOrganizacao_Id() {
		return organizacao_id;
	}
	public void setOrganizacao_Id(Long organizacao) {
		this.organizacao_id = organizacao;
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
