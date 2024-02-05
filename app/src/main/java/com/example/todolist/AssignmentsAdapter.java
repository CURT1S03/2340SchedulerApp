package com.example.todolist;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class AssignmentsAdapter extends RecyclerView.Adapter<AssignmentsAdapter.ViewHolder> {

    private final ArrayList<Assignment> assignments;
    private AssignmentActionListener listener;

    public AssignmentsAdapter(ArrayList<Assignment> assignments, AssignmentActionListener listener) {
        this.assignments = assignments;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.assignment_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Assignment assignment = assignments.get(position);
        holder.title.setText(assignment.getTitle());
        holder.dueDate.setText(assignment.getDueDate());
        holder.associatedClass.setText(assignment.getAssociatedClass());

        holder.editButton.setOnClickListener(v -> {
            // Notify the activity of the edit action
            if (listener != null) {
                listener.onEditSelected(assignment);
            }
        });

        holder.deleteButton.setOnClickListener(v -> {
            new AlertDialog.Builder(holder.itemView.getContext())
                    .setTitle("Delete Assignment")
                    .setMessage("Are you sure you want to delete this assignment?")
                    .setPositiveButton(android.R.string.yes, (dialog, which) -> {
                        // Remove the assignment from the list
                        assignments.remove(position);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position, assignments.size());
                    })
                    .setNegativeButton(android.R.string.no, null)
                    .setIcon(R.drawable.ic_launcher_foreground)
                    .show();
        });
    }

    @Override
    public int getItemCount() {
        return assignments.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, dueDate, associatedClass;
        Button editButton, deleteButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.assignmentTitle);
            dueDate = itemView.findViewById(R.id.assignmentDueDate);
            associatedClass = itemView.findViewById(R.id.assignmentClass);
            editButton = itemView.findViewById(R.id.editButton);
            deleteButton = itemView.findViewById(R.id.deleteButton);
        }
    }

    // Interface for handling edit and delete actions
    public interface AssignmentActionListener {
        void onEditSelected(Assignment assignment);

        void onDeleteSelected(Assignment assignment);
    }
}
