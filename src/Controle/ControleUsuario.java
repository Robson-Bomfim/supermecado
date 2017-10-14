/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle;

import Visao.Home;
import dao.Conectar;
import java.awt.Color;
import java.awt.Image;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class ControleUsuario {
    //criando variaveis especiais para conexão com o banco
    //Prepared Statement e ResultSet são framework do pacote java.sql
    //e serve para preparar e executar as instruções sql

    private Connection conexaoUsuario = null;

    public ControleUsuario() {
        this.conexaoUsuario = new Conectar().openConnection();
    }

    public void logar(JTextField TextFieldUsuario, JPasswordField PasswordField) {

        String slq = "select * from usuario where nome_login = ? and senha = ?";
        try {
            //as linhas abaixo preparam a consulta ao banco em função do 
            //que foi digitado nas caixas de textos 
            //o "?" é substituido pelo conteúdos das variáveis           
            PreparedStatement pst = conexaoUsuario.prepareStatement(slq);
            pst.setString(1, TextFieldUsuario.getText().toUpperCase());
            pst.setString(2, PasswordField.getText());

            ResultSet rs = pst.executeQuery();//a linha executa a query

            if (rs.next()) {//se existir usuario e senha correspondente

                String perfil = rs.getString(4);//a linha contém o conteúdo do campo perfil da tabela login
                //System.out.println(perfil);
                // a estrutura abaixo faz o tratamento do perfil do usuario
                if (perfil.equalsIgnoreCase("Admin")) {
                    Home h = new Home();//estância da tela principal
                    h.setVisible(true);// chama a tela principal
                    Home.MenuRelatorio.setEnabled(true);
                    Home.MenuUsuario.setEnabled(true);
                    Home.LabelTipoUser.setText(rs.getString(4));//aqui seta o tipo do usuario conforme o banco de dados
                    Home.LblUsuario.setText(rs.getString(2));//aqui seta o usuario logado conforme o banco de dados
                    Home.LabelTipoUser.setForeground(Color.RED);

                    pst.close();
                } else if (perfil.equalsIgnoreCase("User")) {
                    Home h = new Home();//estância da tela principal
                    Home.LabelTipoUser.setText(rs.getString(4));//aqui seta o tipo do usuario conforme o banco de dados
                    Home.LblUsuario.setText(rs.getString(2));//aqui seta o usuario logado conforme o banco de dados
                    h.setVisible(true);// chama a tela principal  */    

                    pst.close();
                } else {
                    JOptionPane.showMessageDialog(null, "Perfil desconhecido!");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Usuário e/ou Senha Inválido(s)!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void consultar(JTextField TextFieldUserCodigo, JTextField TextFieldUserNome, JTextField TextFieldUserCelular, JTextField TextFieldResidencial, JTextField TextFieldUserEmail, JTextField TextFieldUserSenha, JTextField TextField_Path, JComboBox ComboBoxUserPerfil, JFormattedTextField FormattedTextFieldData_Nasc, JLabel LabelFoto) throws IOException, SQLException {//metodo para consultar usuário

        Image img;
        String sql = "select * from usuario where id_usuario = ?";//string sql que pesquisa a tabela e id do usuario
        try {
            PreparedStatement pst = conexaoUsuario.prepareStatement(sql);//linha que faz conexão passando a string sql como parametro para que a pesquisa seja feita dentro do banco

            pst.setString(1, TextFieldUserCodigo.getText());//linha que faz a pesquisa do usuario através do seu codigo
            ResultSet rs = pst.executeQuery();//linha que executa a conexão
            if (rs.next()) {//se a conexão for verdadeira
                //as linhas abaixo consulta os valores que são digitados pelo usuário
                TextFieldUserNome.setText(rs.getString(2));
                TextFieldUserCelular.setText(rs.getString(6));
                TextFieldResidencial.setText(rs.getString(7));
                TextFieldUserEmail.setText(rs.getString(8));
                ComboBoxUserPerfil.setSelectedItem(rs.getString(4).toUpperCase());// a linha se refere especificamente ao combobox
                TextFieldUserSenha.setText(rs.getString(3));
                FormattedTextFieldData_Nasc.setText((rs.getString(5)));
                atualizaDataDoMysql(FormattedTextFieldData_Nasc);
                /*img = Toolkit.getDefaultToolkit().createImage(rs.getBytes(10));
                    LabelFoto.setIcon(new javax.swing.ImageIcon(img));*/
            }else{               
            JOptionPane.showMessageDialog(null, "Usuário não cadastrado!");//se não houver usuário com o código pesquisado essa mensagem aparece para o usuário

            //as linhas abaixo "limpam" os campos
           TextFieldUserNome.setText(null);
            TextFieldUserCelular.setText(null);
            TextFieldResidencial.setText(null);
            TextFieldUserEmail.setText(null);
            TextFieldUserSenha.setText(null);
            FormattedTextFieldData_Nasc.setText(null);
            LabelFoto.setText(null);
            TextField_Path.setText(null);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());//essa mensagem mostra o tipo de erro que ocorreu 
        }
    }

    private void atualizaDataDoMysql(JFormattedTextField FormattedTextFieldData_Nasc) {
        //conversão de datas yyyy/mm/dd
        String dia = FormattedTextFieldData_Nasc.getText().substring(8, 10);
        String mes = FormattedTextFieldData_Nasc.getText().substring(5, 7);
        String ano = FormattedTextFieldData_Nasc.getText().substring(0, 4);
        String dataParaMysql = dia + "/" + mes + "/" + ano;
        FormattedTextFieldData_Nasc.setText(dataParaMysql);
    }
}
