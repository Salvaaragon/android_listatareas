package com.salvaaragon.rememberapp;

import android.content.Intent;
import android.media.Image;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class AllTasksActivity extends AppCompatActivity {

    ListView listTasks;
    ArrayAdapter<String> adapter;
    ArrayList<String> nombre_tareas;
    ImageView back;
    FloatingActionButton btnFloat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_tasks);

        nombre_tareas = new ArrayList<String>();

        if(getIntent().getSerializableExtra("tareas_arreglo") != null)
            nombre_tareas = (ArrayList<String>) getIntent().getSerializableExtra("tareas_arreglo");
        else
            nombre_tareas.add("No hay tareas disponibles");

        listTasks = (ListView) findViewById(R.id.listtasks);
        back = (ImageView) findViewById(R.id.back_aat);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nombre_tareas);

        btnFloat = (FloatingActionButton) findViewById(R.id.btn_float);

        listTasks.setAdapter(adapter);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btnFloat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), NewTaskActivity.class);
                startActivity(intent);
            }
        });
    }
}
