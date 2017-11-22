/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle;

import Modelo.ModeloCliente;
import Modelo.ModeloVenda;
import Visao.Home;
import Visao.Venda;
import dao.Conectar;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Dell
 */
public class ControleVenda {

    ModeloCliente modeloCliente = new ModeloCliente();
    ControleUsuario controleUsuario = new ControleUsuario();
    private Connection conexao = null;
    private Connection conector = null;
    private Connection conect = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;
    private int idCliente;
    private int id_venda;
    private int quantidadeLista;

    public void pesquisar_venda(ModeloVenda modeloVenda, JTable TablePesquisa) throws SQLException {
        this.conexao = new Conectar().openConnection();
        String sql = "select cliente.id_cliente as 'Código do cliente',"
                + " cliente.nome_cliente as 'Nome do cliente' "
                + "from cliente where cliente.nome_cliente like ?";
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

    public void pesquisarVenda(ModeloVenda modeloVenda, JTable TablePesquisa) throws SQLException {
        this.conexao = new Conectar().openConnection();
        String sql = "select venda.id_venda as 'Código',strftime('%d/%m/%Y',venda.data_venda)as 'Data',venda.valor_venda as 'Valor total', cliente.nome_cliente as 'Cliente', usuario.nome_login as 'Usuario responsável' from venda join cliente on venda.id_cliente = cliente.id_cliente join usuario on usuario.id_usuario = venda.id_usuario  where venda.data_venda = ?";
        try {
            pst = conexao.prepareStatement(sql);
            SimpleDateFormat formataData = new SimpleDateFormat("yyyy-MM-dd");
            //passando o conteudo da caixa de pesquisa para o ?
            //atenção ao "%" - continuação da string sql
            pst.setString(1, formataData.format(modeloVenda.getData()));
            rs = pst.executeQuery();
            // a linha abaixo usa a biblioteca rs2xml.jar para preencher a tabela
            TablePesquisa.setModel(DbUtils.resultSetToTableModel(rs));
            //JOptionPane.showMessageDialog(null, "Não existe venda realizada no período selecionado!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        this.conexao.close();
    }

    public void pesquisarVendaPeriodo(ModeloVenda modeloVenda, ModeloVenda modelVenda, JTable TablePesquisa) throws SQLException {
        this.conexao = new Conectar().openConnection();
        String sql = "select venda.id_venda as 'Código',strftime('%d/%m/%Y',venda.data_venda)as 'Data',venda.valor_venda as 'Valor total', cliente.nome_cliente as 'Cliente', usuario.nome_login as 'Usuario responsável' from venda join cliente on venda.id_cliente = cliente.id_cliente join usuario on usuario.id_usuario = venda.id_usuario  where venda.data_venda between ? and ?";
        try {
            pst = conexao.prepareStatement(sql);
            SimpleDateFormat formataData = new SimpleDateFormat("yyyy-MM-dd");
            //passando o conteudo da caixa de pesquisa para o ?
            //atenção ao "%" - continuação da string sql
            pst.setString(1, formataData.format(modeloVenda.getData()));
            pst.setString(2, formataData.format(modelVenda.getData()));
            rs = pst.executeQuery();
            // a linha abaixo usa a biblioteca rs2xml.jar para preencher a tabela
            TablePesquisa.setModel(DbUtils.resultSetToTableModel(rs));
            //JOptionPane.showMessageDialog(null, "Não existe venda realizada no período selecionado!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        this.conexao.close();
    }

    public void pesquisar_itens_venda(JTable TableItensVenda) throws SQLException {
        this.conexao = new Conectar().openConnection();
        String sql = "select produto.id_produto as 'Código',"
                + "produto.nome_produto as 'Produto',"
                + "itens_venda.quntidade as 'Quantidade',"
                + "itens_venda.valor_da_venda as 'Valor p/ item',"
                + "(itens_venda.valor_da_venda * itens_venda.quntidade) as 'Valor total' "
                + "from produto join itens_venda on produto.id_produto = itens_venda.id_produto "
                + "join venda on venda.id_venda = itens_venda.id_venda where venda.id_venda = ? "
                + "group by produto.nome_produto, itens_venda.quntidade";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setInt(1, this.id_venda);
            rs = pst.executeQuery();
            // a linha abaixo usa a biblioteca rs2xml.jar para preencher a tabela
            TableItensVenda.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        this.conexao.close();
    }

    public void pesquisarItensVenda(JTable TableItensVenda, int idVenda) throws SQLException {
        this.conexao = new Conectar().openConnection();
        String sql = "select produto.id_produto as 'Código',"
                + "produto.nome_produto as 'Produto',"
                + "itens_venda.quntidade as 'Quantidade',"
                + "itens_venda.valor_da_venda as 'Valor por item',"
                + "(itens_venda.valor_da_venda * itens_venda.quntidade) as 'Valor total' "
                + "from produto join itens_venda on produto.id_produto = itens_venda.id_produto "
                + "join venda on venda.id_venda = itens_venda.id_venda where venda.id_venda = ? "
                + "group by produto.nome_produto, itens_venda.quntidade";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setInt(1, idVenda);
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
        String sql = "insert into venda (valor_venda,id_usuario,id_cliente) values (?,?,?)";
        try {
            pst = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pst.setFloat(1, 0);
            pst.setInt(2, 0);
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

    public void updateFinalizarVenda(ModeloVenda modeloVenda) throws SQLException {
        try {
            buscarIdClienteVenda(modeloVenda);
            this.conexao = new Conectar().openConnection();
            String sql = "update venda set valor_venda = ?,"
                    + "id_cliente = ?,"
                    + "id_usuario = ?"
                    + "where id_venda = ?";
            pst = conexao.prepareStatement(sql);
            pst.setDouble(1, modeloVenda.getValorVenda());
            pst.setInt(2, this.idCliente);
            pst.setInt(3, Home.idUser);
            pst.setInt(4, this.id_venda);
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
        pst.setDouble(4, modeloVenda.getValorIten());
        int adicionado = pst.executeUpdate();
        baixarItem(modeloVenda);//baixar no estoque
        if (adicionado > 0) {
            JOptionPane.showMessageDialog(null, "Item: " + modeloVenda.getProduto().getNomeProduto() + " adicionado lista!");
        }
        conexao.close();
    }

    private void baixarItem(ModeloVenda modeloVenda) throws SQLException {//baixar no estoque
        int quantidade = 0, resultado;
        String sql = "select * from produto where nome_produto='" + modeloVenda.getProduto().getNomeProduto() + "'";
        pst = conexao.prepareStatement(sql);
        rs = pst.executeQuery();
        if (rs.next()) {
            quantidade = rs.getInt("quantidade");
        }
        resultado = quantidade - modeloVenda.getQuantidade();
        updateItem(modeloVenda, resultado);//baixar no estoque passando o nome e a quantidade
    }

    private void updateItem(ModeloVenda modeloVenda, int resultado) throws SQLException {//baixando no estoque
        String sql = "update produto set quantidade = ? where nome_produto = ?";
        pst = conexao.prepareStatement(sql);
        pst.setInt(1, resultado);
        pst.setString(2, modeloVenda.getProduto().getNomeProduto());
        pst.execute();
    }

    public void excluirVenda(ModeloVenda modeloVenda) throws SQLException {//método para excluir os itens da venda
        this.conexao = new Conectar().openConnection();
        excluirItensVenda(modeloVenda);//método chamado para excluir os itens da tabela "itens_venda" primeiro
        try {
            pst = conexao.prepareStatement("delete from venda where id_venda=?");
            pst.setInt(1, modeloVenda.getIdvenda());
            int adicionado = pst.executeUpdate();
            if (adicionado > 0) {
                JOptionPane.showMessageDialog(null, "Venda cancelada com sucesso!");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Venda não excluida!\nErro: " + ex);
        }
        conexao.close();
    }

    private void excluirItensVenda(ModeloVenda modeloVenda) throws SQLException {//método para excluir os itens da tabela "itens_venda"
        this.conexao = new Conectar().openConnection();
        pesquisarVenda();//método chamado para pesquisar os itens antes de excluir
        try {
            pst = conexao.prepareStatement("delete from itens_venda where id_venda=?");
            pst.setInt(1, modeloVenda.getIdvenda());
            pst.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "itens_venda não excluido!\nErro: " + ex);
        }
    }

    private void pesquisarVenda() throws SQLException {//baixar no estoque
        int quantidadeEstoque, quantidadeVendida, resultado;
        String sql = "select produto.id_produto,itens_venda.quntidade,produto.quantidade from venda \n"
                + "join itens_venda on venda.id_venda = itens_venda.id_venda\n"
                + "join produto on itens_venda.id_produto = produto.id_produto\n"
                + "where venda.valor_venda = 0";
        pst = conexao.prepareStatement(sql);
        rs = pst.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id_produto");
            quantidadeVendida = rs.getInt("quntidade");
            quantidadeEstoque = rs.getInt("quantidade");
            resultado = quantidadeEstoque + quantidadeVendida;
            estornoProdutoVenda(resultado, id);//baixar no estoque passando o nome e a quantidade
        }
    }

    private void estornoProdutoVenda(int resultado, int id) throws SQLException {//baixando no estoque
        String sql = "update produto set quantidade = ? where id_produto = ?";
        pst = conexao.prepareStatement(sql);
        pst.setInt(1, resultado);
        pst.setInt(2, id);
        pst.executeUpdate();
    }

    public void excluirItensLista(int quantidade) throws SQLException {
        this.conect = new Conectar().openConnection();
        pesquisarItensVenda(quantidade);
        if (quantidadeLista <= 0) {
            try {
                pst = conect.prepareStatement("delete from itens_venda where id_produto=?");
                pst.setInt(1, Venda.idItensProduto);
                pst.executeUpdate();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "itens_venda não excluido!\nErro: " + ex);
            }
        }
        conect.close();
    }

    private void pesquisarItensVenda(int quantidade) throws SQLException {//pesquisa a quantidade vendida e no estoque e passa como parametro na variável "resultado" para fazer o estorno na tabela produto
        int quantidadeEstoque, quantidadeList, resultado = 0, result = 0;
        String sql = "select itens_venda.quntidade,produto.quantidade from venda \n"
                + "join itens_venda on venda.id_venda = itens_venda.id_venda\n"
                + "join produto on itens_venda.id_produto = produto.id_produto\n"
                + "where itens_venda.id_venda = " + this.id_venda;
        pst = conect.prepareStatement(sql);
        rs = pst.executeQuery();
        if (rs.next()) {
            quantidadeList = rs.getInt("quntidade");
            quantidadeEstoque = rs.getInt("quantidade");
            result = quantidadeList - quantidade;
            resultado = quantidadeEstoque + quantidade;
        }
        estornoDoProduto(resultado);//método que recebe a variável "resultado" para baixar no estoque a quantidade
        estornoList(result);//método que recebe a variável "result" para baixar no tabela itens_venda a quantidade
    }

    private void estornoDoProduto(int resultado) throws SQLException {//método que recebe a variável "resultado" para baixar no estoque a quantidade
        String sql = "update produto set quantidade = ? where id_produto = ?";
        pst = conect.prepareStatement(sql);
        pst.setInt(1, resultado);
        pst.setInt(2, Venda.idItensProduto);
        int adicionado = pst.executeUpdate();
        if (adicionado > 0) {
            JOptionPane.showMessageDialog(null, "Removido com sucesso!");
        }
    }

    private void consultaLista() throws SQLException {
        String sql = "select * from itens_venda where id_produto = " + Venda.idItensProduto;
        pst = conect.prepareStatement(sql);
        rs = pst.executeQuery();
        if (rs.next()) {
            quantidadeLista = rs.getInt("quntidade");
        }
    }

    private void estornoList(int result) throws SQLException {//método que recebe a variável "resultado" para baixar no estoque a quantidade
        String sql = "update itens_venda set quntidade = ? where id_produto = ?";
        pst = conect.prepareStatement(sql);
        pst.setInt(1, result);
        pst.setInt(2, Venda.idItensProduto);
        pst.executeUpdate();    
        consultaLista();
    }

    public int getId_venda() {
        return id_venda;
    }
}
