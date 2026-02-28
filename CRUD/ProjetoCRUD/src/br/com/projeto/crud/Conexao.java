package br.com.projeto.crud;

import java.sql.*;
import java.security.*;
import java.math.*;

public class Conexao {
    
    
    public static Connection getConexao() throws SQLException {
        String driverName = "org.postgresql.Driver";                    
        String serverName = "localhost";
        String mydatabase = "teste";
        int porta = 5432;
        String url = "jdbc:postgresql://" + serverName + ":" + porta + "/" + mydatabase;
        String username = "ti2cc";
        String password = "ti@cc";

        try {
            Class.forName(driverName);
            return DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver não encontrado: " + e.getMessage());
        }
    }

    
    public static String toMD5(String senha) throws Exception {
        MessageDigest m = MessageDigest.getInstance("MD5");
        m.update(senha.getBytes(), 0, senha.length());
        return new BigInteger(1, m.digest()).toString(16);
    }
}