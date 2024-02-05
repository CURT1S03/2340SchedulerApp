package com.example.todolist;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ClassDetailsActivity extends AppCompatActivity {
    private EditText courseNameEditText, timeEditText, instructorEditText;
    private RecyclerView classesRecyclerView;
    private ClassAdapter classAdapter;
    private ClassDetailsViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_details);

        viewModel = new ViewModelProvider(this).get(ClassDetailsViewModel.class);

        initializeUI();
        setupObservers();
    }

    private void initializeUI() {
        courseNameEditText = findViewById(R.id.courseNameEditText);
        timeEditText = findViewById(R.id.timeEditText);
        instructorEditText = findViewById(R.id.instructorEditText);
        Button addButton = findViewById(R.id.addButton);
        classesRecyclerView = findViewById(R.id.classesRecyclerView);
        Button homepageButton = findViewById(R.id.homepageButton);

        classAdapter = new ClassAdapter(this, new ArrayList<>(), new ClassAdapter.OnItemClickListener() {
            @Override
            public void onEditClick(int position) {
                showEditDialog(position);
            }

            @Override
            public void onDeleteClick(int position) {
                viewModel.removeClassDetail(position);
            }
        });

        classesRecyclerView.setAdapter(classAdapter);
        classesRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        addButton.setOnClickListener(view -> addClassDetail());
        homepageButton.setOnClickListener(view -> startActivity(new Intent(ClassDetailsActivity.this, HomepageActivity.class)));
    }

    private void setupObservers() {
        viewModel.getClassDetails().observe(this, classDetails -> {
            classAdapter.setClassDetails(classDetails);
            classAdapter.notifyDataSetChanged();
        });
    }

    private void addClassDetail() {
        String courseName = courseNameEditText.getText().toString();
        String time = timeEditText.getText().toString();
        String instructor = instructorEditText.getText().toString();
        if (!courseName.isEmpty() && !time.isEmpty() && !instructor.isEmpty()) {
            viewModel.addClassDetail(new ClassDetail(courseName, time, instructor));
            courseNameEditText.setText("");
            timeEditText.setText("");
            instructorEditText.setText("");
        }
    }

    private void showEditDialog(final int position) {
        ClassDetail currentClassDetail = viewModel.getClassDetails().getValue().get(position);
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_edit_class, null);
        final EditText editCourseName = dialogView.findViewById(R.id.editCourseName);
        final EditText editTime = dialogView.findViewById(R.id.editTime);
        final EditText editInstructor = dialogView.findViewById(R.id.editInstructor);

        editCourseName.setText(currentClassDetail.getCourseName());
        editTime.setText(currentClassDetail.getTime());
        editInstructor.setText(currentClassDetail.getInstructor());

        new AlertDialog.Builder(this)
                .setView(dialogView)
                .setTitle("Edit Class")
                .setPositiveButton("Save", (dialog, which) -> {
                    String newCourseName = editCourseName.getText().toString();
                    String newTime = editTime.getText().toString();
                    String newInstructor = editInstructor.getText().toString();
                    ClassDetail updatedClassDetail = new ClassDetail(newCourseName, newTime, newInstructor);
                    viewModel.updateClassDetail(position, updatedClassDetail);
                })
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }
}
