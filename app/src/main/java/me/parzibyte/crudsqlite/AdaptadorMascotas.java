package me.parzibyte.crudsqlite;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import me.parzibyte.crudsqlite.modelos.Mascota;

public class AdaptadorMascotas extends RecyclerView.Adapter<AdaptadorMascotas.MyViewHolder> {

    private List<Mascota> listaDeMascotas;

    public void setListaDeMascotas(List<Mascota> listaDeMascotas) {
        this.listaDeMascotas = listaDeMascotas;
    }

    public AdaptadorMascotas(List<Mascota> mascotas) {
        this.listaDeMascotas = mascotas;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View filaMascota = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fila_mascota, viewGroup, false);
        return new MyViewHolder(filaMascota);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        // Obtener  índice i
        Mascota mascota = listaDeMascotas.get(i);
System.out.println( "indice"+mascota );
        // Obtener los datos de la lista
        String nombre = mascota.getNombre();
   //     int codigo = mascota.getCodigo();
    //    String grado=mascota.getGrado();
        int nota1=mascota.getNota1();
        int nota2=mascota.getNota2();
        int nota3=mascota.getNota3();
        // Y poner a los TextView los datos con setText
        myViewHolder.nombre.setText(nombre);
    //    myViewHolder.codigo.setText(String.valueOf(codigo));
     //   myViewHolder.grado.setText(grado);
        myViewHolder.nota1.setText(String.valueOf(nota1));
        myViewHolder.nota2.setText(String.valueOf(nota2));
        myViewHolder.nota3.setText(String.valueOf(nota3));

    }

    @Override
    public int getItemCount() {
        return listaDeMascotas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView nombre,
                //codigo, grado,
                nota1,nota2,nota3;

        MyViewHolder(View itemView) {
            super(itemView);
            this.nombre = itemView.findViewById(R.id.tvNombre);
      //      this.codigo = itemView.findViewById(R.id.codigo);
        //    this.grado = itemView.findViewById(R.id.gradoArray);
            this.nota1 = itemView.findViewById(R.id.tvNota1);
            this.nota2 = itemView.findViewById(R.id.tvNota2);
            this.nota3 = itemView.findViewById(R.id.tvNota3);
        }
    }
}
