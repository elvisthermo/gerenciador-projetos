package com.gerenciador.projetos.model;

import com.gerenciador.projetos.enums.ClassificacaoRisco;
import com.gerenciador.projetos.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

//@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="projeto")
public class Projeto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 200)
    private String nome;
    @Column(name = "data_inicio")
    private Date dataDeInicio;
    @Column(name = "data_previsao_fim")
    private Date previsaoDeTermino;
    @Column(name = "data_fim")
    private Date dataRealDeTermino;
    @Column(name = "idgerente", nullable = false)
    private Long gerenteResponsavel;
    @Column(name = "orcamento")
    private double orcamentoTotal;
    @Column(name = "descricao", length = 5000)
    private String descricao;
    @Column(name = "status", length = 45)
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "risco", length = 45)
    @Enumerated(EnumType.STRING)
    private ClassificacaoRisco classificacao;

    private Membro[] membros;
}
