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

public class ConectarSqlServer {

    private final String URL = "jdbc:sqlserver://DESKTOP-O80CLNM;database = mercado";

    public Connection openConnection() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            return DriverManager.getConnection(URL, "sa", "sa");
        } catch (ClassNotFoundException | SQLException ex) {
            throw new RuntimeException("Erro na conexão!", ex);
        }
    }

    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(ConectarSqlServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void closeConnection(Connection conn, PreparedStatement stat) {
        closeConnection(conn);
        if (stat != null) {
            try {
                stat.close();
            } catch (SQLException ex) {
                Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void closeConnectionn(Connection conn, PreparedStatement stat, ResultSet rs) {
        closeConnection(conn, stat);
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(ConectarSqlServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
