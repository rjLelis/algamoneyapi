package com.algaworks.algamoneyapi.api.resource;


import com.algaworks.algamoneyapi.api.event.RecursoCriadoEvent;
import com.algaworks.algamoneyapi.api.model.Categoria;
import com.algaworks.algamoneyapi.api.repository.CategoriaRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/categorias")
public class CategoriaResource {

    private CategoriaRepository repository;
    private ApplicationEventPublisher publisher;

    CategoriaResource(CategoriaRepository categoriaRepository, ApplicationEventPublisher publisher) {
        this.repository = categoriaRepository;
        this.publisher = publisher;
    }

    @GetMapping
    public List<Categoria> list() {
        return this.repository.findAll()
                .stream()
                .sorted(Comparator.comparing(Categoria::getCodigo))
                .collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<Categoria> create(@Valid @RequestBody Categoria categoria, HttpServletResponse response) {
        Categoria novaCategoria =  this.repository.save(categoria);

        publisher.publishEvent(new RecursoCriadoEvent(this, response, novaCategoria.getCodigo()));

        return ResponseEntity.status(HttpStatus.CREATED).body(novaCategoria);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Categoria> retrieve(@PathVariable Long codigo) {
        return this.repository.findById(codigo)
                .map(record -> ResponseEntity.ok(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<Categoria> update(@PathVariable Long codigo, @RequestBody Categoria categoria) {
        return this.repository.findById(codigo)
            .map(record ->{
                record.setNome(categoria.getNome());
                Categoria updated = this.repository.save(record);
                return ResponseEntity.ok(updated);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<?> delete(@PathVariable Long codigo) {
        return this.repository.findById(codigo).map(record -> {
            this.repository.deleteById(codigo);
            return ResponseEntity.noContent().build();
        }).orElse(ResponseEntity.notFound().build());
    }
}
