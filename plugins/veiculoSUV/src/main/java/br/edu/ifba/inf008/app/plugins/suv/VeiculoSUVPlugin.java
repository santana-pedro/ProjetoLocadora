package br.edu.ifba.inf008.app.plugins.suv;

import br.edu.ifba.inf008.app.interfaces.IVehiclePlugin;
import br.edu.ifba.inf008.app.interfaces.*;
import java.math.BigDecimal;
import java.util.Map;

public class VeiculoSUVPlugin implements IVehiclePlugin {

    @Override
    public String getTipoVeiculo() {
        return "SUV";
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
        System.out.println("Plugin SUV Carregado!");
        return true;
    }
}