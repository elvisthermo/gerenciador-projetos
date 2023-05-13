package com.gerenciador.projetos.controller;

import com.gerenciador.projetos.enums.ClassificacaoRisco;
import com.gerenciador.projetos.model.Projeto;
import com.gerenciador.projetos.enums.Status;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/projetos")
public class ProjetosController {

    @GetMapping
    public List<Projeto> listarProjetos() {
        Projeto projetoTeste = new Projeto();
        projetoTeste.setId(1L);
        projetoTeste.setNome("Projeto de Teste");
        projetoTeste.setDataDeInicio(new Date());
        projetoTeste.setGerenteResponsavel("Gerente de Teste");
        projetoTeste.setPrevisaoDeTermino(new Date());
        projetoTeste.setDataRealDeTermino(new Date());
        projetoTeste.setOrcamentoTotal(10000);
        projetoTeste.setDescricao("Este é um projeto de teste.");
        projetoTeste.setStatus(Status.INICIADO);
        projetoTeste.setClassificacao(ClassificacaoRisco.BAIXO_RISCO);

        return Arrays.asList(projetoTeste);
    }

    @PostMapping
    public Projeto criarProjeto(){
       return new Projeto();
    }
    @DeleteMapping
    public void deletarProjeto(){
    }

    @PutMapping
    public Projeto atualizarProjeto(){
        return  new Projeto();
    }


}
