package com.IPAAS.desafio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.IPAAS.desafio.model.Organizacao;

@Repository
public interface OrganizacaoRepository extends JpaRepository<Organizacao,Long> {

}
