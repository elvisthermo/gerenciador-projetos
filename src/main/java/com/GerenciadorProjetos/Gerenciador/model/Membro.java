package com.GerenciadorProjetos.Gerenciador.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="membros")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Membro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idprojeto")
    private Projeto projeto;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idpessoa")
    private Pessoa pessoa;

}
