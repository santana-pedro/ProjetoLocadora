package br.edu.ifba.inf008.app.shell;

import br.edu.ifba.inf008.app.interfaces.IPluginController;
import br.edu.ifba.inf008.app.interfaces.IPlugin;
import br.edu.ifba.inf008.app.interfaces.IReportPlugin;
import br.edu.ifba.inf008.app.interfaces.IVehiclePlugin;

import java.util.ArrayList;
import java.util.ServiceLoader;
import java.util.List;

public class PluginController implements IPluginController {
    private List<IVehiclePlugin> pluginsVeiculo = new ArrayList<>();
    @Override
    public boolean init() {
        System.out.println("--- INICIANDO CARREGAMENTO DE PLUGINS ---");

        ServiceLoader<IPlugin> loader = ServiceLoader.load(IPlugin.class);

        boolean encontrou = false;

        for (IPlugin plugin : loader) {
            plugin.init();

            if (plugin instanceof IVehiclePlugin) {
                this.pluginsVeiculo.add((IVehiclePlugin) plugin);
                encontrou = true;
            }

            else if (plugin instanceof IReportPlugin) {
                this.pluginsRelatorio.add((IReportPlugin) plugin);
                encontrou = true;
            }
        }

        if (!encontrou) {
            System.out.println("Nenhum plugin encontrado! Verifique se adicionou a dependência no pom.xml do app.");
        }
        return encontrou;
    }
    @Override
    public List<IVehiclePlugin> getPluginsVeiculo() {
        return this.pluginsVeiculo;
    }

    private List<IReportPlugin> pluginsRelatorio = new ArrayList<>();
    public List<IReportPlugin> getPluginsRelatorio() {
        return this.pluginsRelatorio;
    }
}