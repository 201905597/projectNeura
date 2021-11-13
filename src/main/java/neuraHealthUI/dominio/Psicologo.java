package main.java.neuraHealthUI.dominio;

import javax.lang.model.type.ArrayType;
import java.util.ArrayList;

public class Psicologo
{
    private String id;
    private String nombre;
    private String centro;
    private ArrayList<Usuario> pacientes;

    public Psicologo(String id, String nombre, String centro) {
        this.id=id;
        this.nombre=nombre;
        this.centro=centro;

    }

    public void setPacientes(ArrayList<Usuario> pacientes)
    {
        this.pacientes = pacientes;
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
    public ArrayList<Usuario> getPacientes()
    {
        return pacientes;
    }

}
