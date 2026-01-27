import br.edu.ifba.inf008.app.interfaces.IPlugin;
import br.edu.ifba.inf008.app.plugins.relatorio1.RelatorioCombustivelPlugin;

module br.edu.ifba.inf008.app.plugins.relatorio1 {
    requires br.edu.ifba.inf008.interfaces;
    requires javafx.controls;
    requires java.sql;

    provides IPlugin
            with RelatorioCombustivelPlugin;

    exports br.edu.ifba.inf008.app.plugins.relatorio1;
}