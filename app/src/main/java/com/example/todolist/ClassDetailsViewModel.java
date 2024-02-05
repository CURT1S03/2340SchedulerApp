package com.example.todolist;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.util.ArrayList;
import java.util.List;

public class ClassDetailsViewModel extends ViewModel {
    private MutableLiveData<List<ClassDetail>> classDetails;

    public ClassDetailsViewModel() {
        classDetails = new MutableLiveData<>(new ArrayList<>());
        // Initialize your class details list here if needed
    }

    public LiveData<List<ClassDetail>> getClassDetails() {
        return classDetails;
    }

    // Method to add a new class detail
    public void addClassDetail(ClassDetail classDetail) {
        List<ClassDetail> currentList = classDetails.getValue();
        if (currentList != null) {
            currentList.add(classDetail);
            classDetails.setValue(currentList); // Notify observers of the change
        }
    }

    // Method to update an existing class detail
    public void updateClassDetail(int position, ClassDetail classDetail) {
        List<ClassDetail> currentList = classDetails.getValue();
        if (currentList != null && position >= 0 && position < currentList.size()) {
            currentList.set(position, classDetail);
            classDetails.setValue(currentList); // Notify observers of the change
        }
    }

    // Method to remove a class detail
    public void removeClassDetail(int position) {
        List<ClassDetail> currentList = classDetails.getValue();
        if (currentList != null && position >= 0 && position < currentList.size()) {
            currentList.remove(position);
            classDetails.setValue(currentList); // Notify observers of the change
        }
    }
}
