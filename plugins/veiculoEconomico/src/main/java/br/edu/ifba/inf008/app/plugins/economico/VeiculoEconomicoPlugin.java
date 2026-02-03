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
        //calculo padrao
        BigDecimal total = valorDiaria.multiply(BigDecimal.valueOf(dias));
        //taxas extras
        if (taxas != null) {
            for (BigDecimal valorTaxa : taxas.values()) {
                if (valorTaxa != null) {
                    total = total.add(valorTaxa);
                }
            }
        }
        return total;
    }

    @Override
    public boolean init() {
        System.out.println("Plugin de Veículo Econômico iniciado!");
        return true;
    }
}