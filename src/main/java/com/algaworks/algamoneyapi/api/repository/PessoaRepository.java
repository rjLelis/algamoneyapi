package com.algaworks.algamoneyapi.api.repository;

import com.algaworks.algamoneyapi.api.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

}