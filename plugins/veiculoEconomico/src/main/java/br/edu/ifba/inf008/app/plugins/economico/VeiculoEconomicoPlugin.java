package br.edu.ifba.inf008.app.plugins.economico;

import br.edu.ifba.inf008.app.interfaces.IVehiclePlugin;
import java.math.BigDecimal;
import java.util.Map;

public class VeiculoEconomicoPlugin implements IVehiclePlugin {

    @Override
    public String getTipoVeiculo() {
        return "Econômico";
    }

    @Override
    public BigDecimal calcularValorTotal(int dias, BigDecimal valorDiaria, Map<String, BigDecimal> taxas) {
        System.out.println("--- PLUGIN ECONÔMICO ACIONADO ---");
        System.out.println("Dias recebidos: " + dias);
        System.out.println("Valor Diária recebido: " + valorDiaria);
        System.out.println("Taxas recebidas: " + taxas);
        BigDecimal total = valorDiaria.multiply(BigDecimal.valueOf(dias));
        if (taxas != null) {
            for (BigDecimal valorTaxa : taxas.values()) {
                if (valorTaxa != null) {
                    total = total.add(valorTaxa);
                }
            }
        }
        System.out.println("Total calculado pelo plugin: " + total);
        System.out.println("----------------------------------");
        return total;
    }

    @Override
    public boolean init() {
        System.out.println("Plugin de Veículo Econômico iniciado!");
        return true;
    }
}