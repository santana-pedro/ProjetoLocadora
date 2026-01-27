import br.edu.ifba.inf008.app.interfaces.IPlugin;
import br.edu.ifba.inf008.app.plugins.economico.VeiculoEconomicoPlugin;

module br.edu.ifba.inf008.plugins.economico {
    requires br.edu.ifba.inf008.interfaces;

    provides IPlugin
            with VeiculoEconomicoPlugin;
}