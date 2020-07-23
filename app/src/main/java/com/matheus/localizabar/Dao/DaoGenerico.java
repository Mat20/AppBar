package com.matheus.localizabar.Dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

public abstract class DaoGenerico<T> {

    /*
     * declaração variáveis bd
     */
    private static String NOME_DB = "cliente";
    private int VERSAO_DB = 1;
    private Context context;
    private DbHelper dbHelper;

    public abstract List<T> selecionaTodos();


    public abstract T selecionaPorId(int i);


    public abstract boolean insert(T o);


    public abstract void delete(int i);


    public abstract void update(T o);

    //Construtor que ao ser invocado cria a base de dados
    //com auxilia de DbHelper
    protected DaoGenerico(Context context){
        this.context = context;
        //Invocando o construtor de DbHelper para criação do BD
        //informando context, cursor factory e a versão da base de dados
        dbHelper = new DbHelper(context, NOME_DB, null, VERSAO_DB);

    }

    //método retorna o BD em modo escrita
    protected SQLiteDatabase getDb(){
        return dbHelper.getWritableDatabase();
    }
}
