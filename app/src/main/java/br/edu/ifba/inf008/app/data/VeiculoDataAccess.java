package br.edu.ifba.inf008.app.data;

import br.edu.ifba.inf008.app.interfaces.IDataAccess;
import br.edu.ifba.inf008.app.shell.BancoD;
import br.edu.ifba.inf008.app.model.Veiculo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class VeiculoDataAccess implements IDataAccess<Veiculo>{

    public List<Veiculo> listarTodos(){
        List<Veiculo> lista = new ArrayList<>();
        String sql = "SELECT * FROM vehicles";

        try{
            Connection conn = BancoD.getInstance().getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                Veiculo v = new Veiculo(
                        rs.getInt("vehicle_id"),
                        rs.getString("license_plate"),
                        rs.getString("make"),
                        rs.getString("model"),
                        rs.getInt("year"),
                        rs.getInt("type_id"),
                        rs.getString("color"),
                        rs.getString("fuel_type"),
                        rs.getString("transmission"),
                        rs.getBigDecimal("mileage"),
                        rs.getString("status"),
                        rs.getString("current_location")
                );
                lista.add(v);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return lista;
    }
    public List<Veiculo> buscarPorTipo(int idTipo){
        List<Veiculo> lista = new ArrayList<>();
        String sql = "SELECT * FROM vehicles WHERE type_id = ?";
        try{
            Connection conn = BancoD.getInstance().getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idTipo);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Veiculo v = new Veiculo(
                        rs.getInt("vehicle_id"),
                        rs.getString("license_plate"),
                        rs.getString("make"),
                        rs.getString("model"),
                        rs.getInt("year"),
                        rs.getInt("type_id"),
                        rs.getString("color"),
                        rs.getString("fuel_type"),
                        rs.getString("transmission"),
                        rs.getBigDecimal("mileage"),
                        rs.getString("status"),
                        rs.getString("current_location")
                );
                lista.add(v);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return lista;
    }

    public boolean atualizarStatus(int veiculoId, String novoStatus) {
        String sql = "UPDATE vehicles SET status = ? WHERE vehicle_id = ?";
        try {
            Connection conn = BancoD.getInstance().getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, novoStatus);
            stmt.setInt(2, veiculoId);
            stmt.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}