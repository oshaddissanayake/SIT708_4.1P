package com.example.taskmanagerapp;

import android.app.AlertDialog;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Task> taskList;
    private TaskAdapter adapter;
    private ListView taskListView;
    private Button addTaskBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        taskList = new ArrayList<>();
        adapter = new TaskAdapter(this, taskList);

        taskListView = findViewById(R.id.task_list);
        addTaskBtn = findViewById(R.id.btn_add_task);

        taskListView.setAdapter(adapter);

        addTaskBtn.setOnClickListener(view -> showAddTaskDialog());
    }

    private void showAddTaskDialog() {
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);

        EditText inputTitle = new EditText(this);
        inputTitle.setHint("Title");
        layout.addView(inputTitle);

        EditText inputDesc = new EditText(this);
        inputDesc.setHint("Description");
        layout.addView(inputDesc);

        EditText inputDate = new EditText(this);
        inputDate.setHint("Date");
        inputDate.setInputType(InputType.TYPE_CLASS_DATETIME);
        layout.addView(inputDate);

        new AlertDialog.Builder(this)
                .setTitle("Add New Task")
                .setView(layout)
                .setPositiveButton("Add", (dialog, which) -> {
                    String title = inputTitle.getText().toString().trim();
                    String desc = inputDesc.getText().toString().trim();
                    String date = inputDate.getText().toString().trim();

                    if (!title.isEmpty()) {
                        taskList.add(new Task(title, desc, date));
                        adapter.notifyDataSetChanged();
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
    }
}
