package com.matheus.localizabar.Dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DbHelper extends SQLiteOpenHelper {

    public DbHelper(Context context, String NOME_DB,
                    SQLiteDatabase.CursorFactory factory,
                    int VERSAO_DB) {
        super(context, NOME_DB, null, VERSAO_DB);
    }

    //Método subscrito de SQLiteOpenHelper responsável por criar sua base de dados
    //na primeira execução do aplicativo
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        try {
            //string com a sql a ser executada na criação da tabela
            String sql_cliente = " CREATE TABLE "
                    + ClienteDao.NOME_TABELA + " ( " + ClienteDao.Coluna.ID
                    + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
                    + ClienteDao.Coluna.NOME + " TEXT NOT NULL, "
                    + ClienteDao.Coluna.CPF + " TEXT NOT NULL, "
                    + ClienteDao.Coluna.MESA + "TEXT NOT NULL);";

            sqLiteDatabase.execSQL(sql_cliente);

        } catch (Exception e) {
            Log.e("DbHelper", "Erro na criação da tabela Cliente", e);
        }

        try {
            //string com a sql a ser executada na criação da tabela
            String sql_funionario = " CREATE TABLE "
                    + FuncionarioDao.NOME_TABELA + " ( " + FuncionarioDao.Coluna.ID
                    + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
                    + FuncionarioDao.Coluna.NOME + " TEXT NOT NULL, "
                    + FuncionarioDao.Coluna.CPF + " TEXT NOT NULL);";

            sqLiteDatabase.execSQL(sql_funionario);

        } catch (Exception e) {
            Log.e("DbHelper", "Erro na criação da tabela Funcionario", e);
        }

        try {
            //string com a sql a ser executada na criação da tabela
            String sql_menu = " CREATE TABLE "
                    + MenuDao.NOME_TABELA + " ( " + MenuDao.Coluna.ID
                    + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
                    + MenuDao.Coluna.PROTUDO + " TEXT NOT NULL, "
                    + MenuDao.Coluna.BEBIDA + " TEXT NOT NULL,"
                    + MenuDao.Coluna.PRECO + "TEXT NOT NULL);";

            sqLiteDatabase.execSQL(sql_menu);

        } catch (Exception e) {
            Log.e("DbHelper", "Erro na criação da tabela Menu", e);
        }


        try {
            //string com a sql a ser executada na criação da tabela
            String sql_login = " CREATE TABLE "
                    + LoginDao.NOME_TABELA + " (" + LoginDao.Coluna.ID
                    + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
                    + LoginDao.Coluna.USUARIO + " TEXT NOT NULL, "
                    + LoginDao.Coluna.SENHA + " TEXT NOT NULL);";

            sqLiteDatabase.execSQL(sql_login);

        } catch (Exception e) {
            Log.e("LoginDbHelper", "Erro na criação da tabela Login", e);
        }


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }
}
