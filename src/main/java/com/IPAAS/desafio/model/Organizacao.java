package com.IPAAS.desafio.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.IPAAS.desafio.controller.dto.OrganizacaoRequest;

@Entity
public class Organizacao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "organizacao")
	private List<Workspace> workspaces;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "usuario_admin_id")
	private Usuario usuarioAdmin;
	@OneToMany(mappedBy = "organizacao")
	private List<Usuario> usuarios;
	private String nome;
	
	public Organizacao(Long id, List<Workspace> workspaces, Usuario usuarioAdmin, String nome) {
		this.id = id;
		this.workspaces = workspaces;
		this.usuarioAdmin = usuarioAdmin;
		this.nome = nome;
	}
	
	public Organizacao() {
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public List<Workspace> getWorkspaces() {
		return workspaces;
	}
	public void setWorkspaces(List<Workspace> workspaces) {
		this.workspaces = workspaces;
	}
	public Usuario getUsuarioAdmin() {
		return usuarioAdmin;
	}
	public void setUsuarioAdmin(Usuario usuarioAdmin) {
		this.usuarioAdmin = usuarioAdmin;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Organizacao requestConverter(OrganizacaoRequest request) {
		var organizacao = new Organizacao();
		organizacao.setNome(request.getNome());
		var admin = new Usuario();
		admin.setUsuario(request.getUsuario());
		admin.setSenha(request.getSenha());
		organizacao.setUsuarioAdmin(admin);
		return organizacao;
	}
	
}
