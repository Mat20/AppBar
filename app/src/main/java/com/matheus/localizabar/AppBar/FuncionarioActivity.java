package com.matheus.localizabar.AppBar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.matheus.localizabar.Dao.FuncionarioDao;
import com.matheus.localizabar.Model.Funcionario;
import com.matheus.localizabar.R;
import com.matheus.localizabar.Util.Util;

public class FuncionarioActivity extends AppCompatActivity {

    private FuncionarioDao funcionarioDao;
    private Funcionario funcionario;
    private EditText nome, cpf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_funcionario);
    }

    //Método criado para carregar o objeto cliente com os dados digitados pelo usuário
    //e salvar na base de dados
    public void salvarfuncionario(View v){

        funcionario = new Funcionario();
        funcionarioDao = funcionarioDao.getFuncionarioDao(getApplicationContext());

        nome = (EditText) findViewById(R.id.edtNome);
        cpf = (EditText) findViewById(R.id.edtCpf);
        funcionario.setNome(String.valueOf(nome.getText()));
        funcionario.setCpf(String.valueOf(cpf.getText()));

        //verifica se algum dos campos está vazio no form
        if(funcionario.getNome().equals("") || funcionario.getCpf().equals("")){

            //exibe uma mesangem de aviso
            Util.mensagemAviso("Cadastro do Funcionario", "Campo Nome e Cpf devem ser Preenchidos", this);
        }

        //Verifica retorno do método insert
        //true dados gravados com sucesso
        //false erro ao gravar dados
        else if(funcionarioDao.insert(funcionario)){

            //Exibe uma mensagem de curta duração
            Toast.makeText(this, "Funcionario Gravado com Sucesso!", Toast.LENGTH_LONG).show();

            //prepara campos para nova digitação
            nome.setText("");
            cpf.setText("");
        }

        else {
            Toast.makeText(this, "Erro ao grava Funcionario.", Toast.LENGTH_LONG).show();
        }

    }
}