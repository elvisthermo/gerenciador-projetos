package com.GerenciadorProjetos.Gerenciador.dto;

import com.GerenciadorProjetos.Gerenciador.enums.ClassificacaoRisco;
import com.GerenciadorProjetos.Gerenciador.enums.Status;
import com.GerenciadorProjetos.Gerenciador.model.Membro;
import com.GerenciadorProjetos.Gerenciador.model.Pessoa;
import lombok.Data;

import java.util.Date;
import java.util.Set;

@Data
public class ProjetoComMembrosEGerenteDTO {
        private Long id;
        private String nome;
        private Date dataDeInicio;
        private Date previsaoDeTermino;
        private Date dataRealDeTermino;
        private double orcamentoTotal;
        private String descricao;
        private Status status;
        private ClassificacaoRisco classificacao;
        private Pessoa gerente;
        private Set<Membro> membros;


}
