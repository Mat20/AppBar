package com.matheus.localizabar.AppBar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.matheus.localizabar.Dao.MenuDao;
import com.matheus.localizabar.R;

public class CardMenuActivity extends AppCompatActivity {

    Intent menu, menuapagar, menuup, menulistar;
    private MenuDao menuDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_menu);
    }


    public void menu(View view) {
        menu = new Intent(getApplicationContext(), MenuActivity.class);
        startActivity(menu);
    }


    public void menulistar(View view) {
        menulistar = new Intent(getApplicationContext(), ListMenuActivity.class);
        startActivity(menulistar);
    }
    public void menuup (View view) {
        menuup = new Intent(getApplicationContext(), MenuActivity.class);
        startActivity(menuup);
    }

    public void menuapagar(View view) {
        Bundle msg = new Bundle();
        msg.putString("apagar", "confirme");
        menuapagar = new Intent(getApplicationContext(), ListDelMenuActivity.class);
        menuapagar.putExtras(msg);
        startActivity(menuapagar);
    }
}