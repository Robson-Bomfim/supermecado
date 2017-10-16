/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Visao;

import Controle.ControleUsuario;
import Modelo.ModeloEndereco;
import Modelo.ModeloUsuario;
import java.sql.*;
import dao.Conectar;
import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class TelaUsuario extends javax.swing.JInternalFrame {

    ModeloEndereco modeloEndereco = new ModeloEndereco();
    ModeloUsuario modeloUsuario = new ModeloUsuario();
    ControleUsuario controleUsuario = new ControleUsuario();
    Connection conexao = null;//usando a variavel conexão do dal

    //criando variaveis especiais para conexão com o banco
    //Prepared Statement e ResultSet são framework do pacote java.sql
    //e serve para preparar e executar as instruções sql
    PreparedStatement pst;
    ResultSet rs;
    public static String x;

    public TelaUsuario() {
        initComponents();
        this.conexao = new Conectar().openConnection();
        x = "x";
        //aqui está chamando o método conector(); 
        //que chama a class ModuloConexao que está 
        //dentro do pacote dao
        itensObrigatorios();//metodo para setar o label de vermelho
    }

    private void itensObrigatorios() {
        campo.setForeground(Color.RED);
        lblSenha.setForeground(Color.RED);
        lblEmail.setForeground(Color.RED);
        lblNome.setForeground(Color.RED);
        lblPerfil.setForeground(Color.RED);
        lblCodigo.setForeground(Color.RED);
    }

    private void adicionar() throws SQLException {//metodo para adicionar usuário

        if (TextFieldUserCodigo.getText().isEmpty() || TextFieldUserNome.getText().isEmpty() || TextFieldUserSenha.getText().isEmpty() || ComboBoxUserPerfil.getSelectedItem().toString().isEmpty() || TextFieldUserEmail.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios!");//se os campos estiverem vazios retorna essa mensagem para o usuario
            return;
        }

        modeloEndereco.setRua(TextFieldRua.getText());
        modeloEndereco.setNumero(Integer.parseInt(TextFieldNumero.getText()));
        modeloEndereco.setBairro(TextFieldBairro.getText());
        modeloEndereco.setCidade(TextFieldCidade.getText());
        modeloEndereco.setEstado(TextFieldEstado.getText());

        modeloUsuario.setEndereco(modeloEndereco);

        modeloUsuario.setId_usuario(Integer.parseInt(TextFieldUserCodigo.getText()));
        modeloUsuario.setNome_login(TextFieldUserNome.getText());
        modeloUsuario.setCelular(TextFieldUserCelular.getText());
        modeloUsuario.setEmail(TextFieldUserEmail.getText());
        modeloUsuario.setSenha(TextFieldUserSenha.getText());
        modeloUsuario.setPerfil((String) ComboBoxUserPerfil.getSelectedItem());
        modeloUsuario.setRota(TextField_Path.getText());
        //modeloUsuario.setFoto(LabelFoto);

        controleUsuario.adicionarUsuario(modeloUsuario, modeloEndereco);

        //as linhas abaixo "limpam" os campos
        TextFieldRua.setText(null);
        TextFieldNumero.setText(null);
        TextFieldBairro.setText(null);
        TextFieldCidade.setText(null);
        TextFieldEstado.setText(null);
        TextFieldUserNome.setText(null);
        TextFieldUserCelular.setText(null);
        TextFieldUserEmail.setText(null);
        TextFieldUserSenha.setText(null);
        LabelFoto.setText(null);
        TextField_Path.setText(null);
    }

    private void consultar() throws SQLException {

        if (TextFieldUserCodigo.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Preencha o campo código !");
            return;
        }

        modeloUsuario.setId_usuario(Integer.parseInt(TextFieldUserCodigo.getText()));
        ModeloUsuario modelo = controleUsuario.consultar(modeloUsuario);

        TextFieldUserNome.setText(String.valueOf(modelo.getNome_login()));
        TextFieldUserSenha.setText(String.valueOf(modelo.getSenha()));
        TextFieldUserCelular.setText(String.valueOf(modelo.getCelular()));
        TextFieldUserEmail.setText(String.valueOf(modelo.getEmail()));
        ComboBoxUserPerfil.setSelectedItem(String.valueOf(modelo.getPerfil()));
        TextFieldRua.setText(String.valueOf(modelo.getEndereco().getRua()));
        TextFieldNumero.setText(String.valueOf(modelo.getEndereco().getNumero()));
        TextFieldBairro.setText(String.valueOf(modelo.getEndereco().getBairro()));
        TextFieldCidade.setText(String.valueOf(modelo.getEndereco().getCidade()));
        TextFieldEstado.setText(String.valueOf(modelo.getEndereco().getEstado()));
    }

    //criando o metodo para alterar usuários
    private void alterar() throws SQLException {
        //validaçao dos campos obrigatórios
        if (TextFieldUserCodigo.getText().isEmpty() || TextFieldUserNome.getText().isEmpty() || TextFieldUserSenha.getText().isEmpty() || TextFieldUserEmail.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios!");//se os campos estiverem vazios retorna essa mensagem para o usuario
            return;
        }

        modeloEndereco.setRua(TextFieldRua.getText());
        modeloEndereco.setNumero(Integer.parseInt(TextFieldNumero.getText()));
        modeloEndereco.setBairro(TextFieldBairro.getText());
        modeloEndereco.setCidade(TextFieldCidade.getText());
        modeloEndereco.setEstado(TextFieldEstado.getText());

        modeloUsuario.setEndereco(modeloEndereco);

        modeloUsuario.setId_usuario(Integer.parseInt(TextFieldUserCodigo.getText()));
        modeloUsuario.setNome_login(TextFieldUserNome.getText());
        modeloUsuario.setCelular(TextFieldUserCelular.getText());
        modeloUsuario.setEmail(TextFieldUserEmail.getText());
        modeloUsuario.setSenha(TextFieldUserSenha.getText());
        modeloUsuario.setPerfil((String) ComboBoxUserPerfil.getSelectedItem());
        modeloUsuario.setRota(TextField_Path.getText());

        controleUsuario.atualizarUsuario(modeloUsuario, modeloEndereco);

        //as linhas abaixo "limpam" os campos
        TextFieldRua.setText(null);
        TextFieldNumero.setText(null);
        TextFieldBairro.setText(null);
        TextFieldCidade.setText(null);
        TextFieldEstado.setText(null);
        TextFieldUserNome.setText(null);
        TextFieldUserCelular.setText(null);
        TextFieldUserEmail.setText(null);
        TextFieldUserSenha.setText(null);
        LabelFoto.setText(null);
        TextField_Path.setText(null);
    }

//metodo para remoção de usuários
    private void Excluir() throws SQLException {
        if (TextFieldUserCodigo.getText().isEmpty()) {//validaçao do campo "codigo" que é obrigatório para fazer a cunsulta
            JOptionPane.showMessageDialog(null, "Preencha o campo código!");//se o campo estiver vazio retorna essa mensagem para o usuario
            return;
        }

        int confirmar = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover o usuario de código " + TextFieldUserCodigo.getText().toUpperCase() + " ?", "Atenção", JOptionPane.YES_NO_OPTION);

        if (confirmar == JOptionPane.YES_OPTION) {

            modeloUsuario.setId_usuario(Integer.parseInt(TextFieldUserCodigo.getText()));
            controleUsuario.excluirUsuario(modeloUsuario);

            //as linhas abaixo "limpam" os campos
            TextFieldRua.setText(null);
            TextFieldNumero.setText(null);
            TextFieldBairro.setText(null);
            TextFieldCidade.setText(null);
            TextFieldEstado.setText(null);
            TextFieldUserNome.setText(null);
            TextFieldUserCelular.setText(null);
            TextFieldUserEmail.setText(null);
            TextFieldUserSenha.setText(null);
            LabelFoto.setText(null);
            TextField_Path.setText(null);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ButtonAdicionar = new javax.swing.JButton();
        ButtonConsultar = new javax.swing.JButton();
        ButtonAtualizar = new javax.swing.JButton();
        ButtonApagar = new javax.swing.JButton();
        LabelFoto = new javax.swing.JLabel();
        Button_Add_Image = new javax.swing.JButton();
        TextField_Path = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        campo = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        ComboBoxUserPerfil = new javax.swing.JComboBox<>();
        lblPerfil = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        TextFieldUserCodigo = new javax.swing.JTextField();
        lblCodigo = new javax.swing.JLabel();
        TextFieldUserNome = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        lblNome = new javax.swing.JLabel();
        TextFieldUserSenha = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        TextFieldUserCelular = new javax.swing.JFormattedTextField();
        jLabel2 = new javax.swing.JLabel();
        lblSenha = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        TextFieldUserEmail = new javax.swing.JTextField();
        lblEmail = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        TextFieldRua = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        TextFieldNumero = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        TextFieldBairro = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        TextFieldEstado = new javax.swing.JTextField();
        TextFieldCidade = new javax.swing.JTextField();

        setBorder(null);
        setClosable(true);
        setIconifiable(true);
        setTitle("CADASTRO DE USUÁRIOS");
        setToolTipText("");
        setMinimumSize(new java.awt.Dimension(833, 473));
        setPreferredSize(new java.awt.Dimension(836, 475));
        setVisible(true);
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

        ButtonAdicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/if_icon-81-document-add.png"))); // NOI18N
        ButtonAdicionar.setToolTipText("Adicionar");
        ButtonAdicionar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ButtonAdicionar.setPreferredSize(new java.awt.Dimension(60, 60));
        ButtonAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonAdicionarActionPerformed(evt);
            }
        });

        ButtonConsultar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/if_icon-74-document-search_315198.png"))); // NOI18N
        ButtonConsultar.setToolTipText("Consultar");
        ButtonConsultar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ButtonConsultar.setPreferredSize(new java.awt.Dimension(60, 60));
        ButtonConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonConsultarActionPerformed(evt);
            }
        });

        ButtonAtualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/if_icon-136-document-edit_314724.png"))); // NOI18N
        ButtonAtualizar.setToolTipText("Alterar");
        ButtonAtualizar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ButtonAtualizar.setPreferredSize(new java.awt.Dimension(60, 60));
        ButtonAtualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonAtualizarActionPerformed(evt);
            }
        });

        ButtonApagar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/if_icon-27-trash-can_314759.png"))); // NOI18N
        ButtonApagar.setToolTipText("Apagar");
        ButtonApagar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ButtonApagar.setPreferredSize(new java.awt.Dimension(60, 60));
        ButtonApagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonApagarActionPerformed(evt);
            }
        });

        LabelFoto.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        Button_Add_Image.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/if_icon-93-inbox-upload.png"))); // NOI18N
        Button_Add_Image.setToolTipText("Adicionar Imagem");
        Button_Add_Image.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Button_Add_Image.setPreferredSize(new java.awt.Dimension(40, 40));
        Button_Add_Image.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_Add_ImageActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        campo.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        campo.setText("*");

        jLabel10.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel10.setText("Campos obrigatórios");

        jLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel1.setText("Perfil:");

        ComboBoxUserPerfil.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "USER", "ADMIN" }));

        lblPerfil.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblPerfil.setText("*");

        jLabel7.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel7.setText("Código:");

        TextFieldUserCodigo.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        lblCodigo.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblCodigo.setText("*");

        TextFieldUserNome.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel6.setText("Nome:");

        lblNome.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblNome.setText("*");

        TextFieldUserSenha.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel4.setText("Celular:");

        try {
            TextFieldUserCelular.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)#####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        TextFieldUserCelular.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel2.setText("Senha:");

        lblSenha.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblSenha.setText("*");

        jLabel5.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel5.setText("Email:");

        TextFieldUserEmail.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        TextFieldUserEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextFieldUserEmailActionPerformed(evt);
            }
        });

        lblEmail.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblEmail.setText("*");

        jLabel9.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel9.setText("Rua:");

        jLabel8.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel8.setText("Cidade:");

        TextFieldRua.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel3.setText("Numero:");

        TextFieldNumero.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        TextFieldNumero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextFieldNumeroActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel11.setText("Bairro:");

        TextFieldBairro.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        TextFieldBairro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextFieldBairroActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel12.setText("Estado:");

        TextFieldEstado.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        TextFieldCidade.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lblPerfil)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(ComboBoxUserPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblCodigo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(TextFieldUserCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(campo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel10)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lblNome)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel6)
                                .addGap(18, 18, 18)
                                .addComponent(TextFieldUserNome))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lblSenha)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel2))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lblEmail)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9)))
                            .addComponent(jLabel8))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TextFieldUserEmail)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(TextFieldUserSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel4)
                                .addGap(10, 10, 10)
                                .addComponent(TextFieldUserCelular, javax.swing.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(TextFieldRua)
                                    .addComponent(TextFieldCidade, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel12))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(TextFieldNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel11)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(TextFieldBairro))
                                    .addComponent(TextFieldEstado))))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(campo))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(TextFieldUserCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblCodigo))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(lblPerfil)
                        .addComponent(ComboBoxUserPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(TextFieldUserNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNome))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4)
                    .addComponent(TextFieldUserSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextFieldUserCelular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSenha))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(TextFieldUserEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblEmail))
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(TextFieldRua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(TextFieldNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(TextFieldBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel12)
                    .addComponent(TextFieldEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextFieldCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(128, 128, 128)
                        .addComponent(ButtonAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 177, Short.MAX_VALUE)
                        .addComponent(ButtonConsultar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(177, 177, 177)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(ButtonAtualizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(177, 177, 177)
                        .addComponent(ButtonApagar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(TextField_Path)
                                .addGap(18, 18, 18)
                                .addComponent(Button_Add_Image, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(LabelFoto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(97, 97, 97))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {ButtonAdicionar, ButtonApagar, ButtonAtualizar, ButtonConsultar});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ButtonApagar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ButtonAtualizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ButtonConsultar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ButtonAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(114, 114, 114)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(LabelFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(TextField_Path, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Button_Add_Image, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29))))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {ButtonAdicionar, ButtonApagar, ButtonAtualizar, ButtonConsultar});

        setBounds(0, 0, 1039, 638);
    }// </editor-fold>//GEN-END:initComponents

    private void TextFieldUserEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextFieldUserEmailActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_TextFieldUserEmailActionPerformed

    private void ButtonApagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonApagarActionPerformed
        try {
            // chamando o metodo excluir
            Excluir();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir usuario!\nErro: " + ex);
        }
    }//GEN-LAST:event_ButtonApagarActionPerformed

    private void ButtonConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonConsultarActionPerformed
        try {
            // chamando o método consultar
            consultar();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao consultar usuario!\nErro: " + ex);
        }
    }//GEN-LAST:event_ButtonConsultarActionPerformed

    private void ButtonAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonAdicionarActionPerformed
        try {
            // chama o metodo adicionar
            adicionar();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao adicionar usuario!\nErro: " + ex);
        }
    }//GEN-LAST:event_ButtonAdicionarActionPerformed

    private void ButtonAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonAtualizarActionPerformed
        try {
            alterar();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao alterar usuario!\nErro: " + ex);
        }
    }//GEN-LAST:event_ButtonAtualizarActionPerformed

    private void Button_Add_ImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_Add_ImageActionPerformed
        // TODO add your handling code here:
        try {
            JFileChooser escolher = new JFileChooser();
            int janela = escolher.showOpenDialog(null);
            if (janela == JFileChooser.APPROVE_OPTION) {
                escolher.setDialogTitle("Carregar imagem do usuário");
                File arquivo = escolher.getSelectedFile();
                TextField_Path.setText(String.valueOf(arquivo));
                Image foto = getToolkit().getImage(TextField_Path.getText());
                foto = foto.getScaledInstance(297, 280, Image.SCALE_DEFAULT);
                LabelFoto.setIcon(new ImageIcon(foto));
            }
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_Button_Add_ImageActionPerformed

    private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosing
        // TODO add your handling code here:
        x = null;
    }//GEN-LAST:event_formInternalFrameClosing

    private void TextFieldNumeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextFieldNumeroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextFieldNumeroActionPerformed

    private void TextFieldBairroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextFieldBairroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextFieldBairroActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ButtonAdicionar;
    private javax.swing.JButton ButtonApagar;
    private javax.swing.JButton ButtonAtualizar;
    private javax.swing.JButton ButtonConsultar;
    private javax.swing.JButton Button_Add_Image;
    private javax.swing.JComboBox<String> ComboBoxUserPerfil;
    public javax.swing.JLabel LabelFoto;
    public javax.swing.JTextField TextFieldBairro;
    public javax.swing.JTextField TextFieldCidade;
    public javax.swing.JTextField TextFieldEstado;
    public javax.swing.JTextField TextFieldNumero;
    public javax.swing.JTextField TextFieldRua;
    public javax.swing.JFormattedTextField TextFieldUserCelular;
    private javax.swing.JTextField TextFieldUserCodigo;
    public javax.swing.JTextField TextFieldUserEmail;
    public javax.swing.JTextField TextFieldUserNome;
    public javax.swing.JTextField TextFieldUserSenha;
    public javax.swing.JTextField TextField_Path;
    private javax.swing.JLabel campo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblCodigo;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblPerfil;
    private javax.swing.JLabel lblSenha;
    // End of variables declaration//GEN-END:variables
}
