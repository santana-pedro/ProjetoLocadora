package br.edu.ifba.inf008.app.plugins.relatorio1;

import br.edu.ifba.inf008.app.interfaces.IReportPlugin;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

public class RelatorioCombustivelPlugin implements IReportPlugin {

    @Override
    public boolean init() {
        System.out.println("Relatório de Combustível Carregado!");
        return true;
    }

    @Override
    public String getNomeRelatorio() {
        return "Relatório Combustível";
    }

    @Override
    public Node getConteudo() {
        ObservableList<PieChart.Data> dadosGrafico = FXCollections.observableArrayList();

        Map<PieChart.Data, String> colorMap = new HashMap<>();

        String sql =
                "SELECT " +
                        "   v.fuel_type, " +
                        "   COUNT(*) AS vehicle_count, " +
                        "   ROUND(COUNT(*) * 100.0 / SUM(COUNT(*)) OVER (), 2) AS fleet_percentage, " +
                        "   CASE v.fuel_type " +
                        "       WHEN 'GASOLINE' THEN '#FF6B6B' " +
                        "       WHEN 'DIESEL' THEN '#4ECDC4' " +
                        "       WHEN 'ELECTRIC' THEN '#45B7D1' " +
                        "       WHEN 'HYBRID' THEN '#96CEB4' " +
                        "       WHEN 'CNG' THEN '#FFEAA7' " +
                        "       ELSE '#D9D9D9' " +
                        "   END AS chart_color " +
                        "FROM vehicles v " +
                        "GROUP BY v.fuel_type " +
                        "ORDER BY vehicle_count DESC";

        try {
            Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3307/car_rental_system?useSSL=false&allowPublicKeyRetrieval=true","root", "root");
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String tipo = rs.getString("fuel_type");
                int qtd = rs.getInt("vehicle_count");
                double perc = rs.getDouble("fleet_percentage");
                String corHex = rs.getString("chart_color");

                String labelTexto = String.format("%s (%d)", tipo, qtd);

                PieChart.Data data = new PieChart.Data(labelTexto, qtd);
                dadosGrafico.add(data);

                colorMap.put(data, corHex);
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            return new Label("Erro ao carregar dados: " + e.getMessage());
        }

        PieChart grafico = new PieChart(dadosGrafico);
        grafico.setLegendVisible(true);

        for (PieChart.Data data : dadosGrafico) {
            Node slice = data.getNode();
            if (slice != null) {
                String cor = colorMap.get(data);
                slice.setStyle("-fx-pie-color: " + cor + ";");
            }
        }

        return grafico;
    }
}