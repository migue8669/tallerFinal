package me.parzibyte.crudsqlite;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import me.parzibyte.crudsqlite.controllers.MascotasController;
import me.parzibyte.crudsqlite.modelos.Mascota;

public class AgregarMascotaActivity extends AppCompatActivity  {
    private Button btnAgregarMascota, btnCancelarNuevaMascota;
    private EditText etNombres;
    private EditText codigos;
    private EditText nota1s;
    private EditText nota2s;
    private EditText nota3s ;
    private MascotasController mascotasController;
    private Spinner grados;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_mascota);

// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.gradoArray, android.R.layout.simple_spinner_item);
        grados=(Spinner)findViewById( R.id.gradoArray ) ;
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        grados.setAdapter(adapter);

        // Instanciar vistas
        etNombres = findViewById(R.id.etNombre);
        codigos = findViewById(R.id.codigo);
        //grados = findViewById(R.id.gradoArray);
        nota1s = findViewById(R.id.nota1);
        nota2s = findViewById(R.id.nota2);
        nota3s = findViewById(R.id.nota3);
        btnAgregarMascota = findViewById(R.id.btnAgregarMascota);
        btnCancelarNuevaMascota = findViewById(R.id.btnCancelarNuevaMascota);
        // Crear el controlador
        mascotasController = new MascotasController(AgregarMascotaActivity.this);
        final String grado=grados.toString();
        // Agregar listener del botón de guardar
        btnAgregarMascota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Resetear errores a ambos
                etNombres.setError(null);
                codigos.setError(null);

                nota1s.setError(null);
                nota2s.setError(null);
                nota3s.setError(null);
                String nombre = etNombres.getText().toString();


               // Ver si es un entero
                int codigo;
                    codigo = Integer.parseInt( String.valueOf(codigos.getText()));

               int nota1 = Integer.parseInt( String.valueOf( nota1s.getText() ) );
                int nota2 = Integer.parseInt( String.valueOf( nota2s.getText() ) );
                int nota3 = Integer.parseInt( String.valueOf( nota3s.getText() ) );
                int promedio=nota1*nota2*nota3/3;
                // Ya pasó la validación
                Mascota nuevaMascota = new Mascota(nombre, codigo, grado,nota1,nota2,nota3,promedio);
                long id = mascotasController.nuevaMascota(nuevaMascota);
                if (id == -1) {
                    // De alguna manera ocurrió un error
                    Toast.makeText(AgregarMascotaActivity.this, "Error al guardar. Intenta de nuevo", Toast.LENGTH_SHORT).show();
                } else {
                    // Terminar
                    finish();
                }
            }
        });

        // El de cancelar simplemente cierra la actividad
        btnCancelarNuevaMascota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}
