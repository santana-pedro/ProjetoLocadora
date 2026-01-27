package br.edu.ifba.inf008.app.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Cliente {
    private int id;
    private String tipo;
    private String nome;
    private String sobrenome;
    private String razaoSocial;
    private String taxId;
    private String email;
    private String telefone;
    private String endereco;
    private Timestamp dataRegistro;
    private int pontosFidelidade;
    private BigDecimal taxaDesconto;

    // Construtor com TUDO
    public Cliente(int id, String tipo, String nome, String sobrenome, String razaoSocial,
                   String taxId, String email, String telefone, String endereco,
                   Timestamp dataRegistro, int pontosFidelidade, BigDecimal taxaDesconto) {
        this.id = id;
        this.tipo = tipo;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.razaoSocial = razaoSocial;
        this.taxId = taxId;
        this.email = email;
        this.telefone = telefone;
        this.endereco = endereco;
        this.dataRegistro = dataRegistro;
        this.pontosFidelidade = pontosFidelidade;
        this.taxaDesconto = taxaDesconto;
    }

    // Getters
    public int getId() { return id; }
    public String getTipo() { return tipo; }
    public String getNome() { return nome; }
    public String getSobrenome() { return sobrenome; }
    public String getRazaoSocial() { return razaoSocial; }
    public String getTaxId() { return taxId; }
    public String getEmail() { return email; }
    public String getTelefone() { return telefone; }
    public String getEndereco() { return endereco; }
    public Timestamp getDataRegistro() { return dataRegistro; }
    public int getPontosFidelidade() { return pontosFidelidade; }
    public BigDecimal getTaxaDesconto() { return taxaDesconto; }

    // O toString define o que aparece no ComboBox.
    // Vamos mostrar "Nome Sobrenome (Email)" ou "Razão Social (Email)"
    @Override
    public String toString() {
        String identificacao = (nome != null) ? (nome + " " + sobrenome) : razaoSocial;
        if (identificacao == null) identificacao = "Cliente Desconhecido";
        return identificacao + " (" + email + ")";
    }
}