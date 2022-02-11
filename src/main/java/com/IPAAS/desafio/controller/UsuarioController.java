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
import com.IPAAS.desafio.controller.dto.UsuarioRequest;
import com.IPAAS.desafio.controller.dto.UsuarioResponse;
import com.IPAAS.desafio.service.UsuarioService;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
	
	private UsuarioService usuarioService;

	public UsuarioController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	@PostMapping("/")
	public ResponseEntity<JsonElement> criarUsuario(
			@RequestBody UsuarioRequest request){
		try {
			return new ResponseEntity<JsonElement>(usuarioService.criarUsuario(request).toJSON(), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<JsonElement>(new Gson().toJsonTree(e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	@GetMapping("/listar/{organizacao_id}")
	public ResponseEntity<List<UsuarioResponse>> listaUsuario(
			@PathVariable("organizacao_id") Long organizacao_id,
			@RequestBody AutenticadorRequest autenticadorRequest) throws Exception{
		return new ResponseEntity<List<UsuarioResponse>>(usuarioService.criarListaUsuario(organizacao_id, autenticadorRequest.getUsuario(), autenticadorRequest.getSenha()),HttpStatus.OK);
	}
	
	@PutMapping("/{usuario_id}")
	public ResponseEntity<JsonElement> atualizarUsuario (
			@PathVariable("usuario_id") String usuario_id, 
			@RequestBody UsuarioRequest request){
		try {
			return new ResponseEntity<JsonElement>(usuarioService.atualizarUsuario(usuario_id, request).toJSON(),HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<JsonElement>(new Gson().toJsonTree(e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	@DeleteMapping("/{usuario_id}")
	public ResponseEntity<JsonElement> deletarUsuario(
			@PathVariable("usuario_id") String usuario_id,
			@RequestBody AutenticadorRequest autenticadorRequest){
		try {
			usuarioService.deletarUsuario(usuario_id, autenticadorRequest.getUsuario(), autenticadorRequest.getSenha());
			return new ResponseEntity<JsonElement>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<JsonElement>(new Gson().toJsonTree(e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/organizacao/{organizacao_id}/{usuarioAlocado}")
	public ResponseEntity<JsonElement> alocarUsuarioAOrganizacao(
			@PathVariable("organizacao_id") Long organizacao_id,
			@PathVariable("usuarioAlocado") String usuarioAlocado,
			@RequestBody AutenticadorRequest autenticadorRequest){
		try {
			return new ResponseEntity<JsonElement>(usuarioService.alocarUsuarioAOrganizacao(organizacao_id, usuarioAlocado, autenticadorRequest.getUsuario(), autenticadorRequest.getSenha()).toJSON(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<JsonElement>(new Gson().toJsonTree(e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	@PostMapping("/workspace/{workspace_id}/{usuarioAlocado}")
	public ResponseEntity<JsonElement> alocarUsuarioAWorkspace(
			@PathVariable("workspace_id") Long workspace_id,
			@PathVariable("usuarioAlocado") String usuarioAlocado,
			@RequestBody AutenticadorRequest autenticadorRequest){
		try {
			return new ResponseEntity<JsonElement>(usuarioService.alocarUsuarioAWorkspace(workspace_id, usuarioAlocado, autenticadorRequest.getUsuario(), autenticadorRequest.getSenha()).toJSON(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<JsonElement>(new Gson().toJsonTree(e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
