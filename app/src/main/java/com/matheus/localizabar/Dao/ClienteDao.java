package com.matheus.localizabar.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.matheus.localizabar.Model.Cliente;

import java.util.ArrayList;
import java.util.List;

public class ClienteDao extends DaoGenerico<Cliente> {

    public static Context context;


    public static ClienteDao clienteDao;


    public static String NOME_TABELA = "cliente";


    protected ClienteDao(Context context) {
        super(context);
        this.context = context;
    }

    public static ClienteDao getClienteDao(Context ctx) {
        context = ctx;
        if (clienteDao == null) {

            clienteDao = new ClienteDao(context);
        }
        return clienteDao;
    }


    public static final class Coluna {

        public static String ID = "_id";
        public static String NOME = "nome";
        public static String CPF = "cpf";
        public static String MESA = "mesa";

    }

    @Override
    public List<Cliente> selecionaTodos() {

        SQLiteDatabase db = getDb();

        Cursor c = null;

        try {

            String colunas[] = new String[]{Coluna.ID,
                    Coluna.NOME,
                    Coluna.CPF,
                    Coluna.MESA};

            // Execução da consulta.
            // O resultado é um cursor para iteração sobre o resultado.
            c = db.query(NOME_TABELA, colunas,
                    null, null,
                    null, null,
                    Coluna.NOME);

            // Variável para armazenamento dos
            // resultados gerados pela consulta.
            List<Cliente> todos = new ArrayList<Cliente>();

            // Se existe um primeiro registro...
            if (c.moveToFirst()) {
                do {
                    // ... cria-se uma classe que será populada pelos
                    // dados retornados pela consulta
                    Cliente cliente = new Cliente();

                    cliente.setId(c.getInt(c.getColumnIndex(ClienteDao.Coluna.ID)));
                    cliente.setNome((c.getString(c.getColumnIndex(ClienteDao.Coluna.NOME))));
                    cliente.setCpf((c.getString(c.getColumnIndex(ClienteDao.Coluna.CPF))));
                    cliente.setMesa((c.getString(c.getColumnIndex(ClienteDao.Coluna.MESA))));

                    // Adiciona-se a nova instância à lista geral.
                    todos.add(cliente);

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
    public Cliente selecionaPorId(int i) {

        SQLiteDatabase db = getDb();
        Cursor c = null;

        try {
            String colunas[] = new String[]{ClienteDao.Coluna.ID, ClienteDao.Coluna.NOME,
                    ClienteDao.Coluna.CPF, ClienteDao.Coluna.MESA};

            // Column.ID + " = ?" corresponde ao critério de consulta.
            // new String[] { String.valueOf(i) } corresponde ao(s)
            // valor(es) a ser(em) substituído(s) no critério de consulta.

            c = db.query(NOME_TABELA, colunas, ClienteDao.Coluna.ID + " = ?",
                    new String[]{String.valueOf(i)}, null,
                    null, null);

            Cliente cliente = new Cliente();

            if (c.moveToFirst()) {
                cliente.setId(c.getInt(c.getColumnIndex(ClienteDao.Coluna.ID)));
                cliente.setNome((c.getString(c.getColumnIndex(Coluna.NOME))));
                cliente.setCpf((c.getString(c.getColumnIndex(Coluna.CPF))));
                cliente.setMesa((c.getString(c.getColumnIndex(Coluna.MESA))));
                return cliente;
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
    public boolean insert(Cliente cliente) {
        SQLiteDatabase db = getDb();

        try {

            // Variável que conterá os valores a serem armazenados.
            ContentValues values = new ContentValues();

            // Preparação do par coluna/valor para inserção.
            // _id é autoincrementável, bastando que seja inserida
            // a descrição da nova tarefa.
            values.put(ClienteDao.Coluna.NOME, cliente.getNome());
            values.put(ClienteDao.Coluna.CPF, cliente.getCpf());
            values.put(ClienteDao.Coluna.MESA, cliente.getMesa());

            // Inserção do(s) valor(es) na tabela específica.
            db.insert(NOME_TABELA, null, values);
        } catch (Exception e) {
            Log.e("clienteDao", NOME_TABELA + ": falha ao inserir registro "
                    + cliente.getNome() + cliente.getCpf() + cliente.getMesa() , e);
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
            Log.e("clienteDao", NOME_TABELA + ": falha ao excluir registro "
                    + i, e);
        } finally {
            db.close();
        }

    }


    @Override
    public void update(Cliente cliente) {

        // Processo semelhante ao método anterior
        SQLiteDatabase db = getDb();

        try {

            ContentValues values = new ContentValues();

            values.put(ClienteDao.Coluna.NOME, cliente.getNome());
            values.put(ClienteDao.Coluna.CPF, cliente.getCpf());
            values.put(ClienteDao.Coluna.MESA, cliente.getMesa());

            // "_id = ?" corresponde ao critério da atualização.
            // new String[] { String.valueOf(cliente.getId()) } corresponde ao(s)
            // valor(es) a ser(em) substituído(s) no critério de atualização.
            db.update(NOME_TABELA, values, "_id = ?",
                    new String[] { String.valueOf(cliente.getId()) });
        } catch (Exception e) {
            Log.e("clienteDao", NOME_TABELA
                    + ": falha ao atualizar registro " + cliente.getId(), e);
        } finally {
            db.close();
        }
    }
}
