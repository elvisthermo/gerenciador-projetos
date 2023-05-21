package com.GerenciadorProjetos.Gerenciador.dto.request;

import com.GerenciadorProjetos.Gerenciador.enums.ClassificacaoRisco;
import com.GerenciadorProjetos.Gerenciador.enums.Status;
import com.GerenciadorProjetos.Gerenciador.model.Pessoa;
import com.GerenciadorProjetos.Gerenciador.model.Projeto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.Date;

@Data

@Schema(description = "Objeto de requisição para criar um novo projeto")
public class CreateMembroRequest {

    @Schema(description = "Id do projeto", required = true)
    @NotBlank(message = "O ID do projeto é obrigatório.")
    private Long idProjeto;

    @Schema(description = "Id da pessoa", required = true)
    @NotBlank(message = "O  ID da pessoa do projeto é obrigatório.")
    private Long idPessoa;

}
