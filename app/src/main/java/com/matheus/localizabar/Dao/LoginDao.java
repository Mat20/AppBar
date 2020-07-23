package com.matheus.localizabar.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.matheus.localizabar.Model.Login;

import java.util.ArrayList;
import java.util.List;

public class LoginDao extends DaoGenerico<Login> {

    public static  Context context;

    public static LoginDao loginDao;

    public static  String NOME_TABELA = "login";


    protected LoginDao(Context context){
        super(context);
        this.context = context;
    }

    public static LoginDao getLoginDao(Context ctx){
        context = ctx;
        if (loginDao == null){

            loginDao = new LoginDao(context);
        }
        return loginDao;
    }

    public static final class Coluna {

        public static String ID = "_id";
        public static String USUARIO = "usuario";
        public static String SENHA = "senha";
    }

    @Override
    public List<Login> selecionaTodos(){
        SQLiteDatabase db = getDb();

        Cursor c = null;

        try {

            String colunas[] = new String[]{Coluna.ID,
                    Coluna.USUARIO,
                    Coluna.SENHA};

            // Execução da consulta.
            // O resultado é um cursor para iteração sobre o resultado.
            c = db.query(NOME_TABELA, colunas,
                    null, null,
                    null, null,
                      Coluna.USUARIO);

            // Variável para armazenamento dos
            // resultados gerados pela consulta.
            List<Login> todos = new ArrayList<Login>();

            // Se existe um primeiro registro...
            if (c.moveToFirst()) {
                do {
                    // ... cria-se uma classe que será populada pelos
                    // dados retornados pela consulta
                    Login login = new Login();

                    login.setId(c.getInt(c.getColumnIndex(LoginDao.Coluna.ID)));
                    login.setUsuario((c.getString(c.getColumnIndex(LoginDao.Coluna.USUARIO))));
                    login.setSenha((c.getString(c.getColumnIndex(LoginDao.Coluna.SENHA))));

                    // Adiciona-se a nova instância à lista geral.
                    todos.add(login);

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
    public Login selecionaPorId(int i) {

        SQLiteDatabase db = getDb();
        Cursor c = null;

        try {
            String colunas[] = new String[]{LoginDao.Coluna.ID, LoginDao.Coluna.USUARIO, LoginDao.Coluna.SENHA};

            // Column.ID + " = ?" corresponde ao critério de consulta.
            // new String[] { String.valueOf(i) } corresponde ao(s)
            // valor(es) a ser(em) substituído(s) no critério de consulta.

            c = db.query(NOME_TABELA, colunas, LoginDao.Coluna.ID + " = ?",
                    new String[]{String.valueOf(i)}, null,
                    null, null);

            Login login = new Login();

            if (c.moveToFirst()) {
                login.setId(c.getInt(c.getColumnIndex(LoginDao.Coluna.ID)));
                login.setUsuario((c.getString(c.getColumnIndex(Coluna.USUARIO))));
                login.setSenha((c.getString(c.getColumnIndex(Coluna.SENHA))));

                return login;
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
    public boolean insert(Login login) {
        SQLiteDatabase db = getDb();

        try {

            // Variável que conterá os valores a serem armazenados.
            ContentValues valores = new ContentValues();

            // Preparação do par coluna/valor para inserção.
            // _id é autoincrementável, bastando que seja inserida
            // a descrição da nova tarefa.
            valores.put(LoginDao.Coluna.USUARIO, login.getUsuario());
            valores.put(LoginDao.Coluna.SENHA, login.getSenha());

            // Inserção do(s) valor(es) na tabela específica.
            db.insert(NOME_TABELA, null, valores);
        } catch (Exception e) {
            Log.e("loginDao", NOME_TABELA + ": falha ao inserir registro "
                    + login.getUsuario() + login.getSenha(), e);
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
            Log.e("loginDao", NOME_TABELA + ": falha ao excluir registro "
                    + i, e);
        } finally {
            db.close();
        }

    }


    @Override
    public void update(Login login) {

        // Processo semelhante ao método anterior
        SQLiteDatabase db = getDb();

        try {

            ContentValues values = new ContentValues();

            values.put(LoginDao.Coluna.USUARIO, login.getUsuario());
            values.put(LoginDao.Coluna.SENHA, login.getSenha());

            // "_id = ?" corresponde ao critério da atualização.
            // new String[] { String.valueOf(cliente.getId()) } corresponde ao(s)
            // valor(es) a ser(em) substituído(s) no critério de atualização.
            db.update(NOME_TABELA, values, "_id = ?",
                    new String[] { String.valueOf(login.getId()) });
        } catch (Exception e) {
            Log.e("loginDao", NOME_TABELA
                    + ": falha ao atualizar registro " + login.getId(), e);
        } finally {
            db.close();
        }
    }

}
