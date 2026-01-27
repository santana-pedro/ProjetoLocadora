import br.edu.ifba.inf008.app.interfaces.IPlugin;
import br.edu.ifba.inf008.app.plugins.relatorio2.RelatorioDadosPlugin;

module br.edu.ifba.inf008.app.plugins.relatorio2 {
    requires br.edu.ifba.inf008.interfaces;
    requires javafx.controls;
    requires java.sql;

    provides IPlugin
            with RelatorioDadosPlugin;

    exports br.edu.ifba.inf008.app.plugins.relatorio2;
}