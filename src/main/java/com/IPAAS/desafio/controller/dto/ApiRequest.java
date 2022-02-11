package com.IPAAS.desafio.controller.dto;

public class ApiRequest {
	private String nome;
	private String especificacao;
	private Long workspace_id;
	private String usuario;
	private String senha;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEspecificacao() {
		return especificacao;
	}
	public void setEspecificacao(String especificacao) {
		this.especificacao = especificacao;
	}
	public Long getWorkspace_id() {
		return workspace_id;
	}
	public void setWorkspace_id(Long workspace_id) {
		this.workspace_id = workspace_id;
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
