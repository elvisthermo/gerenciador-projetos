package com.GerenciadorProjetos.Gerenciador.repository;

import com.GerenciadorProjetos.Gerenciador.model.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProjetoRepository extends JpaRepository<Projeto, Long> {


}