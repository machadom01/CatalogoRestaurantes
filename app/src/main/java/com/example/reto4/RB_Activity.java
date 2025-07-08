package com.example.reto4;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import java.util.Objects;

public class RB_Activity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    TabItem tabItemComida;
    TabItem tabItemBebidas;
    TabItem tabItemComplementos;

    FragmentPagerAdapter fragmentPagerAdapter;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_ra);

//        findViewById(R.id.)

        Toolbar toolbar = findViewById(R.id.toolbar_listas_lupa);
        ImageButton imageButton = toolbar.findViewById(R.id.imagebutton_search);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Objects.requireNonNull(getSupportActionBar()).setTitle("Restaurante B");
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        int indice = getIntent().getIntExtra("indexMenuItemSeleccionado", -1);

        tabLayout=findViewById(R.id.tabLayout);
        viewPager=findViewById(R.id.viewpager);
        tabItemComida=findViewById(R.id.tabComida);
        tabItemBebidas=findViewById(R.id.tabBebidas);
        tabItemComplementos=findViewById(R.id.tabComplementos);



        fragmentPagerAdapter=new FragmentPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                switch (position){
                    case 0:
                        return new ComidaFragment();
                    case 1:
                        return new BebidasFragment();
                    case 2:
                        return new ComplementosFragment();
                    default:
                        return new Fragment();
                }
            }

            @Override
            public int getCount() {
                return tabLayout.getTabCount();
            }
        };

        viewPager.setAdapter(fragmentPagerAdapter);

        if (indice != -1) {
            viewPager.setCurrentItem(indice);
            fragmentPagerAdapter.notifyDataSetChanged();
        }

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                fragmentPagerAdapter.notifyDataSetChanged();

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
// falta implementar aqui la busqueda
            }
        });



    }
}
