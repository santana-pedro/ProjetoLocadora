package br.edu.ifba.inf008.app.interfaces;

import java.math.BigDecimal;
import java.util.Map;

public interface IVehiclePlugin extends IPlugin{
    String getTipoVeiculo();
    BigDecimal calcularValorTotal(int dias, BigDecimal valorDiaria, Map<String, BigDecimal> taxas);
}
