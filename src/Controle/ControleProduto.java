package Controle;

import Modelo.ModeloFornecedor;
import Modelo.ModeloProduto;
import dao.Conectar;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import net.proteanit.sql.DbUtils;

public class ControleProduto {

    private Connection conexao = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;
    private int idFornecedor;
    ModeloProduto modeloProduto = new ModeloProduto();
    ModeloFornecedor modeloFornecedor = new ModeloFornecedor();

    public void adionarProduto(ModeloProduto modelo) throws SQLException {
        this.conexao = new Conectar().openConnection();
        try {
            buscarIdDoFornecedor(modelo);//metodo para buscar o id do fornecedor
            String sql = "insert into produto (nome_produto, quantidade, valor_custo, valor_venda, id_fornecedor)"
                    + "values (?,?,?,?,?)";
            pst = conexao.prepareStatement(sql);
            pst.setString(1, modelo.getNomeProduto());
            pst.setInt(2, modelo.getQuantidadeProduto());
            pst.setDouble(3, modelo.getPrecoCompra());
            pst.setDouble(4, modelo.getPrecoVenda());
            pst.setInt(5, idFornecedor);//variável contém o id do fornecedor

            int adicionado = pst.executeUpdate();
            if (adicionado > 0) {
                JOptionPane.showMessageDialog(null, "Produto " + modelo.getNomeProduto().toLowerCase() + " cadastrado com sucesso!");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir o produto!\nErro: " + ex);
        }
        conexao.close();
    }

    private void buscarIdDoFornecedor(ModeloProduto modelo) throws SQLException {
        this.conexao = new Conectar().openConnection();
        String sql = "select * from fornecedor where nome_fornecedor = '" + modelo.getFornecedor().getNome() + "'";
        pst = conexao.prepareStatement(sql);
        rs = pst.executeQuery();
        if (rs.next()) {
            idFornecedor = rs.getInt("id_fornecedor");
        }
    }

    public void alterProduto(ModeloProduto modelo) throws SQLException {
        this.conexao = new Conectar().openConnection();
        try {
            buscarIdDoFornecedor(modelo);
            pst = conexao.prepareStatement("update produto set nome_produto=?,quantidade=?,"
                    + "valor_custo=?,valor_venda=?,id_fornecedor=? where id_produto=?");
            pst.setString(1, modelo.getNomeProduto());
            pst.setInt(2, modelo.getQuantidadeProduto());
            pst.setDouble(3, modelo.getPrecoCompra());
            pst.setDouble(4, modelo.getPrecoVenda());
            pst.setInt(5, idFornecedor);//variável que contém o id do fornecedor
            pst.setInt(6, modelo.getIdProduto());

            int adicionado = pst.executeUpdate();
            if (adicionado > 0) {
                JOptionPane.showMessageDialog(null, "Produto " + modelo.getNomeProduto().toLowerCase() + " alterado com sucesso!");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao alterar produto!\nErro: " + ex);
        }
        conexao.close();
    }

    public void excluirProduto(ModeloProduto modelo) throws SQLException {
        this.conexao = new Conectar().openConnection();
        try {
            pst = conexao.prepareStatement("delete from produto where id_produto=?");
            pst.setInt(1, modelo.getIdProduto());
            int adicionado = pst.executeUpdate();
            if (adicionado > 0) {
                JOptionPane.showMessageDialog(null, "Produto excluido com sucesso!");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Produto não cadastrado!\nErro: " + ex);
        }
        conexao.close();
    }

    public void pesquisar_Produto(ModeloProduto modeloProduto, JTable TabelaProduto) throws SQLException {

        this.conexao = new Conectar().openConnection();

        String sql = "select  id_produto as 'Código',nome_produto as 'Nome',quantidade as 'Quantidade', valor_custo as 'Valor de Compra', valor_venda as 'Valor de Venda', nome_fornecedor as 'Nome do Fornecedor' \n"
                + "from produto join fornecedor on fornecedor.id_fornecedor = produto.id_fornecedor  where nome_produto like ?";
        try {
            pst = conexao.prepareStatement(sql);
            //passando o conteudo da caixa de pesquisa para o ?
            //atenção ao "%" - continuação da string sql
            pst.setString(1, modeloProduto.getPesquisa() + "%");
            rs = pst.executeQuery();
            // a linha abaixo usa a biblioteca rs2xml.jar para preencher a tabela
            TabelaProduto.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        conexao.close();
    }

    public void pesquisar_produto_cliente(ModeloProduto modeloProduto, JTable TabelaProduto) throws SQLException {

        this.conexao = new Conectar().openConnection();

        String sql = "select produto.id_produto as 'Código do produto',"
                + " produto.nome_produto as 'Nome do produto', "
                + "produto.quantidade as 'Quantidade em estoque', "
                + "produto.valor_venda as 'Valor de venda' "
                + "from produto\n"
                + "where produto.nome_produto like ?";
        try {
            pst = conexao.prepareStatement(sql);
            //passando o conteudo da caixa de pesquisa para o ?
            //atenção ao "%" - continuação da string sql
            pst.setString(1, modeloProduto.getPesquisa() + "%");
            rs = pst.executeQuery();
            // a linha abaixo usa a biblioteca rs2xml.jar para preencher a tabela
            TabelaProduto.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        conexao.close();
    }
}
