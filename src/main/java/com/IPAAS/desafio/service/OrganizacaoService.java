package com.IPAAS.desafio.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.IPAAS.desafio.controller.dto.OrganizacaoRequest;
import com.IPAAS.desafio.model.Organizacao;
import com.IPAAS.desafio.model.Usuario;
import com.IPAAS.desafio.repository.OrganizacaoRepository;
import com.IPAAS.desafio.repository.UsuarioRepository;

@Service
public class OrganizacaoService {
	
	private OrganizacaoRepository organizacaoRepository;
	
	public OrganizacaoService(OrganizacaoRepository organizacaoRepository, UsuarioRepository usuarioRepository) {
		this.organizacaoRepository = organizacaoRepository;
	}

	public Organizacao criarOrganizacao(OrganizacaoRequest request) throws Exception {
		var u = new Usuario();
		u.setUsuario(request.getUsuario());
		u.setSenha(request.getSenha());
		var organizacao = new Organizacao().requestConverter(request);
		organizacao.setUsuarioAdmin(u);
		u.setOrganizacao(organizacao);
		try {
			return organizacaoRepository.save(organizacao);
		} catch (Exception e) {
			throw new Exception("Erro ao criar organizacao - " + e.getMessage());
		}
	}
	
	public List<Organizacao> criarListaOrganizacao(){
		return organizacaoRepository.findAll();
	}
	
	public boolean verificarSeOUsuarioEhAdministrador(Long organizacao_id, String usuario, String senha) {
		var u = organizacaoRepository.getById(organizacao_id).getUsuarioAdmin();
		return u.getUsuario().equals(usuario) && u.getSenha().equals(senha);
	}
}
