package com.IPAAS.desafio.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.IPAAS.desafio.controller.dto.WorkspaceRequest;
import com.IPAAS.desafio.controller.dto.WorkspaceResponse;
import com.IPAAS.desafio.model.Usuario;
import com.IPAAS.desafio.model.Workspace;
import com.IPAAS.desafio.repository.ApiRepository;
import com.IPAAS.desafio.repository.OrganizacaoRepository;
import com.IPAAS.desafio.repository.UsuarioRepository;
import com.IPAAS.desafio.repository.WorkspaceRepository;

@Service
public class WorkspaceService {
	
	private WorkspaceRepository workspaceRepository;
	private OrganizacaoRepository organizacaoRepository;
	private UsuarioRepository usuarioRepository;
	private OrganizacaoService organizacaoService;
	private ApiRepository apiRepository;
	

	public WorkspaceService(WorkspaceRepository workspaceRepository, OrganizacaoRepository organizacaoRepository,
			UsuarioRepository usuarioRepository, OrganizacaoService organizacaoService, ApiRepository apiRepository) {
		super();
		this.workspaceRepository = workspaceRepository;
		this.organizacaoRepository = organizacaoRepository;
		this.usuarioRepository = usuarioRepository;
		this.organizacaoService = organizacaoService;
		this.apiRepository = apiRepository;
	}

	public WorkspaceResponse criarWorkspace(WorkspaceRequest request) throws Exception {
		if(!organizacaoService.verificarSeOUsuarioEhAdministrador(request.getOrganizacao_id(), request.getUsuario(), request.getSenha()))
			throw new Exception("Você não tem acesso a organizacão selecionada.");
		var workspace = new Workspace().requestConverter(request);
		workspace.setOrganizacao(organizacaoRepository.getById(request.getOrganizacao_id()));
		var u = usuarioRepository.getById(request.getUsuario());
		workspace.setUsuarios(inicializarListaUsuario(u));
		try {
			return new WorkspaceResponse(workspaceRepository.save(workspace));
		} catch (Exception e) {
			throw new Exception("Erro ao criar workspace - " + e.getMessage());
		}
		
	}

	private List<Usuario> inicializarListaUsuario(Usuario u) {
		List<Usuario> listaUsuario = new ArrayList<Usuario>();
		listaUsuario.add(u);
		return listaUsuario;
	}
	
	public List<WorkspaceResponse> criarListaWorkspace(Long organizacao_id, String usuario, String senha) throws Exception{
		if(!organizacaoService.verificarSeOUsuarioEhAdministrador(organizacao_id, usuario, senha))
			throw new Exception("Você não tem acesso a organizacão selecionada.");
		return organizacaoRepository.getById(organizacao_id).getWorkspaces().stream().map((w) -> new WorkspaceResponse(w)).collect(Collectors.toList());
	}
	
	public WorkspaceResponse atualizarWorkspace(Long workspace_id, WorkspaceRequest request) throws Exception {
		if(!organizacaoService.verificarSeOUsuarioEhAdministrador(request.getOrganizacao_id(), request.getUsuario(), request.getSenha()))
			throw new Exception("Você não tem acesso a organizacão selecionada.");
		var w = workspaceRepository.getById(workspace_id);
		w.setNome(request.getNome());
		w.setOrganizacao(organizacaoRepository.getById(request.getOrganizacao_id()));
		workspaceRepository.save(w);
		return new WorkspaceResponse(w);
		
	}
	
	public void deletarWorkspace(Long workspace_id,  String usuario, String senha) throws Exception {
		Workspace w = workspaceRepository.getById(workspace_id);
		if(!organizacaoService.verificarSeOUsuarioEhAdministrador(w.getOrganizacao().getId(), usuario, senha))
			throw new Exception("Você não tem acesso a organizacão selecionada.");
		workspaceRepository.delete(w);
		
	}
	
	public void alocarApiNaWorkspace(Long api_id, Long workspace_id, String usuario, String senha) throws Exception {
		var w = workspaceRepository.getById(workspace_id);
		if(!verificarSeOUsuarioPertenceAoWorkspace(workspace_id, usuario, senha))
			throw new Exception("Você não tem acesso ao workspace selecionado.");
		var a = apiRepository.getById(api_id);
		a.setWorkspace(w);
		workspaceRepository.save(w);
	}
	
//	private boolean verificarUsuario(String usuario, String senha, Usuario usuarioParaVerificar) {
//		var u = usuarioRepository.findById(usuario);
//		if(!u.isEmpty()&& u.get().equals(usuarioParaVerificar)) {
//			return true;
//		}else {
//			return false;
//		}
//	}
	public boolean verificarSeOUsuarioPertenceAoWorkspace(Long workspace_id, String usuario, String senha) {
		var w = workspaceRepository.getById(workspace_id);
		return w.getUsuarios().stream().anyMatch(u -> u.getUsuario().equals(usuario) && u.getSenha().equals(senha));
	}
}
