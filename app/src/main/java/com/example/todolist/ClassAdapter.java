package com.example.todolist;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ClassAdapter extends RecyclerView.Adapter<ClassAdapter.ClassViewHolder> {

    private List<ClassDetail> classDetails;
    private LayoutInflater inflater;
    private OnItemClickListener listener;

    public ClassAdapter(Context context, List<ClassDetail> classDetails, OnItemClickListener listener) {
        this.inflater = LayoutInflater.from(context);
        this.classDetails = classDetails;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ClassViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.class_item, parent, false);
        return new ClassViewHolder(itemView, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ClassViewHolder holder, int position) {
        ClassDetail classDetail = classDetails.get(position);
        holder.courseNameTextView.setText(classDetail.getCourseName());
        holder.timeTextView.setText(classDetail.getTime());
        holder.instructorTextView.setText(classDetail.getInstructor());
    }

    @Override
    public int getItemCount() {
        return classDetails.size();
    }

    public void setClassDetails(List<ClassDetail> classDetails) {
        this.classDetails = classDetails;
        notifyDataSetChanged(); // Notify any registered observers that the data set has changed.
    }

    public interface OnItemClickListener {
        void onEditClick(int position);
        void onDeleteClick(int position);
    }

    public static class ClassViewHolder extends RecyclerView.ViewHolder {
        final TextView courseNameTextView;
        final TextView timeTextView;
        final TextView instructorTextView;
        final ImageButton editButton;
        final ImageButton deleteButton;

        public ClassViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            courseNameTextView = itemView.findViewById(R.id.courseNameTextView);
            timeTextView = itemView.findViewById(R.id.timeTextView);
            instructorTextView = itemView.findViewById(R.id.instructorTextView);
            editButton = itemView.findViewById(R.id.editButton);
            deleteButton = itemView.findViewById(R.id.deleteButton);

            editButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onEditClick(position);
                    }
                }
            });

            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onDeleteClick(position);
                    }
                }
            });
        }
    }
}
