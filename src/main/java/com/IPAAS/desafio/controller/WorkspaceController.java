package com.IPAAS.desafio.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.IPAAS.desafio.controller.dto.AutenticadorRequest;
import com.IPAAS.desafio.controller.dto.WorkspaceRequest;
import com.IPAAS.desafio.controller.dto.WorkspaceResponse;
import com.IPAAS.desafio.service.WorkspaceService;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

@RestController
@RequestMapping("/workspace")
public class WorkspaceController {
	
	private WorkspaceService workspaceService;

	public WorkspaceController(WorkspaceService workspaceService) {
		this.workspaceService = workspaceService;
	}

	@PostMapping("/")
	public ResponseEntity<JsonElement> criarWorkspace(@RequestBody WorkspaceRequest request){
		try {
			return new ResponseEntity<JsonElement>(workspaceService.criarWorkspace(request).toJSON(), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<JsonElement>(new Gson().toJsonTree(e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	@GetMapping("/listar/{organizacao_id}")
	public ResponseEntity<List<WorkspaceResponse>> listaWorkspace(
			@PathVariable("organizacao_id") Long organizacao_id, 
			@RequestBody AutenticadorRequest autenticadorRequest ) throws Exception{
			return new ResponseEntity<List<WorkspaceResponse>>(workspaceService.criarListaWorkspace(organizacao_id, autenticadorRequest.getUsuario(), autenticadorRequest.getSenha()),HttpStatus.OK);
	}
	
	@PutMapping("/{workspace_id}")
	public ResponseEntity<JsonElement> atualizarWorkspace (
			@PathVariable("workspace_id") Long workspace_id, 
			@RequestBody WorkspaceRequest request){
		try {
			return new ResponseEntity<JsonElement>(workspaceService.atualizarWorkspace(workspace_id, request).toJSON(),HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<JsonElement>(new Gson().toJsonTree(e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	@DeleteMapping("/{workspace_id}")
	public ResponseEntity<JsonElement> deletarWorkspace(
			@PathVariable("workspace_id") Long workspace_id,
			@RequestBody AutenticadorRequest autenticadorRequest ){
		try {
			workspaceService.deletarWorkspace(workspace_id, autenticadorRequest.getUsuario(), autenticadorRequest.getSenha());
			return new ResponseEntity<JsonElement>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<JsonElement>(new Gson().toJsonTree(e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
