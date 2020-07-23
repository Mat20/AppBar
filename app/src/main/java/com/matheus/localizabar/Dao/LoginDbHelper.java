package com.matheus.localizabar.Dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class LoginDbHelper extends SQLiteOpenHelper {

    public LoginDbHelper(Context context, String NOME_DB,
                               SQLiteDatabase.CursorFactory factory,
                               int VERSAO_DB) {
        super(context, NOME_DB, null, VERSAO_DB);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        try {
            //string com a sql a ser executada na criação da tabela
            String sql_login = " CREATE TABLE "
                    + LoginDao.NOME_TABELA + " ("
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
