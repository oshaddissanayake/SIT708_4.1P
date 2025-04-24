package com.example.taskmanagerapp;

import android.app.AlertDialog;
import android.content.Context;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import java.util.ArrayList;

public class TaskAdapter extends BaseAdapter {

    private final Context context;
    private final ArrayList<Task> taskList;
    private final LayoutInflater inflater;

    public TaskAdapter(Context context, ArrayList<Task> taskList) {
        this.context = context;
        this.taskList = taskList;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return taskList.size();
    }

    @Override
    public Object getItem(int i) {
        return taskList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    static class ViewHolder {
        TextView title, description, date;
        Button editBtn, deleteBtn;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.task_item, viewGroup, false);
            holder = new ViewHolder();
            holder.title = convertView.findViewById(R.id.tv_task_title);
            holder.description = convertView.findViewById(R.id.tv_task_description);
            holder.date = convertView.findViewById(R.id.tv_task_date);
            holder.editBtn = convertView.findViewById(R.id.btn_edit);
            holder.deleteBtn = convertView.findViewById(R.id.btn_delete);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Task task = taskList.get(i);

        holder.title.setText(task.getTitle());
        holder.description.setText(task.getDescription());
        holder.date.setText(task.getDate());

        holder.deleteBtn.setOnClickListener(v -> {
            taskList.remove(i);
            notifyDataSetChanged();
        });

        holder.editBtn.setOnClickListener(v -> showEditDialog(task, i));

        return convertView;
    }

    private void showEditDialog(Task task, int position) {
        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.VERTICAL);
        EditText inputTitle = new EditText(context);
        inputTitle.setHint("Title");
        inputTitle.setText(task.getTitle());
        layout.addView(inputTitle);

        EditText inputDesc = new EditText(context);
        inputDesc.setHint("Description");
        inputDesc.setText(task.getDescription());
        layout.addView(inputDesc);

        EditText inputDate = new EditText(context);
        inputDate.setHint("Date");
        inputDate.setInputType(InputType.TYPE_CLASS_DATETIME);
        inputDate.setText(task.getDate());
        layout.addView(inputDate);

        new AlertDialog.Builder(context)
                .setTitle("Edit Task")
                .setView(layout)
                .setPositiveButton("Save", (dialog, which) -> {
                    task.setTitle(inputTitle.getText().toString().trim());
                    task.setDescription(inputDesc.getText().toString().trim());
                    task.setDate(inputDate.getText().toString().trim());
                    notifyDataSetChanged();
                })
                .setNegativeButton("Cancel", null)
                .show();
    }
}
