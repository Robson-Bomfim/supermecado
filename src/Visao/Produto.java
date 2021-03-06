/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Visao;

import Controle.ControleFornecedor;
import Controle.ControleProduto;
import Modelo.ModeloFornecedor;
import java.sql.*;
import javax.swing.JOptionPane;
import Modelo.ModeloProduto;
import dao.Conectar;

/**
 *
 * @author Dell
 */
public class Produto extends javax.swing.JInternalFrame {

    ModeloProduto modeloProduto = new ModeloProduto();
    ModeloFornecedor modelofornecedor = new ModeloFornecedor();
    ControleProduto controleProduto = new ControleProduto();
    ControleFornecedor controleFornecedor = new ControleFornecedor();
    public static String x;
    PreparedStatement pst;
    ResultSet rs;

    public Produto() throws SQLException {
        initComponents();
        x = "x";
        controleFornecedor.preencherCombo(ComboBoxFornecedor);
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
        TextFieldCodigo = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        TextFieldProduto = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        TextFieldQuantidade = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        TextFieldPrecoC = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        TextFieldPrecoV = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        ComboBoxFornecedor = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TabelaProduto = new javax.swing.JTable();
        ButtonAdicionar = new javax.swing.JButton();
        ButtonAlterar = new javax.swing.JButton();
        ButtonExcluir = new javax.swing.JButton();
        TextFieldPesquisar = new javax.swing.JTextField();
        ButtonSalvar = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setTitle("CADASTRO DE PRODUTOS");
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
        jPanel1.setName("CADASTRO DE PRODUTOS"); // NOI18N

        jLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel1.setText("Código:");

        TextFieldCodigo.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        TextFieldCodigo.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        TextFieldCodigo.setEnabled(false);

        jLabel2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel2.setText("Produto:");

        TextFieldProduto.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        TextFieldProduto.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        TextFieldProduto.setEnabled(false);

        jLabel3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel3.setText("Quantidade:");

        TextFieldQuantidade.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        TextFieldQuantidade.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        TextFieldQuantidade.setEnabled(false);

        jLabel4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel4.setText("Preço de compra:");

        TextFieldPrecoC.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        TextFieldPrecoC.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        TextFieldPrecoC.setEnabled(false);

        jLabel5.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel5.setText("Preço de venda:");

        TextFieldPrecoV.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        TextFieldPrecoV.setToolTipText("");
        TextFieldPrecoV.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        TextFieldPrecoV.setEnabled(false);

        jLabel6.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel6.setText("Fornecedor:");

        ComboBoxFornecedor.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        ComboBoxFornecedor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        ComboBoxFornecedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboBoxFornecedorActionPerformed(evt);
            }
        });

        jLabel7.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        TabelaProduto.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        TabelaProduto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        TabelaProduto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabelaProdutoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TabelaProduto);

        ButtonAdicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/if_icon-81-document-add.png"))); // NOI18N
        ButtonAdicionar.setToolTipText("Adicionar");
        ButtonAdicionar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ButtonAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonAdicionarActionPerformed(evt);
            }
        });

        ButtonAlterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/if_icon-136-document-edit_314724.png"))); // NOI18N
        ButtonAlterar.setToolTipText("Alterar");
        ButtonAlterar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ButtonAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonAlterarActionPerformed(evt);
            }
        });

        ButtonExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/if_icon-27-trash-can_314759.png"))); // NOI18N
        ButtonExcluir.setToolTipText("Excluir");
        ButtonExcluir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ButtonExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonExcluirActionPerformed(evt);
            }
        });

        TextFieldPesquisar.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
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

        ButtonSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/salvar.png"))); // NOI18N
        ButtonSalvar.setToolTipText("Salvar");
        ButtonSalvar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ButtonSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonSalvarActionPerformed(evt);
            }
        });

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/if_Search.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(TextFieldPrecoC, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(TextFieldPrecoV, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel6))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(TextFieldCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(TextFieldProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel3))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(TextFieldPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 507, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel9))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(123, 123, 123)
                                .addComponent(ButtonAdicionar)
                                .addGap(163, 163, 163)
                                .addComponent(ButtonSalvar)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(ButtonAlterar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(ButtonExcluir)
                                .addGap(75, 75, 75))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(TextFieldQuantidade)
                                    .addComponent(ComboBoxFornecedor, 0, 146, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)))))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {ButtonAdicionar, ButtonAlterar, ButtonExcluir, ButtonSalvar});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(ButtonSalvar)
                    .addComponent(ButtonAdicionar)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(ButtonAlterar)
                        .addComponent(ButtonExcluir)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 147, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(TextFieldCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(TextFieldProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(TextFieldQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(57, 57, 57)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ComboBoxFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5)
                            .addComponent(TextFieldPrecoV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(TextFieldPrecoC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(53, 53, 53)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TextFieldPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {ButtonAdicionar, ButtonAlterar, ButtonExcluir, ButtonSalvar});

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
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        setBounds(0, 0, 1039, 638);
    }// </editor-fold>//GEN-END:initComponents

    private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosing
        // TODO add your handling code here:
        x = null;
    }//GEN-LAST:event_formInternalFrameClosing

    private void TextFieldPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextFieldPesquisarActionPerformed

    }//GEN-LAST:event_TextFieldPesquisarActionPerformed

    private void ButtonAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonAdicionarActionPerformed
        //chamando o metodo adicionar_produto
        adicionar_produto();
    }//GEN-LAST:event_ButtonAdicionarActionPerformed

    private void ButtonSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonSalvarActionPerformed
        try {
            // chamando metodo salvar
            salvarProduto();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir o produto!\nErro: " + ex);
        }
    }//GEN-LAST:event_ButtonSalvarActionPerformed

    private void ButtonExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonExcluirActionPerformed
        try {
            // chamando o metodo excluir
            excluir();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, "Erro ao excluir produto!\nErro: " + ex);
        }
    }//GEN-LAST:event_ButtonExcluirActionPerformed

    private void ButtonAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonAlterarActionPerformed
        try {
            // chamando o metodo alterar
            alterar();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, "Erro ao alterar produto!\nErro: " + ex);
        }
    }//GEN-LAST:event_ButtonAlterarActionPerformed

    private void TextFieldPesquisarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextFieldPesquisarKeyReleased
        try {
            // chamando o metodo pesquisar_Produto
            pesquisar_Produto();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, "Erro ao pesquisar produto!\nErro: " + ex);
        }
    }//GEN-LAST:event_TextFieldPesquisarKeyReleased

    private void TabelaProdutoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabelaProdutoMouseClicked
        // chamando o metodo setar_campos
        setar_campos();
    }//GEN-LAST:event_TabelaProdutoMouseClicked

    private void ComboBoxFornecedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboBoxFornecedorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ComboBoxFornecedorActionPerformed

    private void excluir() throws SQLException {

        // condiçao para verificar se os campos estão vazios
        if (TextFieldCodigo.getText().isEmpty()) {//validaçao do campo "codigo" que é obrigatório para fazer a cunsulta
            JOptionPane.showMessageDialog(null, "Selecione o Produto!");//se o campo estiver vazio retorna essa mensagem para o usuario
            return;
        }

        int excluir = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover o produto " + TextFieldProduto.getText().toLowerCase() + " ?", "Atenção", JOptionPane.YES_NO_OPTION);
        // a condição abaixo valida a escolha do usuário
        if (excluir == JOptionPane.YES_OPTION)//se a opção que o usuario for sim
        {

            modeloProduto.setIdProduto(Integer.parseInt(TextFieldCodigo.getText()));
            controleProduto.excluirProduto(modeloProduto);

            //desabilita os campos
            TextFieldPrecoC.setEnabled(!true);
            TextFieldPrecoV.setEnabled(!true);
            TextFieldProduto.setEnabled(!true);
            TextFieldQuantidade.setEnabled(!true);
            //Habilita os botões
            ButtonSalvar.setEnabled(true);
            //limpam os campos
            TextFieldCodigo.setText(null);
            TextFieldPrecoC.setText(null);
            TextFieldPrecoV.setText(null);
            TextFieldProduto.setText(null);
            TextFieldQuantidade.setText(null);
            pesquisar_Produto();
        }
    }

    private void alterar() throws SQLException {

        // condiçao para verificar se os campos estão vazios
        if (TextFieldProduto.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Preencha o campo produto.");
            TextFieldProduto.requestFocus();
            return;
        } else if (TextFieldQuantidade.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Preencha o campo quantidade.");
            TextFieldQuantidade.requestFocus();
            return;
        } else if (TextFieldPrecoC.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Preencha o campo preço de compra.");
            TextFieldPrecoC.requestFocus();
            return;
        } else if (TextFieldPrecoV.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Preencha o campo preço de venda.");
            TextFieldPrecoV.requestFocus();
            return;
        }

        modeloProduto.setIdProduto(Integer.parseInt(TextFieldCodigo.getText()));
        modelofornecedor.setNome((String) ComboBoxFornecedor.getSelectedItem());
        modeloProduto.setFornecedor(modelofornecedor);
        modeloProduto.setNomeProduto(TextFieldProduto.getText().toUpperCase());
        modeloProduto.setPrecoCompra(Double.parseDouble(TextFieldPrecoC.getText()));
        modeloProduto.setPrecoVenda(Double.parseDouble(TextFieldPrecoV.getText()));
        modeloProduto.setQuantidadeProduto(Integer.parseInt(TextFieldQuantidade.getText()));
        controleProduto.alterProduto(modeloProduto);

        //habilita os campos
        TextFieldPrecoC.setEnabled(!true);
        TextFieldPrecoV.setEnabled(!true);
        TextFieldProduto.setEnabled(!true);
        TextFieldQuantidade.setEnabled(!true);
        //Habilita o botão salvar
        ButtonSalvar.setEnabled(true);
        //limpam os campos
        TextFieldCodigo.setText(null);
        TextFieldPrecoC.setText(null);
        TextFieldPrecoV.setText(null);
        TextFieldProduto.setText(null);
        TextFieldQuantidade.setText(null);
        pesquisar_Produto();
    }

    private void salvarProduto() throws SQLException {

        // condiçao para verificar se os campos estão vazios
        if (TextFieldProduto.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Preencha o campo produto.");
            TextFieldProduto.requestFocus();
            return;
        } else if (TextFieldQuantidade.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Preencha o campo quantidade.");
            TextFieldQuantidade.requestFocus();
            return;
        } else if (TextFieldPrecoC.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Preencha o campo preço de compra.");
            TextFieldPrecoC.requestFocus();
            return;
        } else if (TextFieldPrecoV.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Preencha o campo preço de venda.");
            TextFieldPrecoV.requestFocus();
            return;
        }

        modelofornecedor.setNome((String) ComboBoxFornecedor.getSelectedItem());
        modeloProduto.setFornecedor(modelofornecedor);
        modeloProduto.setNomeProduto(TextFieldProduto.getText().toUpperCase());
        modeloProduto.setPrecoCompra(Float.parseFloat(TextFieldPrecoC.getText()));
        modeloProduto.setPrecoVenda(Float.parseFloat(TextFieldPrecoV.getText()));
        modeloProduto.setQuantidadeProduto(Integer.parseInt(TextFieldQuantidade.getText()));
        controleProduto.adionarProduto(modeloProduto);

        //desabilita os campos
        TextFieldPrecoC.setEnabled(!true);
        TextFieldPrecoV.setEnabled(!true);
        TextFieldProduto.setEnabled(!true);
        TextFieldQuantidade.setEnabled(!true);
        //Habilita os botões
        ButtonAlterar.setEnabled(true);
        ButtonExcluir.setEnabled(true);
        ButtonSalvar.setEnabled(true);
        //limpam os campos
        TextFieldPrecoC.setText(null);
        TextFieldPrecoV.setText(null);
        TextFieldProduto.setText(null);
        TextFieldQuantidade.setText(null);
        pesquisar_Produto();
    }

    private void pesquisar_Produto() throws SQLException {

        modeloProduto.setPesquisa(TextFieldPesquisar.getText());
        controleProduto.pesquisar_Produto(modeloProduto, TabelaProduto);
    }

    private void adicionar_produto() {
        TextFieldPrecoC.setEnabled(true);
        TextFieldPrecoV.setEnabled(true);
        TextFieldProduto.setEnabled(true);
        TextFieldQuantidade.setEnabled(true);
        ButtonSalvar.setEnabled(true);
        TextFieldCodigo.setText(null);
        TextFieldPesquisar.setText(null);
        TextFieldPrecoC.setText(null);
        TextFieldPrecoV.setText(null);
        TextFieldProduto.setText(null);
        TextFieldQuantidade.setText(null);
    }

    private void setar_campos() {
        // setar os valores na TabelaProduto
        int setar = TabelaProduto.getSelectedRow();

        TextFieldCodigo.setText(TabelaProduto.getModel().getValueAt(setar, 0).toString());
        TextFieldProduto.setText(TabelaProduto.getModel().getValueAt(setar, 1).toString());
        TextFieldPesquisar.setText(TabelaProduto.getModel().getValueAt(setar, 1).toString());
        TextFieldQuantidade.setText(TabelaProduto.getModel().getValueAt(setar, 2).toString());
        TextFieldPrecoC.setText(TabelaProduto.getModel().getValueAt(setar, 3).toString());
        TextFieldPrecoV.setText(TabelaProduto.getModel().getValueAt(setar, 4).toString());
        ComboBoxFornecedor.setSelectedItem(TabelaProduto.getModel().getValueAt(setar, 5).toString());
        //desabilita o botão salvar
        ButtonSalvar.setEnabled(false);
        //habilita os campos
        TextFieldProduto.setEnabled(true);
        TextFieldQuantidade.setEnabled(true);
        TextFieldPrecoC.setEnabled(true);
        TextFieldPrecoV.setEnabled(true);
        ComboBoxFornecedor.setEnabled(true);
        ButtonAlterar.setEnabled(true);
        ButtonExcluir.setEnabled(true);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ButtonAdicionar;
    private javax.swing.JButton ButtonAlterar;
    private javax.swing.JButton ButtonExcluir;
    private javax.swing.JButton ButtonSalvar;
    private javax.swing.JComboBox<String> ComboBoxFornecedor;
    private javax.swing.JTable TabelaProduto;
    private javax.swing.JTextField TextFieldCodigo;
    private javax.swing.JTextField TextFieldPesquisar;
    private javax.swing.JTextField TextFieldPrecoC;
    private javax.swing.JTextField TextFieldPrecoV;
    private javax.swing.JTextField TextFieldProduto;
    private javax.swing.JTextField TextFieldQuantidade;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
