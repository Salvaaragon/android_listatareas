package com.salvaaragon.rememberapp;

import android.content.Intent;
import android.database.Cursor;
import android.media.Image;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AllTasksActivity extends AppCompatActivity {

    ListView listTasks;
    ArrayAdapter<String> adapter;
    ArrayList<String> nombre_tareas;
    ImageView back;
    FloatingActionButton btnFloat;
    DataBaseTasks dataBaseTasks;
    ArrayList<Task> tareasBD;
    Map<String, Integer> mapaTareas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_tasks);
        dataBaseTasks = new DataBaseTasks(this);

        tareasBD = new ArrayList<Task>();
        tareasBD = obtenDatos();
        nombre_tareas = new ArrayList<String>();
        mapaTareas = new HashMap<String, Integer>();

        llenaArreglo();
        llenarMapa();

        listTasks = (ListView) findViewById(R.id.listtasks);
        back = (ImageView) findViewById(R.id.back_aat);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nombre_tareas);

        btnFloat = (FloatingActionButton) findViewById(R.id.btn_float);

        listTasks.setAdapter(adapter);
        listTasks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String nombre = adapter.getItem(position);
                //Toast.makeText(getApplicationContext(), "ID: " + mapaTareas.get(nombre), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(AllTasksActivity.this, TaskActivity.class);
                intent.putExtra("ID", mapaTareas.get(nombre).toString());
                startActivity(intent);
            }
        });

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
        int id;
        String name, date, desc;

        while(datos.moveToNext()) {
            id = datos.getInt(datos.getColumnIndex("id"));
            name = datos.getString(datos.getColumnIndex("nombre"));
            date = datos.getString(datos.getColumnIndex("fecha"));
            desc = datos.getString(datos.getColumnIndex("descripcion"));
            Task task = new Task(id, name, date, desc);
            tareas.add(task);
        }

        return tareas;
    }

    public void llenaArreglo() {
        for(int i = 0; i < tareasBD.size(); i++) {
            nombre_tareas.add(tareasBD.get(i).getNombre());
        }
    }

    public void llenarMapa() {
        for(int i = 0; i < tareasBD.size(); i++) {
            mapaTareas.put(tareasBD.get(i).getNombre(), tareasBD.get(i).getId());
        }
    }


}
