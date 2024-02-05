package com.example.todolist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class HomepageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        Button toDoListButton = findViewById(R.id.toDoListButton);
        Button courseDetailsButton = findViewById(R.id.courseDetailsButton);
        Button assignmentsButton = findViewById(R.id.assignmentsButton);
        Button buttonViewExams = findViewById(R.id.buttonViewExams);

        buttonViewExams.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Intent to navigate to ExamsListActivity
                Intent intent = new Intent(HomepageActivity.this, CombinedActivity.class);
                startActivity(intent);
            }
        });

        toDoListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navigate to ToDoList Activity
                Intent intent = new Intent(HomepageActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        courseDetailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navigate to CourseDetails Activity
                Intent intent = new Intent(HomepageActivity.this, ClassDetailsActivity.class);
                startActivity(intent);
            }
        });

        assignmentsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navigate to ToDoList Activity
                Intent intent = new Intent(HomepageActivity.this, AssignmentsActivity.class);
                startActivity(intent);
            }
        });
    }
}