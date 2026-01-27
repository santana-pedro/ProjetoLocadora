package br.edu.ifba.inf008.app.interfaces;

import java.util.List;

public interface IPluginController
{
    public abstract boolean init();
    List<IVehiclePlugin> getPluginsVeiculo();
    List<IReportPlugin> getPluginsRelatorio();
}
