package com.algaworks.algamoneyapi.api.resource;

import com.algaworks.algamoneyapi.api.event.RecursoCriadoEvent;
import com.algaworks.algamoneyapi.api.model.Pessoa;
import com.algaworks.algamoneyapi.api.repository.PessoaRepository;
import com.algaworks.algamoneyapi.api.service.PessoaService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pessoas")
public class PessoaResource {

    private PessoaService pessoaService;
    private PessoaRepository pessoaRepository;
    private ApplicationEventPublisher publisher;

    public PessoaResource(PessoaService pessoaService, PessoaRepository pessoaRepository, ApplicationEventPublisher publisher) {
        this.pessoaService = pessoaService;
        this.pessoaRepository = pessoaRepository;
        this.publisher = publisher;
    }

    @GetMapping
    public List<Pessoa> listar() {
        return this.pessoaRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(Pessoa::getId))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> porNome(@PathVariable Long id) {
        return this.pessoaRepository.findById(id)
                .map(record -> ResponseEntity.ok(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Pessoa> criar(@RequestBody @Valid Pessoa pessoa, HttpServletResponse response) {
        Pessoa novaPessoa = this.pessoaRepository.save(pessoa);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, novaPessoa.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(novaPessoa);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        return this.pessoaRepository.findById(id)
                .map(pessoa -> {
                    this.pessoaRepository.deleteById(id);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pessoa> atualizar(@PathVariable Long id, @Valid @RequestBody Pessoa pessoa) {
        Pessoa updated = this.pessoaService.atualizar(pessoa, id);
        return ResponseEntity.ok(updated);
    }

    @PutMapping("/{id}/ativo")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizarAtivo(@PathVariable Long id, @RequestBody Boolean ativo) {
        this.pessoaService.atualizarAtivo(id, ativo);

    }
}
