package com.GerenciadorProjetos.Gerenciador.dto.request;

import com.GerenciadorProjetos.Gerenciador.enums.ClassificacaoRisco;
import com.GerenciadorProjetos.Gerenciador.enums.Status;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.Date;
@Data

@Schema(description = "Objeto de requisição para criar um novo projeto")
public class CreateProjetoRequest {

    @Schema(description = "Nome do projeto", required = true)
    @NotBlank(message = "O nome do projeto é obrigatório.")
    private String nome;

    @Schema(description = "Descrição do projeto")
    private String descricao;


    @Schema(description = "Data de início do projeto", required = true, example = "2023-01-01")
    @NotNull(message = "A data de início é obrigatória.")
    @FutureOrPresent(message = "A data de início deve ser no presente ou no futuro.")
    private Date dataDeInicio;

    @Schema(description = "Previsão de término do projeto", required = true, example = "2023-12-31")
    @NotNull(message = "A previsão de término é obrigatória.")
    @Future(message = "A previsão de término deve ser no futuro.")
    private Date previsaoDeTermino;

    @Schema(description = "Data real de término do projeto", example = "2023-12-31")
    private Date dataRealDeTermino;

    @Schema(description = "ID do gerente responsável pelo projeto", required = true)
    @NotNull(message = "O gerente responsavel é obrigatório.")
    private Long idgerente;

    @Schema(description = "Orçamento total do projeto", required = true, example = "50000")
    @PositiveOrZero(message = "O orçamento total deve ser um valor positivo ou zero.")
    private double orcamentoTotal;

    @Schema(description = "Status do projeto")
    private Status status;

    @Schema(description = "Classificação de risco do projeto")
    private ClassificacaoRisco classificacao;

}
