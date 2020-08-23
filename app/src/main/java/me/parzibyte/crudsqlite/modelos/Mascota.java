package me.parzibyte.crudsqlite.modelos;

public class Mascota {

    private String nombre;
    private String grado;
    private int codigo;
    private int nota1;
    private int nota2;
    private int nota3;
    private double promedio;

    private long id; // El ID de la BD

    public Mascota(String nombre,  int codigo, String grado, int nota1, int nota2, int nota3, double promedio) {
        this.nombre = nombre;
        this.grado = grado;
        this.codigo = codigo;
        this.nota1 = nota1;
        this.nota2 = nota2;
        this.nota3 = nota3;
        this.promedio=promedio;
    }

    public double getPromedio() {
        return promedio;
    }

    public void setPromedio(double promedio) {
        this.promedio = promedio;
    }

    // Constructor para cuando instanciamos desde la BD
    public Mascota(String nombre, int codigo, String grado,int nota1, int nota2, int nota3,double promedio,long id) {
        this.nombre = nombre;
        this.grado = grado;
        this.codigo = codigo;
        this.nota1 = nota1;
        this.nota2 = nota2;
        this.nota3 = nota3;
        this.promedio=promedio;
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGrado() {
        return grado;
    }

    public void setGrado(String grado) {
        this.grado = grado;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getNota1() {
        return nota1;
    }

    public void setNota1(int nota1) {
        this.nota1 = nota1;
    }

    public int getNota2() {
        return nota2;
    }

    public void setNota2(int nota2) {
        this.nota2 = nota2;
    }

    public int getNota3() {
        return nota3;
    }

    public void setNota3(int nota3) {
        this.nota3 = nota3;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }



    @Override
    public String toString() {
        return "Mascota{" +
                "nombre='" + nombre + '\'' +
                ", codigo=" + codigo +
                ", grado=" + grado +
                ", nota1=" + nota1 +
                ", nota2=" + nota2 +
                ", nota3=" + nota3 +
                ", promedio=" + promedio +


                '}';
    }
}
