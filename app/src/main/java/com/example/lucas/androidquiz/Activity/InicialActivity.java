package com.example.lucas.androidquiz.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.lucas.androidquiz.R;

public class InicialActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicial);

        // Pegando a referência
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    // Criando o menu de opções no Toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    // Implementando uma ação para cada item do menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.itExcluir:
                Toast.makeText(this, "Funcionalidade a ser implementada", Toast.LENGTH_SHORT).show();
                return(true);

            case R.id.itAlterar:
                Toast.makeText(this, "Funcionalidade a ser implementada", Toast.LENGTH_SHORT).show();
                return(true);

            case R.id.itExit:
                Toast.makeText(this, "Funcionalidade a ser implementada", Toast.LENGTH_SHORT).show();
                return(true);
        }

        return super.onOptionsItemSelected(item);
    }
}
