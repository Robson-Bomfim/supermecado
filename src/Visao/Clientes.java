/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Visao;

import Controle.ControleCliente;
import Modelo.ModeloCliente;
import Modelo.ModeloEndereco;
import dao.Conectar;
import java.awt.Color;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Dell
 */
public class Clientes extends javax.swing.JInternalFrame {

    Connection conexao = null;
    ModeloCliente modeloCliente = new ModeloCliente();
    ModeloEndereco modeloEndereco = new ModeloEndereco();
    ControleCliente controleCliente = new ControleCliente();
    public static String y;

    public Clientes() {
        this.conexao = new Conectar().openConnection();
        initComponents();
        this.y = "y";
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
        TextFieldPesquisar = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        Salvar = new javax.swing.JButton();
        ButtonAlterar = new javax.swing.JButton();
        ButtonExcluir = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        TextFieldNome = new javax.swing.JTextField();
        TextFieldRua = new javax.swing.JTextField();
        TextFieldEmail = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        FormattedTextFieldTelefone = new javax.swing.JFormattedTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        TextFieldNumero = new javax.swing.JTextField();
        TextFieldBairro = new javax.swing.JTextField();
        TextFieldCidade = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        TextFieldEstado = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        ButtonAdicionar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TableCliente = new javax.swing.JTable();
        FormattedTextFieldCPF = new javax.swing.JFormattedTextField();

        setClosable(true);
        setIconifiable(true);
        setTitle("CADASTRO DE CLIENTES");
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameClosing(evt);
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
            }
        });

        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        TextFieldPesquisar.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        TextFieldPesquisar.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        TextFieldPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextFieldPesquisarActionPerformed(evt);
            }
        });
        TextFieldPesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TextFieldPesquisarKeyReleased(evt);
            }
        });

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/if_Search.png"))); // NOI18N
        jLabel9.setToolTipText("Pesquisar");

        Salvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/salvar.png"))); // NOI18N
        Salvar.setToolTipText("Salvar");
        Salvar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Salvar.setPreferredSize(new java.awt.Dimension(60, 60));
        Salvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SalvarActionPerformed(evt);
            }
        });

        ButtonAlterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/if_icon-136-document-edit_314724.png"))); // NOI18N
        ButtonAlterar.setToolTipText("Alterar");
        ButtonAlterar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ButtonAlterar.setPreferredSize(new java.awt.Dimension(60, 60));
        ButtonAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonAlterarActionPerformed(evt);
            }
        });

        ButtonExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/if_icon-27-trash-can_314759.png"))); // NOI18N
        ButtonExcluir.setToolTipText("Excluir");
        ButtonExcluir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ButtonExcluir.setPreferredSize(new java.awt.Dimension(60, 60));
        ButtonExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonExcluirActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel3.setText("Telefone:");

        jLabel4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel4.setText("Email:");

        TextFieldNome.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        TextFieldNome.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        TextFieldNome.setEnabled(false);
        TextFieldNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextFieldNomeActionPerformed(evt);
            }
        });

        TextFieldRua.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        TextFieldRua.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        TextFieldRua.setEnabled(false);
        TextFieldRua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextFieldRuaActionPerformed(evt);
            }
        });

        TextFieldEmail.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        TextFieldEmail.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        TextFieldEmail.setEnabled(false);

        jLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel1.setText("Nome:");

        try {
            FormattedTextFieldTelefone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        FormattedTextFieldTelefone.setEnabled(false);
        FormattedTextFieldTelefone.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel2.setText("Rua:");

        jLabel10.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel10.setText("Número:");

        jLabel11.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel11.setText("Bairro:");

        jLabel12.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel12.setText("Cidade:");

        TextFieldNumero.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        TextFieldNumero.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        TextFieldNumero.setEnabled(false);

        TextFieldBairro.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        TextFieldBairro.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        TextFieldBairro.setEnabled(false);
        TextFieldBairro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextFieldBairroActionPerformed(evt);
            }
        });

        TextFieldCidade.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        TextFieldCidade.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        TextFieldCidade.setEnabled(false);

        jLabel13.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel13.setText("Estado:");

        TextFieldEstado.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        TextFieldEstado.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        TextFieldEstado.setEnabled(false);

        jLabel15.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel15.setText("Cpf:");

        ButtonAdicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/if_icon-81-document-add.png"))); // NOI18N
        ButtonAdicionar.setToolTipText("Adicionar");
        ButtonAdicionar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ButtonAdicionar.setPreferredSize(new java.awt.Dimension(60, 60));
        ButtonAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonAdicionarActionPerformed(evt);
            }
        });

        TableCliente.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        TableCliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        TableCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableClienteMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TableCliente);

        try {
            FormattedTextFieldCPF.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        FormattedTextFieldCPF.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        FormattedTextFieldCPF.setEnabled(false);
        FormattedTextFieldCPF.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(TextFieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, 422, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(TextFieldRua)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TextFieldNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(FormattedTextFieldTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(TextFieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 425, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(97, 97, 97)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel11)
                        .addComponent(jLabel12)
                        .addComponent(jLabel13))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel15)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TextFieldCidade)
                    .addComponent(TextFieldEstado)
                    .addComponent(TextFieldBairro)
                    .addComponent(FormattedTextFieldCPF))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(116, 116, 116)
                .addComponent(ButtonAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 182, Short.MAX_VALUE)
                .addComponent(Salvar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(200, 200, 200)
                .addComponent(ButtonAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(200, 200, 200)
                .addComponent(ButtonExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(TextFieldPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 475, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Salvar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(ButtonAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(ButtonExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(ButtonAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(118, 118, 118)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(TextFieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addComponent(FormattedTextFieldCPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(TextFieldRua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(TextFieldBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(TextFieldNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(FormattedTextFieldTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(TextFieldCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(TextFieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(TextFieldEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(TextFieldPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        setBounds(0, 0, 1039, 638);
    }// </editor-fold>//GEN-END:initComponents

    private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosing
        // TODO add your handling code here:
        y = null;
    }//GEN-LAST:event_formInternalFrameClosing

    private void TextFieldNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextFieldNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextFieldNomeActionPerformed

    private void SalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SalvarActionPerformed
        try {
            // TODO add your handling code here:
            salvarCliente();
            pesquisar_cliente();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao adicionar cliente!\nErro: " + ex);
        }
    }//GEN-LAST:event_SalvarActionPerformed

    private void TextFieldBairroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextFieldBairroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextFieldBairroActionPerformed

    private void TextFieldRuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextFieldRuaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextFieldRuaActionPerformed
    // o evento abaixo é do tipo enquanto for digitando 
    private void TextFieldPesquisarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextFieldPesquisarKeyReleased
        try {
            // TODO add your handling code here:
            pesquisar_cliente();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao pesquisar cliente!\nErro: " + ex);
        }
    }//GEN-LAST:event_TextFieldPesquisarKeyReleased

    private void ButtonAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonAlterarActionPerformed
        try {
            // TODO add your handling code here:
            atualizarCliente();
            pesquisar_cliente();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar cliente!\nErro: " + ex);
        }
    }//GEN-LAST:event_ButtonAlterarActionPerformed

    private void ButtonExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonExcluirActionPerformed
        try {
            // TODO add your handling code here:
            ExcluirCliente();
            pesquisar_cliente();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao remover cliente!\nErro: " + ex);
        }
    }//GEN-LAST:event_ButtonExcluirActionPerformed

    private void ButtonAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonAdicionarActionPerformed
        // chamando o metodo adicionarCliente
        adicionarCliente();
    }//GEN-LAST:event_ButtonAdicionarActionPerformed

    private void TextFieldPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextFieldPesquisarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextFieldPesquisarActionPerformed

    private void TableClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableClienteMouseClicked
        // chamando o metodo setar_campos
        setarCamposCliente();
    }//GEN-LAST:event_TableClienteMouseClicked

    private void pesquisar_cliente() throws SQLException {

        modeloCliente.setPesquisar(TextFieldPesquisar.getText());
        controleCliente.pesquisar_cliente(modeloCliente, TableCliente);
    }

    //metodo para setar os campos do formulario com o conteudo da tabela
    private void setarCamposCliente() {
        int setar = TableCliente.getSelectedRow();

        TextFieldNome.setText(TableCliente.getModel().getValueAt(setar, 0).toString());
        FormattedTextFieldTelefone.setText(TableCliente.getModel().getValueAt(setar, 1).toString());
        FormattedTextFieldCPF.setText(TableCliente.getModel().getValueAt(setar, 2).toString());
        TextFieldEmail.setText(TableCliente.getModel().getValueAt(setar, 3).toString());
        TextFieldRua.setText(TableCliente.getModel().getValueAt(setar, 4).toString());
        TextFieldNumero.setText(TableCliente.getModel().getValueAt(setar, 5).toString());
        TextFieldBairro.setText(TableCliente.getModel().getValueAt(setar, 6).toString());
        TextFieldCidade.setText(TableCliente.getModel().getValueAt(setar, 7).toString());
        TextFieldEstado.setText(TableCliente.getModel().getValueAt(setar, 8).toString());
        //a linha abaixo desabilita o botão salvar
        Salvar.setEnabled(false);
        TextFieldNome.setEnabled(true);
        TextFieldRua.setEnabled(true);
        TextFieldNumero.setEnabled(true);
        FormattedTextFieldTelefone.setEnabled(true);
        TextFieldEmail.setEnabled(true);
        TextFieldBairro.setEnabled(true);
        TextFieldCidade.setEnabled(true);
        TextFieldEstado.setEnabled(true);
    }

    private void salvarCliente() throws SQLException {
        if (TextFieldNome.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Preencha o campo nome do cliente.");
            TextFieldNome.requestFocus();
            return;
        } else if (TextFieldRua.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Preencha o campo rua.");
            TextFieldRua.requestFocus();
            return;
        } else if (TextFieldNumero.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Preencha o campo numero.");
            TextFieldNumero.requestFocus();
            return;
        } else if (FormattedTextFieldTelefone.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Preencha o campo telefone.");
            FormattedTextFieldTelefone.requestFocus();
            return;
        } else if (TextFieldEmail.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Preencha o campo email.");
            TextFieldEmail.requestFocus();
            return;
        } else if (FormattedTextFieldCPF.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Preencha o campo cpf.");
            FormattedTextFieldCPF.requestFocus();
            return;
        } else if (TextFieldBairro.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Preencha o campo bairro.");
            TextFieldBairro.requestFocus();
            return;
        } else if (TextFieldCidade.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Preencha o campo cidade.");
            TextFieldCidade.requestFocus();
            return;
        } else if (TextFieldEstado.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Preencha o campo estado.");
            TextFieldEstado.requestFocus();
            return;
        }

        modeloEndereco.setRua(TextFieldRua.getText().toUpperCase());
        modeloEndereco.setNumero(Integer.parseInt(TextFieldNumero.getText()));
        modeloEndereco.setBairro(TextFieldBairro.getText().toUpperCase());
        modeloEndereco.setCidade(TextFieldCidade.getText().toUpperCase());
        modeloEndereco.setEstado(TextFieldEstado.getText().toUpperCase());
        modeloCliente.setEndereco(modeloEndereco);
        modeloCliente.setNome(TextFieldNome.getText().toUpperCase());
        modeloCliente.setTelefone(FormattedTextFieldTelefone.getText());
        modeloCliente.setCpf(FormattedTextFieldCPF.getText());
        modeloCliente.setEmail(TextFieldEmail.getText());

        controleCliente.adionarCliente(modeloCliente, modeloEndereco);

        //as linhas abaixo "limpam" os campos
        TextFieldNome.setText(null);
        FormattedTextFieldTelefone.setText(null);
        FormattedTextFieldCPF.setText(null);
        TextFieldRua.setText(null);
        TextFieldNumero.setText(null);
        TextFieldEstado.setText(null);
        TextFieldCidade.setText(null);
        TextFieldBairro.setText(null);
        TextFieldEmail.setText(null);
    }
    //criando o metodo para alterar usuários

    private void atualizarCliente() throws SQLException {
        //validaçao dos campos obrigatórios
        if (TextFieldNome.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Preencha o campo nome do cliente.");
            TextFieldNome.requestFocus();
            return;
        } else if (TextFieldRua.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Preencha o campo rua.");
            TextFieldRua.requestFocus();
            return;
        } else if (TextFieldNumero.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Preencha o campo numero.");
            TextFieldNumero.requestFocus();
            return;
        } else if (FormattedTextFieldTelefone.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Preencha o campo telefone.");
            FormattedTextFieldTelefone.requestFocus();
            return;
        } else if (TextFieldEmail.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Preencha o campo email.");
            TextFieldEmail.requestFocus();
            return;
        } else if (FormattedTextFieldCPF.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Preencha o campo cpf.");
            FormattedTextFieldCPF.requestFocus();
            return;
        } else if (TextFieldBairro.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Preencha o campo bairro.");
            TextFieldBairro.requestFocus();
            return;
        } else if (TextFieldCidade.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Preencha o campo cidade.");
            TextFieldCidade.requestFocus();
            return;
        } else if (TextFieldEstado.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Preencha o campo estado.");
            TextFieldEstado.requestFocus();
            return;
        }

        modeloEndereco.setRua(TextFieldRua.getText().toUpperCase());
        modeloEndereco.setNumero(Integer.parseInt(TextFieldNumero.getText()));
        modeloEndereco.setBairro(TextFieldBairro.getText().toUpperCase());
        modeloEndereco.setCidade(TextFieldCidade.getText().toUpperCase());
        modeloEndereco.setEstado(TextFieldEstado.getText().toUpperCase());
        modeloCliente.setEndereco(modeloEndereco);
        modeloCliente.setNome(TextFieldNome.getText().toUpperCase());
        modeloCliente.setTelefone(FormattedTextFieldTelefone.getText());
        modeloCliente.setCpf(FormattedTextFieldCPF.getText());
        modeloCliente.setEmail(TextFieldEmail.getText());

        controleCliente.atualizarCliente(modeloCliente, modeloEndereco);

        //as linhas abaixo "limpam" os campos
        TextFieldNome.setText(null);
        FormattedTextFieldTelefone.setText(null);
        FormattedTextFieldCPF.setText(null);
        TextFieldRua.setText(null);
        TextFieldNumero.setText(null);
        TextFieldEstado.setText(null);
        TextFieldCidade.setText(null);
        TextFieldBairro.setText(null);
        TextFieldEmail.setText(null);
        //a linha abaixo Habilita o botão adicionar
        Salvar.setEnabled(true);
        //a linha abaixo desabilita os campos
        TextFieldNome.setEnabled(!true);
        TextFieldRua.setEnabled(!true);
        TextFieldNumero.setEnabled(!true);
        FormattedTextFieldTelefone.setEnabled(!true);
        TextFieldEmail.setEnabled(!true);
        FormattedTextFieldCPF.setEnabled(!true);
        TextFieldBairro.setEnabled(!true);
        TextFieldCidade.setEnabled(!true);
        TextFieldEstado.setEnabled(!true);
    }

    private void ExcluirCliente() throws SQLException {
        if (FormattedTextFieldCPF.getText().isEmpty()) {//validaçao do campo "codigo" que é obrigatório para fazer a cunsulta
            JOptionPane.showMessageDialog(null, "Preencha o campo Cpf!");//se o campo estiver vazio retorna essa mensagem para o usuario
            return;
        }
        int confirmar = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover o cliente " + TextFieldNome.getText().toUpperCase() + " ?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (confirmar == JOptionPane.YES_OPTION) {
            modeloCliente.setCpf(FormattedTextFieldCPF.getText());

            controleCliente.ExcluirCliente(modeloCliente);

            TextFieldNome.setText(null);
            FormattedTextFieldTelefone.setText(null);
            FormattedTextFieldCPF.setText(null);
            TextFieldRua.setText(null);
            TextFieldNumero.setText(null);
            TextFieldEstado.setText(null);
            TextFieldCidade.setText(null);
            TextFieldBairro.setText(null);
            TextFieldEmail.setText(null);
            //a linha abaixo Habilita o botão adicionar
            Salvar.setEnabled(true);
        }
    }

    private void adicionarCliente() {
        // habilita os campos
        TextFieldNome.setEnabled(true);
        TextFieldRua.setEnabled(true);
        TextFieldNumero.setEnabled(true);
        FormattedTextFieldTelefone.setEnabled(true);
        TextFieldEmail.setEnabled(true);
        FormattedTextFieldCPF.setEnabled(true);
        TextFieldBairro.setEnabled(true);
        TextFieldCidade.setEnabled(true);
        TextFieldEstado.setEnabled(true);
        Salvar.setEnabled(true);
        //limpam os campos
        TextFieldNome.setText(null);
        FormattedTextFieldTelefone.setText(null);
        FormattedTextFieldCPF.setText(null);
        TextFieldRua.setText(null);
        TextFieldNumero.setText(null);
        TextFieldEstado.setText(null);
        TextFieldCidade.setText(null);
        TextFieldBairro.setText(null);
        TextFieldEmail.setText(null);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ButtonAdicionar;
    private javax.swing.JButton ButtonAlterar;
    private javax.swing.JButton ButtonExcluir;
    private javax.swing.JFormattedTextField FormattedTextFieldCPF;
    private javax.swing.JFormattedTextField FormattedTextFieldTelefone;
    private javax.swing.JButton Salvar;
    private javax.swing.JTable TableCliente;
    private javax.swing.JTextField TextFieldBairro;
    private javax.swing.JTextField TextFieldCidade;
    private javax.swing.JTextField TextFieldEmail;
    private javax.swing.JTextField TextFieldEstado;
    private javax.swing.JTextField TextFieldNome;
    private javax.swing.JTextField TextFieldNumero;
    private javax.swing.JTextField TextFieldPesquisar;
    private javax.swing.JTextField TextFieldRua;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

}
