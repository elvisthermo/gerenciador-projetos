package com.GerenciadorProjetos.Gerenciador.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;


@JsonIgnoreProperties({"projetosParticipados", "projetosGerenciados", "hibernateLazyInitializer"})
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Entity
@Table(name="pessoa")

public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", length = 200, nullable = false)
    private String nome;

    @Column(name = "cpf", length =  14)
    private String cpf;

    @Column(name = "funcionario")
    private Boolean funcionario;

    @OneToMany(mappedBy = "gerente", fetch = FetchType.LAZY)
    private Set<Projeto> projetosGerenciados;

    @ManyToMany(mappedBy = "membros", fetch = FetchType.LAZY)
    private Set<Projeto> projetosParticipados;

}
