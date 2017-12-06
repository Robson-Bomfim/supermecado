/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle;

import Modelo.ModeloCliente;
import Modelo.ModeloEndereco;
import dao.Conectar;
import dao.ConectarSqlServer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import static java.sql.Statement.RETURN_GENERATED_KEYS;
import javax.swing.JTable;
import net.proteanit.sql.DbUtils;

public class ControleCliente {

    ModeloEndereco modeloEndereco = new ModeloEndereco();
    private Connection conexao = null;
    private PreparedStatement pst;
    private ResultSet rs;
    private int idEndereco;

    public void adionarCliente(ModeloCliente modeloCliente) throws SQLException {
        this.conexao = new ConectarSqlServer().openConnection();
        try {
            String sql = "insert into endereco (nome_endereco,numero,estado_endereco,Cidade_endereco,bairro_endereco)"
                    + "values (?,?,?,?,?)";
            pst = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, modeloCliente.getEndereco().getRua());
            pst.setInt(2, modeloCliente.getEndereco().getNumero());
            pst.setString(3, modeloCliente.getEndereco().getEstado());
            pst.setString(4, modeloCliente.getEndereco().getCidade());
            pst.setString(5, modeloCliente.getEndereco().getBairro());
            pst.executeUpdate();

            // Recupera a idEndereco
            int id_Endereco = 0;
            rs = pst.getGeneratedKeys();
            if (rs.next()) {
                id_Endereco = rs.getInt(1);
            }

            String sql2 = "insert into cliente (nome_Cliente,telefone_cliente,cpf_cliente,email_cliente,id_endereco) values (?,?,?,?,?)";
            pst = conexao.prepareStatement(sql2);
            pst.setString(1, modeloCliente.getNome());
            pst.setString(2, modeloCliente.getTelefone());
            pst.setString(3, modeloCliente.getCpf());
            pst.setString(4, modeloCliente.getEmail());
            pst.setInt(5, id_Endereco);

            int adicionado = pst.executeUpdate();
            if (adicionado > 0) {
                JOptionPane.showMessageDialog(null, "Cliente " + modeloCliente.getNome().toUpperCase() + " cadastrado com sucesso!");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao adicionar cliente!\nErro: " + ex);
        }
        conexao.close();
    }

    public void atualizarCliente(ModeloCliente modeloCliente) throws SQLException {
        this.conexao = new ConectarSqlServer().openConnection();
        try {
            buscarIdDoEndereco(modeloCliente);//metodo para buscar o id do endereço
            String sql2 = "update cliente set nome_Cliente=?,telefone_cliente=?,email_cliente=?,id_endereco=? where cpf_cliente =?";
            pst = conexao.prepareStatement(sql2);
            pst.setString(1, modeloCliente.getNome());
            pst.setString(2, modeloCliente.getTelefone());
            pst.setString(3, modeloCliente.getEmail());
            pst.setInt(4, idEndereco);//variável que contém o id do endereço
            pst.setString(5, modeloCliente.getCpf());
            pst.executeUpdate();

            String sql = "update endereco set nome_endereco=?,numero=?,estado_endereco=?,Cidade_endereco=?,bairro_endereco=? where id_endereco=?";
            pst = conexao.prepareStatement(sql);
            pst.setString(1, modeloCliente.getEndereco().getRua());
            pst.setInt(2, modeloCliente.getEndereco().getNumero());
            pst.setString(3, modeloCliente.getEndereco().getEstado());
            pst.setString(4, modeloCliente.getEndereco().getCidade());
            pst.setString(5, modeloCliente.getEndereco().getBairro());
            pst.setInt(6, idEndereco);//variável que contém o id do endereço

            int adicionado = pst.executeUpdate();
            if (adicionado > 0) {
                JOptionPane.showMessageDialog(null, "Dados do Cliente " + modeloCliente.getNome().toUpperCase() + " alterado com sucesso!");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar cliente!\nErro: " + ex);
        }
        conexao.close();
    }

    public void pesquisar_cliente(ModeloCliente modeloCliente, JTable TableCliente) throws SQLException {

        this.conexao = new ConectarSqlServer().openConnection();

        String sql = "select nome_cliente as 'Nome',telefone_cliente as 'Telefone',cpf_cliente as 'Cpf',email_cliente as 'Email',nome_endereco as 'Rua',numero as 'Número',bairro_endereco as 'Bairro',Cidade_endereco as 'Cidade',estado_endereco as 'Estado' from cliente join endereco on endereco.id_endereco = cliente.id_endereco where nome_Cliente like ?";
        try {
            pst = conexao.prepareStatement(sql);
            //passando o conteudo da caixa de pesquisa para o ?
            //atenção ao "%" - continuação da string sql
            pst.setString(1, modeloCliente.getPesquisar() + "%");
            rs = pst.executeQuery();
            // a linha abaixo usa a biblioteca rs2xml.jar para preencher a tabela
            TableCliente.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        conexao.close();
    }

    public void ExcluirCliente(ModeloCliente modeloCliente) throws SQLException {
        this.conexao = new ConectarSqlServer().openConnection();
        String sql = "delete from cliente where cpf_cliente = ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, modeloCliente.getId());
            int apagado = pst.executeUpdate();

            if (apagado > 0) {
                JOptionPane.showMessageDialog(null, "Cliente removido com sucesso!");

            } else {
                JOptionPane.showMessageDialog(null, "Ciente não cadastrado!");//se não houver usuário com o código pesquisado essa mensagem aparece para o usuário
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        conexao.close();
    }

    private void buscarIdDoEndereco(ModeloCliente modeloCliente) {
        this.conexao = new ConectarSqlServer().openConnection();
        String sql = "select * from cliente where cpf_cliente = '" + modeloCliente.getCpf() + "'";
        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {
                idEndereco = rs.getInt("id_endereco");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar o código de endereço!\nErro: " + ex);
        }
    }
}
