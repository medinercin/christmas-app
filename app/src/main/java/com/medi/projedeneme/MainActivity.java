package com.medi.projedeneme;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
private TextView textViewTanitim;
private ImageView imageHappyYear;
private Button btnAdvent,btnTodo;
private TextView countdownTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("YILBAŞI UYGULAMASI");
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });
        imageHappyYear=(ImageView)findViewById(R.id.imageHappyYear) ;
        textViewTanitim=(TextView)findViewById(R.id.textViewTanitim);
        btnAdvent=(Button)findViewById(R.id.btnAdvent);
        btnTodo=(Button)findViewById(R.id.btnTodo);
        btnAdvent.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#574A34")));
        btnTodo.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#574A34")));

        btnAdvent.setOnClickListener(v->{
            Intent intent=new Intent(MainActivity.this, AdventCalenderActivity.class);
            startActivity(intent);
        });
        btnTodo.setOnClickListener(v->{
            Intent intent=new Intent(MainActivity.this, ToDoActivity.class);
            startActivity(intent);
        });
        countdownTextView = findViewById(R.id.countdownTextView);

        // Yılbaşı tarihini al
        Calendar newYear = Calendar.getInstance();
        newYear.set(Calendar.MONTH, Calendar.DECEMBER); // Aralık
        newYear.set(Calendar.DAY_OF_MONTH, 31); // 31 Aralık
        newYear.set(Calendar.HOUR_OF_DAY, 23); // Saat 23
        newYear.set(Calendar.MINUTE, 59); // Dakika 59
        newYear.set(Calendar.SECOND, 59); // Saniye 59

        // Şu anki zamanı al
        long currentTime = System.currentTimeMillis();
        long newYearTime = newYear.getTimeInMillis();

        // Geriye kalan zamanı hesapla
        long timeLeft = newYearTime - currentTime;

        // CountDownTimer başlat
        new CountDownTimer(timeLeft, 1000) { // Her 1 saniyede bir tetiklenir
            @Override
            public void onTick(long millisUntilFinished) {
                // Gün, saat, dakika ve saniyeyi hesapla
                long days = millisUntilFinished / (1000 * 60 * 60 * 24);
                long hours = (millisUntilFinished / (1000 * 60 * 60)) % 24;
                long minutes = (millisUntilFinished / (1000 * 60)) % 60;
                long seconds = (millisUntilFinished / 1000) % 60;

                // TextView'e ayarla
                String timeLeftFormatted = String.format(
                        "%d Gün %02d Saat %02d Dakika %02d Saniye",
                        days, hours, minutes, seconds
                );
                countdownTextView.setText(timeLeftFormatted);
            }

            @Override
            public void onFinish() {
                countdownTextView.setText("Mutlu Yıllar! 🎉");
            }
        }.start();
    }

}