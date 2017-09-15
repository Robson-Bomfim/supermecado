/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

//import com.mysql.jdbc.Connection;
import java.sql.Connection;
import java.sql.DriverManager;

public class ModuloConexao {

    public static java.sql.Connection conector(){
        java.sql.Connection conexao;
       
        
        String driver = "com.mysql.jdbc.Driver";// a linha "chama" o driver
        
        // Armazenando informações ao banco
        String url = "jdbc:mysql://localhost:3306/db_restaurante";//caminho que localiza o banco de dados "db_restaurante" "jdbc:mysql://localhost:3306/db_restaurante";
        String user = "root";//usuario root ou administrador do banco
        String password = "Rbs@2014";//senha do usuário root
       
        //estabelecendo conexão com o banco
        try {
            Class.forName(driver);
            conexao = DriverManager.getConnection(url,user,password);
            return conexao;
        } catch (Exception e) {
            // a linha abaixo serve de apoio para esclarecer o erro
            //System.out.println(e.getMessage());
            return null;
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
