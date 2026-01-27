package br.edu.ifba.inf008.app.shell;

import br.edu.ifba.inf008.app.data.ClienteDataAccess;
import br.edu.ifba.inf008.app.data.RentalDataAccess;
import br.edu.ifba.inf008.app.data.TipoVeiculoDataAccess;
import br.edu.ifba.inf008.app.data.VeiculoDataAccess;
import br.edu.ifba.inf008.app.interfaces.IReportPlugin;
import br.edu.ifba.inf008.app.interfaces.IVehiclePlugin;
import br.edu.ifba.inf008.app.interfaces.IUIController;
import br.edu.ifba.inf008.app.interfaces.ICore;
import br.edu.ifba.inf008.app.model.Cliente;
import br.edu.ifba.inf008.app.model.TipoVeiculo;
import br.edu.ifba.inf008.app.model.Veiculo;

import javafx.application.Application;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TabPane;
import javafx.scene.control.Tab;
import javafx.scene.Node;

import java.math.BigDecimal;

public class UIController extends Application implements IUIController {
    private ICore core;
    private MenuBar menuBar;
    private TabPane tabPane;
    private static UIController uiController;
    private javafx.scene.layout.BorderPane painelPrincipal;

    public UIController(){
    }

    @Override
    public void init() {
        uiController = this;
    }

    public static UIController getInstance() {
        return uiController;
    }

    @Override
    public void start(javafx.stage.Stage primaryStage) {
        primaryStage.setTitle("Locadora de Veículos");

        var pluginController = br.edu.ifba.inf008.app.shell.Core.getInstance().getPluginController();
        pluginController.init();

        javafx.scene.layout.HBox menuTop = new javafx.scene.layout.HBox(15); // Espaçamento de 15px
        menuTop.setStyle("-fx-padding: 15; -fx-background-color: #f4f4f4; -fx-border-color: #cccccc; -fx-border-width: 0 0 1 0; -fx-alignment: center-left;");

        javafx.scene.control.Button btnLocacao = new javafx.scene.control.Button("Realizar Locação");
        btnLocacao.setStyle("-fx-background-color: transparent; -fx-font-size: 14px; -fx-font-weight: bold; -fx-cursor: hand; -fx-text-fill: #333;");

        btnLocacao.setOnMouseEntered(e -> btnLocacao.setStyle("-fx-background-color: #e0e0e0; -fx-font-size: 14px; -fx-font-weight: bold; -fx-cursor: hand; -fx-text-fill: #000;"));
        btnLocacao.setOnMouseExited(e -> btnLocacao.setStyle("-fx-background-color: transparent; -fx-font-size: 14px; -fx-font-weight: bold; -fx-cursor: hand; -fx-text-fill: #333;"));

        btnLocacao.setOnAction(e -> {
            painelPrincipal.setCenter(criarTelaLocacao());
        });

        javafx.scene.control.MenuButton menuRelatorios = new javafx.scene.control.MenuButton("Relatórios");
        menuRelatorios.setStyle("-fx-background-color: transparent; -fx-font-size: 14px; -fx-font-weight: bold; -fx-cursor: hand; -fx-text-fill: #333;");

        menuRelatorios.setOnMouseEntered(e -> menuRelatorios.setStyle("-fx-background-color: #e0e0e0; -fx-font-size: 14px; -fx-font-weight: bold; -fx-cursor: hand; -fx-text-fill: #000;"));
        menuRelatorios.setOnMouseExited(e -> menuRelatorios.setStyle("-fx-background-color: transparent; -fx-font-size: 14px; -fx-font-weight: bold; -fx-cursor: hand; -fx-text-fill: #333;"));

        for (br.edu.ifba.inf008.app.interfaces.IReportPlugin plugin : pluginController.getPluginsRelatorio()) {

            javafx.scene.control.MenuItem item = new javafx.scene.control.MenuItem(plugin.getNomeRelatorio());

            item.setOnAction(e -> {
                painelPrincipal.setCenter(plugin.getConteudo());
            });

            menuRelatorios.getItems().add(item);
        }

        menuTop.getChildren().addAll(btnLocacao, menuRelatorios);

        painelPrincipal = new javafx.scene.layout.BorderPane();
        painelPrincipal.setTop(menuTop);
        painelPrincipal.setCenter(criarTelaLocacao());

        javafx.scene.Scene scene = new javafx.scene.Scene(painelPrincipal, 1024, 768);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private javafx.scene.layout.VBox criarTelaLocacao() {

        //menu bar
        javafx.scene.control.MenuBar menuBar = new javafx.scene.control.MenuBar();

        //selecao de cliente
        ClienteDataAccess clienteDAO = new ClienteDataAccess();
        javafx.scene.control.ComboBox<Cliente> comboClientes = new javafx.scene.control.ComboBox<>();
        comboClientes.setPromptText("Selecione o Cliente:");
        comboClientes.setMaxWidth(400);
        try{
            comboClientes.getItems().addAll(clienteDAO.listarTodos());
        }catch(Exception e){
            e.printStackTrace();
        }
        //selecao de veiculos
        TipoVeiculoDataAccess tipoDAO = new TipoVeiculoDataAccess();
        javafx.scene.control.ComboBox<TipoVeiculo> comboTipos = new javafx.scene.control.ComboBox<>();
        comboTipos.setPromptText("Selecione o Tipo de Veículo:");
        comboTipos.setMaxWidth(400);
        try{
            comboTipos.getItems().addAll(tipoDAO.listarTodos());
        }catch(Exception e){
            e.printStackTrace();
        }

        //tabela
        javafx.scene.control.TableView<Veiculo> tabelaVeiculos = new javafx.scene.control.TableView<>();

        //colunas
        var colMarca = new javafx.scene.control.TableColumn<Veiculo, String>("Marca");
        colMarca.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("marca"));

        var colModelo = new javafx.scene.control.TableColumn<Veiculo, String>("Modelo");
        colModelo.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("modelo"));

        var colPlaca = new javafx.scene.control.TableColumn<Veiculo, String>("Placa");
        colPlaca.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("placa"));

        var colAno = new javafx.scene.control.TableColumn<Veiculo, Integer>("Ano");
        colAno.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("ano"));

        var colCor = new javafx.scene.control.TableColumn<Veiculo, String>("Cor");
        colCor.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("cor"));

        var colCambio = new javafx.scene.control.TableColumn<Veiculo, String>("Câmbio");
        colCambio.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("cambio"));

        var colKm = new javafx.scene.control.TableColumn<Veiculo, java.math.BigDecimal>("Km");
        colKm.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("quilometragem"));

        var colStatus = new javafx.scene.control.TableColumn<Veiculo, String>("Status");
        colStatus.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("status"));

        tabelaVeiculos.getColumns().addAll(colMarca, colModelo, colPlaca, colAno, colCor, colCambio, colKm, colStatus);

        //dados
        VeiculoDataAccess veiculoDAO = new VeiculoDataAccess();
        tabelaVeiculos.getItems().addAll(veiculoDAO.listarTodos());

        //filtro de tipo
        comboTipos.setOnAction(event -> {
            TipoVeiculo tipoSelecionado = comboTipos.getValue();
            if(tipoSelecionado != null){
                VeiculoDataAccess dao = new VeiculoDataAccess();
                var listaFiltrada = dao.buscarPorTipo(tipoSelecionado.getId());
                tabelaVeiculos.getItems().clear();
                tabelaVeiculos.getItems().addAll(listaFiltrada);
            }
        });

        //btn limpar
        javafx.scene.control.Button btnLimpar = new javafx.scene.control.Button("X");
        btnLimpar.setOnAction(e -> {
            comboTipos.getSelectionModel().clearSelection();
            tabelaVeiculos.getItems().clear();
            tabelaVeiculos.getItems().addAll(new VeiculoDataAccess().listarTodos());
        });

        //confirmar locacao
        javafx.scene.control.Button btnLocar = new javafx.scene.control.Button("Confirmar Locação");
        btnLocar.setOnAction(event -> {
            var cliente = comboClientes.getValue();
            var veiculo = tabelaVeiculos.getSelectionModel().getSelectedItem();

            if(cliente == null){
                new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR, "Por favor, selecione um Cliente!").show();
                return;
            }
            if(veiculo == null){
                new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR, "Por favor, selecione um Veículo na tabela!").show();
                return;
            }
            if(!"AVAILABLE".equalsIgnoreCase(veiculo.getStatus())){
                new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.WARNING, "Este veículo não está disponível!").show();
                return;
            }
            abrirJanelaFinalizacao(cliente, veiculo, tabelaVeiculos);
        });

        javafx.scene.layout.HBox boxFiltro = new javafx.scene.layout.HBox(10, comboTipos, btnLimpar);

        javafx.scene.layout.VBox vBox = new javafx.scene.layout.VBox(10);
        vBox.setStyle("-fx-padding: 15;");

        vBox.getChildren().addAll(
                new javafx.scene.control.Label("1. Quem vai alugar?"),
                comboClientes,
                new javafx.scene.control.Separator(),
                new javafx.scene.control.Label("2. Escolha o Tipo de Veículo:"),
                boxFiltro,
                new javafx.scene.control.Separator(),
                btnLocar,
                tabelaVeiculos
        );

        return vBox;
    }

    private void abrirJanelaFinalizacao(Cliente cliente, Veiculo veiculo, javafx.scene.control.TableView<Veiculo> tabela) {
        javafx.stage.Stage stageConfirmacao = new javafx.stage.Stage();
        stageConfirmacao.setTitle("Finalizar Locação");
        stageConfirmacao.initModality(javafx.stage.Modality.APPLICATION_MODAL);
        //busca os dados do tipo do veículo
        TipoVeiculoDataAccess tipoDAO = new TipoVeiculoDataAccess();
        var listaTipos = tipoDAO.listarTodos();
        //filtra tipo de veiculo
        TipoVeiculo tipoVeiculo = listaTipos.stream()
                .filter(t -> t.getId() == veiculo.getTipoId())
                .findFirst()
                .orElse(null);
        //campos
        javafx.scene.control.Label lblInicio = new javafx.scene.control.Label("Data Início:");
        javafx.scene.control.DatePicker dtInicio = new javafx.scene.control.DatePicker(java.time.LocalDate.now());
        javafx.scene.control.Label lblFim = new javafx.scene.control.Label("Data Fim (Prevista):");
        javafx.scene.control.DatePicker dtFim = new javafx.scene.control.DatePicker(java.time.LocalDate.now().plusDays(1));
        javafx.scene.control.Label lblLocal = new javafx.scene.control.Label("Local de Retirada:");
        javafx.scene.control.TextField txtLocal = new javafx.scene.control.TextField(veiculo.getLocalizacao());
        String valorSugerido = (tipoVeiculo != null) ? tipoVeiculo.getTarifaDiaria().toString() : "0.00";
        javafx.scene.control.Label lblValorDiaria = new javafx.scene.control.Label("Valor da Diária (R$):");
        javafx.scene.control.TextField txtValorDiaria = new javafx.scene.control.TextField(valorSugerido);
        javafx.scene.control.Label lblValorSeguro = new javafx.scene.control.Label("Valor do Seguro (R$):");
        String seguroSugerido = (tipoVeiculo != null) ? tipoVeiculo.getTaxaSeguro().toString() : "0.00";
        javafx.scene.control.TextField txtValorSeguro = new javafx.scene.control.TextField(seguroSugerido);
        javafx.scene.control.Button btnCalcularSalvar = new javafx.scene.control.Button("Calcular e Confirmar");
        btnCalcularSalvar.setMaxWidth(Double.MAX_VALUE);
        //botao
        btnCalcularSalvar.setOnAction(e -> {
            try{
                //validacoes
                if(dtInicio.getValue() == null || dtFim.getValue() == null){
                    mostrarAlerta("Datas Inválidas", "Por favor, preencha as datas."); return;
                }
                if(dtFim.getValue().isBefore(dtInicio.getValue())){
                    mostrarAlerta("Datas Inválidas", "A data final deve ser depois da inicial."); return;
                }
                //organizando dados
                long dias = java.time.temporal.ChronoUnit.DAYS.between(dtInicio.getValue(), dtFim.getValue());
                if(dias < 1) dias = 1;
                java.math.BigDecimal valDiaria = new java.math.BigDecimal(txtValorDiaria.getText().replace(",", "."));
                //pegando chaves _fee
                java.util.Map<String, java.math.BigDecimal> taxasMap = new java.util.HashMap<>();
                if(tipoVeiculo != null && tipoVeiculo.getTaxasAdicionais() != null){
                    //limpa chars do json: { }
                    String jsonLimpo = tipoVeiculo.getTaxasAdicionais().replace("{", "").replace("}", "").replace("\"", "");
                    if(!jsonLimpo.isBlank()){
                        String[] pares = jsonLimpo.split(",");
                        for(String par : pares){
                            String[] entrada = par.split(":");
                            if(entrada.length == 2){
                                String chave = entrada[0].trim();
                                //regra do _fee
                                if(chave.endsWith("_fee")){
                                    try{
                                        taxasMap.put(chave, new java.math.BigDecimal(entrada[1].trim()));
                                    }catch(Exception ex){ }
                                }
                            }
                        }
                    }
                }
                //uso do plugin
                var plugins = Core.getInstance().getPluginController().getPluginsVeiculo();
                if(plugins.isEmpty()) throw new RuntimeException("Nenhum plugin encontrado na pasta!");
                IVehiclePlugin pluginEscolhido = null;
                for(var p : plugins){
                    if(tipoVeiculo.getNome().toUpperCase().contains(p.getTipoVeiculo().toUpperCase()) ||
                            p.getTipoVeiculo().toUpperCase().contains(tipoVeiculo.getNome().toUpperCase())) {
                        pluginEscolhido = p;
                        break;
                    }
                }
                if(pluginEscolhido == null){
                    System.out.println("Aviso: Plugin específico não encontrado. Usando o genérico.");
                    pluginEscolhido = plugins.get(0);
                }
                System.out.println("Usando plugin: " + pluginEscolhido.getTipoVeiculo()); // Debug
                //calculo do total
                BigDecimal total = pluginEscolhido.calcularValorTotal((int) dias, valDiaria, taxasMap);
                //confirmacao do usuario
                javafx.scene.control.Alert confirmacao = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.CONFIRMATION);
                confirmacao.setTitle("Confirmar Locação");
                confirmacao.setHeaderText("Valor Total Calculado: R$ " + total.toString());
                confirmacao.setContentText("Deseja confirmar a locação e salvar no banco?");
                if(confirmacao.showAndWait().get() == javafx.scene.control.ButtonType.OK){
                    //salvar no banco
                    RentalDataAccess rentalDAO = new RentalDataAccess();
                    java.math.BigDecimal valSeguro = new java.math.BigDecimal(txtValorSeguro.getText().replace(",", "."));
                    boolean salvou = new RentalDataAccess().salvarLocacao(cliente.getId(), veiculo.getId(), dtInicio.getValue(),
                            dtFim.getValue(), total, veiculo.getQuilometragem(), valDiaria, valSeguro, txtLocal.getText()
                    );
                    if(salvou){
                        //atualiza status do veiculo
                        new VeiculoDataAccess().atualizarStatus(veiculo.getId(), "RENTED");
                        mostrarAlerta("Sucesso", "Locação realizada com sucesso!");
                        if(tabela != null){
                            tabela.getItems().clear();
                            tabela.getItems().addAll(new VeiculoDataAccess().listarTodos());
                            tabela.refresh();
                        }
                        stageConfirmacao.close();
                    }else{
                        mostrarAlerta("Erro", "Erro ao salvar no banco de dados.");
                    }
                }

            } catch(NumberFormatException ex){
                mostrarAlerta("Erro de Valor", "Verifique se os valores numéricos estão corretos.");
            }
        });
        //layout
        javafx.scene.layout.GridPane grid = new javafx.scene.layout.GridPane();
        grid.setHgap(10); grid.setVgap(15);
        grid.setStyle("-fx-padding: 20;");
        grid.add(new javafx.scene.control.Label("Veículo: " + veiculo.getModelo()), 0, 0, 2, 1);
        grid.add(lblInicio, 0, 1);      grid.add(dtInicio, 1, 1);
        grid.add(lblFim, 0, 2);         grid.add(dtFim, 1, 2);
        grid.add(lblLocal, 0, 3);       grid.add(txtLocal, 1, 3);
        grid.add(lblValorDiaria, 0, 4); grid.add(txtValorDiaria, 1, 4);
        grid.add(lblValorSeguro, 0, 5); grid.add(txtValorSeguro, 1, 5);
        javafx.scene.control.Label lblInfo = new javafx.scene.control.Label("*Taxas extras (_fee) serão somadas automaticamente.");
        lblInfo.setStyle("-fx-font-size: 10px; -fx-text-fill: grey;");
        grid.add(lblInfo, 0, 6, 2, 1);
        grid.add(btnCalcularSalvar, 1, 7);
        javafx.scene.Scene scene = new javafx.scene.Scene(grid, 450, 400);
        stageConfirmacao.setScene(scene);
        stageConfirmacao.show();
    }

    private void mostrarAlerta(String titulo, String mensagem){
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    public MenuItem createMenuItem(String menuText, String menuItemText) {
        //criar o menu caso nao exista
        Menu newMenu = null;
        for (Menu menu : menuBar.getMenus()) {
            if (menu.getText() == menuText) {
                newMenu = menu;
                break;
            }
        }
        if (newMenu == null) {
            newMenu = new Menu(menuText);
            menuBar.getMenus().add(newMenu);
        }

        //criar o menu item no menu
        MenuItem menuItem = new MenuItem(menuItemText);
        newMenu.getItems().add(menuItem);

        return menuItem;
    }

    public boolean createTab(String tabText, Node contents) {
        Tab tab = new Tab();
        tab.setText(tabText);
        tab.setContent(contents);
        tabPane.getTabs().add(tab);

        return true;
    }
}
