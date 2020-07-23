package com.matheus.localizabar.AppBar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.matheus.localizabar.Adapter.MenuBasicAdapter;
import com.matheus.localizabar.Dao.MenuDao;
import com.matheus.localizabar.Model.Menu;
import com.matheus.localizabar.R;

import java.util.ArrayList;
import java.util.List;

public class ListMenuActivity extends AppCompatActivity
        implements AdapterView.OnItemClickListener {

    private ListView listaMenus;
    private List<Menu> menus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_menu);

        listaMenus = (ListView) findViewById(R.id.listMenus);
        menus = new ArrayList<Menu>();
        menus = MenuDao.getMenuDao(this).selecionaTodos();
        listaMenus.setAdapter(new MenuBasicAdapter(this, menus));
        listaMenus.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        Toast.makeText(getApplicationContext(),
                "Menu Cadastrado",
                Toast.LENGTH_LONG).show();
    }
}