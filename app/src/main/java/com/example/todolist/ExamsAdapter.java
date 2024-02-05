package com.example.todolist;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ExamsAdapter extends RecyclerView.Adapter<ExamsAdapter.ExamViewHolder> {

    private final List<Exam> examsList;

    public ExamsAdapter(List<Exam> examsList) {
        this.examsList = examsList;
    }

    @NonNull
    @Override
    public ExamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.exam_item, parent, false);
        return new ExamViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ExamViewHolder holder, int position) {
        Exam exam = examsList.get(position);
        holder.textViewDate.setText(exam.getDate());
        holder.textViewTime.setText(exam.getTime());
        holder.textViewLocation.setText(exam.getLocation());
    }

    @Override
    public int getItemCount() {
        return examsList.size();
    }

    static class ExamViewHolder extends RecyclerView.ViewHolder {
        private final TextView textViewDate, textViewTime, textViewLocation;

        public ExamViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewDate = itemView.findViewById(R.id.textViewDate);
            textViewTime = itemView.findViewById(R.id.textViewTime);
            textViewLocation = itemView.findViewById(R.id.textViewLocation);
        }
    }
}
