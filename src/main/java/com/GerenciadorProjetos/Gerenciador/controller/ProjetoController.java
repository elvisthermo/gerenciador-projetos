package com.GerenciadorProjetos.Gerenciador.controller;

import com.GerenciadorProjetos.Gerenciador.dto.ProjetoComMembrosEGerenteDTO;
import com.GerenciadorProjetos.Gerenciador.dto.request.CreateProjetoRequest;
import com.GerenciadorProjetos.Gerenciador.model.Membro;
import com.GerenciadorProjetos.Gerenciador.model.Pessoa;
import com.GerenciadorProjetos.Gerenciador.model.Projeto;
import com.GerenciadorProjetos.Gerenciador.service.ProjetoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@Tag(name = "Projetos", description = "Operações relacionadas a projetos")
@RestController
@RequestMapping("api/projetos")
@Validated
public class ProjetoController {

    private final ProjetoService projetoService;
    private final ModelMapper modelMapper;

    public ProjetoController(ProjetoService projetoService, ModelMapper modelMapper) {
        this.projetoService = projetoService;
        this.modelMapper = modelMapper;
    }

    @Operation(summary = "Obter todos os projetos")
    @GetMapping
    public ResponseEntity<List<ProjetoComMembrosEGerenteDTO>> getAllProjetos() {
        List<ProjetoComMembrosEGerenteDTO> projetos = projetoService.findAll();

        return ResponseEntity.ok(projetos);
    }

    @Operation(summary = "Obter projeto por ID",
            description = "Forneça um ID para procurar um projeto específico")
    @GetMapping("/{id}")
    public ResponseEntity<ProjetoComMembrosEGerenteDTO> getProjetoById(@Parameter(description = "ID do projeto para recuperar") @PathVariable Long id) {
        ProjetoComMembrosEGerenteDTO projetos = projetoService.findById(id);

        return ResponseEntity.ok(projetos);
    }

    @Operation(summary = "Criar novo projeto")
    @PostMapping
    public ResponseEntity<Projeto> createProjeto(@Parameter(description = "Projeto para criar") @Valid @RequestBody CreateProjetoRequest projeto) {
        Projeto projetoData = this.modelMapper.map(projeto, Projeto.class);

        return new ResponseEntity<>(projetoService.save(projetoData,projeto.getIdgerente()), HttpStatus.CREATED);
    }

//    @Operation(summary = "Atualizar um projeto existente",
//            description = "Forneça um ID e os detalhes do projeto para atualizar um projeto existente")
//    @PutMapping("/{id}")
//    public ResponseEntity<Projeto> updateProjeto(@Parameter(description = "ID do projeto para atualizar") @PathVariable Long id, @RequestBody Projeto projeto) {
////        return projetoService.findById(id)
////                .map(projetoObj -> {
////                    projeto.setId(id);
////                    return
////                            new ResponseEntity<>(projetoService.save(projeto), HttpStatus.OK);
////                })
////                .orElseGet(() -> ResponseEntity.notFound().build());
//    }

//    @Operation(summary = "Excluir um projeto",
//            description = "Forneça um ID para excluir um projeto")
//    @DeleteMapping("/{id}")
//    public HttpEntity<?> deleteProjeto(@Parameter(description = "ID do projeto para excluir") @PathVariable Long id) {
////        return projetoService.findById(id)
////                .map(projeto -> {
////                    return projetoService.deleteById(projeto);
////                })
////                .orElseGet(() -> ResponseEntity.notFound().build());
//    }

    @GetMapping("/{projetoId}/gerente")
    public ResponseEntity<Pessoa> findGerenteByProjetoId(@PathVariable Long projetoId) {
        Pessoa gerente = projetoService.findGerenteByProjetoId(projetoId);
        return ResponseEntity.ok(gerente);
    }

    @GetMapping("/{projetoId}/membros")
    public ResponseEntity<Set<Membro>> findMembrosByProjetoId(@PathVariable Long projetoId) {
        Set<Membro> membros = projetoService.findMembrosByProjetoId(projetoId);
        return ResponseEntity.ok(membros);
    }
}
