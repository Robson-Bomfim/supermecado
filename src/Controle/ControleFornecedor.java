package Controle;

import dao.Conectar;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

public class ControleFornecedor {

    Connection conexaoFornecedor = null;
    PreparedStatement pst;
    ResultSet rs;

    public void preencherCombo(JComboBox ComboBoxFornecedor) throws SQLException {
        this.conexaoFornecedor =  new Conectar().openConnection();
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

}
