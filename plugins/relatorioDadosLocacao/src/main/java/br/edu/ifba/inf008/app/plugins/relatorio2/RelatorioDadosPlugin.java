package br.edu.ifba.inf008.app.plugins.relatorio2;

import br.edu.ifba.inf008.app.interfaces.IReportPlugin;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class RelatorioDadosPlugin implements IReportPlugin {

    public static class RentalRow {
        private int id;
        private String cliente;
        private String tipoCliente;
        private String veiculo;
        private String tipoVeiculo;
        private String dataInicio;
        private double valor;
        private String status;

        public RentalRow(int id, String cliente, String tipoCliente, String veiculo, String tipoVeiculo, String dataInicio, double valor, String status) {
            this.id = id;
            this.cliente = cliente;
            this.tipoCliente = tipoCliente;
            this.veiculo = veiculo;
            this.tipoVeiculo = tipoVeiculo;
            this.dataInicio = dataInicio;
            this.valor = valor;
            this.status = status;
        }

        public int getId() { return id; }
        public String getCliente() { return cliente; }
        public String getTipoCliente() { return tipoCliente; }
        public String getVeiculo() { return veiculo; }
        public String getTipoVeiculo() { return tipoVeiculo; }
        public String getDataInicio() { return dataInicio; }
        public double getValor() { return valor; }
        public String getStatus() { return status; }
    }

    @Override
    public boolean init() {
        System.out.println("Relatório de Dados Carregado!");
        return true;
    }

    @Override
    public String getNomeRelatorio() {
        return "Relatório Dados";
    }

    @Override
    public Node getConteudo() {
        TableView<RentalRow> tabela = new TableView<>();

        TableColumn<RentalRow, Integer> colId = new TableColumn<>("ID");
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<RentalRow, String> colCliente = new TableColumn<>("Cliente");
        colCliente.setCellValueFactory(new PropertyValueFactory<>("cliente"));
        colCliente.setMinWidth(150);

        TableColumn<RentalRow, String> colTipoCli = new TableColumn<>("Tipo");
        colTipoCli.setCellValueFactory(new PropertyValueFactory<>("tipoCliente"));

        TableColumn<RentalRow, String> colVeiculo = new TableColumn<>("Veículo");
        colVeiculo.setCellValueFactory(new PropertyValueFactory<>("veiculo"));
        colVeiculo.setMinWidth(150);

        TableColumn<RentalRow, String> colData = new TableColumn<>("Data Início");
        colData.setCellValueFactory(new PropertyValueFactory<>("dataInicio"));

        TableColumn<RentalRow, Double> colValor = new TableColumn<>("Valor (R$)");
        colValor.setCellValueFactory(new PropertyValueFactory<>("valor"));

        TableColumn<RentalRow, String> colStatus = new TableColumn<>("Status");
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        tabela.getColumns().addAll(colId, colData, colCliente, colTipoCli, colVeiculo, colValor, colStatus);

        ObservableList<RentalRow> dados = FXCollections.observableArrayList();

        String sql =
                "SELECT " +
                        "   r.rental_id, " +
                        "   COALESCE(c.company_name, CONCAT(c.first_name, ' ', c.last_name)) as customer_name, " +
                        "   c.customer_type, " +
                        "   CONCAT(v.make, ' ', v.model) as vehicle, " +
                        "   vt.type_name as vehicle_type, " +
                        "   DATE_FORMAT(r.start_date, '%Y-%m-%d') as start_date, " +
                        "   r.total_amount, " +
                        "   r.rental_status " +
                        "FROM rentals r " +
                        "JOIN customers c ON r.customer_id = c.customer_id " +
                        "JOIN vehicles v ON r.vehicle_id = v.vehicle_id " +
                        "JOIN vehicle_types vt ON v.type_id = vt.type_id " +
                        "ORDER BY r.start_date DESC " +
                        "LIMIT 100";

        try {
            Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/car_rental_system?useSSL=false&allowPublicKeyRetrieval=true","root", "root");
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                dados.add(new RentalRow(
                        rs.getInt("rental_id"),
                        rs.getString("customer_name"),
                        rs.getString("customer_type"),
                        rs.getString("vehicle"),
                        rs.getString("vehicle_type"),
                        rs.getString("start_date"),
                        rs.getDouble("total_amount"),
                        rs.getString("rental_status")
                ));
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            return new Label("Erro ao carregar tabela: " + e.getMessage());
        }

        tabela.setItems(dados);
        return tabela;
    }
}