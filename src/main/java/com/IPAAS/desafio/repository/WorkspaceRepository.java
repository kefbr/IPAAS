package com.IPAAS.desafio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.IPAAS.desafio.model.Workspace;

@Repository
public interface WorkspaceRepository extends JpaRepository<Workspace,Long> {

}
