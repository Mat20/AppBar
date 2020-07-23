package com.matheus.localizabar.Dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

public abstract class FuncionarioDaoGenerico<F> {

    /*
     * declaração variáveis bd
     */
    private static String NOME_DB = "funcionario";
    private int VERSAO_DB = 1;
    private Context context;
    private FuncionarioDbHelper funcionariodbHelper;

    public abstract List<F> selecionaTodos();


    public abstract F selecionaPorId(int i);


    public abstract boolean insert(F o);


    public abstract void delete(int i);


    public abstract void update(F o);

    //Construtor que ao ser invocado cria a base de dados
    //com auxilia de DbHelper
    protected FuncionarioDaoGenerico(Context context){
        this.context = context;
        //Invocando o construtor de DbHelper para criação do BD
        //informando context, cursor factory e a versão da base de dados
        funcionariodbHelper = new FuncionarioDbHelper(context, NOME_DB, null, VERSAO_DB);

    }

    //método retorna o BD em modo escrita
    protected SQLiteDatabase getDb(){
        return funcionariodbHelper.getWritableDatabase();
    }
}
