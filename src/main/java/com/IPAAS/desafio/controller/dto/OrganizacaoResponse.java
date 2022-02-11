package com.IPAAS.desafio.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.IPAAS.desafio.model.Organizacao;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

public class OrganizacaoResponse {
	
	private Long id;
	private String nome;
	private String admin;
	List<Long> workspaces;

	public OrganizacaoResponse() {
	}
	public OrganizacaoResponse(Organizacao o) {
		super();
		this.id = o.getId();
		this.nome = o.getNome();
		this.admin = o.getUsuarioAdmin().getUsuario();
		if(o.getWorkspaces() != null)
			this.workspaces = o.getWorkspaces().stream().map((o2) -> o2.getId()).collect(Collectors.toList());
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getAdmin() {
		return admin;
	}
	public void setAdmin(String admin) {
		this.admin = admin;
	}
	public List<Long> getWorkspaces() {
		return workspaces;
	}
	public void setWorkspaces(List<Long> workspaces) {
		this.workspaces = workspaces;
	}
	public JsonElement toJSON() {
		return new Gson().toJsonTree(this);
	}
}
