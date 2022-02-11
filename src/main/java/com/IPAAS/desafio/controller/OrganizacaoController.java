package com.IPAAS.desafio.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.IPAAS.desafio.controller.dto.OrganizacaoRequest;
import com.IPAAS.desafio.controller.dto.OrganizacaoResponse;
import com.IPAAS.desafio.service.OrganizacaoService;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

@RestController
@RequestMapping("/organizacao")
public class OrganizacaoController {
	
	private OrganizacaoService organizacaoService;

	public OrganizacaoController(OrganizacaoService organizacaoService) {
		this.organizacaoService = organizacaoService;
	}

	@PostMapping("/")
	public ResponseEntity<JsonElement> criarOrganizacao(@RequestBody OrganizacaoRequest request){
		try {
			return new ResponseEntity<JsonElement>(new OrganizacaoResponse(organizacaoService.criarOrganizacao(request)).toJSON(), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<JsonElement>(new Gson().toJsonTree(e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	@GetMapping("/listar")
	public List<OrganizacaoResponse> listaOrganizacao(){
		return organizacaoService.criarListaOrganizacao().stream().map((o) -> new OrganizacaoResponse(o)).collect(Collectors.toList());
	}
}
