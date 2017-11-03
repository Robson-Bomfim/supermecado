package Controle;

import Modelo.ModeloCliente;
import Modelo.ModeloFornecedor;
import dao.Conectar;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import net.proteanit.sql.DbUtils;

public class ControleFornecedor {

    private Connection conexaoFornecedor = null;
    private PreparedStatement pst;
    private ResultSet rs;
    private int idEndereco;

    public void preencherCombo(JComboBox ComboBoxFornecedor) throws SQLException {
        this.conexaoFornecedor = new Conectar().openConnection();
        String sql = "select * from fornecedor order by nome_fornecedor";
        try {
            pst = conexaoFornecedor.prepareStatement(sql);
            rs = pst.executeQuery();
            ComboBoxFornecedor.removeAllItems();
            while (rs.next()) {
                ComboBoxFornecedor.addItem(rs.getString("nome_fornecedor"));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao preencher combobox!\nErro: " + ex);
        }
        conexaoFornecedor.close();
    }

    public void adionarFornecedor(ModeloFornecedor modeloFornecedor) throws SQLException {
        this.conexaoFornecedor = new Conectar().openConnection();
        try {
            String sql = "insert into endereco (nome_endereco,numero,estado_endereco,Cidade_endereco,bairro_endereco)"
                    + "values (?,?,?,?,?)";
            pst = conexaoFornecedor.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, modeloFornecedor.getEndereco().getRua());
            pst.setInt(2, modeloFornecedor.getEndereco().getNumero());
            pst.setString(3, modeloFornecedor.getEndereco().getEstado());
            pst.setString(4, modeloFornecedor.getEndereco().getCidade());
            pst.setString(5, modeloFornecedor.getEndereco().getBairro());
            pst.executeUpdate();

            // Recupera a idEndereco
            int id_Endereco = 0;
            rs = pst.getGeneratedKeys();
            if (rs.next()) {
                id_Endereco = rs.getInt(1);
            }

            String sql2 = "insert into fornecedor (nome_fornecedor,cnpj_fornecedor,email_fornecedor,telefone_fornecedor,id_endereco) values (?,?,?,?,?)";
            pst = conexaoFornecedor.prepareStatement(sql2);
            pst.setString(1, modeloFornecedor.getNome());
            pst.setString(2, modeloFornecedor.getCnpj());
            pst.setString(3, modeloFornecedor.getEmail());
            pst.setString(4, modeloFornecedor.getTelefone());
            pst.setInt(5, id_Endereco);

            int adicionado = pst.executeUpdate();
            if (adicionado > 0) {
                JOptionPane.showMessageDialog(null, "Fornecedor " + modeloFornecedor.getNome().toUpperCase() + " cadastrado com sucesso!");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao adicionar fornecedor!\nErro: " + ex);
        }
        conexaoFornecedor.close();
    }

    public void pesquisar_cliente(ModeloFornecedor modeloFornecedor, JTable TableFornecedor) throws SQLException {

        this.conexaoFornecedor = new Conectar().openConnection();

        String sql = "select fornecedor.nome_fornecedor as 'Nome',fornecedor.telefone_fornecedor as 'Telefone',fornecedor.cnpj_fornecedor as 'Cnpj',fornecedor.email_fornecedor as 'Email',nome_endereco as 'Rua',numero as 'Número',bairro_endereco as 'Bairro',Cidade_endereco as 'Cidade',estado_endereco as 'Estado' from fornecedor join endereco on endereco.id_endereco = fornecedor.id_endereco where fornecedor.nome_fornecedor like ?";
        try {
            pst = conexaoFornecedor.prepareStatement(sql);
            //passando o conteudo da caixa de pesquisa para o ?
            //atenção ao "%" - continuação da string sql
            pst.setString(1, modeloFornecedor.getPesquisar() + "%");
            rs = pst.executeQuery();
            // a linha abaixo usa a biblioteca rs2xml.jar para preencher a tabela
            TableFornecedor.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        conexaoFornecedor.close();
    }

    public void atualizarCliente(ModeloFornecedor modeloFornecedor) throws SQLException {
        this.conexaoFornecedor = new Conectar().openConnection();
        try {
            buscarIdDoEndereco(modeloFornecedor);//metodo para buscar o id do endereço
            String sql2 = "update fornecedor set nome_fornecedor=?,email_fornecedor=?,telefone_fornecedor=?,id_endereco=? where cnpj_fornecedor=?";
            pst = conexaoFornecedor.prepareStatement(sql2);
            pst.setString(1, modeloFornecedor.getNome());
            pst.setString(2, modeloFornecedor.getEmail());
            pst.setString(3, modeloFornecedor.getTelefone());
            pst.setInt(4, this.idEndereco);//variável que contém o id do endereço
            pst.setString(5, modeloFornecedor.getCnpj());
            pst.executeUpdate();

            String sql = "update endereco set nome_endereco=?,numero=?,estado_endereco=?,Cidade_endereco=?,bairro_endereco=? where id_endereco=?";
            pst = conexaoFornecedor.prepareStatement(sql);
            pst.setString(1, modeloFornecedor.getEndereco().getRua());
            pst.setInt(2, modeloFornecedor.getEndereco().getNumero());
            pst.setString(3, modeloFornecedor.getEndereco().getEstado());
            pst.setString(4, modeloFornecedor.getEndereco().getCidade());
            pst.setString(5, modeloFornecedor.getEndereco().getBairro());
            pst.setInt(6, this.idEndereco);//variável que contém o id do endereço

            int adicionado = pst.executeUpdate();
            if (adicionado > 0) {
                JOptionPane.showMessageDialog(null, "Dados do Fornecedor " + modeloFornecedor.getNome().toUpperCase() + " alterado com sucesso!");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar fornecedor!\nErro: " + ex);
        }
        conexaoFornecedor.close();
    }

    private void buscarIdDoEndereco(ModeloFornecedor modeloFornecedor) {
        this.conexaoFornecedor = new Conectar().openConnection();
        String sql = "select * from fornecedor where cnpj_fornecedor = '" + modeloFornecedor.getCnpj() + "'";
        try {
            pst = conexaoFornecedor.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {
                this.idEndereco = rs.getInt("id_endereco");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar o código de endereço!\nErro: " + ex);
        }
    }

    public void ExcluirCliente(ModeloFornecedor modeloFornecedor) throws SQLException {
        this.conexaoFornecedor = new Conectar().openConnection();
        String sql = "delete from fornecedor where cnpj_fornecedor = ?";
        try {
            pst = conexaoFornecedor.prepareStatement(sql);
            pst.setString(1, modeloFornecedor.getCnpj());
            int apagado = pst.executeUpdate();

            if (apagado > 0) {
                JOptionPane.showMessageDialog(null, "Fornecedor removido com sucesso!");

            } else {
                JOptionPane.showMessageDialog(null, "Fornecedor não cadastrado!");//se não houver usuário com o código pesquisado essa mensagem aparece para o usuário
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        conexaoFornecedor.close();
    }

}
