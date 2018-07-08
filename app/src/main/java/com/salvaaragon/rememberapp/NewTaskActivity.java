package com.salvaaragon.rememberapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class NewTaskActivity extends AppCompatActivity {

    ImageView back;

    ArrayList<String> tarea, fecha, desc;
    EditText etTarea, etDia, etMes, etAnio, etDesc;
    LinearLayout btnSave;
    Task taskObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);

        tarea = new ArrayList<String>();
        fecha = new ArrayList<String>();
        desc = new ArrayList<String>();

        etTarea = (EditText) findViewById(R.id.et_task);
        etDia = (EditText) findViewById(R.id.et_dia);
        etMes = (EditText) findViewById(R.id.et_mes);
        etAnio = (EditText) findViewById(R.id.et_anio);
        etDesc = (EditText) findViewById(R.id.et_desc);

        btnSave = (LinearLayout) findViewById(R.id.btn_save);

        back = (ImageView) findViewById(R.id.back_ant);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarDatos();
            }
        });
    }

    public void guardarDatos(){
        String task, date, description;

        task = etTarea.getText().toString();
        date = etDia.getText().toString() +"/"+ etMes.getText().toString() +"/"+ etAnio.getText().toString();
        description = etDesc.getText().toString();

        /*tarea.add(task);
        fecha.add(date);
        desc.add(description);*/

        taskObject = new Task(task, date, description);
        Toast.makeText(this, "Se ha creado una nueva tarea",Toast.LENGTH_SHORT);
    }

}