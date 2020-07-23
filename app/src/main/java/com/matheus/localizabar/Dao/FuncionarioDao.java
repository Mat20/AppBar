package com.matheus.localizabar.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.matheus.localizabar.Model.Funcionario;

import java.util.ArrayList;
import java.util.List;

public class FuncionarioDao extends DaoGenerico<Funcionario> {

    public static Context context;


    public static FuncionarioDao funcionarioDao;


    public static String NOME_TABELA = "funcionario";


    protected FuncionarioDao(Context context) {
        super(context);
        this.context = context;
    }

    public static FuncionarioDao getFuncionarioDao(Context ctx) {
        context = ctx;
        if (funcionarioDao == null) {

            funcionarioDao = new FuncionarioDao(context);
        }
        return funcionarioDao;
    }


    public static final class Coluna {

        public static String ID = "_id";
        public static String NOME = "nome";
        public static String CPF = "cpf";

    }


    @Override
    public List<Funcionario> selecionaTodos() {

        SQLiteDatabase db = getDb();

        Cursor c = null;

        try {

            String colunas[] = new String[]{Coluna.ID,
                    Coluna.NOME,
                    Coluna.CPF};

            // Execução da consulta.
            // O resultado é um cursor para iteração sobre o resultado.
            c = db.query(NOME_TABELA, colunas,
                    null, null,
                    null, null,
                    Coluna.NOME);

            // Variável para armazenamento dos
            // resultados gerados pela consulta.
            List<Funcionario> todos = new ArrayList<Funcionario>();

            // Se existe um primeiro registro...
            if (c.moveToFirst()) {
                do {
                    // ... cria-se uma classe que será populada pelos
                    // dados retornados pela consulta
                    Funcionario funcionario = new Funcionario();

                    funcionario.setId(c.getInt(c.getColumnIndex(FuncionarioDao.Coluna.ID)));
                    funcionario.setNome((c.getString(c.getColumnIndex(FuncionarioDao.Coluna.NOME))));
                    funcionario.setCpf((c.getString(c.getColumnIndex(FuncionarioDao.Coluna.CPF))));



                    // Adiciona-se a nova instância à lista geral.
                    todos.add(funcionario);

                    // Itera enquanto houver um próximo registro.
                } while (c.moveToNext());
            }

            // Devolve a lista com todos os resgistros encontrados.
            // Pode ser nulo, caso não haja resgistros armazenados.
            return todos;

        } catch (Exception e) {
            Log.e(this.getClass().getName(), "Falha na leitura dos dados.", e);
            e.printStackTrace();
        } finally {
            // Libera recursos para o sistema.
            // startManagingCursor(Cursor) só funciona
            // para o ciclo de vida de uma Activity!
            if (c != null) {
                c.close();
            }
            db.close();
        }

        // Garante que haja um valor de retorno
        return null;
    }

    @Override
    public Funcionario selecionaPorId(int i) {

        SQLiteDatabase db = getDb();
        Cursor c = null;

        try {
            String colunas[] = new String[]{FuncionarioDao.Coluna.ID, FuncionarioDao.Coluna.NOME,
                    FuncionarioDao.Coluna.CPF};

            // Column.ID + " = ?" corresponde ao critério de consulta.
            // new String[] { String.valueOf(i) } corresponde ao(s)
            // valor(es) a ser(em) substituído(s) no critério de consulta.

            c = db.query(NOME_TABELA, colunas, FuncionarioDao.Coluna.ID + " = ?",
                    new String[]{String.valueOf(i)}, null,
                    null, null);

            Funcionario funcionario = new Funcionario();

            if (c.moveToFirst()) {
                funcionario.setId(c.getInt(c.getColumnIndex(FuncionarioDao.Coluna.ID)));
                funcionario.setNome((c.getString(c.getColumnIndex(Coluna.NOME))));
                funcionario.setCpf((c.getString(c.getColumnIndex(Coluna.CPF))));

                return funcionario;
            }

        } catch (Exception e) {
            Log.e(this.getClass().getName(), "Falha na leitura dos dados.", e);
            e.printStackTrace();
        } finally {
            if (c != null) {
                c.close();
            }
            db.close();
        }

        return null;
    }

    @Override
    public boolean insert(Funcionario funcionario) {
        SQLiteDatabase db = getDb();

        try {

            // Variável que conterá os valores a serem armazenados.
            ContentValues values = new ContentValues();

            // Preparação do par coluna/valor para inserção.
            // _id é autoincrementável, bastando que seja inserida
            // a descrição da nova tarefa.
            values.put(FuncionarioDao.Coluna.NOME, funcionario.getNome());
            values.put(FuncionarioDao.Coluna.CPF, funcionario.getCpf());

            // Inserção do(s) valor(es) na tabela específica.
            db.insert(NOME_TABELA, null, values);
        } catch (Exception e) {
            Log.e("funcionarioDao", NOME_TABELA + ": falha ao inserir registro "
                    + funcionario.getNome() + funcionario.getCpf(), e);
            return false;
        } finally {
            db.close();
        }
        return true;
    }


    @Override
    public void delete(int i) {

        SQLiteDatabase db = getDb();

        try {
            // "_id = ?" corresponde ao critério da exclusão.
            // new String[] { String.valueOf(i) } corresponde ao(s)
            // valor(es) a ser(em) substituído(s) no critério de exclusão.
            db.delete(NOME_TABELA, "_id = ?",
                    new String[] { String.valueOf(i) });
        } catch (Exception e) {
            Log.e("funcionarioDao", NOME_TABELA + ": falha ao excluir registro "
                    + i, e);
        } finally {
            db.close();
        }

    }


    @Override
    public void update(Funcionario funcionario) {

        // Processo semelhante ao método anterior
        SQLiteDatabase db = getDb();

        try {

            ContentValues values = new ContentValues();

            values.put(FuncionarioDao.Coluna.NOME, funcionario.getNome());
            values.put(FuncionarioDao.Coluna.CPF, funcionario.getCpf());

            // "_id = ?" corresponde ao critério da atualização.
            // new String[] { String.valueOf(cliente.getId()) } corresponde ao(s)
            // valor(es) a ser(em) substituído(s) no critério de atualização.
            db.update(NOME_TABELA, values, "_id = ?",
                    new String[] { String.valueOf(funcionario.getId()) });
        } catch (Exception e) {
            Log.e("funcionarioDao", NOME_TABELA
                    + ": falha ao atualizar registro " + funcionario.getId(), e);
        } finally {
            db.close();
        }
    }
}
