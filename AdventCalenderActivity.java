package com.medi.projedeneme;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AdventCalenderActivity extends AppCompatActivity {

    private ImageButton[] buttons = new ImageButton[25];  // 25 gün için
    private int[] dayImages = {R.drawable.day1, R.drawable.day2, R.drawable.day3, R.drawable.day4, R.drawable.day5,
            R.drawable.day6, R.drawable.day7, R.drawable.day8, R.drawable.day9, R.drawable.day10,
            R.drawable.day11, R.drawable.day12, R.drawable.day13, R.drawable.day14, R.drawable.day15,
            R.drawable.day16, R.drawable.day17, R.drawable.day18, R.drawable.day19, R.drawable.day20,
            R.drawable.day21, R.drawable.day22, R.drawable.day23, R.drawable.day24, R.drawable.day25};
    private int[] giftImages = {R.drawable.gift1, R.drawable.gift2, R.drawable.gift3, R.drawable.gift4, R.drawable.gift5,
            R.drawable.gift6, R.drawable.gift7, R.drawable.gift8, R.drawable.gift9, R.drawable.gift10,
            R.drawable.gift11, R.drawable.gift12, R.drawable.gift13, R.drawable.gift14, R.drawable.gift15,
            R.drawable.gift16, R.drawable.gift17, R.drawable.gift18, R.drawable.gift19, R.drawable.gift20,
            R.drawable.gift21, R.drawable.gift22, R.drawable.gift23, R.drawable.gift24, R.drawable.gift25};


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_advent_calender);
        //action bar ayarlamaları
        getSupportActionBar().setTitle("ADVENT CALENDER");
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true); // Geri butonunu ekle
            getSupportActionBar().setDisplayShowHomeEnabled(true); // Ana simgeyi göster
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.tableLayout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });
// Butonları diziye bağla
        for (int i = 0; i < 25; i++) {
            String buttonId = "day_" + (i + 1);
            int resId = getResources().getIdentifier(buttonId, "id", getPackageName());
            buttons[i] = findViewById(resId);

            // Her buton için tıklama olayını ekle
            final int dayIndex = i;  // Günün indeksini al
            buttons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    toggleImage(buttons[dayIndex], dayImages[dayIndex], giftImages[dayIndex]);
                }
            });
        }

    }
    // Tıklandığında resmi değiştiren metod
    private void toggleImage(ImageButton button, int dayImage, int giftImage) {
        // Eğer buton şu anda gün resmiyle gösteriliyorsa, hediye resmine geç
        if (button.getDrawable().getConstantState().equals(getResources().getDrawable(dayImage).getConstantState())) {
            button.setImageResource(giftImage);  // Hediye resmi
        } else {
            button.setImageResource(dayImage);   // Gün resmi
        }


    }
    //geri butonunu eklemek için actionh bara
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        // Geri butonuna basıldığında, bir önceki aktiviteye gitmek için finish() kullan
        if (id == android.R.id.home) {
            finish(); // Bu aktiviteyi kapat ve bir önceki aktiviteye dön
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}