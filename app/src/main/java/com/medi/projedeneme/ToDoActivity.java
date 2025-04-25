package com.medi.projedeneme;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ToDoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_to_do);
        //action bar düzenlemeleri
        getSupportActionBar().setTitle("YENİ YILDA YAPILACAKLAR");
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true); // Geri butonunu ekle
            getSupportActionBar().setDisplayShowHomeEnabled(true); // Ana simgeyi göster
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        // Geri butonuna basıldığında, bir önceki aktiviteye gitmek için finish() kullan
        if (id == android.R.id.home) {
            finish(); // Bu aktiviteyi kapat ve bir önceki aktiviteye dön
            return true;
        }
        // Menü öğesi tıklandığında AddTaskActivity'yi başlat
        if (item.getItemId() == R.id.menugorev) {
            startActivity(new Intent(this, AddTaskActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu); // Menüyi yükle
        return true;
    }
}