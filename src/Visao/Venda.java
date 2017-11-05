/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Visao;

import Controle.ControleProduto;
import Controle.ControleVenda;
import Modelo.ModeloCliente;
import Modelo.ModeloProduto;
import Modelo.ModeloVenda;
import dao.Conectar;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

public class Venda extends javax.swing.JInternalFrame {

    Connection conexao = null;
    ModeloCliente modeloCliente = new ModeloCliente();
    ModeloVenda modeloVenda = new ModeloVenda();
    ModeloProduto modeloProduto = new ModeloProduto();
    ControleVenda controleVenda = new ControleVenda();
    ControleProduto controleProduto = new ControleProduto();
    public static int venda;
    private boolean flag = false;
    private boolean value = false;
    public static int idProduto;
    public static int idItensProduto;
    private double valorTotal;
    private int quantidadeEstoque;

    public Venda() throws SQLException, ParseException {
        initComponents();
        this.conexao = new Conectar().openConnection();
        this.dataDaCompra();
        this.registroDeVenda();
        this.venda = 1;
    }

    private void dataDaCompra() {
        SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
        Date dataDeHoje = new Date();
        FormattedTextFieldDataVenda.setText(date.format(dataDeHoje));
    }

    private void pesquisarCliente() throws SQLException {

        modeloVenda.setPesquisar(TextFieldNomeCiente.getText());
        controleVenda.pesquisar_venda(modeloVenda, TablePesquisa);
    }

    private void pesquisarVendaProduto() throws SQLException {
        modeloProduto.setPesquisa(TextFieldProduto.getText());
        controleProduto.pesquisar_produto_cliente(modeloProduto, TablePesquisa);
    }

    private void pesquisarItensVenda() throws SQLException {
        int codigo = 0;
        controleVenda.pesquisar_itens_venda(TableItensVenda,codigo);
    }

    private void selecionador() {
        if (flag == true) {
            setarCamposCliente();
        } else {
            setarCamposProduto();
        }
    }

    private void setarCamposCliente() {
        int setar = TablePesquisa.getSelectedRow();
        TextFieldNomeCiente.setText(TablePesquisa.getModel().getValueAt(setar, 1).toString());
        TextFieldNomeCiente.setEnabled(false);
        TextFieldProduto.requestFocus();
    }

    private void setarCamposProduto() {
        int setar = TablePesquisa.getSelectedRow();
        idProduto = (int) TablePesquisa.getModel().getValueAt(setar, 0);
        TextFieldProduto.setText(TablePesquisa.getModel().getValueAt(setar, 1).toString());
        quantidadeEstoque = (int) TablePesquisa.getModel().getValueAt(setar, 2);
        TextFieldValorItem.setText(TablePesquisa.getModel().getValueAt(setar, 3).toString());
        TextFieldQuantidade.setText(null);
        TextFieldQuantidade.requestFocus();
    }

    private void setarCamposItensVenda() {
        int setar = TableItensVenda.getSelectedRow();
        idItensProduto = (int) TableItensVenda.getModel().getValueAt(setar, 0);
        TextFieldNomeItemVenda.setText(TableItensVenda.getModel().getValueAt(setar, 1).toString());
        TextFieldProduto.setText(TableItensVenda.getModel().getValueAt(setar, 1).toString());
        TextFieldQuantidade.setText(TableItensVenda.getModel().getValueAt(setar, 2).toString());
        TextFieldValorItem.setText(TableItensVenda.getModel().getValueAt(setar, 3).toString());
    }

    private void registroDeVenda() throws SQLException, ParseException {
        SimpleDateFormat formateData = new SimpleDateFormat("yyyy/MM/dd");
        Date date = formateData.parse(FormattedTextFieldDataVenda.getText());
        modeloVenda.setData(date);
        modeloCliente.setNome(TextFieldNomeCiente.getText());
        modeloVenda.setCliente(modeloCliente);
        controleVenda.registroVenda(modeloVenda);
    }

    private void adicionar() throws SQLException {
        modeloProduto.setNomeProduto(TextFieldProduto.getText());
        modeloVenda.setProduto(modeloProduto);
        modeloVenda.setQuantidade(Integer.parseInt(TextFieldQuantidade.getText()));
        modeloVenda.setValorVenda(Float.parseFloat(TextFieldValorItem.getText()));
        modeloVenda.setValorIten(Double.parseDouble(TextFieldValorItem.getText()));
        controleVenda.adicionarItem(modeloVenda);
    }

    private void excluir() throws SQLException {
        modeloVenda.setIdvenda(controleVenda.getId_venda());
        controleVenda.excluirVenda(modeloVenda);
    }

    private void finalizarVenda() throws SQLException {
        if (TextFieldValorTotal.getText().isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "Primeiro adicione a lista o(s) produto(s) desejado(s)!");
            TextFieldNomeCiente.requestFocus();
            return;
        }
        modeloVenda.setValorVenda(Float.parseFloat(TextFieldValorTotal.getText()));
        modeloCliente.setNome(TextFieldNomeCiente.getText());
        modeloVenda.setCliente(modeloCliente);
        controleVenda.updateFinalizarVenda(modeloVenda);
        this.dispose();//fecha o formulário de vendas
    }

    private void calcularValorCompra() {
        if (value) {
            valorTotal -= Double.parseDouble(TextFieldValorItem.getText()) * Integer.parseInt(TextFieldQuantidade.getText());
            TextFieldValorTotal.setText(String.valueOf(this.valorTotal));
            return;
        }
        valorTotal += Double.parseDouble(TextFieldValorItem.getText()) * Integer.parseInt(TextFieldQuantidade.getText());
        TextFieldValorTotal.setText(String.valueOf(this.valorTotal));
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
        TextFieldNomeCiente = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        TextFieldProduto = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        TextFieldQuantidade = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        TextFieldValorItem = new javax.swing.JTextField();
        ButtonAdicionar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablePesquisa = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        TableItensVenda = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        TextFieldValorTotal = new javax.swing.JTextField();
        ButtonFinalizarVenda = new javax.swing.JButton();
        ButtonCancelarVenda = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        FormattedTextFieldDataVenda = new javax.swing.JFormattedTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        TextFieldNomeItemVenda = new javax.swing.JTextField();
        ButtonRemoverLista = new javax.swing.JButton();

        setIconifiable(true);
        setTitle("FORMULÁRIO DE VENDAS");
        setPreferredSize(new java.awt.Dimension(836, 475));
        try {
            setSelected(true);
        } catch (java.beans.PropertyVetoException e1) {
            e1.printStackTrace();
        }
        setVisible(false);
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameClosed(evt);
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

        jLabel1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel1.setText("Nome do cliente:");

        TextFieldNomeCiente.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        TextFieldNomeCiente.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        TextFieldNomeCiente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TextFieldNomeCienteKeyReleased(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel2.setText("Nome do produto:");

        TextFieldProduto.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        TextFieldProduto.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                TextFieldProdutoFocusGained(evt);
            }
        });
        TextFieldProduto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TextFieldProdutoMouseClicked(evt);
            }
        });
        TextFieldProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextFieldProdutoActionPerformed(evt);
            }
        });
        TextFieldProduto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TextFieldProdutoKeyReleased(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel3.setText("Quantidade:");

        TextFieldQuantidade.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        TextFieldQuantidade.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                TextFieldQuantidadeFocusGained(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel4.setText("Valor por item/kg:");

        TextFieldValorItem.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N

        ButtonAdicionar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        ButtonAdicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/shopcartadd.png"))); // NOI18N
        ButtonAdicionar.setToolTipText("Adicionar");
        ButtonAdicionar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ButtonAdicionar.setPreferredSize(new java.awt.Dimension(40, 40));
        ButtonAdicionar.setSelected(true);
        ButtonAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonAdicionarActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel5.setText("Data:");

        jLabel6.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel6.setText("Tabela de pesquisa:");

        TablePesquisa.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        TablePesquisa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        TablePesquisa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TablePesquisaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TablePesquisa);

        TableItensVenda.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        TableItensVenda.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        TableItensVenda.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableItensVendaMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(TableItensVenda);

        jLabel7.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel7.setText("Lista de produtos:");

        jLabel9.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel9.setText("Valor total da venda");

        TextFieldValorTotal.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        TextFieldValorTotal.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        TextFieldValorTotal.setEnabled(false);

        ButtonFinalizarVenda.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        ButtonFinalizarVenda.setText("Finalizar venda");
        ButtonFinalizarVenda.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ButtonFinalizarVenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonFinalizarVendaActionPerformed(evt);
            }
        });

        ButtonCancelarVenda.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        ButtonCancelarVenda.setText("Cancelar venda");
        ButtonCancelarVenda.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ButtonCancelarVenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonCancelarVendaActionPerformed(evt);
            }
        });

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/shopcartapply.png"))); // NOI18N
        jLabel10.setPreferredSize(new java.awt.Dimension(50, 50));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/shopcartexclude.png"))); // NOI18N
        jLabel11.setPreferredSize(new java.awt.Dimension(50, 50));

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/if_Search.png"))); // NOI18N
        jLabel12.setToolTipText("Pesquisar");

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/if_Search.png"))); // NOI18N
        jLabel13.setToolTipText("Pesquisar");

        try {
            FormattedTextFieldDataVenda.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        FormattedTextFieldDataVenda.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        FormattedTextFieldDataVenda.setEnabled(false);
        FormattedTextFieldDataVenda.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/comprar.jpg"))); // NOI18N
        jLabel14.setPreferredSize(new java.awt.Dimension(40, 130));

        jLabel8.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel8.setText("R$");

        TextFieldNomeItemVenda.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        TextFieldNomeItemVenda.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        TextFieldNomeItemVenda.setEnabled(false);

        ButtonRemoverLista.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/if_list-delete_59948.png"))); // NOI18N
        ButtonRemoverLista.setToolTipText("Excluir item");
        ButtonRemoverLista.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ButtonRemoverLista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonRemoverListaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(317, 317, 317))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 421, Short.MAX_VALUE)
                        .addContainerGap())))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(157, 157, 157)
                .addComponent(ButtonFinalizarVenda)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(102, 102, 102)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(TextFieldValorTotal))
                .addGap(156, 156, 156)
                .addComponent(ButtonCancelarVenda)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 116, Short.MAX_VALUE))
            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TextFieldNomeCiente)
                    .addComponent(TextFieldProduto)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13))
                .addGap(70, 70, 70)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(TextFieldQuantidade))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(TextFieldValorItem, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(FormattedTextFieldDataVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(TextFieldNomeItemVenda))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ButtonAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ButtonRemoverLista))
                .addGap(13, 13, 13))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel10, jLabel11});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {ButtonAdicionar, ButtonRemoverLista});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TextFieldNomeCiente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TextFieldProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(TextFieldNomeItemVenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel13)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(ButtonRemoverLista)
                                .addGap(11, 11, 11))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel12))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel4)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(TextFieldValorItem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TextFieldQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(FormattedTextFieldDataVenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addComponent(ButtonAdicionar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(62, 62, 62)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TextFieldValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ButtonCancelarVenda)
                            .addComponent(jLabel8)))
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ButtonFinalizarVenda))
                .addGap(36, 36, 36))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel10, jLabel11});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {ButtonAdicionar, ButtonRemoverLista});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        setBounds(0, 0, 1039, 638);
    }// </editor-fold>//GEN-END:initComponents

    private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosing
        // TODO add your handling code here:   
        venda = 0;
    }//GEN-LAST:event_formInternalFrameClosing

    private void TextFieldProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextFieldProdutoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextFieldProdutoActionPerformed

    private void TextFieldNomeCienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextFieldNomeCienteKeyReleased
        try {
            flag = true;
            this.pesquisarCliente();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, "Erro ao pesquisar cliente!");
        }
    }//GEN-LAST:event_TextFieldNomeCienteKeyReleased

    private void TextFieldProdutoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextFieldProdutoKeyReleased
        try {
            flag = !true;
            this.pesquisarVendaProduto();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, "Erro ao pesquisar produto!");
        }
    }//GEN-LAST:event_TextFieldProdutoKeyReleased

    private void TextFieldProdutoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TextFieldProdutoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_TextFieldProdutoMouseClicked

    private void TablePesquisaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablePesquisaMouseClicked
        // chamando o metodo setar_campos
        this.selecionador();
    }//GEN-LAST:event_TablePesquisaMouseClicked

    private void TextFieldQuantidadeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TextFieldQuantidadeFocusGained
        // chamando o metodo focusgained
        //this.focusgained();
    }//GEN-LAST:event_TextFieldQuantidadeFocusGained

    private void ButtonAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonAdicionarActionPerformed
        //a codição abaixo avisa o usuario que um ou mais campos estão sem preencher
        if (TextFieldNomeCiente.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Preencha o campo nome do cliente.");
            TextFieldNomeCiente.requestFocus();
            return;
        } else if (TextFieldProduto.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Preencha o campo produto.");
            TextFieldProduto.requestFocus();
            return;
        } else if (TextFieldQuantidade.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Preencha o campo quantidade.");
            TextFieldQuantidade.requestFocus();
            return;
        } else if (TextFieldValorItem.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Preencha o campo valor item.");
            TextFieldValorItem.requestFocus();
            return;
        } else if (FormattedTextFieldDataVenda.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Preencha o campo data.");
            FormattedTextFieldDataVenda.requestFocus();
            return;
        }
        // a condiçao abaixo trata o erro comparando a condiçao requisitada com a quantidade em estoque
        if (quantidadeEstoque >= Integer.parseInt(TextFieldQuantidade.getText())
                && Integer.parseInt(TextFieldQuantidade.getText()) >= 1) {
            try {
                this.calcularValorCompra();//chamando o metodo "calcularValorCompra" para fazer o calculo da compra final
                this.adicionar();//chamando o metodo "adicionar" para adicionar um produto a lista de compras
                this.pesquisarItensVenda();//chamando o metodo "pesquisar_itens_venda" para listar os produtos adicionado
                this.pesquisarVendaProduto();//chamando o metodo "pesquisar_cliente_produto" para atualizar a quantidade
                TextFieldProduto.requestFocus();//dando focu no campo produto para continuar a adicionar produtos
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "A quantidade desejada não está disponível!");
            TextFieldQuantidade.requestFocus();
        }
    }//GEN-LAST:event_ButtonAdicionarActionPerformed

    private void TextFieldProdutoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TextFieldProdutoFocusGained

    }//GEN-LAST:event_TextFieldProdutoFocusGained

    private void ButtonFinalizarVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonFinalizarVendaActionPerformed
        // TODO add your handling code here:
        try {
            if (valorTotal > 0) {//condiçao para não deixar o usuario finalizar uma venda com valor "0"
                finalizarVenda();//chamando o método finalizarVenda
                venda = 0;//variavel que define o formulario de venda fechado
            } else {
                JOptionPane.showMessageDialog(rootPane, "Primeiro adicione itens a lista de produto!");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage());
        }
    }//GEN-LAST:event_ButtonFinalizarVendaActionPerformed

    private void formInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosed
        // TODO add your handling code here:
    }//GEN-LAST:event_formInternalFrameClosed

    private void ButtonCancelarVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonCancelarVendaActionPerformed
        // TODO add your handling code here:
        try {
            int sair = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja cancelar esta venda?", "Atenção", JOptionPane.YES_NO_OPTION);
            // a condição abaixo valida a escolha do usuário
            if (sair == JOptionPane.YES_OPTION)//se a opção que o usuario for sim
            {
                this.excluir();//chama metodo para excluir uma venda
                this.dispose();//sai do formulario de venda
                venda = 0;//variavel que define o formulario de venda fechado
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage());
        }
    }//GEN-LAST:event_ButtonCancelarVendaActionPerformed

    private void TableItensVendaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableItensVendaMouseClicked
        // TODO add your handling code here:
        setarCamposItensVenda();
    }//GEN-LAST:event_TableItensVendaMouseClicked

    private void ButtonRemoverListaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonRemoverListaActionPerformed
        //a codição abaixo avisa o usuario que um ou mais campos estão sem preencher
        if (TextFieldNomeItemVenda.getText().isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "Selecione o produto a ser removido!");
            return;
        }
        try {
            value = true;
            controleVenda.excluirItensLista();
            this.pesquisarItensVenda();
            this.pesquisarVendaProduto();
            this.calcularValorCompra();
            TextFieldNomeItemVenda.setText(null);
            value = false;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(rootPane, e.getMessage());
        }
    }//GEN-LAST:event_ButtonRemoverListaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ButtonAdicionar;
    private javax.swing.JButton ButtonCancelarVenda;
    private javax.swing.JButton ButtonFinalizarVenda;
    private javax.swing.JButton ButtonRemoverLista;
    private javax.swing.JFormattedTextField FormattedTextFieldDataVenda;
    private javax.swing.JTable TableItensVenda;
    private javax.swing.JTable TablePesquisa;
    private javax.swing.JTextField TextFieldNomeCiente;
    private javax.swing.JTextField TextFieldNomeItemVenda;
    private javax.swing.JTextField TextFieldProduto;
    private javax.swing.JTextField TextFieldQuantidade;
    private javax.swing.JTextField TextFieldValorItem;
    private javax.swing.JTextField TextFieldValorTotal;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables

}
