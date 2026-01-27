import br.edu.ifba.inf008.app.interfaces.IPlugin;
import br.edu.ifba.inf008.app.interfaces.IReportPlugin;
import br.edu.ifba.inf008.app.interfaces.IVehiclePlugin;

module br.edu.ifba.inf008.app {
    requires javafx.controls;
    requires javafx.fxml;
    requires br.edu.ifba.inf008.interfaces;

    requires java.sql;
    requires org.mariadb.jdbc;

    uses IPlugin;
    uses IVehiclePlugin;
    uses IReportPlugin;

    exports br.edu.ifba.inf008.app.shell to javafx.graphics;
    opens br.edu.ifba.inf008.app.shell to javafx.fxml;
    opens br.edu.ifba.inf008.app.model to javafx.base;
}