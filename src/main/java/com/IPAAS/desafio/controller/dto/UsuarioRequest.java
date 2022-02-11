package com.IPAAS.desafio.controller.dto;

public class UsuarioRequest {
	private String usuario;
	private String senha;
	private Long organizacao_id;
	private String admin;
	private String senhaAdmin;
	
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
	public Long getOrganizacao_id() {
		return organizacao_id;
	}
	public void setOrganizacao_id(Long organizacao_id) {
		this.organizacao_id = organizacao_id;
	}
	public String getAdmin() {
		return admin;
	}
	public void setAdmin(String admin) {
		this.admin = admin;
	}
	public String getSenhaAdmin() {
		return senhaAdmin;
	}
	public void setSenhaAdmin(String senhaAdmin) {
		this.senhaAdmin = senhaAdmin;
	}
	
}
