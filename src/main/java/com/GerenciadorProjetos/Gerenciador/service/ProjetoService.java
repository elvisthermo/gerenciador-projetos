package com.GerenciadorProjetos.Gerenciador.service;

import com.GerenciadorProjetos.Gerenciador.dto.ProjetoComMembrosEGerenteDTO;
import com.GerenciadorProjetos.Gerenciador.enums.Status;
import com.GerenciadorProjetos.Gerenciador.model.Membro;
import com.GerenciadorProjetos.Gerenciador.model.Pessoa;
import com.GerenciadorProjetos.Gerenciador.model.Projeto;
import com.GerenciadorProjetos.Gerenciador.repository.ProjetoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProjetoService {
    private final ProjetoRepository projetoRepository;

    private final PessoaService pessoaService;


    @Autowired
    public ProjetoService(ProjetoRepository projetoRepository, PessoaService pessoaService) {
        this.projetoRepository = projetoRepository;
        this.pessoaService = pessoaService;
    }

    @Transactional
    public List<ProjetoComMembrosEGerenteDTO> findAll() {
        List<Projeto> projetos = projetoRepository.findAll();
        return projetos.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public ProjetoComMembrosEGerenteDTO findById(Long id) {
        Optional<Projeto> optionalProjeto = projetoRepository.findById(id);
        if (optionalProjeto.isPresent()) {
            Projeto projeto = optionalProjeto.get();
            projeto.getMembros().size(); // Carrega os membros antecipadamente
            ProjetoComMembrosEGerenteDTO dto = convertToDTO(projeto);
            return dto;
        }
        return null;
    }


    private ProjetoComMembrosEGerenteDTO convertToDTO(Projeto projeto) {
        ProjetoComMembrosEGerenteDTO dto = new ProjetoComMembrosEGerenteDTO();
        dto.setId(projeto.getId());
        dto.setNome(projeto.getNome());
        dto.setDataDeInicio(projeto.getDataDeInicio());
        dto.setPrevisaoDeTermino(projeto.getPrevisaoDeTermino());
        dto.setDataRealDeTermino(projeto.getDataRealDeTermino());
        dto.setOrcamentoTotal(projeto.getOrcamentoTotal());
        dto.setDescricao(projeto.getDescricao());
        dto.setStatus(projeto.getStatus());
        dto.setClassificacao(projeto.getClassificacao());
        dto.setGerente(projeto.getGerente());
        dto.setMembros(projeto.getMembros());
        return dto;
    }
//    @Transactional
//    public List<Projeto> findAll() {
//        return projetoRepository.findAll();
//    }
//
//    public Optional<Projeto> findById(Long id) {
//        return projetoRepository.findById(id);
//    }

    public Projeto save(Projeto projeto) {
        return projetoRepository.save(projeto);
    }

//    @Transient
//    public Optional<Projeto> buscarProjetoCompleto(Long id) {
//        return projetoRepository.findAllWithMembrosAndGerente();
//    }

    public Projeto save(Projeto projeto,Long idGerente) {
        Optional<Pessoa> novoGerente = this.pessoaService.findById(idGerente);
        projeto.setGerente(novoGerente.get());
        return projetoRepository.save(projeto);
    }

    public HttpEntity<? extends Object> deleteById(ProjetoComMembrosEGerenteDTO projeto) {
        if(projeto.getStatus() == Status.INICIADO || projeto.getStatus() == Status.EM_ANDAMENTO || projeto.getStatus() == Status.ENCERRADO){
            String errorMessage = "Não é possível excluir o projeto com status " + projeto.getStatus();
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(errorMessage);

        }
        projetoRepository.deleteById(projeto.getId());
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }


    @Transactional
    public Pessoa findGerenteByProjetoId(Long projetoId) {
        Projeto projeto = projetoRepository.findById(projetoId)
                .orElseThrow(() -> new NoSuchElementException("Projeto não encontrado"));
        return projeto.getGerente();
    }

    @Transactional
    public Set<Membro> findMembrosByProjetoId(Long projetoId) {
        Projeto projeto = projetoRepository.findById(projetoId)
                .orElseThrow(() -> new NoSuchElementException("Projeto não encontrado"));
        return projeto.getMembros();
    }
}
