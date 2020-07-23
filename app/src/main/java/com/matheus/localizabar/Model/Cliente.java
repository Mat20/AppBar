package com.matheus.localizabar.Model;

public class Cliente {

    //classe POJO usada para instaciar objetos cliente

    private int id;
    private String nome = "";
    private String cpf = "";
    private String mesa = "";

    public Cliente(){
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return  cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getMesa() {
        return mesa;
    }

    public void setMesa(String mesa) {
        this.mesa = mesa;
    }

    public int getId(){return id;}

    public void setId(int id){this.id = id;}
}
