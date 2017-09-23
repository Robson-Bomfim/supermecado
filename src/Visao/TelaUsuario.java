/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Visao;

import java.sql.*;
import dao.ModuloConexao;
import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class TelaUsuario extends javax.swing.JInternalFrame {

    Connection conexao = null;//usando a variavel conexão do dal

    //criando variaveis especiais para conexão com o banco
    //Prepared Statement e ResultSet são framework do pacote java.sql
    //e serve para preparar e executar as instruções sql
    PreparedStatement pst = null;
    ResultSet rs = null;

    public TelaUsuario() {
        initComponents();

        conexao = ModuloConexao.conector();
        //aqui está chamando o método conector(); 
        //que chama a class ModuloConexao que está 
        //dentro do pacote dao
        itensObrigatorios();
    }

    private void itensObrigatorios() {
        campo.setForeground(Color.RED);
        lblSenha.setForeground(Color.RED);
        lblEmail.setForeground(Color.RED);
        lblNome.setForeground(Color.RED);
        lblPerfil.setForeground(Color.RED);
        lblCodigo.setForeground(Color.RED);
    }

    private void consultar() {//metodo para consultar usuário
        String sql = "select * from usuario where id_usuario = ?";//string sql que pesquisa a tabela e id do usuario
        try {
            pst = conexao.prepareStatement(sql);//linha que faz conexão passando a string sql como parametro para que a pesquisa seja feita dentro do banco

            if (TextFieldUserCodigo.getText().isEmpty()) {//validaçao do campo "codigo" que é obrigatório para fazer a cunsulta

                JOptionPane.showMessageDialog(null, "Preencha o campo código!");//se o campo estiver vazio retorna essa mensagem para o usuario

            } else {

                pst.setString(1, TextFieldUserCodigo.getText());//linha que faz a pesquisa do usuario através do seu codigo
                rs = pst.executeQuery();//linha que executa a conexão

                if (rs.next()) {//se a conexão for verdadeira
                    //as linhas abaixo consulta os valores que são digitados pelo usuário
                    TextFieldUserNome.setText(rs.getString(2));
                    TextFieldUserCelular.setText(rs.getString(6));
                    TextFieldResidencial.setText(rs.getString(7));
                    TextFieldUserEmail.setText(rs.getString(8));
                    ComboBoxUserPerfil.setSelectedItem(rs.getString(4));// a linha se refere especificamente ao combobox
                    TextFieldUserSenha.setText(rs.getString(3));
                    FormattedTextFieldData_Nasc.setText(rs.getString(5));

                } else {
                    JOptionPane.showMessageDialog(null, "Usuário não cadastrado!");//se não houver usuário com o código pesquisado essa mensagem aparece para o usuário

                    //as linhas abaixo "limpam" os campos
                    TextFieldUserNome.setText(null);
                    TextFieldUserCelular.setText(null);
                    TextFieldResidencial.setText(null);
                    TextFieldUserEmail.setText(null);
                    //ComboBoxUserPerfil.setSelectedItem(null);// a linha se refere especificamente ao combobox
                    TextFieldUserSenha.setText(null);
                    FormattedTextFieldData_Nasc.setText(null);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());//essa mensagem mostra o tipo de erro que ocorreu 
        }
    }

    private void adicionar() {//metodo para adicionar usuário

        //a string sql é executada dentro do banco de dados passando os valores que serão inseridos dentro do banco
        String sql = "insert into usuario (id_usuario,nome_login,senha,perfil,data_nasc,celular,residencial,email,foto,rota) values (?,?,?,?,?,?,?,?,?,?)";

        try {
            pst = conexao.prepareStatement(sql);//faz a conexão com o banco executando e passando a string sql como parametro

            //as linhas abaixo insere os valores que são digitados pelo usuário
            FileInputStream arquivoFoto;
            pst.setString(1, TextFieldUserCodigo.getText());
            pst.setString(2, TextFieldUserNome.getText());
            pst.setString(3, TextFieldUserSenha.getText());
            pst.setString(4, ComboBoxUserPerfil.getSelectedItem().toString());// a linha se refere especificamente ao combobox
            pst.setString(5, FormattedTextFieldData_Nasc.getText());
            pst.setString(6, TextFieldUserCelular.getText());
            pst.setString(7, TextFieldResidencial.getText());
            pst.setString(8, TextFieldUserEmail.getText());
            arquivoFoto = new FileInputStream(TextField_Path.getText());
            pst.setBinaryStream(9, arquivoFoto);
            pst.setString(10, TextField_Path.getText());

            //validaçao dos campos obrigatórios
            if (TextFieldUserCodigo.getText().isEmpty() || TextFieldUserNome.getText().isEmpty() || TextFieldUserSenha.getText().isEmpty() || ComboBoxUserPerfil.getSelectedItem().toString().isEmpty() || TextFieldUserEmail.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos!");//se os campos estiverem vazios retorna essa mensagem para o usuario

            } else {

                //a linha abaixo atualiza a tabela usuario com os dados do formulario
                //a estrutura abaixo é usado para confirmar os dados cadastrados na tabela
                int adicionado = pst.executeUpdate();
                //System.out.println(adicionado);//serve de apoio para o entendimento do codigo e essa linha retorna o valor da variavel adicionado

                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Usuário adicionado com sucesso!");//se tudo ocorreu comforme o esperado, essa mensagem é exibida para o usuário
                    //as linhas abaixo "limpam" os campos
                    TextFieldUserCodigo.setText(null);
                    TextFieldUserNome.setText(null);
                    TextFieldUserCelular.setText(null);
                    TextFieldResidencial.setText(null);
                    TextFieldUserEmail.setText(null);
                    //ComboBoxUserPerfil.setSelectedItem(null);// a linha se refere especificamente ao combobox
                    TextFieldUserSenha.setText(null);
                    FormattedTextFieldData_Nasc.setText(null);
                }
            }
        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e.getMessage());//essa mensagem mostra o tipo de erro que ocorreu 

        }

    }

    //criando o metodo para alterar usuários
    public void alterar() {
        String sql = "update usuario set nome_login=?,senha=?,perfil=?,data_nasc=?,celular=?,residencial=?,email=? where id_usuario=?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, TextFieldUserNome.getText());
            pst.setString(2, TextFieldUserCelular.getText());
            pst.setString(3, TextFieldResidencial.getText());
            pst.setString(4, TextFieldUserEmail.getText());
            pst.setString(5, ComboBoxUserPerfil.getSelectedItem().toString());
            pst.setString(6, TextFieldUserSenha.getText());
            pst.setString(7, FormattedTextFieldData_Nasc.getText());
            pst.setString(8, TextFieldUserCodigo.getText());

            //validaçao dos campos obrigatórios
            if (TextFieldUserCodigo.getText().isEmpty() || TextFieldUserNome.getText().isEmpty() || TextFieldUserSenha.getText().isEmpty() || ComboBoxUserPerfil.getSelectedItem().toString().isEmpty() || TextFieldUserEmail.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos!");//se os campos estiverem vazios retorna essa mensagem para o usuario

            } else {

                //a linha abaixo atualiza a tabela usuario com os dados do formulario
                //a estrutura abaixo é usado para confirmar os dados cadastrados na tabela
                int adicionado = pst.executeUpdate();
                //System.out.println(adicionado);//serve de apoio para o entendimento do codigo e essa linha retorna o valor da variavel adicionado

                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Dados do usuário alterado com sucesso!");//se tudo ocorreu comforme o esperado, essa mensagem é exibida para o usuário
                    //as linhas abaixo "limpam" os campos
                    TextFieldUserCodigo.setText(null);
                    TextFieldUserNome.setText(null);
                    TextFieldUserCelular.setText(null);
                    TextFieldResidencial.setText(null);
                    TextFieldUserEmail.setText(null);
                    //ComboBoxUserPerfil.setSelectedItem(null);// a linha se refere especificamente ao combobox
                    TextFieldUserSenha.setText(null);
                    FormattedTextFieldData_Nasc.setText(null);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());//essa mensagem mostra o tipo de erro que ocorreu 
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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        TextFieldUserNome = new javax.swing.JTextField();
        TextFieldUserEmail = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        ButtonAdicionar = new javax.swing.JButton();
        ButtonConsultar = new javax.swing.JButton();
        ButtonAtualizar = new javax.swing.JButton();
        ButtonApagar = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        TextFieldUserCodigo = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        TextFieldUserSenha = new javax.swing.JTextField();
        ComboBoxUserPerfil = new javax.swing.JComboBox<>();
        FormattedTextFieldData_Nasc = new javax.swing.JFormattedTextField();
        TextFieldUserCelular = new javax.swing.JFormattedTextField();
        TextFieldResidencial = new javax.swing.JFormattedTextField();
        jLabel10 = new javax.swing.JLabel();
        campo = new javax.swing.JLabel();
        lblSenha = new javax.swing.JLabel();
        lblCodigo = new javax.swing.JLabel();
        lblPerfil = new javax.swing.JLabel();
        lblNome = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        LabelFoto = new javax.swing.JLabel();
        Button_Add_Image = new javax.swing.JButton();
        TextField_Path = new javax.swing.JTextField();

        setBorder(null);
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Usuários");
        setToolTipText("Adicionar Imagem");
        setMinimumSize(new java.awt.Dimension(833, 473));
        setPreferredSize(new java.awt.Dimension(833, 476));
        setVisible(true);

        jLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel1.setText("Perfil");

        jLabel2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel2.setText("Senha");

        jLabel4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel4.setText("Celular");

        jLabel5.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel5.setText("Email");

        TextFieldUserNome.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        TextFieldUserEmail.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        TextFieldUserEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextFieldUserEmailActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel6.setText("Nome");

        ButtonAdicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/if_file_add_48761.png"))); // NOI18N
        ButtonAdicionar.setToolTipText("Adicionar");
        ButtonAdicionar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ButtonAdicionar.setPreferredSize(new java.awt.Dimension(60, 60));
        ButtonAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonAdicionarActionPerformed(evt);
            }
        });

        ButtonConsultar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/if_file_search_48764.png"))); // NOI18N
        ButtonConsultar.setToolTipText("Consultar");
        ButtonConsultar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ButtonConsultar.setPreferredSize(new java.awt.Dimension(60, 60));
        ButtonConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonConsultarActionPerformed(evt);
            }
        });

        ButtonAtualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/if_file_edit_48763.png"))); // NOI18N
        ButtonAtualizar.setToolTipText("Alterar");
        ButtonAtualizar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ButtonAtualizar.setPreferredSize(new java.awt.Dimension(60, 60));
        ButtonAtualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonAtualizarActionPerformed(evt);
            }
        });

        ButtonApagar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/if_file_delete_48762.png"))); // NOI18N
        ButtonApagar.setToolTipText("Apagar");
        ButtonApagar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ButtonApagar.setPreferredSize(new java.awt.Dimension(60, 60));
        ButtonApagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonApagarActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel7.setText("Código:");

        TextFieldUserCodigo.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel8.setText("Data de nascimento");

        jLabel9.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel9.setText("Residencial");

        TextFieldUserSenha.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        ComboBoxUserPerfil.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        ComboBoxUserPerfil.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Usuário", "Adminstrador" }));

        FormattedTextFieldData_Nasc.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        try {
            TextFieldUserCelular.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)#####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        TextFieldUserCelular.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        try {
            TextFieldResidencial.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        TextFieldResidencial.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel10.setText("Campos obrigatórios");

        campo.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        campo.setText("*");

        lblSenha.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblSenha.setText("*");

        lblCodigo.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblCodigo.setText("*");

        lblPerfil.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblPerfil.setText("*");

        lblNome.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblNome.setText("*");

        lblEmail.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblEmail.setText("*");

        LabelFoto.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        Button_Add_Image.setText("Adicionar");
        Button_Add_Image.setToolTipText("Adicionar Imagem");
        Button_Add_Image.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_Add_ImageActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(104, 104, 104)
                .addComponent(ButtonAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(ButtonConsultar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(ButtonAtualizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(ButtonApagar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(51, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(campo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblSenha)
                                    .addComponent(lblPerfil)
                                    .addComponent(lblNome))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING))
                                    .addComponent(jLabel2)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblEmail)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(ComboBoxUserPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(102, 102, 102))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(TextFieldUserSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel4)))
                                .addGap(10, 10, 10)
                                .addComponent(TextFieldUserCelular))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblCodigo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(TextFieldUserCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(TextFieldUserNome, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TextFieldUserEmail, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 398, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(FormattedTextFieldData_Nasc, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(8, 8, 8)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(TextFieldResidencial)
                                    .addComponent(TextField_Path, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(LabelFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(316, 316, 316)
                                .addComponent(Button_Add_Image)))))
                .addContainerGap(44, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {ButtonAdicionar, ButtonApagar, ButtonAtualizar, ButtonConsultar});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ButtonAdicionar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ButtonConsultar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ButtonAtualizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ButtonApagar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(173, 173, 173)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(campo))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel7)
                            .addComponent(TextFieldUserCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ComboBoxUserPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblCodigo)
                            .addComponent(lblPerfil)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(LabelFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(TextFieldUserNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblNome))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 76, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(TextField_Path, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Button_Add_Image))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4)
                    .addComponent(jLabel9)
                    .addComponent(TextFieldUserSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextFieldUserCelular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextFieldResidencial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSenha))
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(TextFieldUserEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblEmail)
                    .addComponent(jLabel8)
                    .addComponent(FormattedTextFieldData_Nasc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {ButtonAdicionar, ButtonApagar, ButtonAtualizar, ButtonConsultar});

        setBounds(0, 0, 980, 627);
    }// </editor-fold>//GEN-END:initComponents

    private void TextFieldUserEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextFieldUserEmailActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_TextFieldUserEmailActionPerformed

    private void ButtonApagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonApagarActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_ButtonApagarActionPerformed

    private void ButtonConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonConsultarActionPerformed
        // chamando o método consultar
        try {
            consultar();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_ButtonConsultarActionPerformed

    private void ButtonAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonAdicionarActionPerformed
        // chama o metodo adicionar
        adicionar();

    }//GEN-LAST:event_ButtonAdicionarActionPerformed

    private void ButtonAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonAtualizarActionPerformed
        // chama o metodo alterar
        alterar();
    }//GEN-LAST:event_ButtonAtualizarActionPerformed

    private void Button_Add_ImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_Add_ImageActionPerformed
        // TODO add your handling code here:
        try {
            JFileChooser escolher = new JFileChooser();
            int janela = escolher.showOpenDialog(this);
            if (janela == JFileChooser.APPROVE_OPTION) {
                File arquivo = escolher.getSelectedFile();
                TextField_Path.setText(String.valueOf(arquivo));
                Image foto = getToolkit().getImage(TextField_Path.getText());
                LabelFoto.setIcon(new ImageIcon(foto));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_Button_Add_ImageActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ButtonAdicionar;
    private javax.swing.JButton ButtonApagar;
    private javax.swing.JButton ButtonAtualizar;
    private javax.swing.JButton ButtonConsultar;
    private javax.swing.JButton Button_Add_Image;
    private javax.swing.JComboBox<String> ComboBoxUserPerfil;
    private javax.swing.JFormattedTextField FormattedTextFieldData_Nasc;
    private javax.swing.JLabel LabelFoto;
    private javax.swing.JFormattedTextField TextFieldResidencial;
    private javax.swing.JFormattedTextField TextFieldUserCelular;
    private javax.swing.JTextField TextFieldUserCodigo;
    private javax.swing.JTextField TextFieldUserEmail;
    private javax.swing.JTextField TextFieldUserNome;
    private javax.swing.JTextField TextFieldUserSenha;
    private javax.swing.JTextField TextField_Path;
    private javax.swing.JLabel campo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel lblCodigo;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblPerfil;
    private javax.swing.JLabel lblSenha;
    // End of variables declaration//GEN-END:variables
}
