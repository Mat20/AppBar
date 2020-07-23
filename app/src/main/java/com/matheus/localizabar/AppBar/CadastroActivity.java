package com.matheus.localizabar.AppBar;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.matheus.localizabar.Dao.ClienteDao;
import com.matheus.localizabar.Model.Cliente;
import com.matheus.localizabar.R;
import com.matheus.localizabar.Util.Util;

public class CadastroActivity extends AppCompatActivity {

    private ClienteDao clienteDao;
    private Cliente cliente;
    private EditText nome, cpf, mesa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
    }

    //Método criado para carregar o objeto cliente com os dados digitados pelo usuário
    //e salvar na base de dados
    public void salvarcadastro(View v){

        cliente = new Cliente();
        clienteDao = clienteDao.getClienteDao(getApplicationContext());

        nome = (EditText) findViewById(R.id.edtNome);
        cpf = (EditText) findViewById(R.id.edtCpf);
        mesa = (EditText) findViewById(R.id.edtMesa);
        cliente.setNome(String.valueOf(nome.getText()));
        cliente.setCpf(String.valueOf(cpf.getText()));
        cliente.setMesa(String.valueOf(mesa.getText()));

        //verifica se algum dos campos está vazio no form
        if(cliente.getNome().equals("") || cliente.getCpf().equals("") || cliente.getMesa().equals("")){

            //exibe uma mesangem de aviso
            Util.mensagemAviso("Cadastro do Cliente", "Campo Nome e Senha , Mesa devem ser Preenchidos", this);
        }

        //Verifica retorno do método insert
        //true dados gravados com sucesso
        //false erro ao gravar dados
        else if(clienteDao.insert(cliente)){

            //Exibe uma mensagem de curta duração
            Toast.makeText(this, "Cliente Gravado com Sucesso!", Toast.LENGTH_LONG).show();

            //prepara campos para nova digitação
            nome.setText("");
            cpf.setText("");
            mesa.setText("");
        }

        else {
            Toast.makeText(this, "Erro ao grava Cliente.", Toast.LENGTH_LONG).show();
        }

    }
}
