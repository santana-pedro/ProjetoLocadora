import br.edu.ifba.inf008.app.interfaces.IPlugin;
import br.edu.ifba.inf008.app.plugins.eletrico.VeiculoEletricoPlugin;

module br.edu.ifba.inf008.plugins.eletrico {
    requires br.edu.ifba.inf008.interfaces;

    provides IPlugin
            with VeiculoEletricoPlugin;
}