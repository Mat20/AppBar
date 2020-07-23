package com.matheus.localizabar.AppBar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.matheus.localizabar.Adapter.LoginBasicAdapter;
import com.matheus.localizabar.Dao.LoginDao;
import com.matheus.localizabar.Model.Login;
import com.matheus.localizabar.R;

import java.util.ArrayList;
import java.util.List;

public class ListLoginActivity extends AppCompatActivity
        implements AdapterView.OnItemClickListener {

    private ListView listaLogins;
    private List<Login> logins;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_login);


        listaLogins = (ListView) findViewById(R.id.listLogin);
        logins = new ArrayList<Login>();
        logins = LoginDao.getLoginDao(this).selecionaTodos();
        listaLogins.setAdapter(new LoginBasicAdapter(this,logins));
        listaLogins.setOnItemClickListener(this);
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(getApplicationContext(),
                "Login Cadastro",
                Toast.LENGTH_LONG).show();
    }
}