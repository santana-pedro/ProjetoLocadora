package br.edu.ifba.inf008.app.data;

import br.edu.ifba.inf008.app.shell.BancoD;
import br.edu.ifba.inf008.app.model.TipoVeiculo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TipoVeiculoDataAccess{

    public List<TipoVeiculo> listarTodos(){
        List<TipoVeiculo> lista = new ArrayList<>();
        String sql = "SELECT * FROM vehicle_types";

        try{
            Connection conn = BancoD.getInstance().getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                TipoVeiculo t = new TipoVeiculo(
                        rs.getInt("type_id"),
                        rs.getString("type_name"),
                        rs.getBigDecimal("daily_rate"),
                        rs.getBigDecimal("weekly_rate"),
                        rs.getBigDecimal("monthly_rate"),
                        rs.getBigDecimal("security_deposit"),
                        rs.getBigDecimal("insurance_rate"),
                        rs.getBigDecimal("late_fee_per_hour"),
                        rs.getInt("max_passengers"),
                        rs.getInt("max_luggage"),
                        rs.getString("special_features"),
                        rs.getString("additional_fees")
                );
                lista.add(t);
            }
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Erro ao buscar tipos: " + e.getMessage());
        }
        return lista;
    }
}