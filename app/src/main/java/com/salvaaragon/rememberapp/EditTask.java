package com.salvaaragon.rememberapp;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class EditTask extends AppCompatActivity {

    EditText nombre;
    EditText dia;
    EditText mes;
    EditText annio;
    EditText descripcion;
    LinearLayout btnActualizar;
    ImageView btnBack;

    DataBaseTasks bd;

    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task);

        bd = new DataBaseTasks(this);

        nombre = (EditText) findViewById(R.id.et_task_update);
        dia = (EditText) findViewById(R.id.et_dia_update);
        mes = (EditText) findViewById(R.id.et_mes_update);
        annio = (EditText) findViewById(R.id.et_anio_update);
        descripcion = (EditText) findViewById(R.id.et_desc_update);
        btnActualizar = (LinearLayout) findViewById(R.id.btn_update);
        btnBack = (ImageView) findViewById(R.id.back_aew);

        id = getIntent().getExtras().getString("ID_TASK");

        ObtenerMostrarDatos();

        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fecha = dia.getText().toString() +"/"+ mes.getText().toString() +"/"+ annio.getText().toString();
                bd.updateTask(id, nombre.getText().toString(), fecha, descripcion.getText().toString());
                finish();
                Toast.makeText(getApplicationContext(),"La tarea ha sido actualizada correctamente", Toast.LENGTH_SHORT).show();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
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
            nombre.setText(name);
            descripcion.setText(desc);
            dia.setText(date.substring(0,2));
            mes.setText(date.substring(3,6));
            annio.setText(date.substring(7));
        }
    }
}
