package com.GerenciadorProjetos.Gerenciador.service;

import com.GerenciadorProjetos.Gerenciador.dto.ProjetoComMembrosEGerenteDTO;
import com.GerenciadorProjetos.Gerenciador.model.Membro;
import com.GerenciadorProjetos.Gerenciador.model.Pessoa;
import com.GerenciadorProjetos.Gerenciador.model.Projeto;
import com.GerenciadorProjetos.Gerenciador.repository.MembroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class MembroService {

    private final ProjetoService projetoService;

    private final PessoaService pessoaService;
    private final MembroRepository membroRepository;

    @Autowired
    public MembroService(ProjetoService projetoService, PessoaService pessoaService, MembroRepository projetoRepository) {
        this.projetoService = projetoService;
        this.pessoaService = pessoaService;
        this.membroRepository = projetoRepository;
    }

    public List<Membro> findAll() {
        return membroRepository.findAll();
    }

    public Optional<Membro> findById(Long id) {
        return membroRepository.findById(id);
    }

    public Membro save(Long idProjeto, Long idPessoa) {

//        Optional<ProjetoComMembrosEGerenteDTO> projeto = this.projetoService.findById(idProjeto);

        Optional<Pessoa> pessoa = this.pessoaService.findById(idPessoa);

        Membro novoMembro = new Membro();
//        novoMembro.setProjeto(projeto.get());
        novoMembro.setPessoa(pessoa.get());
        System.out.println(novoMembro);

        return membroRepository.save(novoMembro);
    }

    public List<Membro> buscarMembrosPorIdProjeto(Long idProjeto) {
//        Projeto projeto = this.projetoService.findById(idProjeto)
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Projeto não encontrado"));
        return membroRepository.findByProjetoId(idProjeto);
    }

    public Membro save(Membro membro) {
        return membroRepository.save(membro);
    }

    public void deleteById(Long id) {
        membroRepository.deleteById(id);
    }
}
