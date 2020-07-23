package com.matheus.localizabar.Util;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import androidx.appcompat.app.AlertDialog;

import com.matheus.localizabar.AppBar.CadastroActivity;
import com.matheus.localizabar.AppBar.FuncionarioActivity;
import com.matheus.localizabar.AppBar.LoginActivity;
import com.matheus.localizabar.AppBar.MenuActivity;

public class Util {

    public static void mensagemAviso(String titulo, String msg,
                                     Context contesto) {
        AlertDialog.Builder mensagem = new AlertDialog.Builder(contesto);
        mensagem.setTitle(titulo);
        mensagem.setMessage(msg);
        mensagem.setNeutralButton("OK", null);
        mensagem.show();
    }

    public static void menssagemAlerta(String titulo, String msg, final Context contesto) {
        AlertDialog.Builder mensagem = new AlertDialog.Builder(contesto);
        mensagem.setTitle(titulo);
        mensagem.setMessage(msg);
        mensagem.setNeutralButton("Sair",new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {

                Runnable r = new Runnable() {

                    public void run() {

                        Intent c = new Intent(contesto, CadastroActivity.class);
                        contesto.startActivity(c);
                        Intent f = new Intent(contesto, FuncionarioActivity.class);
                        contesto.startActivity(f);
                        Intent m = new Intent(contesto, MenuActivity.class);
                        contesto.startActivity(m);
                        Intent l = new Intent(contesto, LoginActivity.class);
                        contesto.startActivity(l);
                    }
                };
            }

        });
        mensagem.show();
    }
}
