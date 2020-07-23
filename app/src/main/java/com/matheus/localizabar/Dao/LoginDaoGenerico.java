package com.matheus.localizabar.Dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.matheus.localizabar.Model.Login;

import java.util.List;

public abstract class LoginDaoGenerico<L> {

    /*
     * declaração variáveis bd
     */
    private static String NOME_DB = "login";
    private int VERSAO_DB = 1;
    private Context context;
    private LoginDbHelper logindbHelper;

    public abstract List<L> selecionaTodos();


    public abstract L selecionaPorId(int i);


    public abstract boolean insert(L o);


    public abstract void delete(int i);


    public abstract void update(L o);

    //Construtor que ao ser invocado cria a base de dados
    //com auxilia de DbHelper
    protected LoginDaoGenerico(Context context){
        this.context = context;
        //Invocando o construtor de DbHelper para criação do BD
        //informando context, cursor factory e a versão da base de dados
        logindbHelper = new LoginDbHelper(context, NOME_DB, null, VERSAO_DB);

    }

    //método retorna o BD em modo escrita
    protected SQLiteDatabase getDb(){
        return logindbHelper.getWritableDatabase();
    }
}
