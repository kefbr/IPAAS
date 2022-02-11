package com.IPAAS.desafio.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.IPAAS.desafio.controller.dto.ApiRequest;
import com.IPAAS.desafio.controller.dto.ApiResponse;
import com.IPAAS.desafio.model.Api;
import com.IPAAS.desafio.repository.ApiRepository;
import com.IPAAS.desafio.repository.WorkspaceRepository;

@Service
public class ApiService {

	private ApiRepository apiRepository;
	private WorkspaceRepository workspaceRepository;
	private WorkspaceService workspaceService;
	
	public ApiService(ApiRepository apiRepository, WorkspaceRepository workspaceRepository,
			WorkspaceService workspaceService) {
		this.apiRepository = apiRepository;
		this.workspaceRepository = workspaceRepository;
		this.workspaceService = workspaceService;
	}

	public ApiResponse criarApi(ApiRequest request) throws Exception {
		if(!workspaceService.verificarSeOUsuarioPertenceAoWorkspace(request.getWorkspace_id(),request.getUsuario(), request.getSenha()))
			throw new Exception("Você não tem acesso ao workspace selecionado.");
		var api = new Api().requestConverter(request);
		api.setWorkspace(workspaceRepository.getById(request.getWorkspace_id()));
		try {
			return new ApiResponse(apiRepository.save(api));
		} catch (Exception e) {
			throw new Exception("Erro ao criar api - " + e.getMessage());
		}
		
	}
	
	public List<ApiResponse> criarListaApi(Long workspace_Id, String usuario, String senha) throws Exception{
		if(!workspaceService.verificarSeOUsuarioPertenceAoWorkspace(workspace_Id,usuario, senha))
			throw new Exception("Você não tem acesso ao workspace selecionado.");
		return workspaceRepository.getById(workspace_Id).getApis().stream().map((a) -> new ApiResponse(a)).collect(Collectors.toList());
	}
	
	public ApiResponse atualizarApi(Long api, ApiRequest request) throws Exception {
		if(!workspaceService.verificarSeOUsuarioPertenceAoWorkspace(request.getWorkspace_id(),request.getUsuario(), request.getSenha()))
			throw new Exception("Você não tem acesso ao workspace selecionado.");
		var a = apiRepository.getById(api);
		var w = workspaceRepository.getById(request.getWorkspace_id());
		a.setEspecificacoes(request.getEspecificacao());
		a.setNome(request.getNome());
		a.setWorkspace(w);
		apiRepository.save(a);
		return new ApiResponse(a);
		
	}
	
	public void deletarApi(Long api, String usuario, String senha) throws Exception {
		var a = apiRepository.getById(api);
		if(!workspaceService.verificarSeOUsuarioPertenceAoWorkspace(a.getWorkspace().getId(),usuario, senha))
			throw new Exception("Você não tem acesso ao workspace selecionado.");
		apiRepository.delete(a);
	}
}
