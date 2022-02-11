package com.IPAAS.desafio.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.IPAAS.desafio.controller.dto.UsuarioRequest;
import com.IPAAS.desafio.controller.dto.UsuarioResponse;
import com.IPAAS.desafio.model.Usuario;
import com.IPAAS.desafio.repository.OrganizacaoRepository;
import com.IPAAS.desafio.repository.UsuarioRepository;
import com.IPAAS.desafio.repository.WorkspaceRepository;

@Service
public class UsuarioService {
	
	private UsuarioRepository usuarioRepository;
	private OrganizacaoRepository organizacaoRepository;
	private WorkspaceRepository workspaceRepository;
	private OrganizacaoService organizacaoService;
	
	public UsuarioService(UsuarioRepository usuarioRepository, OrganizacaoRepository organizacaoRepository,
			WorkspaceRepository workspaceRepository, OrganizacaoService organizacaoService) {
		this.usuarioRepository = usuarioRepository;
		this.organizacaoRepository = organizacaoRepository;
		this.workspaceRepository = workspaceRepository;
		this.organizacaoService = organizacaoService;
	}

	public UsuarioResponse criarUsuario(UsuarioRequest request) throws Exception {
		if(!organizacaoService.verificarSeOUsuarioEhAdministrador(request.getOrganizacao_id(), request.getAdmin(), request.getSenhaAdmin()))
			throw new Exception("Você não é administrador da Organização selecionada.");
		var u = new Usuario().requestConverter(request);
		u.setOrganizacao(organizacaoRepository.getById(request.getOrganizacao_id()));
		try {
			return new UsuarioResponse(usuarioRepository.save(u));
		} catch (Exception e) {
			throw new Exception("Erro ao criar usuario - " + e.getMessage());
		}
	}
	
	public List<UsuarioResponse> criarListaUsuario(Long organizacao_id, String usuario, String senha) throws Exception{
		if(!organizacaoService.verificarSeOUsuarioEhAdministrador(organizacao_id, usuario, senha))
			throw new Exception("Você não é administrador da Organização selecionada.");
		return organizacaoRepository.getById(organizacao_id).getUsuarios().stream().map((u) -> new UsuarioResponse(u)).collect(Collectors.toList());
	}
	
	public UsuarioResponse atualizarUsuario(String usuario_id, UsuarioRequest request) throws Exception {
		if(!organizacaoService.verificarSeOUsuarioEhAdministrador(request.getOrganizacao_id(), request.getAdmin(), request.getSenha()))
			throw new Exception("Você não é administrador da Organização selecionada.");
		var u = usuarioRepository.getById(usuario_id);
		u.setSenha(request.getSenha());
		u.setUsuario(request.getUsuario());
		u.setOrganizacao(organizacaoRepository.getById(request.getOrganizacao_id()));
		usuarioRepository.save(u);
		return new UsuarioResponse(u);
	}
	
	public void deletarUsuario(String usuario_id, String usuario, String senha) throws Exception {
		var u = usuarioRepository.getById(usuario);
		if(!organizacaoService.verificarSeOUsuarioEhAdministrador(u.getOrganizacao().getId(), usuario, senha))
			throw new Exception("Você não tem acesso a organizacão selecionada.");
		usuarioRepository.deleteById(usuario_id);
	}
	
	public UsuarioResponse alocarUsuarioAOrganizacao(Long organizacao_id, String usuarioAlocado, String usuario, String senha) throws Exception {
		var uAdmin = usuarioRepository.getById(usuario);
		if(!organizacaoService.verificarSeOUsuarioEhAdministrador(uAdmin.getOrganizacao().getId(), usuario, senha) && !uAdmin.getSenha().equals(senha))
			throw new Exception("Você não tem acesso a organizacão selecionada.");
		var u = usuarioRepository.getById(usuarioAlocado);
		var o = organizacaoRepository.getById(organizacao_id);
		u.setOrganizacao(o);
		usuarioRepository.save(u);
		return new UsuarioResponse(u);
	}
	
	public UsuarioResponse alocarUsuarioAWorkspace(Long workspace_id, String usuarioAlocado, String usuario, String senha) throws Exception {
		var uAdmin = usuarioRepository.getById(usuario);
		if(!organizacaoService.verificarSeOUsuarioEhAdministrador(uAdmin.getOrganizacao().getId(), usuario, senha) && !uAdmin.getSenha().equals(senha))
			throw new Exception("Você não tem acesso a organizacão selecionada.");
		var u = usuarioRepository.getById(usuarioAlocado);
		var w = workspaceRepository.getById(workspace_id);
		w.getUsuarios().add(u);
		workspaceRepository.save(w);
		return new UsuarioResponse(u);
	}
	
	public boolean verificarUsuario(String usuario, String senha, Usuario usuarioParaVerificar) {
		var u = usuarioRepository.findById(usuario);
		if(!u.isEmpty()&& u.get().equals(usuarioParaVerificar)) {
			return true;
		}else {
			return false;
		}
	}
}
