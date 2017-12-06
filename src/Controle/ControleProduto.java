package Controle;

import Modelo.ModeloFornecedor;
import Modelo.ModeloProduto;
import dao.Conectar;
import dao.ConectarSqlServer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
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

    public void pesquisarHistorico(ModeloProduto modeloProduto, JTable TableHistorico) throws SQLException {
        this.conexao = new ConectarSqlServer().openConnection();
        String sql = "select idPrd as 'Código', nomeProduto as 'Produto', precoAnt as 'Preço anterior', precoNovo as 'Preço novo', convert(varchar,datAlteracao,103) as 'Data da alteração' \n"
                + "from historicoPreco where datAlteracao = ?";
        try {
            pst = conexao.prepareStatement(sql);
            SimpleDateFormat formataData = new SimpleDateFormat("yyyy-MM-dd");
            //passando o conteudo da caixa de pesquisa para o ?
            //atenção ao "%" - continuação da string sql
            pst.setString(1, formataData.format(modeloProduto.getData()));
            rs = pst.executeQuery();

            //if (rs.next()) {
            // a linha abaixo usa a biblioteca rs2xml.jar para preencher a tabela
            TableHistorico.setModel(DbUtils.resultSetToTableModel(rs));
            /*} else {
                JOptionPane.showMessageDialog(null, "Não foi alterado nenhum produto nessa data!");
            }*/
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        this.conexao.close();
    }

    public void pesquisarHistoricoPeriodo(ModeloProduto modeloProduto, ModeloProduto modelProduto, JTable TableHistorico) throws SQLException {
        this.conexao = new ConectarSqlServer().openConnection();
        String sql = "select idPrd as 'Código', nomeProduto as 'Produto', precoAnt as 'Preço anterior', precoNovo as 'Preço novo', convert(varchar,datAlteracao,103) as 'Data da alteração' \n"
                + "from historicoPreco where datAlteracao between ? and ?";
        try {
            pst = conexao.prepareStatement(sql);
            SimpleDateFormat formataData = new SimpleDateFormat("yyyy-MM-dd");
            //passando o conteudo da caixa de pesquisa para o ?
            //atenção ao "%" - continuação da string sql
            pst.setString(1, formataData.format(modeloProduto.getData()));
            pst.setString(2, formataData.format(modelProduto.getData()));
            rs = pst.executeQuery();

            //if (rs.next()) {
            // a linha abaixo usa a biblioteca rs2xml.jar para preencher a tabela
            TableHistorico.setModel(DbUtils.resultSetToTableModel(rs));
            /*} else {
                JOptionPane.showMessageDialog(null, "Não foi alterado nenhum produto nessa data!");
            }*/
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        this.conexao.close();
    }

    public void pesquisarHistoricoProduto(ModeloProduto modeloProduto, JTable TableHistorico) throws SQLException {

        this.conexao = new ConectarSqlServer().openConnection();

        String sql = "select idPrd as 'Código', nomeProduto as 'Produto', precoAnt as 'Preço anterior', precoNovo as 'Preço novo', convert(varchar,datAlteracao,103) as 'Data da alteração' \n"
                + "from historicoPreco where nomeProduto like ?";
        try {
            pst = conexao.prepareStatement(sql);
            //passando o conteudo da caixa de pesquisa para o ?
            //atenção ao "%" - continuação da string sql
            pst.setString(1, modeloProduto.getPesquisa() + "%");
            rs = pst.executeQuery();
            // a linha abaixo usa a biblioteca rs2xml.jar para preencher a tabela
            TableHistorico.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        conexao.close();
    }

    public void pesquisarView(ModeloProduto modeloProduto, JTable TableEstoque) throws SQLException {

        this.conexao = new ConectarSqlServer().openConnection();

        String sql = "select Codigo as 'Código', Fornecedor, Produto, convert(varchar,DataCompra,103) as 'Data', ValorTotal "
                + "from V_Compra_Produto where V_Compra_Produto.Fornecedor like ?";
        try {
            pst = conexao.prepareStatement(sql);
            //passando o conteudo da caixa de pesquisa para o ?
            //atenção ao "%" - continuação da string sql
            pst.setString(1, modeloProduto.getPesquisa() + "%");
            rs = pst.executeQuery();
            // a linha abaixo usa a biblioteca rs2xml.jar para preencher a tabela
            TableEstoque.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        conexao.close();
    }

    public void adionarProduto(ModeloProduto modelo) throws SQLException {
        this.conexao = new ConectarSqlServer().openConnection();
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
        this.conexao = new ConectarSqlServer().openConnection();
        String sql = "select * from fornecedor where nome_fornecedor = '" + modelo.getFornecedor().getNome() + "'";
        pst = conexao.prepareStatement(sql);
        rs = pst.executeQuery();
        if (rs.next()) {
            idFornecedor = rs.getInt("id_fornecedor");
        }
    }

    public void alterProduto(ModeloProduto modelo) throws SQLException {
        this.conexao = new ConectarSqlServer().openConnection();
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
        this.conexao = new ConectarSqlServer().openConnection();
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

        this.conexao = new ConectarSqlServer().openConnection();

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

        this.conexao = new ConectarSqlServer().openConnection();

        String sql = "select produto.id_produto as 'Código',"
                + " produto.nome_produto as 'Nome', "
                + "produto.quantidade as 'Quantidade em estoque', "
                + "produto.valor_venda as 'Valor por item' "
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
