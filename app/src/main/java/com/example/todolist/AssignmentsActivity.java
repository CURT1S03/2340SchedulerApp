package com.example.todolist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.UUID;

public class AssignmentsActivity extends AppCompatActivity implements AssignmentsAdapter.AssignmentActionListener {

    private EditText titleInput, dueDateInput, classInput;
    private RecyclerView assignmentsList;
    private ArrayList<Assignment> assignments;
    private AssignmentsAdapter adapter;
    private Assignment currentlyEditing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignments);

        titleInput = findViewById(R.id.assignmentTitle);
        dueDateInput = findViewById(R.id.assignmentDueDate);
        classInput = findViewById(R.id.assignmentClass);
        assignmentsList = findViewById(R.id.assignmentsList);

        assignments = new ArrayList<>();
        adapter = new AssignmentsAdapter(assignments, this);
        assignmentsList.setAdapter(adapter);
        assignmentsList.setLayoutManager(new LinearLayoutManager(this));

        Button saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(view -> saveAssignment());

        Button sortByDateButton = findViewById(R.id.sortByDateButton);
        Button sortByClassButton = findViewById(R.id.sortByClassButton);

        sortByDateButton.setOnClickListener(v -> sortByDueDate());
        sortByClassButton.setOnClickListener(v -> sortByClass());

        Button backToHomepageButton = findViewById(R.id.backToHomepageButton);
        backToHomepageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateBackToHomepage();
            }
        });
    }

    private void navigateBackToHomepage() {
        Intent intent = new Intent(this, HomepageActivity.class);
        startActivity(intent);
    }


    private void saveAssignment() {
        String title = titleInput.getText().toString().trim();
        String dueDate = dueDateInput.getText().toString().trim();
        String associatedClass = classInput.getText().toString().trim();

        if (!title.isEmpty() && !dueDate.isEmpty() && !associatedClass.isEmpty()) {
            if (currentlyEditing != null) {
                assignments.remove(currentlyEditing); // Remove the old assignment if we are editing
            }
            Assignment assignment = new Assignment(UUID.randomUUID().toString(), title, dueDate, associatedClass);
            assignments.add(assignment);
            adapter.notifyDataSetChanged();

            titleInput.setText("");
            dueDateInput.setText("");
            classInput.setText("");
            currentlyEditing = null; // Reset the editing state
        }
    }

    private void sortByDueDate() {
        Collections.sort(assignments, (a1, a2) -> a1.getDueDate().compareTo(a2.getDueDate()));
        adapter.notifyDataSetChanged();
    }

    private void sortByClass() {
        Collections.sort(assignments, (a1, a2) -> a1.getAssociatedClass().compareTo(a2.getAssociatedClass()));
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onEditSelected(Assignment assignment) {
        // Fill the input fields with the assignment's details for editing
        titleInput.setText(assignment.getTitle());
        dueDateInput.setText(assignment.getDueDate());
        classInput.setText(assignment.getAssociatedClass());
        currentlyEditing = assignment; // Set the current assignment being edited
    }

    @Override
    public void onDeleteSelected(Assignment assignment) {
        new AlertDialog.Builder(this)
                .setTitle("Delete Assignment")
                .setMessage("Are you sure you want to delete this assignment?")
                .setPositiveButton(android.R.string.yes, (dialog, which) -> {
                    // Remove the assignment from the list
                    assignments.remove(assignment);
                    // Notify the adapter to refresh the list
                    adapter.notifyDataSetChanged();
                })
                .setNegativeButton(android.R.string.no, null)
                .show();
    }

}
