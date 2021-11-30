package main.java.neuraHealthUI.dominio;

import java.io.Serializable;
import java.util.HashMap;

public class Usuario  implements Serializable {
    private String id;
    private String nombre;
    private HashMap<String,String> hmFechaEmocion;
    private HashMap<String,String> hmFechaHabEst;

    public Usuario(String id, String nombre) {
        this.id=id;
        this.nombre=nombre;
    }

    public void setHmFechaEmocion(HashMap<String,String> hmFechaEmocion)
    {
        this.hmFechaEmocion = hmFechaEmocion;
    }

    public void setHmFechaHabEst(HashMap<String,String> hmFechaHabEst)
    {
        this.hmFechaHabEst = hmFechaHabEst;
    }

    public String getId(){
        return id;
    }

    public String getNombre(){
        return nombre;
    }

    public HashMap<String, String> gethmFechaEmoc()
    {
        return hmFechaEmocion;
    }
    public HashMap<String, String> getHmFechaHabEst()
    {
        return hmFechaHabEst;
    }
}
