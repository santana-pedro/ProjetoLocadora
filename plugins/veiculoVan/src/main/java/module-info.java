import br.edu.ifba.inf008.app.interfaces.IPlugin;
import br.edu.ifba.inf008.app.plugins.van.VeiculoVanPlugin;

module br.edu.ifba.inf008.plugins.van {
    requires br.edu.ifba.inf008.interfaces;

    provides IPlugin
            with VeiculoVanPlugin;
}