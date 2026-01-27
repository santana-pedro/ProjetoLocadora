package br.edu.ifba.inf008.app.interfaces;

import javafx.scene.Node;

public interface IReportPlugin extends IPlugin {
    String getNomeRelatorio();
    Node getConteudo();
}
