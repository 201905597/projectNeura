package main.java.neuraHealthUI.dominio;

public class Psicologo
{
    private String id;
    private String nombre;
    private String centro;

    public Psicologo(String id, String nombre, String centro) {
        this.id=id;
        this.nombre=nombre;
        this.centro=centro;

    }


    public String getId(){
        return id;
    }
    public String getNombre(){
        return nombre;
    }
    public String getCentro(){
        return centro;
    }
}
