package com.matheus.localizabar.AppBar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.matheus.localizabar.Dao.LoginDao;
import com.matheus.localizabar.Model.Login;
import com.matheus.localizabar.R;
import com.matheus.localizabar.Util.Util;

public class LoginActivity extends AppCompatActivity {

    Intent logar;
    private EditText usuario, senha;
    private LoginDao loginDao;
    private Login login;
    RelativeLayout relative, relative1;
    Handler handler = new Handler();
    Runnable runnable = new Runnable() {

        @Override
        public void run() {

            relative.setVisibility(View.VISIBLE);
            relative1.setVisibility(View.VISIBLE);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        relative = (RelativeLayout) findViewById(R.id.relative);
        relative1 = (RelativeLayout) findViewById(R.id.relative1);

        handler.postDelayed(runnable, 1000); //1000 tempo para o splash


    }

    public void logar(View v) {

        login = new Login();
        loginDao = loginDao.getLoginDao(getApplicationContext());

        usuario = (EditText) findViewById(R.id.edtusuario);
        senha = (EditText) findViewById(R.id.edtsenha);

        login.setUsuario(String.valueOf(usuario.getText()));
        login.setSenha(String.valueOf(senha.getText()));


        if(login.getUsuario().equals("") || login.getSenha().equals("")) {

            Util.mensagemAviso("Cadastro do Login", "Campo Usuario e Senha devem ser Preenchidos", this);
        }

        else if(loginDao.insert(login)){

            Toast.makeText(this, "Login Gravado com Sucesso!", Toast.LENGTH_LONG).show();

            usuario.setText("");
            senha.setText("");
        }

        /*
            public void btnLogar(View view) {
            logar = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(logar);
            }

         */
    }



}