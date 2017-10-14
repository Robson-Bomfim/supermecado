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

    Connection conexao = null;
    Connection conexaoFornecedor = null;
    Connection conexaoRead = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    ModeloProduto modeloProduto = new ModeloProduto();
    ModeloFornecedor modeloFornecedor = new ModeloFornecedor();
    int codigoFornecedor;
    String nomefornecedor;

    public void adionarProduto(ModeloProduto modelo) throws SQLException {
        this.conexao = new Conectar().openConnection();
        try {
            buscarCodigoDoFornecedor(modelo);
            String sql = "insert into produto (nome_produto, quantidade, valor_custo, valor_venda, id_fornecedor)"
                    + "values (?,?,?,?,?)";
            pst = conexao.prepareStatement(sql);
            pst.setString(1, modelo.getNomeProduto());
            pst.setInt(2, modelo.getQuantidadeProduto());
            pst.setFloat(3, modelo.getPrecoCompra());
            pst.setFloat(4, modelo.getPrecoVenda());
            pst.setInt(5, codigoFornecedor);

            int adicionado = pst.executeUpdate();
            if (adicionado > 0) {
                JOptionPane.showMessageDialog(null, "Produto "+modelo.getNomeProduto().toLowerCase() +" cadastrado com sucesso!");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir o produto!\nErro: " + ex);
        }
        conexao.close();
    }

    private void buscarCodigoDoFornecedor(ModeloProduto modelo) throws SQLException {
        this.conexao = new Conectar().openConnection();
        String sql = "select * from fornecedor where nome_fornecedor = '" + modelo.getFornecedor().getNome() + "'";
        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {
                codigoFornecedor = rs.getInt("id_fornecedor");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar o código!\nErro: " + ex);
        }
    }

    public ModeloProduto buscarProduto(ModeloProduto modelo) throws SQLException {

        this.conexao = new Conectar().openConnection();
        String sql = "select * from produto where nome_produto like '%" + modelo.getPesquisa() + "%'";
        pst = conexao.prepareStatement(sql);
        rs = pst.executeQuery();
        try {
            modeloProduto.setIdProduto(rs.getInt("id_produto"));
            modeloProduto.setNomeProduto(rs.getString("nome_produto"));
            modeloProduto.setQuantidadeProduto(rs.getInt("quantidade"));
            modeloProduto.setPrecoCompra(rs.getFloat("valor_custo"));
            modeloProduto.setPrecoVenda(rs.getFloat("valor_venda"));
            buscarNomeFornecedor(rs.getInt("id_fornecedor"));
            modeloFornecedor.setNome(nomefornecedor);
            modeloProduto.setFornecedor(modeloFornecedor);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Produto não cadastrado!\nErro: " + ex);
        }
        conexao.close();
        return modeloProduto;
    }

    private void buscarNomeFornecedor(int codigo) throws SQLException {

        this.conexaoFornecedor = new Conectar().openConnection();
        String sql = "select * from fornecedor where id_fornecedor = " + codigo + " ";
        pst = conexaoFornecedor.prepareStatement(sql);
        rs = pst.executeQuery();
        try {
            nomefornecedor = rs.getString("nome_fornecedor");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar o código!\nErro: " + ex);
        }
        conexaoFornecedor.close();
    }

    public void alterProduto(ModeloProduto modelo) throws SQLException {
        this.conexao = new Conectar().openConnection();
        try {
            buscarCodigoDoFornecedor(modelo);
            pst = conexao.prepareStatement("update produto set nome_produto=?,quantidade=?,"
                    + "valor_custo=?,valor_venda=?,id_fornecedor=? where id_produto=?");
            pst.setString(1, modelo.getNomeProduto());
            pst.setInt(2, modelo.getQuantidadeProduto());
            pst.setFloat(3, modelo.getPrecoCompra());
            pst.setFloat(4, modelo.getPrecoVenda());
            pst.setInt(5, codigoFornecedor);
            pst.setInt(6, modelo.getIdProduto());

            int adicionado = pst.executeUpdate();
            if (adicionado > 0) {
                JOptionPane.showMessageDialog(null, "Produto "+modelo.getNomeProduto().toLowerCase()+" alterado com sucesso!");
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
}
