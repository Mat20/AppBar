package com.matheus.localizabar.AppBar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.matheus.localizabar.Dao.MenuDao;
import com.matheus.localizabar.Model.Menu;
import com.matheus.localizabar.R;
import com.matheus.localizabar.Util.Util;

public class MenuActivity extends AppCompatActivity {

    private MenuDao menuDao;
    private Menu menu;
    private EditText produto, bebida, preco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void salvarmenu(View v) {

        menu = new Menu();
        menuDao =  menuDao.getMenuDao(getApplicationContext());

        produto = (EditText) findViewById(R.id.edtProduto);
        bebida = (EditText) findViewById(R.id.edtBebida);
        preco = (EditText) findViewById(R.id.edtPreco);
        menu.setProduto(String.valueOf(produto.getText()));
        menu.setBebida(String.valueOf(bebida.getText()));
        menu.setPreco(String.valueOf(preco.getText()));

        //verifica se algum dos campos está vazio no form
        if (menu.getProduto().equals("") || menu.getBebida().equals("") || menu.getPreco().equals("")){

            //exibe uma mesangem de aviso
            Util.mensagemAviso("Cadastro do Menu", "Campo Produto e Bebida e Preço e Mesas devem ser Preenchidos", this);
        }

        else if(menuDao.insert(menu)){

            //Exibe uma mensagem de curta duração
            Toast.makeText(this, "Menu Gravado com Sucesso!", Toast.LENGTH_LONG).show();

            //prepara campos para nova digitação
            produto.setText("");
            bebida.setText("");
            preco.setText("");
        }

        else {
            Toast.makeText(this, "Erro ao grava Menu.", Toast.LENGTH_LONG).show();
        }
    }
}