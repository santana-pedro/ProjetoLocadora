package br.edu.ifba.inf008.app.data;

import br.edu.ifba.inf008.app.interfaces.IDataAccess; // Importe a interface
import br.edu.ifba.inf008.app.model.Cliente;
import br.edu.ifba.inf008.app.shell.BancoD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

// Implementa a interface especificando que o T é <Cliente>
public class ClienteDataAccess implements IDataAccess<Cliente> {

    @Override // Agora o Java garante que você escreveu certo
    public List<Cliente> listarTodos() {
        List<Cliente> lista = new ArrayList<>();
        String sql = "SELECT * FROM customers";

        try {
            Connection conn = BancoD.getInstance().getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // ... (seu código de criar Cliente igualzinho antes) ...
                Cliente c = new Cliente(
                        rs.getInt("customer_id"),
                        rs.getString("customer_type"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("company_name"),
                        rs.getString("tax_id"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getString("address"),
                        rs.getTimestamp("registration_date"),
                        rs.getInt("loyalty_points"),
                        rs.getBigDecimal("discount_rate")
                );
                lista.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
}