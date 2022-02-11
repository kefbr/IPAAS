package com.IPAAS.desafio.controller.dto;

public class WorkspaceRequest {
	private String nome;
	private Long organizacao_id;
	private String usuario;
	private String senha;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Long getOrganizacao_id() {
		return organizacao_id;
	}
	public void setOrganizacao_id(Long organizacao_id) {
		this.organizacao_id = organizacao_id;
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
	
}
