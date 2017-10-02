package Controle;

import Modelo.ModeloFornecedor;
import Modelo.ModeloProduto;
import dao.ModuloConexao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ControleProduto {

    ModuloConexao conexao = new ModuloConexao();
    ModuloConexao conexaoFornecedor = new ModuloConexao();
    PreparedStatement pst;
    ResultSet rs;
    ModeloProduto modeloProduto = new ModeloProduto();
    ModeloFornecedor modeloFornecedor = new ModeloFornecedor();
    int codigoFornecedor;
    String nomefornecedor;

    public void adionarProduto(ModeloProduto modelo) throws SQLException {
        conexao.conector();
        try {
            buscarCodigoDoProduto(modelo.getFornecedor().getNome());
            String sql = "insert into produto (nome_produto, quantidade, valor_custo, valor_venda, id_fornecedor)"
                    + "values (?,?,?,?,?)";
            pst = conexao.connection.prepareStatement(sql);
            pst.setString(1, modelo.getNomeProduto());
            pst.setInt(2, modelo.getQuantidadeProduto());
            pst.setFloat(3, modelo.getPrecoCompra());
            pst.setFloat(4, modelo.getPrecoVenda());
            pst.setInt(5, codigoFornecedor);

            int adicionado = pst.executeUpdate();
            if (adicionado > 0) {
                JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir o produto!\nErro: " + ex);
        }
        conexao.desconecta();
    }

    private void buscarCodigoDoProduto(String nome) {
        conexao.conector();
        String sql = "select * from fornecedor where nome_fornecedor = '" + nome + "'";
        try {
            pst = conexao.connection.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {
                codigoFornecedor = rs.getInt("id_fornecedor");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar o código!\nErro: " + ex);
        }
    }

    public ModeloProduto buscarProduto(ModeloProduto modelo) throws SQLException {

        conexao.conector();
        conexao.SQL("select * from produto where nome_produto like '%" + modelo.getPesquisa() + "%'");
        conexao.rs.first();
        try {
            if (conexao.rs.isBeforeFirst()) {
                JOptionPane.showMessageDialog(null, "Produto não cadastrado!");
            }
            buscarNomeFornecedor(conexao.rs.getInt("id_fornecedor"));
            modeloProduto.setIdProduto(conexao.rs.getInt("id_produto"));
            modeloProduto.setNomeProduto(conexao.rs.getString("nome_produto"));
            modeloProduto.setQuantidadeProduto(conexao.rs.getInt("quantidade"));
            modeloProduto.setPrecoCompra(conexao.rs.getFloat("valor_custo"));
            modeloProduto.setPrecoVenda(conexao.rs.getFloat("valor_venda"));
            modeloFornecedor.setNome(nomefornecedor);
            modeloProduto.setFornecedor(modeloFornecedor);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar o produto!\nErro: " + ex);
        }
        conexao.desconecta();
        return modeloProduto;
    }

    private void buscarNomeFornecedor(int codigo) {

        conexaoFornecedor.conector();
        conexaoFornecedor.SQL("select * from fornecedor where id_fornecedor = " + codigo + " ");
        try {
            conexaoFornecedor.rs.first();
            nomefornecedor = conexaoFornecedor.rs.getString("nome_fornecedor");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar o código!\nErro: " + ex);
        }
        conexaoFornecedor.desconecta();
    }

    public void alterProduto(ModeloProduto modelo) {
        buscarCodigoDoProduto(modelo.getFornecedor().getNome());
        conexao.conector();
        try {
            pst = conexao.connection.prepareStatement("update produto set nome_produto=?,quantidade=?,"
                    + "valor_custo=?,valor_venda=?,id_fornecedor=? where id_produto=?");
            pst.setString(1, modelo.getNomeProduto());
            pst.setInt(2, modelo.getQuantidadeProduto());
            pst.setFloat(3, modelo.getPrecoCompra());
            pst.setFloat(4, modelo.getPrecoVenda());
            pst.setInt(5, codigoFornecedor);
            pst.setInt(6, modelo.getIdProduto());
            int adicionado = pst.executeUpdate();
            if (adicionado > 0) {
                JOptionPane.showMessageDialog(null, "Produto alterado com sucesso!");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao alterar produto!\nErro: " + ex);
        }
        conexao.desconecta();
    }

    public void excluirProduto(ModeloProduto modelo) {
        conexao.conector();
        try {
            pst = conexao.connection.prepareStatement("delete from produto where id_produto=?");
            pst.setInt(1, modelo.getIdProduto());
            int adicionado = pst.executeUpdate();
            if (adicionado > 0) {
                JOptionPane.showMessageDialog(null, "Produto excluido com sucesso!");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir produto!\nErro: " + ex);
        }
        conexao.desconecta();
    }
}
