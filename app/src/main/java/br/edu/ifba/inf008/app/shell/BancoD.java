package br.edu.ifba.inf008.app.shell;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BancoD {

    private static BancoD instance;
    private Connection connection;

    private static final String URL = "jdbc:mariadb://localhost:3306/inf008";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    private BancoD() {
        try {
            this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("--- CONEXÃO COM BANCO DE DADOS BEM SUCEDIDA ---");
        } catch (SQLException e) {
            System.err.println("ERRO CRÍTICO: Não foi possível conectar ao banco!");
            e.printStackTrace();
        }
    }

    public static BancoD getInstance() {
        if (instance == null) {
            instance = new BancoD();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}