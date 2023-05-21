package com.GerenciadorProjetos.Gerenciador.repository;

import com.GerenciadorProjetos.Gerenciador.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

}