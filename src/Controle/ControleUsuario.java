/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle;

import Modelo.ModeloEndereco;
import Modelo.ModeloUsuario;
import Visao.Home;
import dao.Conectar;
import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.Image;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.*;


public class ControleUsuario{
    //criando variaveis especiais para conexão com o banco
    //Prepared Statement e ResultSet são framework do pacote java.sql
    //e serve para preparar e executar as instruções sql

    private Connection conexaoUsuario = null;
    private Connection conexaoUser = null;
    private ResultSet rs = null;
    private PreparedStatement pst = null;
    private int idEndereco;
    ModeloEndereco modeloEndereco = new ModeloEndereco();
    ModeloEndereco modelEndereco = new ModeloEndereco();

    public void logar(ModeloUsuario modeloUsuario) throws SQLException {
        this.conexaoUsuario = new Conectar().openConnection();

        String slq = "select * from usuario where nome_login = ? and senha = ?";
        try {
            //as linhas abaixo preparam a consulta ao banco em função do 
            //que foi digitado nas caixas de textos 
            //o "?" é substituido pelo conteúdos das variáveis           
            pst = conexaoUsuario.prepareStatement(slq);
            pst.setString(1, modeloUsuario.getNome_login().toUpperCase());
            pst.setString(2, modeloUsuario.getSenha());

            rs = pst.executeQuery();//a linha executa a query

            if (rs.next()) {//se existir usuario e senha correspondente

                String perfil = rs.getString(4);//a linha contém o conteúdo do campo perfil da tabela login
                //System.out.println(perfil);
                // a estrutura abaixo faz o tratamento do perfil do usuario
                if (perfil.equalsIgnoreCase("Admin")) {
                    Home h = new Home();//estância da tela principal
                    h.setVisible(true);// chama a tela principal
                    Home.MenuRelatorio.setEnabled(true);
                    Home.MenuUsuario.setEnabled(true);
                    Home.LabelTipoUser.setText(rs.getString(4));//aqui seta o tipo do usuario conforme o banco de dados
                    Home.LblUsuario.setText(rs.getString(2));//aqui seta o usuario logado conforme o banco de dados
                    Home.LabelTipoUser.setForeground(Color.RED);

                    pst.close();
                } else if (perfil.equalsIgnoreCase("User")) {
                    Home h = new Home();//estância da tela principal
                    Home.LabelTipoUser.setText(rs.getString(4));//aqui seta o tipo do usuario conforme o banco de dados
                    Home.LblUsuario.setText(rs.getString(2));//aqui seta o usuario logado conforme o banco de dados
                    h.setVisible(true);// chama a tela principal  */    

                    pst.close();
                } else {
                    JOptionPane.showMessageDialog(null, "Perfil desconhecido!");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Usuário e/ou Senha Inválido(s)!");
            }
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        conexaoUsuario.close();
    }

    public void atualizarUsuario(ModeloUsuario modeloUsuario, ModeloEndereco modeloEndereco) throws SQLException {

        this.conexaoUsuario = new Conectar().openConnection();

        try {
            buscarIdDoEndereco(modeloUsuario);//metodo para buscar o id do endereço referente ao usuario correspondente
            
            String sql2 = "update usuario set nome_login=?,senha=?,perfil=?,celular=?,email=?,id_endereco=? where id_usuario=?";
            pst = conexaoUsuario.prepareStatement(sql2);
            pst.setString(1, modeloUsuario.getNome_login().toUpperCase());
            pst.setString(2, modeloUsuario.getSenha());
            pst.setString(3, modeloUsuario.getPerfil());
            pst.setString(4, modeloUsuario.getCelular());
            pst.setString(5, modeloUsuario.getEmail());
            pst.setInt(6, idEndereco);//variável que contém o id do endereço correspondente
            pst.setInt(7, modeloUsuario.getId_usuario());
            pst.executeUpdate();

            String sql = "update endereco set nome_endereco=?,numero=?,estado_endereco=?,Cidade_endereco=?,bairro_endereco=? where id_endereco=?";
            pst = conexaoUsuario.prepareStatement(sql);
            pst.setString(1, modeloEndereco.getRua());
            pst.setInt(2, modeloEndereco.getNumero());
            pst.setString(3, modeloEndereco.getEstado());
            pst.setString(4, modeloEndereco.getCidade());
            pst.setString(5, modeloEndereco.getBairro());
            pst.setInt(6, idEndereco);//variável que contém o id do endereço correspondente

            int adicionado = pst.executeUpdate();

            if (adicionado > 0) {
                JOptionPane.showMessageDialog(null, "Dados do Usuario " + modeloUsuario.getNome_login().toUpperCase() + " alterado com sucesso!");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar Usuario!\nErro: " + ex);
        }
        conexaoUsuario.close();
    }

    public ModeloUsuario consultar(ModeloUsuario modeloUsuario) throws SQLException {
        this.conexaoUsuario = new Conectar().openConnection();
        try {
            String sql = "select * from usuario where id_usuario = " + modeloUsuario.getId_usuario();

            pst = conexaoUsuario.prepareStatement(sql);
            rs = pst.executeQuery();

            if (rs.next()) {//se a conexão for verdadeira
                //as linhas abaixo consulta os valores que são digitados pelo usuário
                modeloUsuario.setNome_login(rs.getString("nome_login").toUpperCase());
                modeloUsuario.setSenha(rs.getString("senha"));
                modeloUsuario.setPerfil(rs.getString("perfil").toUpperCase());// a linha se refere especificamente ao combobox
                modeloUsuario.setCelular(rs.getString("celular"));
                modeloUsuario.setEmail(rs.getString("email"));
                modeloEndereco.setId(rs.getInt("id_endereco"));              
                buscarDadosEndereco(modeloEndereco);//metodo para buscar os dados do endereço referente ao seu id
                modeloUsuario.setEndereco(modelEndereco);
            } 
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao consultar o usuário!\nErro: " + ex);
        }
        conexaoUsuario.close();
        return modeloUsuario;
    }

    public void adicionarUsuario(ModeloUsuario modeloUsuario, ModeloEndereco modeloEndereco) throws SQLException {//metodo para adicionar usuário
        this.conexaoUsuario = new Conectar().openConnection();
        try {

            String endereco = "insert into endereco (nome_endereco,numero,estado_endereco,Cidade_endereco,bairro_endereco)"
                    + "values (?,?,?,?,?)";
            pst = conexaoUsuario.prepareStatement(endereco, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, modeloEndereco.getRua());
            pst.setInt(2, modeloEndereco.getNumero());
            pst.setString(3, modeloEndereco.getEstado());
            pst.setString(4, modeloEndereco.getCidade());
            pst.setString(5, modeloEndereco.getBairro());
            pst.executeUpdate();

            // Recupera a idEndereco
            int id_Endereco = 0;
            rs = pst.getGeneratedKeys();
            if (rs.next()) {
                id_Endereco = rs.getInt(1);
            }

            String sql = "insert into usuario (id_usuario,nome_login,senha,perfil,celular,email,id_endereco) values (?,?,?,?,?,?,?)";
            pst = conexaoUsuario.prepareStatement(sql);
            pst.setInt(1, modeloUsuario.getId_usuario());
            pst.setString(2, modeloUsuario.getNome_login().toUpperCase());
            pst.setString(3, modeloUsuario.getSenha());
            pst.setString(4, modeloUsuario.getPerfil());
            pst.setString(5, modeloUsuario.getCelular());
            pst.setString(6, modeloUsuario.getEmail());
            pst.setInt(7, id_Endereco);

            int adicionado = pst.executeUpdate();

            if (adicionado > 0) {
                JOptionPane.showMessageDialog(null, "Usuário adicionado com sucesso!");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Usuáiro já cadastrado!");
        }
        conexaoUsuario.close();
    }

    public void excluirUsuario(ModeloUsuario modeloUsuario) throws SQLException {

        this.conexaoUsuario = new Conectar().openConnection();

        String sql = "delete from usuario where id_usuario = ?";
        try {
            pst = conexaoUsuario.prepareStatement(sql);
            pst.setInt(1, modeloUsuario.getId_usuario());

            int apagado = pst.executeUpdate();

            if (apagado > 0) {
                JOptionPane.showMessageDialog(null, "Usuario removido com sucesso!");

            } else {
                JOptionPane.showMessageDialog(null, "Usuario não cadastrado!");//se não houver usuário com o código pesquisado essa mensagem aparece para o usuário
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        conexaoUsuario.close();
    }

    private void buscarIdDoEndereco(ModeloUsuario modeloUsuario) {
        this.conexaoUsuario = new Conectar().openConnection();
        String sql = "select * from usuario where id_usuario = '" + modeloUsuario.getId_usuario() + "'";
        try {
            pst = conexaoUsuario.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {
                idEndereco = rs.getInt("id_endereco");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar o código de endereço!\nErro: " + ex);
        }
    }

    private void buscarDadosEndereco(ModeloEndereco modeloEndereco) throws SQLException {

        this.conexaoUser = new Conectar().openConnection();

        String sql = "select * from endereco where id_endereco = " + modeloEndereco.getId() + " ";
        pst = conexaoUser.prepareStatement(sql);
        rs = pst.executeQuery();
        try {
            if (rs.next()) {
                modelEndereco.setRua(rs.getString("nome_endereco").toUpperCase());
                modelEndereco.setNumero(rs.getInt("numero"));
                modelEndereco.setEstado(rs.getString("estado_endereco").toUpperCase());
                modelEndereco.setCidade(rs.getString("Cidade_endereco").toUpperCase());
                modelEndereco.setBairro(rs.getString("bairro_endereco").toUpperCase());
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar o código!\nErro: " + ex);
        }
        conexaoUser.close();
    }
}
