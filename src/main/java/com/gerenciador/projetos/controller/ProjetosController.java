package com.gerenciador.projetos.controller;

import com.gerenciador.projetos.model.Projeto;
import com.gerenciador.projetos.service.ProjetoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "Gerenciamento de Projetos", description = "Operações para gerenciar projetos")
@Controller
@RequestMapping("/api/projetos")
public class ProjetosController {

    private final ProjetoService projetoService;

    @Autowired
    public ProjetosController(ProjetoService projetoService) {
        this.projetoService = projetoService;
    }

    @ApiOperation(value = "Ver todos os projetos", response = List.class)
    @GetMapping("/")
    public ResponseEntity<List<Projeto>> getAllProjetos() {
        return ResponseEntity.ok(projetoService.findAll());
    }

    @ApiOperation(value = "Buscar um projeto por ID", response = Projeto.class)
    @GetMapping("/{id}")
    public ResponseEntity<Projeto> getProjetoById(@PathVariable Long id) {
        return projetoService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @ApiOperation(value = "Criar um novo projeto", response = Projeto.class)
    @PostMapping
    public ResponseEntity<Projeto> createProjeto(@RequestBody Projeto projeto) {
        return new ResponseEntity<>(projetoService.save(projeto), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Atualizar um projeto existente", response = Projeto.class)
    @PutMapping("/{id}")
    public ResponseEntity<Projeto> updateProjeto(@PathVariable Long id, @RequestBody Projeto projeto) {
        return projetoService.findById(id)
                .map(projetoObj -> {
                    projeto.setId(id);
                    return
                            new ResponseEntity<>(projetoService.save(projeto), HttpStatus.OK);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @ApiOperation(value = "Deletar um projeto")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProjeto(@PathVariable Long id) {
        return projetoService.findById(id)
                .map(projeto -> {
                    projetoService.deleteById(id);
                    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


}
