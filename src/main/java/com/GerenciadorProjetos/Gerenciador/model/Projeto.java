package com.GerenciadorProjetos.Gerenciador.model;

import com.GerenciadorProjetos.Gerenciador.dto.ProjetoComMembrosEGerenteDTO;
import com.GerenciadorProjetos.Gerenciador.enums.ClassificacaoRisco;
import com.GerenciadorProjetos.Gerenciador.enums.Status;
import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")

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

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idgerente", nullable = false)
    private Pessoa gerente;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "membros",
            joinColumns = @JoinColumn(name = "idprojeto"),
            inverseJoinColumns = @JoinColumn(name = "idpessoa")
    )
    private Set<Membro> membros;


    @Transient
    private ProjetoComMembrosEGerenteDTO projetoComMembrosEGerenteDTO;

    @Transient
    public ProjetoComMembrosEGerenteDTO getProjetoComMembrosEGerente() {
        ProjetoComMembrosEGerenteDTO dto = new ProjetoComMembrosEGerenteDTO();
        dto.setId(id);
        dto.setNome(nome);
        dto.setDataDeInicio(dataDeInicio);
        dto.setPrevisaoDeTermino(previsaoDeTermino);
        dto.setDataRealDeTermino(dataRealDeTermino);
        dto.setOrcamentoTotal(orcamentoTotal);
        dto.setDescricao(descricao);
        dto.setStatus(status);
        dto.setClassificacao(classificacao);
        dto.setGerente(gerente);
        dto.setMembros(getMembros());
        return dto;
    }
}
