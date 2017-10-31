/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle;

import Modelo.ModeloCliente;
import Modelo.ModeloVenda;
import Visao.Venda;
import dao.Conectar;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Dell
 */
public class ControleVenda {

    ModeloCliente modeloCliente = new ModeloCliente();
    private Connection conexao = null;
    private Connection conectar = null;
    private Connection conect = null;
    private Connection conector = null;
    private Connection conn = null;
    private Connection conecta = null;
    private Connection conex = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;
    private int idCliente;
    private int id_venda;

    public void pesquisar_venda(ModeloVenda modeloVenda, JTable TablePesquisa) throws SQLException {
        this.conexao = new Conectar().openConnection();
        String sql = "select cliente.id_cliente as 'Código do cliente', cliente.nome_cliente as 'Nome do cliente' from cliente where cliente.nome_cliente like ?";
        try {
            pst = conexao.prepareStatement(sql);
            //passando o conteudo da caixa de pesquisa para o ?
            //atenção ao "%" - continuação da string sql
            pst.setString(1, modeloVenda.getPesquisar() + "%");
            rs = pst.executeQuery();
            // a linha abaixo usa a biblioteca rs2xml.jar para preencher a tabela
            TablePesquisa.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        this.conexao.close();
    }

    public void pesquisar_itens_venda(JTable TableItensVenda) throws SQLException {
        this.conexao = new Conectar().openConnection();
        String sql = "select produto.nome_produto as 'Produto',itens_venda.quntidade as 'Quantidade',itens_venda.valor_da_venda as 'Valor por item',(itens_venda.valor_da_venda * itens_venda.quntidade) as 'Valor total' from produto join itens_venda on produto.id_produto = itens_venda.id_produto join venda on venda.id_venda = itens_venda.id_venda where venda.id_venda = ? group by produto.nome_produto, itens_venda.quntidade";
        try {
            pst = conexao.prepareStatement(sql);
            //passando o conteudo da caixa de pesquisa para o ?
            //atenção ao "%" - continuação da string sql
            pst.setInt(1, this.id_venda);
            rs = pst.executeQuery();
            // a linha abaixo usa a biblioteca rs2xml.jar para preencher a tabela
            TableItensVenda.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        this.conexao.close();
    }

    public void registroVenda(ModeloVenda modeloVenda) throws SQLException {
        this.conexao = new Conectar().openConnection();
        String sql = "insert into venda (data_venda,valor_venda,id_cliente) values (?,?,?)";
        try {
            pst = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, modeloVenda.getData());
            pst.setFloat(2, 0);
            pst.setInt(3, 0);
            pst.execute();

            // Recupera o id_venda
            rs = pst.getGeneratedKeys();
            if (rs.next()) {
                id_venda = rs.getInt(1);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        this.conexao.close();
    }

    public void updateVenda(ModeloVenda modeloVenda) throws SQLException {
        try {
            buscarIdClienteVenda(modeloVenda);
            this.conexao = new Conectar().openConnection();
            String sql = "update venda set valor_venda = ?,"
                    + "id_cliente = ?"
                    + "where id_venda = ?";
            pst = conexao.prepareStatement(sql);
            pst.setFloat(1, modeloVenda.getValorVenda());
            pst.setInt(2, this.idCliente);
            pst.setInt(3, this.id_venda);
            int atualizado = pst.executeUpdate();
            if (atualizado > 0) {
                JOptionPane.showMessageDialog(null, "Venda finalizada com sucesso!");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        conexao.close();
    }

    private void buscarIdClienteVenda(ModeloVenda modeloVenda) throws SQLException {
        this.conector = new Conectar().openConnection();
        String sql = "select * from cliente where nome_cliente ='" + modeloVenda.getCliente().getNome() + "'";
        try {
            pst = conector.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {
                this.idCliente = rs.getInt("id_cliente");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        conector.close();
    }

    public void adicionarItem(ModeloVenda modeloVenda) throws SQLException {
        this.conexao = new Conectar().openConnection();
        String sql = "insert into itens_venda (id_venda, id_produto, quntidade, valor_da_venda) values (?,?,?,?)";
        pst = conexao.prepareStatement(sql);
        pst.setInt(1, this.id_venda);
        pst.setInt(2, Venda.idProduto);//pega o id_produto do produto
        pst.setInt(3, modeloVenda.getQuantidade());
        pst.setInt(4, modeloVenda.getValorIten());
        int adicionado = pst.executeUpdate();
        baixarItem(modeloVenda);//baixar no estoque
        if (adicionado > 0) {
            JOptionPane.showMessageDialog(null, "Item: " + modeloVenda.getProduto().getNomeProduto() + " adicionado lista!");
        }
        conexao.close();
    }

    private void baixarItem(ModeloVenda modeloVenda) throws SQLException {//baixar no estoque
        this.conectar = new Conectar().openConnection();
        int quantidade = 0, resultado = 0;
        String sql = "select * from produto where nome_produto='" + modeloVenda.getProduto().getNomeProduto() + "'";
        pst = conexao.prepareStatement(sql);
        rs = pst.executeQuery();
        if (rs.next()) {
            quantidade = rs.getInt("quantidade");
        }
        resultado = quantidade - modeloVenda.getQuantidade();
        updateItem(modeloVenda, resultado);//baixar no estoque passando o nome e a quantidade
        conectar.close();
    }

    private void updateItem(ModeloVenda modeloVenda, int resultado) throws SQLException {//baixando no estoque
        this.conect = new Conectar().openConnection();
        String sql = "update produto set quantidade = ? where nome_produto = ?";
        pst = conexao.prepareStatement(sql);
        pst.setInt(1, resultado);
        pst.setString(2, modeloVenda.getProduto().getNomeProduto());
        pst.execute();
        conect.close();
    }
    
    private void estornoDoProduto(ModeloVenda modeloVenda, int resultado) throws SQLException {//baixando no estoque
        this.conn = new Conectar().openConnection();
        String sql = "update produto set quantidade = ? where id_produto = ?";
        pst = conn.prepareStatement(sql);
        pst.setInt(1, resultado);
        pst.setInt(2, modeloVenda.getProduto().getIdProduto());
        pst.execute();
        conn.close();
    }

    public void excluirVenda(ModeloVenda modeloVenda) throws SQLException {
        excluirItensVenda(modeloVenda);
        this.conexao = new Conectar().openConnection();
        try {
            pst = conexao.prepareStatement("delete from venda where id_venda=?");
            pst.setInt(1, modeloVenda.getIdVenda());
            int adicionado = pst.executeUpdate();
            if (adicionado > 0) {
                JOptionPane.showMessageDialog(null, "Venda cancelada com sucesso!");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Venda não excluida!\nErro: " + ex);
        }
        conexao.close();
    }

    private void excluirItensVenda(ModeloVenda modeloVenda) throws SQLException {
        pesquisarItensVenda(modeloVenda);
        this.conex = new Conectar().openConnection();
        try {
            pst = conex.prepareStatement("delete from itens_venda where id_venda=?");
            pst.setInt(1, modeloVenda.getIdVenda());
            pst.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "itens_venda não excluido!\nErro: " + ex);
        }
        conex.close();
    }

    private void pesquisarItensVenda(ModeloVenda modeloVenda) throws SQLException {//baixar no estoque
        this.conecta = new Conectar().openConnection();
        int quantidadeEstoque = 0, quantidadeVendida = 0, resultado;
        String sql = "select * from venda \n"
                + "join itens_venda on venda.id_venda = itens_venda.id_venda\n"
                + "join produto on itens_venda.id_produto = produto.id_produto\n"
                + "where venda.valor_venda = 0;";
        pst = conecta.prepareStatement(sql);
        rs = pst.executeQuery();
        if (rs.next()) {
            quantidadeEstoque = rs.getInt("quantidade");
            quantidadeVendida = rs.getInt("quntidade");
        }
        resultado = quantidadeEstoque + quantidadeVendida;
        estornoDoProduto(modeloVenda, resultado);//baixar no estoque passando o nome e a quantidade
        conecta.close();
    }

    public int getId_venda() {
        return id_venda;
    }
}
