package com.algaworks.algamoneyapi.api.resource;

import com.algaworks.algamoneyapi.api.event.RecursoCriadoEvent;
import com.algaworks.algamoneyapi.api.model.Lancamento;
import com.algaworks.algamoneyapi.api.repository.LancamentoRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoResource {

    private LancamentoRepository repository;
    private ApplicationEventPublisher publisher;

    public LancamentoResource(LancamentoRepository repository, ApplicationEventPublisher publisher) {
        this.repository = repository;
        this.publisher = publisher;
    }

    @GetMapping
    public List<Lancamento> listar() {
        return this.repository.findAll();
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Lancamento> buscar(@PathVariable Long codigo){
        return this.repository.findById(codigo)
                .map(record -> ResponseEntity.ok(record))
                .orElseThrow(() -> new EmptyResultDataAccessException(1));
    }

    @PostMapping
    public ResponseEntity<Lancamento> criar(@RequestBody @Valid Lancamento lancamento, HttpServletResponse response) {
        Lancamento novoLancamento = this.repository.save(lancamento);

        publisher.publishEvent(new RecursoCriadoEvent(this, response, novoLancamento.getCodigo()));

        return ResponseEntity.status(HttpStatus.CREATED).body(novoLancamento);
    }
}
