/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Visao;

import java.sql.*;
import dao.ModuloConexao;
import java.awt.Color;
import javax.swing.JOptionPane;

public class Login extends javax.swing.JFrame {

    ModuloConexao conexao = new ModuloConexao();
    //Connection conexao = null;//usando a variavel conexão do dal

    //criando variaveis especiais para conexão com o banco
    //Prepared Statement e ResultSet são framework do pacote java.sql
    //e serve para preparar e executar as instruções sql
    PreparedStatement pst;
    ResultSet rs;

    public void logar() {
        String slq = "select * from usuario where nome_login = ? and senha = ?";
        try {
            //as linhas abaixo preparam a consulta ao banco em função do 
            //que foi digitado nas caixas de textos 
            //o "?" é substituido pelo conteúdos das variáveis           
            pst = conexao.connection.prepareStatement(slq);
            pst.setString(1, TextFieldUsuario.getText().toUpperCase());
            pst.setString(2, PasswordField.getText());

            rs = pst.executeQuery();//a linha executa a query

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
                    this.dispose();
                    conexao.desconecta();
                } else if (perfil.equalsIgnoreCase("User")) {
                    Home h = new Home();//estância da tela principal
                    Home.LabelTipoUser.setText(rs.getString(4));//aqui seta o tipo do usuario conforme o banco de dados
                    Home.LblUsuario.setText(rs.getString(2));//aqui seta o usuario logado conforme o banco de dados
                    h.setVisible(true);// chama a tela principal  */    
                    this.dispose();//fecha a tela de login
                    conexao.desconecta();
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

    public Login() {
        initComponents();
        conexao.conector();
        // a linha abaixo serve de apoio ao status de conexão
        //System.out.println(conexao);
        if (conexao != null) {
            jLabelConexao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/if_database_accept_84521.png")));
            jLabelStatusLegenda.setText("Conectado");
        } else {
            jLabelConexao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/if_database_close_36899.png")));
            jLabelStatusLegenda.setText("Desconectado");
        }
        getRootPane().setDefaultButton(ButtonLogar);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        TextFieldUsuario = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        ButtonLogar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        PasswordField = new javax.swing.JPasswordField();
        jLabelConexao = new javax.swing.JLabel();
        jLabelStatusLegenda = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle(" LOGIN");

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setFont(new java.awt.Font("Serif", 1, 36)); // NOI18N
        jLabel1.setText("TELA DE LOGIN");

        jLabel2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel2.setText("User");

        TextFieldUsuario.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        TextFieldUsuario.setToolTipText("Insira o nome do seu usuário");
        TextFieldUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextFieldUsuarioActionPerformed(evt);
            }
        });
        TextFieldUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TextFieldUsuarioKeyPressed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel3.setText("Password");

        ButtonLogar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        ButtonLogar.setText("Login");
        ButtonLogar.setToolTipText("Clique para acessar");
        ButtonLogar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ButtonLogar.setSelected(true);
        ButtonLogar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonLogarActionPerformed(evt);
            }
        });

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/if_user21_216548.png"))); // NOI18N

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/key-48_44464.png"))); // NOI18N

        PasswordField.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        PasswordField.setToolTipText("Insira a senha do seu usuário");
        PasswordField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PasswordFieldActionPerformed(evt);
            }
        });
        PasswordField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PasswordFieldKeyPressed(evt);
            }
        });

        jLabelConexao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/if_database_accept_84521.png"))); // NOI18N

        jLabelStatusLegenda.setText("status");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(134, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabelConexao)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelStatusLegenda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ButtonLogar))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(0, 86, Short.MAX_VALUE))
                            .addComponent(TextFieldUsuario)
                            .addComponent(PasswordField))))
                .addContainerGap(134, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TextFieldUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelConexao)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelStatusLegenda)
                        .addComponent(ButtonLogar)))
                .addContainerGap(40, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        setSize(new java.awt.Dimension(843, 471));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void TextFieldUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextFieldUsuarioActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_TextFieldUsuarioActionPerformed

    private void ButtonLogarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonLogarActionPerformed
        //a codição abaixo avisa o usuario que um ou mais campos estão sem preencher
        if (TextFieldUsuario.getText().isEmpty() || PasswordField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Preencha os campos antes de Logar!");
        } else {
            logar();// chamando o metodo logar
        }
    }//GEN-LAST:event_ButtonLogarActionPerformed

    private void PasswordFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PasswordFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PasswordFieldActionPerformed

    private void TextFieldUsuarioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextFieldUsuarioKeyPressed
        // seleciona o campo senha
        if (evt.getKeyCode() == evt.VK_ENTER) {
            PasswordField.requestFocus();
        }
    }//GEN-LAST:event_TextFieldUsuarioKeyPressed

    private void PasswordFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PasswordFieldKeyPressed
        // selecina foco no botão e captura a tecla enter
        if (evt.getKeyCode() == evt.VK_ENTER) {
            ButtonLogar.requestFocus();
        }
    }//GEN-LAST:event_PasswordFieldKeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Login().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ButtonLogar;
    private javax.swing.JPasswordField PasswordField;
    private javax.swing.JTextField TextFieldUsuario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabelConexao;
    private javax.swing.JLabel jLabelStatusLegenda;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
