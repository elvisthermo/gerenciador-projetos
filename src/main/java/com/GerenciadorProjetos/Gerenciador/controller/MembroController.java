package com.GerenciadorProjetos.Gerenciador.controller;

import com.GerenciadorProjetos.Gerenciador.dto.request.CreateMembroRequest;
import com.GerenciadorProjetos.Gerenciador.model.Membro;
import com.GerenciadorProjetos.Gerenciador.model.Pessoa;
import com.GerenciadorProjetos.Gerenciador.model.Projeto;
import com.GerenciadorProjetos.Gerenciador.service.MembroService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Tag(name = "Membros", description = "Operações com membros")
@RestController
@RequestMapping("api/membros")
public class MembroController {

    private final ModelMapper modelMapper;

    private final MembroService membroService;


    @Autowired
    public MembroController(ModelMapper modelMapper, MembroService membroService) {
        this.modelMapper = modelMapper;
        this.membroService = membroService;
    }

    @GetMapping
    public ResponseEntity<List<Membro>> getAllMembros() {
        return ResponseEntity.ok(membroService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Membro> getMembroById(@PathVariable Long id) {
        return membroService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Membro> createMembro(@Valid @RequestBody CreateMembroRequest membro) {

        return new ResponseEntity<>(membroService.save(membro.getIdProjeto(),membro.getIdPessoa()), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Membro> updateMembro(@PathVariable Long id, @RequestBody Membro membro) {
        return membroService.findById(id)
                .map(membroObj -> {
                    membro.setId(id);
                    return new ResponseEntity<>(membroService.save(membro), HttpStatus.OK);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/membros")
    public ResponseEntity<List<Membro>> getMembrosPorProjeto(@PathVariable Long id) {

        List<Membro> membros = this.membroService.buscarMembrosPorIdProjeto(id);
        return ResponseEntity.ok(membros);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMembro(@PathVariable Long id) {
        return membroService.findById(id)
                .map(membro -> {
                    membroService.deleteById(id);
                    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
