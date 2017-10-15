/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Controle.ControleUsuario;
import java.io.File;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ModeloUsuario {

    ControleUsuario controleUsuario = new ControleUsuario();
    private int id_usuario;
    private String nome_login;
    private String senha;
    private String perfil;
    private String celular;
    private String email;
    private ModeloEndereco endereco;
    private String rota;
    private File foto;

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNome_login() {
        return nome_login;
    }

    public void setNome_login(String nome_login) {
        this.nome_login = nome_login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRota() {
        return rota;
    }

    public void setRota(String rota) {
        this.rota = rota;
    }

    public File getFoto() {
        return foto;
    }

    public void setFoto(File foto) {
        this.foto = foto;
    }

    public ModeloEndereco getEndereco() {
        return endereco;
    }

    public void setEndereco(ModeloEndereco endereco) {
        this.endereco = endereco;
    }
}
