package com.matheus.localizabar.Model;

public class Funcionario {

    //classe POJO usada para instaciar objetos cliente

    private int id;
    private String nome = "";
    private String cpf = "";

    public Funcionario(){

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

    public int getId(){return id;}

    public void setId(int id){this.id = id;}
}
