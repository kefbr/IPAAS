package com.IPAAS.desafio.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.IPAAS.desafio.controller.dto.UsuarioRequest;

@Entity
public class Usuario {
	
	@Id
	private String usuario;
	private String senha;
	@ManyToMany(mappedBy = "usuarios", cascade = CascadeType.ALL)
	private List<Workspace> workspaces;
	@ManyToOne
	@JoinColumn(name = "organizacao_id")
	private Organizacao organizacao;
	
	public List<Workspace> getWorkspaces() {
		return workspaces;
	}
	public void setWorkspaces(List<Workspace> workspaces) {
		this.workspaces = workspaces;
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
	public Organizacao getOrganizacao() {
		return organizacao;
	}
	public void setOrganizacao(Organizacao organizacao) {
		this.organizacao = organizacao;
	}
	public Usuario requestConverter(UsuarioRequest request) {
		var usuario = new Usuario();
		usuario.setSenha(request.getSenha());
		usuario.setUsuario(request.getUsuario());
		return usuario;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		return true;
	}
	
}
