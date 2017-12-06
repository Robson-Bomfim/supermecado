/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Visao;

import Controle.ControleUsuario;
import Modelo.ModeloEndereco;
import Modelo.ModeloUsuario;
import static Visao.TelaUser.x;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Dell
 */
public class TelaUser extends javax.swing.JInternalFrame {

    ModeloEndereco modeloEndereco = new ModeloEndereco();
    ModeloUsuario modeloUsuario = new ModeloUsuario();
    ControleUsuario controleUsuario = new ControleUsuario();

    public static String x;
    /**
     * Creates new form TelaUser
     */
    public TelaUser() {
        initComponents();
        x = "x";
    }
    
    private void adicionar() throws SQLException {//metodo para adicionar usuário

        if (TextFieldUserCodigo.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Preencha o campo código.");
            TextFieldUserCodigo.requestFocus();
            return;
        } else if (TextFieldUserNome.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Preencha o campo nome.");
            TextFieldUserNome.requestFocus();
            return;
        } else if (TextFieldUserSenha.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Preencha o campo senha.");
            TextFieldUserSenha.requestFocus();
            return;
        } else if (TextFieldUserCelular.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Preencha o campo valor item.");
            TextFieldUserCelular.requestFocus();
            return;
        } else if (TextFieldUserEmail.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Preencha o campo email.");
            TextFieldUserEmail.requestFocus();
            return;
        } else if (TextFieldRua.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Preencha o campo rua.");
            TextFieldRua.requestFocus();
            return;
        } else if (TextFieldBairro.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Preencha o campo bairro.");
            TextFieldBairro.requestFocus();
            return;
        } else if (TextFieldNumero.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Preencha o campo numero.");
            TextFieldNumero.requestFocus();
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
        //modeloUsuario.setFoto(LabelFoto);

        controleUsuario.adicionarUsuario(modeloUsuario);
        
        //condiçao para que caso não exista usuario cadastrado
        //faça com que o campo "TextFieldUserCodigo" receba foco
        if(controleUsuario.flag1 == true){
            TextFieldUserCodigo.requestFocus();
        }

        //chamando o metodo "limpam" os campos
        limpaCampos();
    }

    private void consultar() throws SQLException {

        if (TextFieldUserCodigo.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Preencha o campo código !");
            TextFieldUserCodigo.requestFocus();
            return;
        }

        modeloUsuario.setId_usuario(Integer.parseInt(TextFieldUserCodigo.getText()));
        ModeloUsuario modelo = controleUsuario.consultar(modeloUsuario);

        if (controleUsuario.flag == false) {
            JOptionPane.showMessageDialog(null, "Usuário de código " + TextFieldUserCodigo.getText() + " ainda não está cadastrado!");
            //chamando o metodo "limpam" os campos
            limpaCampos();
            TextFieldUserCodigo.requestFocus();
            return;
        }
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
        controleUsuario.flag = false;
    }

    //criando o metodo para alterar usuários
    private void alterar() throws SQLException {
        //validaçao dos campos obrigatórios
        if (TextFieldUserCodigo.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Preencha o campo código.");
            TextFieldUserCodigo.requestFocus();
            return;
        } else if (TextFieldUserNome.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Preencha o campo nome.");
            TextFieldUserNome.requestFocus();
            return;
        } else if (TextFieldUserSenha.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Preencha o campo senha.");
            TextFieldUserSenha.requestFocus();
            return;
        } else if (TextFieldUserCelular.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Preencha o campo valor item.");
            TextFieldUserCelular.requestFocus();
            return;
        } else if (TextFieldUserEmail.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Preencha o campo email.");
            TextFieldUserEmail.requestFocus();
            return;
        } else if (TextFieldRua.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Preencha o campo rua.");
            TextFieldRua.requestFocus();
            return;
        } else if (TextFieldBairro.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Preencha o campo bairro.");
            TextFieldBairro.requestFocus();
            return;
        } else if (TextFieldNumero.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Preencha o campo numero.");
            TextFieldNumero.requestFocus();
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
        controleUsuario.atualizarUsuario(modeloUsuario);

        //chamando o metodo "limpam" os campos
        limpaCampos();
    }

//metodo para remoção de usuários
    private void Excluir() throws SQLException {
        if (TextFieldUserCodigo.getText().isEmpty()) {//validaçao do campo "codigo" que é obrigatório para fazer a cunsulta
            JOptionPane.showMessageDialog(null, "Preencha o campo código!");//se o campo estiver vazio retorna essa mensagem para o usuario
            TextFieldUserCodigo.requestFocus();
            return;
        }

        int confirmar = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover o usuario de código " + TextFieldUserCodigo.getText().toUpperCase() + " ?", "Atenção", JOptionPane.YES_NO_OPTION);

        if (confirmar == JOptionPane.YES_OPTION) {

            modeloUsuario.setId_usuario(Integer.parseInt(TextFieldUserCodigo.getText()));
            controleUsuario.excluirUsuario(modeloUsuario);

            //chamando o metodo "limpam" os campos
            limpaCampos();
        }
    }

    private void limpaCampos() {
        TextFieldRua.setText(null);
        TextFieldNumero.setText(null);
        TextFieldBairro.setText(null);
        TextFieldCidade.setText(null);
        TextFieldEstado.setText(null);
        TextFieldUserNome.setText(null);
        TextFieldUserCelular.setText(null);
        TextFieldUserEmail.setText(null);
        TextFieldUserSenha.setText(null);
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
        ButtonAdicionar = new javax.swing.JButton();
        ButtonConsultar = new javax.swing.JButton();
        ButtonAtualizar = new javax.swing.JButton();
        ButtonApagar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        TextFieldUserSenha = new javax.swing.JTextField();
        celular = new javax.swing.JLabel();
        TextFieldUserCelular = new javax.swing.JFormattedTextField();
        senha = new javax.swing.JLabel();
        perfil = new javax.swing.JLabel();
        ComboBoxUserPerfil = new javax.swing.JComboBox<>();
        codigo = new javax.swing.JLabel();
        TextFieldUserCodigo = new javax.swing.JTextField();
        TextFieldUserNome = new javax.swing.JTextField();
        nome = new javax.swing.JLabel();
        email = new javax.swing.JLabel();
        TextFieldUserEmail = new javax.swing.JTextField();
        rua = new javax.swing.JLabel();
        TextFieldRua = new javax.swing.JTextField();
        numero = new javax.swing.JLabel();
        TextFieldNumero = new javax.swing.JTextField();
        bairro = new javax.swing.JLabel();
        TextFieldBairro = new javax.swing.JTextField();
        cidade = new javax.swing.JLabel();
        estado = new javax.swing.JLabel();
        TextFieldEstado = new javax.swing.JTextField();
        TextFieldCidade = new javax.swing.JTextField();

        setClosable(true);
        setIconifiable(true);

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        ButtonAdicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/salvar.png"))); // NOI18N
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

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        TextFieldUserSenha.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        TextFieldUserSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextFieldUserSenhaActionPerformed(evt);
            }
        });

        celular.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        celular.setText("Celular:");

        try {
            TextFieldUserCelular.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)#####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        TextFieldUserCelular.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        senha.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        senha.setText("Senha:");

        perfil.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        perfil.setText("Perfil:");

        ComboBoxUserPerfil.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "USER", "ADMIN" }));

        codigo.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        codigo.setText("Código:");

        TextFieldUserCodigo.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        TextFieldUserNome.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        nome.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        nome.setText("Nome:");

        email.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        email.setText("Email:");

        TextFieldUserEmail.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        TextFieldUserEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextFieldUserEmailActionPerformed(evt);
            }
        });

        rua.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        rua.setText("Rua:");

        TextFieldRua.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        numero.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        numero.setText("Numero:");

        TextFieldNumero.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        TextFieldNumero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextFieldNumeroActionPerformed(evt);
            }
        });

        bairro.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        bairro.setText("Bairro:");

        TextFieldBairro.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        TextFieldBairro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextFieldBairroActionPerformed(evt);
            }
        });

        cidade.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        cidade.setText("Cidade:");

        estado.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        estado.setText("Estado:");

        TextFieldEstado.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        TextFieldEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextFieldEstadoActionPerformed(evt);
            }
        });

        TextFieldCidade.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(cidade)
                        .addGap(18, 18, 18)
                        .addComponent(TextFieldCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(17, 17, 17)
                        .addComponent(estado)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(TextFieldEstado))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rua))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(TextFieldRua)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(numero)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(TextFieldNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(bairro)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(TextFieldBairro, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(TextFieldUserEmail)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(nome)
                                    .addGap(18, 18, 18)
                                    .addComponent(TextFieldUserNome, javax.swing.GroupLayout.PREFERRED_SIZE, 611, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(perfil, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(ComboBoxUserPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(codigo)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(TextFieldUserCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(senha)
                                .addGap(18, 18, 18)
                                .addComponent(TextFieldUserSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(celular)
                                .addGap(10, 10, 10)
                                .addComponent(TextFieldUserCelular, javax.swing.GroupLayout.PREFERRED_SIZE, 411, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(27, 27, 27))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(perfil)
                    .addComponent(ComboBoxUserPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(codigo)
                    .addComponent(TextFieldUserCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nome)
                    .addComponent(TextFieldUserNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(senha)
                    .addComponent(celular)
                    .addComponent(TextFieldUserSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextFieldUserCelular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(email)
                    .addComponent(TextFieldUserEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rua)
                    .addComponent(TextFieldRua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(numero)
                    .addComponent(TextFieldNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bairro)
                    .addComponent(TextFieldBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cidade)
                    .addComponent(estado)
                    .addComponent(TextFieldEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextFieldCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addComponent(ButtonAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(177, 177, 177)
                        .addComponent(ButtonConsultar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(177, 177, 177)
                        .addComponent(ButtonAtualizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(177, 177, 177)
                        .addComponent(ButtonApagar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(128, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ButtonApagar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ButtonAtualizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ButtonConsultar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ButtonAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 154, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
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

        setBounds(0, 0, 1039, 638);
    }// </editor-fold>//GEN-END:initComponents

    private void ButtonAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonAdicionarActionPerformed
        try {
            // chama o metodo adicionar
            adicionar();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao adicionar usuario!\nErro: " + ex);
        }
    }//GEN-LAST:event_ButtonAdicionarActionPerformed

    private void ButtonConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonConsultarActionPerformed
        try {
            // chamando o método consultar
            consultar();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao consultar usuario!\nErro: " + ex);
        }
    }//GEN-LAST:event_ButtonConsultarActionPerformed

    private void ButtonAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonAtualizarActionPerformed
        try {
            alterar();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao alterar usuario!\nErro: " + ex);
        }
    }//GEN-LAST:event_ButtonAtualizarActionPerformed

    private void ButtonApagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonApagarActionPerformed
        try {
            // chamando o metodo excluir
            Excluir();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir usuario!\nErro: " + ex);
        }
    }//GEN-LAST:event_ButtonApagarActionPerformed

    private void TextFieldUserSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextFieldUserSenhaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextFieldUserSenhaActionPerformed

    private void TextFieldUserEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextFieldUserEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextFieldUserEmailActionPerformed

    private void TextFieldNumeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextFieldNumeroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextFieldNumeroActionPerformed

    private void TextFieldBairroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextFieldBairroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextFieldBairroActionPerformed

    private void TextFieldEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextFieldEstadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextFieldEstadoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ButtonAdicionar;
    private javax.swing.JButton ButtonApagar;
    private javax.swing.JButton ButtonAtualizar;
    private javax.swing.JButton ButtonConsultar;
    private javax.swing.JComboBox<String> ComboBoxUserPerfil;
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
    private javax.swing.JLabel bairro;
    private javax.swing.JLabel celular;
    private javax.swing.JLabel cidade;
    private javax.swing.JLabel codigo;
    private javax.swing.JLabel email;
    private javax.swing.JLabel estado;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel nome;
    private javax.swing.JLabel numero;
    private javax.swing.JLabel perfil;
    private javax.swing.JLabel rua;
    private javax.swing.JLabel senha;
    // End of variables declaration//GEN-END:variables
}
