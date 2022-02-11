package com.IPAAS.desafio.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.IPAAS.desafio.controller.dto.WorkspaceRequest;

@Entity
public class Workspace {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "workspace")
	private List<Api> apis;
	@ManyToMany
	@JoinTable(name = "workspace_usuario",
				joinColumns = @JoinColumn(name = "workspace_id"),
				inverseJoinColumns = @JoinColumn(name = "usuario_id"))
	private List<Usuario> usuarios;
	@ManyToOne
	@JoinColumn(name = "organizacao_id")
	private Organizacao organizacao;
	private String nome;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public List<Api> getApis() {
		return apis;
	}
	public void setApis(List<Api> apis) {
		this.apis = apis;
	}
	public List<Usuario> getUsuarios() {
		return usuarios;
	}
	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	public Organizacao getOrganizacao() {
		return organizacao;
	}
	public void setOrganizacao(Organizacao organizacao) {
		this.organizacao = organizacao;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Workspace requestConverter(WorkspaceRequest request) {
		var workspace = new Workspace();
		workspace.setNome(request.getNome());
		return workspace;
	}
	
}
