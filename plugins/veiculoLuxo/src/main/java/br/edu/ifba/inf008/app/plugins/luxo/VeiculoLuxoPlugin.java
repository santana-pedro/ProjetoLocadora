package br.edu.ifba.inf008.app.plugins.luxo;

import br.edu.ifba.inf008.app.interfaces.IVehiclePlugin;
import br.edu.ifba.inf008.app.interfaces.*;
import java.math.BigDecimal;
import java.util.Map;

public class VeiculoLuxoPlugin implements IVehiclePlugin {

    @Override
    public String getTipoVeiculo() {
        return "Luxo";
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
        System.out.println("Plugin Luxo Carregado!");
        return true;
    }
}