/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.Date;



public class ModeloVenda {
    private int idVenda;
    private Date data;
    private String pesquisar;
    private double valorVenda;
    private ModeloCliente Cliente;
    private ModeloProduto Produto;
    private ModeloUsuario idUsuario;
    private double valorIten;
    private int valorTotal;
    private int quantidade;

    public int getIdvenda() {
        return idVenda;
    }

    public void setIdvenda(int idvenda) {
        this.idVenda = idvenda;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public double getValorVenda() {
        return valorVenda;
    }

    public void setValorVenda(double valorVenda) {
        this.valorVenda = valorVenda;
    }

    public ModeloCliente getCliente() {
        return Cliente;
    }

    public void setCliente(ModeloCliente nomeCliente) {
        this.Cliente = nomeCliente;
    }

    public ModeloProduto getProduto() {
        return Produto;
    }

    public void setProduto(ModeloProduto nomeProduto) {
        this.Produto = nomeProduto;
    }

    public double getValorIten() {
        return valorIten;
    }

    public void setValorIten(double valorIten) {
        this.valorIten = valorIten;
    }

    public String getPesquisar() {
        return pesquisar;
    }

    public void setPesquisar(String pesquisar) {
        this.pesquisar = pesquisar;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(int valorTotal) {
        this.valorTotal = valorTotal;
    }

    public ModeloUsuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(ModeloUsuario idUsuario) {
        this.idUsuario = idUsuario;
    }
    
}
