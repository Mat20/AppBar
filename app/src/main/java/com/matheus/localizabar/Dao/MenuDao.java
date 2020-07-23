package com.matheus.localizabar.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.matheus.localizabar.Model.Menu;

import java.util.ArrayList;
import java.util.List;

public class MenuDao extends DaoGenerico<Menu> {

    public static Context context;


    public static MenuDao menuDao;


    public static String NOME_TABELA = "menu";


    protected MenuDao(Context context) {
        super(context);
        this.context = context;
    }

    public static MenuDao getMenuDao(Context ctx) {
        context = ctx;
        if (menuDao == null) {

            menuDao = new MenuDao(context);
        }
        return menuDao;
    }


    public static final class Coluna {

        public static String ID = "_id";
        public static String PROTUDO = "protudo";
        public static String BEBIDA = "bebida";
        public static String PRECO = "preco";

    }


    @Override
    public List<Menu> selecionaTodos() {

        SQLiteDatabase db = getDb();

        Cursor c = null;

        try {

            String colunas[] = new String[]{Coluna.ID,
                    Coluna.PROTUDO,
                    Coluna.BEBIDA,
                    Coluna.PRECO};

            // Execução da consulta.
            // O resultado é um cursor para iteração sobre o resultado.
            c = db.query(NOME_TABELA, colunas,
                    null, null,
                    null, null,
                    Coluna.PROTUDO);

            // Variável para armazenamento dos
            // resultados gerados pela consulta.
            List<Menu> todos = new ArrayList<Menu>();

            // Se existe um primeiro registro...
            if (c.moveToFirst()) {
                do {
                    // ... cria-se uma classe que será populada pelos
                    // dados retornados pela consulta
                    Menu menu = new Menu();

                    menu.setProduto((c.getString(c.getColumnIndex(MenuDao.Coluna.PROTUDO))));
                    menu.setBebida((c.getString(c.getColumnIndex(MenuDao.Coluna.BEBIDA))));
                    menu.setPreco((c.getString(c.getColumnIndex(MenuDao.Coluna.PRECO))));


                    // Adiciona-se a nova instância à lista geral.
                    todos.add(menu);

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
    public Menu selecionaPorId(int i) {

        SQLiteDatabase db = getDb();
        Cursor c = null;

        try {
            String colunas[] = new String[]{MenuDao.Coluna.ID, MenuDao.Coluna.PROTUDO,
                    MenuDao.Coluna.BEBIDA, MenuDao.Coluna.PRECO};

            // Column.ID + " = ?" corresponde ao critério de consulta.
            // new String[] { String.valueOf(i) } corresponde ao(s)
            // valor(es) a ser(em) substituído(s) no critério de consulta.

            c = db.query(NOME_TABELA, colunas, MenuDao.Coluna.ID + " = ?",
                    new String[]{String.valueOf(i)}, null,
                    null, null);

            Menu menu = new Menu();

            if (c.moveToFirst()) {
                menu.setId(c.getInt(c.getColumnIndex(MenuDao.Coluna.ID)));
                menu.setProduto((c.getString(c.getColumnIndex(Coluna.PROTUDO))));
                menu.setBebida((c.getString(c.getColumnIndex(Coluna.BEBIDA))));
                menu.setPreco((c.getString(c.getColumnIndex(Coluna.PRECO))));

                return menu;
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
    public boolean insert(Menu menu) {
        SQLiteDatabase db = getDb();

        try {

            // Variável que conterá os valores a serem armazenados.
            ContentValues values = new ContentValues();

            // Preparação do par coluna/valor para inserção.
            // _id é autoincrementável, bastando que seja inserida
            // a descrição da nova tarefa.
            values.put(MenuDao.Coluna.PROTUDO, menu.getProduto());
            values.put(MenuDao.Coluna.BEBIDA, menu.getBebida());
            values.put(MenuDao.Coluna.PRECO, menu.getPreco());

            // Inserção do(s) valor(es) na tabela específica.
            db.insert(NOME_TABELA, null, values);
        } catch (Exception e) {
            Log.e("menuDao", NOME_TABELA + ": falha ao inserir registro "
                    + menu.getProduto() + menu.getBebida() + menu.getPreco() , e);
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
            Log.e("menuDao", NOME_TABELA + ": falha ao excluir registro "
                    + i, e);
        } finally {
            db.close();
        }

    }


    @Override
    public void update(Menu menu) {

        // Processo semelhante ao método anterior
        SQLiteDatabase db = getDb();

        try {

            ContentValues values = new ContentValues();

            values.put(MenuDao.Coluna.PROTUDO, menu.getProduto());
            values.put(MenuDao.Coluna.BEBIDA, menu.getBebida());
            values.put(MenuDao.Coluna.PRECO, menu.getPreco());

            // "_id = ?" corresponde ao critério da atualização.
            // new String[] { String.valueOf(cliente.getId()) } corresponde ao(s)
            // valor(es) a ser(em) substituído(s) no critério de atualização.
            db.update(NOME_TABELA, values, "_id = ?",
                    new String[] { String.valueOf(menu.getId()) });
        } catch (Exception e) {
            Log.e("menuDao", NOME_TABELA
                    + ": falha ao atualizar registro " + menu.getId(), e);
        } finally {
            db.close();
        }
    }
}
