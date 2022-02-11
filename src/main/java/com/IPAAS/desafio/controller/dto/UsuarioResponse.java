package com.IPAAS.desafio.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.IPAAS.desafio.model.Usuario;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

public class UsuarioResponse {
	private String usuario;
	private String senha;
	private List<Long> workspaces;
	private Long organizacao_id;
	
	public UsuarioResponse (Usuario u) {
		this.usuario = u.getUsuario();
		this.senha = u.getSenha();
		if(u.getWorkspaces() != null)
			this.workspaces = u.getWorkspaces().stream().map((u2) -> u2.getId()).collect(Collectors.toList());
		if(u.getOrganizacao() != null)
		this.organizacao_id = u.getOrganizacao().getId();
	}
	public UsuarioResponse() {
		
	}
	
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public List<Long> getWorkspaces() {
		return workspaces;
	}
	public void setWorkspaces(List<Long> workspaces) {
		this.workspaces = workspaces;
	}
	public Long getOrganizacao_id() {
		return organizacao_id;
	}
	public void setOrganizacao_id(Long organizacao_id) {
		this.organizacao_id = organizacao_id;
	}
	public JsonElement toJSON() {
		return new Gson().toJsonTree(this);
	}
}
