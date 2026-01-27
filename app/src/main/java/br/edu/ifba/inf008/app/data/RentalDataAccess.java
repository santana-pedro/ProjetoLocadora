package br.edu.ifba.inf008.app.data;

import br.edu.ifba.inf008.app.shell.BancoD;
import java.math.BigDecimal;
import java.time.LocalDate;

public class RentalDataAccess {

    public boolean salvarLocacao(int clienteId, int veiculoId, LocalDate inicio, LocalDate fim, BigDecimal total, BigDecimal quilometragemInicial,
                                 BigDecimal valorDiaria, BigDecimal valorSeguro, String localRetirada){
        String sql = "INSERT INTO rentals (customer_id, vehicle_id, start_date, " + "scheduled_end_date, total_amount, rental_type, rental_status, " +
                "pickup_location, initial_mileage, base_rate, insurance_fee) " + "VALUES (?, ?, ?, ?, ?, 'DAILY', 'ACTIVE', ?, ?, ?, ?)";

        try {
            java.sql.Connection conn = BancoD.getInstance().getConnection();
            java.sql.PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, clienteId);
            stmt.setInt(2, veiculoId);
            stmt.setDate(3, java.sql.Date.valueOf(inicio));
            stmt.setDate(4, java.sql.Date.valueOf(fim));
            stmt.setBigDecimal(5, total);
            stmt.setString(6, localRetirada);
            stmt.setBigDecimal(7, quilometragemInicial);
            stmt.setBigDecimal(8, valorDiaria);
            stmt.setBigDecimal(9, valorSeguro);
            int linhas = stmt.executeUpdate();
            return linhas > 0;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
}