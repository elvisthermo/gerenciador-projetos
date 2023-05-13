package com.gerenciador.projetos.model;

import com.gerenciador.projetos.enums.ClassificacaoRisco;
import com.gerenciador.projetos.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//import javax.persistence.*;
import java.util.Date;

//@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Projeto {
    private Long id;
    private String nome;
    private Date dataDeInicio;
    private String gerenteResponsavel;
    private Date previsaoDeTermino;
    private Date dataRealDeTermino;
    private double orcamentoTotal;
    private String descricao;
//    @Enumerated(EnumType.STRING)
    private Status status;
//    @Enumerated(EnumType.STRING)
    private ClassificacaoRisco classificacao;

    private Membro[] membros;
}
