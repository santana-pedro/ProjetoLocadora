import br.edu.ifba.inf008.app.interfaces.IPlugin;
import br.edu.ifba.inf008.app.plugins.compact.VeiculoCompactPlugin;

module br.edu.ifba.inf008.plugins.compact {
    requires br.edu.ifba.inf008.interfaces;

    provides IPlugin
            with VeiculoCompactPlugin;
}