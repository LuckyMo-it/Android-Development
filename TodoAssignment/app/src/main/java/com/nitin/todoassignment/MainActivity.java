package com.nitin.todoassignment;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> listTask = new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;
    ListView lst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        // Delay before showing main layout
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                setContentView(R.layout.activity_main);

                // Now initialize views AFTER setContentView
                lst = findViewById(R.id.task);
                FloatingActionButton fab = findViewById(R.id.floatingActionButton);

                // Sample task
                listTask.add("project");

                // Set adapter
                arrayAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, listTask);
                lst.setAdapter(arrayAdapter);

                // Delete on item click
                lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String item1 = arrayAdapter.getItem(position);

                        new AlertDialog.Builder(MainActivity.this)
                                .setTitle("Delete Task")
                                .setMessage("Are you sure you want to delete this task?")
                                .setNegativeButton("Cancel", null)
                                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        listTask.remove(position);

                                        arrayAdapter.notifyDataSetChanged();
                                    }
                                }).show();
                    }
                });

                // TODO: Set onClickListener for FAB (e.g., add task)
                fab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Create an EditText view
                        final EditText input = new EditText(MainActivity.this);
                        input.setHint("Enter task");

                        // Create and show the input dialog
                        new AlertDialog.Builder(MainActivity.this)
                                .setTitle("Add New Task")
                                .setView(input)
                                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int whichButton) {
                                        String newTask = input.getText().toString().trim();
                                        if (!newTask.isEmpty()) {
                                            listTask.add(newTask);
                                            arrayAdapter.notifyDataSetChanged();
                                        }
                                    }
                                })
                                .setNegativeButton("Cancel", null)
                                .show();
                    }
                });
            }
        }, 3000); // 3-second splash screen
    }
}
