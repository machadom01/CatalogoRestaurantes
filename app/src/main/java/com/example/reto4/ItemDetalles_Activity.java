package com.example.reto4;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;

public class ItemDetalles_Activity extends AppCompatActivity {

    TextView itemTitle, itemPrice;
    Toolbar toolbarDescripciones;
    ImageView imageView;

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }


    //    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//
//        if (item.getItemId() == android.R.id.home){
//            String claseAnterior = getIntent().getStringExtra("ClaseAnterior");
//
//            if (claseAnterior != null){
//                try {
//                    Class<?> claseAnt = Class.forName(claseAnterior);
//                    startActivity(new Intent(this, claseAnt));
//                } catch (ClassNotFoundException e) {
//                    throw new RuntimeException(e);
//                }
//
//            }
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itemdetalles);


        itemTitle= findViewById(R.id.itemTitle);
        itemPrice= findViewById(R.id.itemPrice);
        toolbarDescripciones=findViewById(R.id.toolbarDescripciones);
        imageView=findViewById(R.id.imageView);

        setSupportActionBar(toolbarDescripciones);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        Intent intentRecibido = getIntent();
        int position = intentRecibido.getIntExtra("Position", -1);
        String claseOrigen = intentRecibido.getStringExtra("ClaseOrigen");
        System.out.println(claseOrigen);
        if (claseOrigen.equals("ComidaFragment")){
            itemTitle.setText(MiArrayAdapter.COMIDAS[position]);
            toolbarDescripciones.setTitle(MiArrayAdapter.COMIDAS[position]);
            imageView.setImageDrawable(getDrawable(R.drawable.comida));
        } else if (claseOrigen.equals("BebidasFragment")){
            itemTitle.setText(MiArrayAdapter.BEBIDAS[position]);
            toolbarDescripciones.setTitle(MiArrayAdapter.BEBIDAS[position]);
            imageView.setImageDrawable(getDrawable(R.drawable.bebida));
        } else if (claseOrigen.equals("ComplementosFragment")){
            itemTitle.setText(MiArrayAdapter.COMPLEMENTOS[position]);
            toolbarDescripciones.setTitle(MiArrayAdapter.COMPLEMENTOS[position]);
            imageView.setImageDrawable(getDrawable(R.drawable.complemento));
        }

        itemPrice.setText(MiArrayAdapter.PRECIOS[position]);





    }
}