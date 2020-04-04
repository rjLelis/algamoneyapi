package com.algaworks.algamoneyapi.api.service;

import com.algaworks.algamoneyapi.api.model.Pessoa;
import com.algaworks.algamoneyapi.api.repository.PessoaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PessoaService {

    private PessoaRepository repository;

    public PessoaService(PessoaRepository repository) {
        this.repository = repository;
    }

    public Pessoa atualizar(Pessoa pessoa, Long id) {
        Pessoa updated = getPessoaById(id);

        BeanUtils.copyProperties(pessoa, updated, "id");
        return this.repository.save(updated);
    }

    public void atualizarAtivo(Long id, Boolean ativo) {
        Pessoa pessoa = getPessoaById(id);
        pessoa.setAtivo(ativo);
        this.repository.save(pessoa);
    }

    public Pessoa getPessoaById(Long id) {
        Optional<Pessoa> pessoa = this.repository.findById(id);

        if (pessoa.isPresent()) {
            return pessoa.get();
        }

        throw new EmptyResultDataAccessException(1);
    }

}
