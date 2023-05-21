package com.GerenciadorProjetos.Gerenciador.repository;

import com.GerenciadorProjetos.Gerenciador.model.Membro;
import com.GerenciadorProjetos.Gerenciador.model.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MembroRepository extends JpaRepository<Membro, Long> {
    List<Membro> findByProjetoId(Long idProjeto);

}