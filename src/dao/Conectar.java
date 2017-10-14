package dao;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author manoa
 */
public class Conectar {
	
	
    private  final String DRIVER = "org.sqlite.JDBC";
    private  final String URL = "jdbc:sqlite:db_mercado.db";
    
    public Connection openConnection(){
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL);
        } catch (ClassNotFoundException | SQLException ex) {
            throw new RuntimeException("Erro na conexão!", ex);
        } 
    }
    
    public static void closeConnection(Connection conn){
        if(conn != null){
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(Conectar.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public static void closeConnection(Connection conn, PreparedStatement stat){
        closeConnection(conn);
        if(stat != null){
            try {
                stat.close();
            } catch (SQLException ex) {
                Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public static void closeConnectionn(Connection conn, PreparedStatement stat, ResultSet rs){
        closeConnection(conn,stat);
        if(rs != null){
            try {
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(Conectar.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
