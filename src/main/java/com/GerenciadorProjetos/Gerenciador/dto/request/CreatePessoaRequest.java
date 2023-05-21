package com.GerenciadorProjetos.Gerenciador.dto.request;

import com.GerenciadorProjetos.Gerenciador.enums.ClassificacaoRisco;
import com.GerenciadorProjetos.Gerenciador.enums.Status;
import com.GerenciadorProjetos.Gerenciador.model.Membro;
import com.GerenciadorProjetos.Gerenciador.model.Projeto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data

@Schema(description = "Objeto de requisição para criar uma nova pessoa")
public class CreatePessoaRequest {

    @Schema(description = "Nome da Pessoa", required = true)
    @NotBlank(message = "O nome do Pessoa é obrigatório.")
    private String nome;

    @Schema(description = "CPF da Pessoa", required = true)
    @NotBlank(message = "O nome do Pessoa é obrigatório.")
    private String cpf;

    @Schema(description = "campo para indicar se e funcionario ou não funcionario", required = true)
    @NotNull(message = "O campo funcionário é obrigatório.")
    private Boolean funcionario;

}