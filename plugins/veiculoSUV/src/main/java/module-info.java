import br.edu.ifba.inf008.app.interfaces.IPlugin;
import br.edu.ifba.inf008.app.plugins.suv.VeiculoSUVPlugin;

module br.edu.ifba.inf008.plugins.suv {
    requires br.edu.ifba.inf008.interfaces;

    provides IPlugin
            with VeiculoSUVPlugin;
}