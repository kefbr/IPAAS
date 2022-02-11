package com.IPAAS.desafio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.IPAAS.desafio.model.Api;

@Repository
public interface ApiRepository extends JpaRepository<Api,Long>  {

}
