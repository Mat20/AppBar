package com.matheus.localizabar.Dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class FuncionarioDbHelper extends SQLiteOpenHelper {

    public FuncionarioDbHelper(Context context, String NOME_DB,
                               SQLiteDatabase.CursorFactory factory,
                               int VERSAO_DB) {
        super(context, NOME_DB, null, VERSAO_DB);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        try {
            //string com a sql a ser executada na criação da tabela
            String sql_funcionario = " CREATE TABLE "
                    + FuncionarioDao.NOME_TABELA + " ( " + FuncionarioDao.Coluna.ID
                    + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
                    + FuncionarioDao.Coluna.NOME + " TEXT NOT NULL, "
                    + FuncionarioDao.Coluna.CPF + " TEXT NOT NULL);";

            sqLiteDatabase.execSQL(sql_funcionario);

        } catch (Exception e) {
            Log.e("FuncionarioDbHelper", "Erro na criação da tabela Funcionario", e);
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }
}
