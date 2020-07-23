package com.matheus.localizabar.Dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MenuDbHelper extends SQLiteOpenHelper {

    public MenuDbHelper(Context context, String NOME_DB,
                        SQLiteDatabase.CursorFactory factory,
                        int VERSAO_DB) {
        super(context, NOME_DB, null, VERSAO_DB);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        try {
            //string com a sql a ser executada na criação da tabela
            String sql_menu = " CREATE TABLE "
                    + MenuDao.NOME_TABELA + " ( " + MenuDao.Coluna.ID
                    + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
                    + MenuDao.Coluna.PROTUDO + " TEXT NOT NULL, "
                    + MenuDao.Coluna.BEBIDA + " TEXT NOT NULL, "
                    + MenuDao.Coluna.PRECO + "TEXT NOT NULL);";

            sqLiteDatabase.execSQL(sql_menu);

        } catch (Exception e) {
            Log.e("DbHelper", "Erro na criação da tabela Menu", e);
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }
}
