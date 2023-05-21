package com.GerenciadorProjetos.Gerenciador.controller;

import com.GerenciadorProjetos.Gerenciador.dto.request.CreatePessoaRequest;
import com.GerenciadorProjetos.Gerenciador.model.Pessoa;
import com.GerenciadorProjetos.Gerenciador.service.PessoaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Pessoas", description = "Operações com pessoas")
@RestController
@RequestMapping("api/pessoas")
public class PessoaController {

    private final PessoaService pessoaService;
    private final ModelMapper modelMapper;

    public PessoaController(PessoaService pessoaService, ModelMapper modelMapper) {
        this.pessoaService = pessoaService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public ResponseEntity<List<Pessoa>> getAllPessoas() {
        return ResponseEntity.ok(pessoaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> getPessoaById(@PathVariable Long id) {
        return pessoaService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Pessoa> createPessoa(@Validated @RequestBody CreatePessoaRequest pessoa) {
        Pessoa novaPessoa = this.modelMapper.map(pessoa, Pessoa.class);
        return new ResponseEntity<>(pessoaService.save(novaPessoa), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pessoa> updatePessoa(@PathVariable Long id, @RequestBody Pessoa pessoa) {
        return pessoaService.findById(id)
                .map(pessoaObj -> {
                    pessoa.setId(id);
                    return new ResponseEntity<>(pessoaService.save(pessoa), HttpStatus.OK);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePessoa(@PathVariable Long id) {
        return pessoaService.findById(id)
                .map(pessoa -> {
                    pessoaService.deleteById(id);
                    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
