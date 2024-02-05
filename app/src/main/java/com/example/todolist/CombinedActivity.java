package com.example.todolist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class CombinedActivity extends AppCompatActivity {

    private EditText editTextDate, editTextTime, editTextLocation;
    private RecyclerView recyclerViewExams;
    private List<Exam> examsList = new ArrayList<>();
    private ExamsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combined);

        editTextDate = findViewById(R.id.editTextDate);
        editTextTime = findViewById(R.id.editTextTime);
        editTextLocation = findViewById(R.id.editTextLocation);
        Button buttonSave = findViewById(R.id.buttonSave);
        recyclerViewExams = findViewById(R.id.recyclerViewExams);

        recyclerViewExams.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ExamsAdapter(examsList);
        recyclerViewExams.setAdapter(adapter);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveExam();
            }
        });

        Button homepageButton = findViewById(R.id.homepageButton);
        homepageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to start ClassDetailsActivity
                Intent intent = new Intent(CombinedActivity.this, HomepageActivity.class);
                startActivity(intent); // Start the new activity
            }
        });
    }

    private void saveExam() {
        String date = editTextDate.getText().toString().trim();
        String time = editTextTime.getText().toString().trim();
        String location = editTextLocation.getText().toString().trim();

        if (date.isEmpty() || time.isEmpty() || location.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        Exam exam = new Exam(date, time, location);
        examsList.add(exam);
        adapter.notifyDataSetChanged();

        editTextDate.setText("");
        editTextTime.setText("");
        editTextLocation.setText("");

        Toast.makeText(this, "Exam Saved", Toast.LENGTH_SHORT).show();
    }
}
