/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.Date;


public class ModeloVenda {
    private int idVenda;
    private String data;
    private String pesquisar;
    private float valorVenda;
    private ModeloCliente Cliente;
    private ModeloProduto Produto;
    private int valorIten;
    private int valorTotal;
    private int quantidade;

    public int getIdvenda() {
        return idVenda;
    }

    public void setIdvenda(int idvenda) {
        this.idVenda = idvenda;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public float getValorVenda() {
        return valorVenda;
    }

    public void setValorVenda(float valorVenda) {
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

    public int getValorIten() {
        return valorIten;
    }

    public void setValorIten(int valorIten) {
        this.valorIten = valorIten;
    }

    public String getPesquisar() {
        return pesquisar;
    }

    public void setPesquisar(String pesquisar) {
        this.pesquisar = pesquisar;
    }

    public int getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(int idVenda) {
        this.idVenda = idVenda;
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
    
}
