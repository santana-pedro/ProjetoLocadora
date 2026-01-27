package br.edu.ifba.inf008.app.model;

import java.math.BigDecimal;

public class Veiculo {
    private int id;
    private String placa;
    private String marca;
    private String modelo;
    private int ano;
    private int tipoId;
    private String cor;
    private String combustivel;
    private String cambio;
    private BigDecimal quilometragem;
    private String status;
    private String localizacao;

    // Construtor completo
    public Veiculo(int id, String placa, String marca, String modelo, int ano, int tipoId, String cor, String combustivel, String cambio, BigDecimal quilometragem, String status, String localizacao) {
        this.id = id;
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.tipoId = tipoId;
        this.cor = cor;
        this.combustivel = combustivel;
        this.cambio = cambio;
        this.quilometragem = quilometragem;
        this.status = status;
        this.localizacao = localizacao;
    }

    // Getters (Essenciais para a Tabela do JavaFX funcionar)
    public int getId() { return id; }
    public String getPlaca() { return placa; }
    public String getMarca() { return marca; }
    public String getModelo() { return modelo; }
    public int getAno() { return ano; }
    public int getTipoId() { return tipoId; }
    public String getCor() { return cor; }
    public String getCombustivel() { return combustivel; }
    public String getCambio() { return cambio; }
    public BigDecimal getQuilometragem() { return quilometragem; }
    public String getStatus() { return status; }
    public String getLocalizacao() { return localizacao; }

    @Override
    public String toString() {
        return marca + " " + modelo + " (" + placa + ")";
    }
}