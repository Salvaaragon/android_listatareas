package com.salvaaragon.rememberapp;

import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class TaskActivity extends AppCompatActivity {

    ImageView back;
    TextView tvNombre;
    TextView tvFecha;
    TextView tvDesc;

    FloatingActionButton btnEditTask;

    DataBaseTasks bd;

    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        tvNombre = (TextView) findViewById(R.id.tv_task_name);
        tvFecha = (TextView) findViewById(R.id.tv_task_date);
        tvDesc = (TextView) findViewById(R.id.tv_task_desc);

        btnEditTask = (FloatingActionButton) findViewById(R.id.edit_task_btn);

        back = (ImageView) findViewById(R.id.back_at);

        id = getIntent().getExtras().getString("ID");

        bd = new DataBaseTasks(this);

        ObtenerMostrarDatos();

        btnEditTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TaskActivity.this, EditTask.class);
                intent.putExtra("ID_TASK", id);
                startActivity(intent);
                finish();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    public void ObtenerMostrarDatos() {
        Cursor datos = bd.getTaskById(id);
        int id;
        String name, date, desc;

        while(datos.moveToNext()) {
            id = datos.getInt(datos.getColumnIndex("id"));
            name = datos.getString(datos.getColumnIndex("nombre"));
            date = datos.getString(datos.getColumnIndex("fecha"));
            desc = datos.getString(datos.getColumnIndex("descripcion"));
            Task task = new Task(id, name, date, desc);
            tvNombre.setText(name);
            tvFecha.setText(date);
            tvDesc.setText(desc);
        }
    }
}
