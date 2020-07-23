package com.matheus.localizabar.Model;

public class Menu {

    //classe POJO usada para instaciar objetos cliente

    private int id;
    private String produto = "";
    private String bebida = "";
    private String preco = "";

    public Menu(){

    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public String getBebida() {
        return bebida;
    }

    public void setBebida(String bebida) {
        this.bebida = bebida;
    }

    public String getPreco() {
        return preco;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }

    public int getId(){return id;}

    public void setId(int id){this.id = id;}
}
