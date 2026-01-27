package br.edu.ifba.inf008.app.model;

import java.math.BigDecimal;

public class TipoVeiculo{
    private int id;
    private String nome;                // type_name
    private BigDecimal tarifaDiaria;    // daily_rate
    private BigDecimal tarifaSemanal;   // weekly_rate
    private BigDecimal tarifaMensal;    // monthly_rate
    private BigDecimal depositoSeguranca; // security_deposit
    private BigDecimal taxaSeguro;      // insurance_rate
    private BigDecimal multaAtrasoHora; // late_fee_per_hour
    private int maxPassageiros;         // max_passengers
    private int maxBagagem;             // max_luggage
    private String caracteristicas;     // special_features
    private String taxasAdicionais;     // additional_fees (JSON salvo como String)

    public TipoVeiculo(int id, String nome, BigDecimal tarifaDiaria, BigDecimal tarifaSemanal,
                       BigDecimal tarifaMensal, BigDecimal depositoSeguranca, BigDecimal taxaSeguro,
                       BigDecimal multaAtrasoHora, int maxPassageiros, int maxBagagem,
                       String caracteristicas, String taxasAdicionais) {
        this.id = id;
        this.nome = nome;
        this.tarifaDiaria = tarifaDiaria;
        this.tarifaSemanal = tarifaSemanal;
        this.tarifaMensal = tarifaMensal;
        this.depositoSeguranca = depositoSeguranca;
        this.taxaSeguro = taxaSeguro;
        this.multaAtrasoHora = multaAtrasoHora;
        this.maxPassageiros = maxPassageiros;
        this.maxBagagem = maxBagagem;
        this.caracteristicas = caracteristicas;
        this.taxasAdicionais = taxasAdicionais;
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public BigDecimal getTarifaDiaria() { return tarifaDiaria; }
    public BigDecimal getTarifaSemanal() { return tarifaSemanal; }
    public BigDecimal getTarifaMensal() { return tarifaMensal; }
    public BigDecimal getDepositoSeguranca() { return depositoSeguranca; }
    public BigDecimal getTaxaSeguro() { return taxaSeguro; }
    public BigDecimal getMultaAtrasoHora() { return multaAtrasoHora; }
    public int getMaxPassageiros() { return maxPassageiros; }
    public int getMaxBagagem() { return maxBagagem; }
    public String getCaracteristicas() { return caracteristicas; }
    public String getTaxasAdicionais() { return taxasAdicionais; }

    @Override
    public String toString() {
        return nome;
    }
}