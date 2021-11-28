package main.java.neuraHealthUI.dominio;

import java.io.Serializable;
import java.util.HashSet;

public class Psicologo implements Serializable
{
    private String id;
    private String nombre;
    private String centro;
    private HashSet<Usuario> pacientes;

    public Psicologo(String id, String nombre, String centro) {
        this.id=id;
        this.nombre=nombre;
        this.centro=centro;

    }

    public void setPacientes(HashSet<Usuario> pacientes)
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
    public HashSet<Usuario> getPacientes()
    {
        return pacientes;
    }

}
