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

import com.IPAAS.desafio.controller.dto.ApiRequest;
import com.IPAAS.desafio.controller.dto.ApiResponse;
import com.IPAAS.desafio.controller.dto.AutenticadorRequest;
import com.IPAAS.desafio.service.ApiService;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

@RestController
@RequestMapping("/api")
public class ApiController {
	
	private ApiService apiService;
	
	public ApiController(ApiService apiService) {
		this.apiService = apiService;
	}
	@PostMapping("/")
	public ResponseEntity<JsonElement> criarApi(@RequestBody ApiRequest request){
		try {
			return new ResponseEntity<JsonElement>(apiService.criarApi(request).toJSON(), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<JsonElement>(new Gson().toJsonTree(e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	@GetMapping("/listar/{workspace_id}")
	public ResponseEntity<List<ApiResponse>> listaApi(
			@PathVariable("workspace_id") Long workspace_id, 
			@RequestBody AutenticadorRequest autenticadorRequest ) throws Exception{
		return new ResponseEntity<List<ApiResponse>>(apiService.criarListaApi(workspace_id, autenticadorRequest.getUsuario(), autenticadorRequest.getSenha()),HttpStatus.OK);
		
	}
	
	@PutMapping("/{api}")
	public ResponseEntity<JsonElement> atualizarApi (
			@PathVariable("api") Long api, 
			@RequestBody ApiRequest request){
		try {
			return new ResponseEntity<JsonElement>(apiService.atualizarApi(api, request).toJSON(),HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<JsonElement>(new Gson().toJsonTree(e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	@DeleteMapping("/{api_id}")
	public ResponseEntity<JsonElement> deletarApi(
			@PathVariable("api_id") Long api_id,
			@RequestBody AutenticadorRequest autenticadorRequest ){
		try {
			apiService.deletarApi(api_id, autenticadorRequest.getUsuario(), autenticadorRequest.getSenha());
			return new ResponseEntity<JsonElement>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<JsonElement>(new Gson().toJsonTree(e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
}
