/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle;

import Modelo.ModeloCliente;
import Modelo.ModeloEndereco;
import dao.Conectar;
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
    private int codigoEndereco;

    public void adionarCliente(ModeloCliente modeloCliente, ModeloEndereco modeloEndereco) throws SQLException {
        this.conexao = new Conectar().openConnection();
        try {
            String sql = "insert into endereco (nome_endereco,numero,estado_endereco,Cidade_endereco,bairro_endereco)"
                    + "values (?,?,?,?,?)";
            pst = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, modeloEndereco.getRua());
            pst.setInt(2, modeloEndereco.getNumero());
            pst.setString(3, modeloEndereco.getEstado());
            pst.setString(4, modeloEndereco.getCidade());
            pst.setString(5, modeloEndereco.getBairro());
            pst.executeUpdate();

            // Recupera a id
            int id = 0;
            rs = pst.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
            }

            String sql2 = "insert into cliente (nome_Cliente,telefone_cliente,cpf_cliente,cnpj_cliente,email_cliente,id_endereco) values (?,?,?,?,?,?)";
            pst = conexao.prepareStatement(sql2);
            pst.setString(1, modeloCliente.getNome());
            pst.setString(2, modeloCliente.getTelefone());
            pst.setString(3, modeloCliente.getCpf());
            pst.setString(4, modeloCliente.getCnpj());
            pst.setString(5, modeloCliente.getEmail());
            pst.setInt(6, id);

            int adicionado = pst.executeUpdate();
            if (adicionado > 0) {
                JOptionPane.showMessageDialog(null, "Cliente " + modeloCliente.getNome().toUpperCase() + " cadastrado com sucesso!");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao adicionar cliente!\nErro: " + ex);
        }
        conexao.close();
    }

    public void atualizarCliente(ModeloCliente modeloCliente, ModeloEndereco modeloEndereco) throws SQLException {
        this.conexao = new Conectar().openConnection();
        try {
            buscarCodigoDoEndereco(modeloCliente);
            String sql2 = "update cliente set nome_Cliente=?,telefone_cliente=?,cnpj_cliente=?,email_cliente=?,id_endereco=? where cpf_cliente =?";
            pst = conexao.prepareStatement(sql2);
            pst.setString(1, modeloCliente.getNome());
            pst.setString(2, modeloCliente.getTelefone());
            pst.setString(3, modeloCliente.getCnpj());
            pst.setString(4, modeloCliente.getEmail());
            pst.setInt(5, codigoEndereco);
            pst.setString(6, modeloCliente.getCpf());
            pst.executeUpdate();

            String sql = "update endereco set nome_endereco=?,numero=?,estado_endereco=?,Cidade_endereco=?,bairro_endereco=? where id_endereco=?";
            pst = conexao.prepareStatement(sql);
            pst.setString(1, modeloEndereco.getRua());
            pst.setInt(2, modeloEndereco.getNumero());
            pst.setString(3, modeloEndereco.getEstado());
            pst.setString(4, modeloEndereco.getCidade());
            pst.setString(5, modeloEndereco.getBairro());
            pst.setInt(6, codigoEndereco);

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

        this.conexao = new Conectar().openConnection();

        String sql = "select nome_cliente as 'Nome',telefone_cliente as 'Telefone',cpf_cliente as 'Cpf',cnpj_cliente as 'Cnpj',email_cliente as 'Email',nome_endereco as 'Rua',numero as 'Número',bairro_endereco as 'Bairro',Cidade_endereco as 'Cidade',estado_endereco as 'Estado' from cliente join endereco on endereco.id_endereco = cliente.id_endereco where nome_Cliente like ?";
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

        this.conexao = new Conectar().openConnection();

        String sql = "delete from cliente where cpf_cliente = ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, modeloCliente.getCpf());
            int apagado = pst.executeUpdate();

            if (apagado > 0) {
                JOptionPane.showMessageDialog(null, "Cliete removido com sucesso!");

            } else {
                JOptionPane.showMessageDialog(null, "Ciente não cadastrado!");//se não houver usuário com o código pesquisado essa mensagem aparece para o usuário
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        conexao.close();
    }

    private void buscarCodigoDoEndereco(ModeloCliente modeloCliente) {
        this.conexao = new Conectar().openConnection();
        String sql = "select * from cliente where cpf_cliente = '" + modeloCliente.getCpf() + "'";
        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {
                codigoEndereco = rs.getInt("id_endereco");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar o código de endereço!\nErro: " + ex);
        }
    }
}
