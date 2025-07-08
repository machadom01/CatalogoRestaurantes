package com.example.reto4;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        menuInflater.inflate(R.menu.menucontextual, menu);
        menu.setHeaderTitle("Opciones:");
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if (item.getMenuInfo() == null) return super.onContextItemSelected(item);

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        String restauranteSeleccionado = adapter.getItem(info.position);
        int itemId = item.getItemId();


        int indexMenuItemSeleccionado;


        //evaluando cual menu item se presionó


        if (itemId == R.id.comida_menuitem) {
            indexMenuItemSeleccionado =0;
        }
        else if (itemId == R.id.bebidas_menuitem) indexMenuItemSeleccionado =1;
        else if (itemId == R.id.complementos_menuitem) indexMenuItemSeleccionado =2;
        else return super.onContextItemSelected(item);



        if (restauranteSeleccionado.equals("Restaurante A")) {
            c= RA_Activity.class;
        } else if (restauranteSeleccionado.equals("Restaurante B")) {
            c= RB_Activity.class;
        } else if (restauranteSeleccionado.equals("Restaurante C")) {
            c= RC_Activity.class;
        } else if (restauranteSeleccionado.equals("Restaurante D")) {
            c= RD_Activity.class;
        } else {
            return super.onContextItemSelected(item);
        }
        intent = new Intent(cont, c);
        intent.putExtra("indexMenuItemSeleccionado", indexMenuItemSeleccionado);
        startActivity(intent);

        return super.onContextItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar(findViewById(R.id.toolbar_listas_lupa));
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        cont = this;
        editTextDeBusqueda=findViewById(R.id.searchEditText);
        botonDeBusqueda=findViewById(R.id.imagebutton_search);
        editTextDeBusqueda=findViewById(R.id.searchEditText);
        botonClear=findViewById(R.id.clearButton);
        layoutBusqueda = findViewById(R.id.searchLayout);


        todosLosRestaurantes= new ArrayList<>();
        todosLosRestaurantes.add(RESTAURANTS[0]);
        todosLosRestaurantes.add(RESTAURANTS[1]);
        todosLosRestaurantes.add(RESTAURANTS[2]);
        todosLosRestaurantes.add(RESTAURANTS[3]);

        restaurantesFiltrados = new ArrayList<>(todosLosRestaurantes);

        listView = findViewById(R.id.listview_main);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                restaurantesFiltrados);
        listView.setAdapter(adapter);

        establecerFuncionalidadDeBusqueda();


        registerForContextMenu(listView);
        menuInflater = getMenuInflater();


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (position == 0) {
                    c= RA_Activity.class;
                } else if (position == 1) {
                    c= RB_Activity.class;
                } else if (position == 2) {
                    c= RC_Activity.class;
                } else if (position == 3) {
                    c= RD_Activity.class;
                }
                intent = new Intent(MainActivity.this, c);
                startActivity(intent);
            }
        });
    }

    private void establecerFuncionalidadDeBusqueda() {
        botonDeBusqueda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarModoBusqueda();
            }
        });
        botonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ocultarModoBusqueda();
            }
        });
        editTextDeBusqueda.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filtrarLista(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    private void filtrarLista(String textoDeBusqueda) {
        restaurantesFiltrados.clear();

        if (textoDeBusqueda.isEmpty()){
            restaurantesFiltrados.addAll(todosLosRestaurantes);
        } else {
            for (String restaurante : todosLosRestaurantes) {
                if (restaurante.toLowerCase().contains(textoDeBusqueda.toLowerCase())){
                    restaurantesFiltrados.add(restaurante);
                }
            }
        }
        adapter.notifyDataSetChanged();
    }

    private void ocultarModoBusqueda() {
        editTextDeBusqueda.setText("");
        layoutBusqueda.setVisibility(View.GONE);

        filtrarLista("");
    }

    private void mostrarModoBusqueda() {
        layoutBusqueda.setVisibility(View.VISIBLE);
        editTextDeBusqueda.requestFocus();
    }

    public static final String[] RESTAURANTS = {"Restaurante A", "Restaurante B", "Restaurante C", "Restaurante D"};

    private ArrayAdapter<String> adapter;
    private ListView listView;
//    private TextView pA, pB,pC,pD;
    private MenuInflater menuInflater;
    private Intent intent;
    private Class c;
    private Context cont;
    private EditText editTextDeBusqueda;
    private ImageButton botonDeBusqueda, botonClear;
    private LinearLayout layoutBusqueda;
    private ArrayList<String> todosLosRestaurantes, restaurantesFiltrados;




}