import br.edu.ifba.inf008.app.interfaces.IPlugin;
import br.edu.ifba.inf008.app.plugins.luxo.VeiculoLuxoPlugin;

module br.edu.ifba.inf008.plugins.luxo {
    requires br.edu.ifba.inf008.interfaces;

    provides IPlugin
            with VeiculoLuxoPlugin;
}