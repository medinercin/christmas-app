package com.medi.projedeneme;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class AddTaskActivity extends AppCompatActivity {
    private EditText taskEditText;
    private Button addButton;
    private ListView taskListView;
    private ArrayList<String> taskList;
    private ArrayAdapter<String> adapter;
    private DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        //action bar isim değişikliği
        getSupportActionBar().setTitle("GÖREV EKLEME");
        //geri butonunu eklemek için actionh bara
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true); // Geri butonunu ekle
            getSupportActionBar().setDisplayShowHomeEnabled(true); // Ana simgeyi göster
        }

        taskEditText = findViewById(R.id.taskEditText);
        addButton = findViewById(R.id.addButton);
        taskListView = findViewById(R.id.taskListView);
        databaseHelper = new DatabaseHelper(this);
        taskList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, taskList);
        taskListView.setAdapter(adapter);

        loadTasks(); // Veritabanındaki tüm görevleri yükle
        addButton.setOnClickListener(v -> {
            String task = taskEditText.getText().toString();
            if (!task.isEmpty()) {
                databaseHelper.addTask(task); // Veritabanına ekle
                taskEditText.setText(""); // Giriş alanını temizle
                loadTasks(); // Listeyi yeniden yükle
            }
        });

        taskListView.setOnItemClickListener((parent, view, position, id) -> {
            String task = taskList.get(position);
            // Burada silme işlemi yapılabilir
            deleteTask(task);
        });
    }
    private void loadTasks() {
        taskList.clear(); // Önceki görevleri temizle
        Cursor cursor = databaseHelper.getAllTasks();
        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") String task = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_TASK));
                taskList.add(task); // Görevleri listeye ekle
            } while (cursor.moveToNext());
        }
        cursor.close();
        adapter.notifyDataSetChanged(); // Listeyi güncelle
    }

    private void deleteTask(String task) {
        Cursor cursor = databaseHelper.getAllTasks();
        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") String currentTask = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_TASK));
                if (currentTask.equals(task)) {
                    @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_ID));
                    databaseHelper.deleteTask(id); // Silme işlemi
                    loadTasks(); // Listeyi güncelle
                    break;
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
    }
    //geri butonunu eklemek için action bara
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