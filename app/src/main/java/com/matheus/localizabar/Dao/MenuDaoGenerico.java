package com.matheus.localizabar.Dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

public abstract class MenuDaoGenerico<M> {

    /*
     * declaração variáveis bd
     */
    private static String NOME_DB = "menu";
    private int VERSAO_DB = 1;
    private Context context;
    private MenuDbHelper menudbHelper;

    public abstract List<M> selecionaTodos();


    public abstract M selecionaPorId(int i);


    public abstract boolean insert(M o);


    public abstract void delete(int i);


    public abstract void update(M o);

    //Construtor que ao ser invocado cria a base de dados
    //com auxilia de DbHelper
    protected MenuDaoGenerico(Context context){
        this.context = context;
        //Invocando o construtor de DbHelper para criação do BD
        //informando context, cursor factory e a versão da base de dados
        menudbHelper = new MenuDbHelper(context, NOME_DB, null, VERSAO_DB);

    }

    //método retorna o BD em modo escrita
    protected SQLiteDatabase getDb(){
        return menudbHelper.getWritableDatabase();
    }
}
