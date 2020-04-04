package com.algaworks.algamoneyapi.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import javax.persistence.Embeddable;

@Embeddable
public class Endereco {

    @JsonInclude(Include.NON_NULL)
    private String logradouro;

    @JsonInclude(Include.NON_NULL)
    private String numero;

    @JsonInclude(Include.NON_NULL)
    private String complemento;

    @JsonInclude(Include.NON_NULL)
    private String bairro;

    @JsonInclude(Include.NON_NULL)
    private String cep;

    @JsonInclude(Include.NON_NULL)
    private String cidade;

    @JsonInclude(Include.NON_NULL)
    private String estado;

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
