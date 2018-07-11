package com.salvaaragon.rememberapp;

import android.content.Intent;
import android.database.Cursor;
import android.media.Image;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class AllTasksActivity extends AppCompatActivity {

    ListView listTasks;
    ArrayAdapter<String> adapter;
    ArrayList<String> nombre_tareas;
    ImageView back;
    FloatingActionButton btnFloat;
    DataBaseTasks dataBaseTasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_tasks);
        dataBaseTasks = new DataBaseTasks(this);

        nombre_tareas = new ArrayList<String>();
        
        llenaArreglo();

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

    public ArrayList<Task> obtenDatos() {
        Cursor datos = dataBaseTasks.getTareas();
        ArrayList<Task> tareas = new ArrayList<Task>();
        String name, date, desc;

        while(datos.moveToNext()) {
            name = datos.getString(datos.getColumnIndex("nombre"));
            date = datos.getString(datos.getColumnIndex("fecha"));
            desc = datos.getString(datos.getColumnIndex("descripcion"));
            Task task = new Task(name, date, desc);
            tareas.add(task);
        }

        return tareas;
    }

    public void llenaArreglo() {
        ArrayList<Task> tareas = obtenDatos();
        for(int i = 0; i < tareas.size(); i++) {
            nombre_tareas.add(tareas.get(i).getNombre());
        }
    }
}
