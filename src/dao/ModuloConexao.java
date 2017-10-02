/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

//import com.mysql.jdbc.Connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ModuloConexao {

    // Armazenando informações ao banco
    
    public Statement pst;
    public ResultSet rs;
    private String url = "jdbc:mysql://localhost:3306/db_restaurante";//caminho que localiza o banco de dados "db_restaurante" "jdbc:mysql://localhost:3306/db_restaurante";
    private String user = "root";//usuario root ou administrador do banco
    private String password = "Rbs@2014";//senha do usuário root
    public Connection connection;
    public void conector() {

        String driver = "com.mysql.jdbc.Driver";// a linha "chama" o driver

        //estabelecendo conexão com o banco
        try {
            //System.setProperty("jdbc.Driver", driver);
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            // a linha abaixo serve de apoio para esclarecer o erro
            //System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao conectar!\nErro: " + e);
        }
    }

    public void SQL(String sql){
        try {
            pst = connection.createStatement(rs.TYPE_SCROLL_INSENSITIVE,rs.CONCUR_READ_ONLY);
            rs = pst.executeQuery(sql);
        } catch (SQLException ex) {
            //JOptionPane.showMessageDialog(null, "Erro de execulta sql\nErro: " + ex);
        }
    }
    
    public void desconecta() {
        try {
            connection.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao desconectar!\nErro: " + ex);
        }
    }
    /*public static Connection getConnection() {
        Connection conn;
        try {
            String url = "jdbc:sqlite:bd_sistema.db";
            conn = DriverManager.getConnection(url);
            //System.out.println("SQLite – Conexão Estabelecida!");
            return conn;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }*/

}
