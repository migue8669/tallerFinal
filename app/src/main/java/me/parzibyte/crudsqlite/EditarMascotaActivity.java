package me.parzibyte.crudsqlite;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import me.parzibyte.crudsqlite.controllers.MascotasController;
import me.parzibyte.crudsqlite.modelos.Mascota;

public class EditarMascotaActivity extends AppCompatActivity {
    private EditText etEditarNombre, etEditarNota1, etEditarNota2, etEditarNota3;
    private Button btnGuardarCambios, btnCancelarEdicion;
    private Mascota mascota;
    private MascotasController mascotasController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_mascota);

        // Recuperar datos que enviaron
        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            finish();
            return;
        }
        mascotasController = new MascotasController(EditarMascotaActivity.this);


        long id = extras.getLong("id");
        String nombre = extras.getString("nombre");
        int codigo = extras.getInt("codigo");
        String grado = extras.getString("grado");
        final int nota1 = extras.getInt("nota1");
        final int nota2 = extras.getInt("nota2");
        final int nota3 = extras.getInt("nota3");
        final double promedio = extras.getInt("promedio");

        mascota = new Mascota(nombre, codigo, grado,nota1,nota2,nota3,promedio,id);

System.out.println( "datos" +mascota );
        // Ahora declaramos las vistas
        etEditarNota1 = findViewById(R.id.editarNota1);
        etEditarNota2 = findViewById(R.id.editarNota2);
        etEditarNota3 = findViewById(R.id.editarNota3);
        etEditarNombre = findViewById(R.id.etEditarNombre);
        btnCancelarEdicion = findViewById(R.id.btnCancelarEdicionMascota);
        btnGuardarCambios = findViewById(R.id.btnGuardarCambiosMascota);

System.out.println( "editar   "+mascota );
        // Rellenar los EditText con los datos de la mascota
        etEditarNota1.setText(String.valueOf(mascota.getNota1()));
        etEditarNota2.setText(String.valueOf(mascota.getNota2()));
        etEditarNota3.setText(String.valueOf(mascota.getNota3()));
        etEditarNombre.setText(mascota.getNombre());

        // Listener del click del botón para salir, simplemente cierra la actividad
        btnCancelarEdicion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Listener del click del botón que guarda cambios
        btnGuardarCambios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Remover previos errores si existen
                etEditarNombre.setError( null );
                etEditarNota1.setError( null );
                etEditarNota2.setError( null );
                etEditarNota3.setError( null );

                String nuevoNombre = etEditarNombre.getText().toString();
                String posibleNuevaNota1 = etEditarNota1.getText().toString();
                String posibleNuevaNota2 = etEditarNota2.getText().toString();
                String posibleNuevaNota3 = etEditarNota3.getText().toString();
                if (nuevoNombre.isEmpty()) {
                    etEditarNombre.setError( "Escribe el nombre" );
                    etEditarNombre.requestFocus();
                    return;
                }
                if (posibleNuevaNota1.isEmpty()) {
                    etEditarNota1.setError( "Escribe la nota 1" );
                    etEditarNota1.requestFocus();
                    return;
                }
                if (posibleNuevaNota2.isEmpty()) {
                    etEditarNota2.setError( "Escribe la nota 2" );
                    etEditarNota2.requestFocus();
                    return;
                }
                if (posibleNuevaNota3.isEmpty()) {
                    etEditarNota3.setError( "Escribe la nota 3" );
                    etEditarNota3.requestFocus();
                    return;
                }
                // Si no es entero, igualmente marcar error
                int nuevaNota1, nuevaNota2, nuevaNota3;
                try {
                    nuevaNota1 = Integer.parseInt( posibleNuevaNota1 );
                    nuevaNota2 = Integer.parseInt( posibleNuevaNota2 );
                    nuevaNota3 = Integer.parseInt( posibleNuevaNota3 );
                } catch (NumberFormatException e) {
                    etEditarNota1.setError( "Escribe un número" );
                    etEditarNota1.requestFocus();
                    etEditarNota2.setError( "Escribe un número" );
                    etEditarNota2.requestFocus();
                    etEditarNota3.setError( "Escribe un número" );
                    etEditarNota3.requestFocus();
                    return;
                }

                // Si llegamos hasta aquí es porque los datos ya están validados
                Mascota mascotaConNuevosCambios = new Mascota( nuevoNombre, mascota.getCodigo(), mascota.getGrado(), nuevaNota1, nuevaNota2, nuevaNota3,mascota.getPromedio(),mascota.getId() );
System.out.println("mascotanueva"+ mascotaConNuevosCambios );
            int filasModificadas = mascotasController.guardarCambios(mascotaConNuevosCambios);
            System.out.println( filasModificadas );
                if (filasModificadas != 1) {
                    Toast.makeText(EditarMascotaActivity.this, "Error guardando cambios. Intente de nuevo.", Toast.LENGTH_SHORT).show();
                } else {
                    // Si las cosas van bien, volvemos a la principal
                    // cerrando esta actividad
                    finish();
                }
            }
        });
    }
}
