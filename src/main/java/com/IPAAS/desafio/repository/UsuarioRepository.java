package com.IPAAS.desafio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.IPAAS.desafio.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,String> {

}
