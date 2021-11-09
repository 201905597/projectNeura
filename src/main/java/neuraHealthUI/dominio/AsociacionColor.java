package main.java.neuraHealthUI.dominio;

import java.awt.Color;
import java.util.HashMap;

public class AsociacionColor
{
    private String asociacion; //lo que el color representa, ánimo o hábito (triste, feliz, hecho, no hecho...)
    private Color color;
    private HashMap<String,Color> asociacionColores;

    public AsociacionColor(String asociacion)
    {
        this.setAsociacion(asociacion);
        asociacionColores = new HashMap<String,Color>();
        asociacionColores.put("Feliz",new Color(255,153,0));
        asociacionColores.put("Triste",new Color(51,153,255));
        asociacionColores.put("Estresad@",new Color(255,51,51));
        asociacionColores.put("Cansad@",new Color(102,20,153));
        asociacionColores.put("Productiv@",new Color(0,204,0));
        asociacionColores.put("Hecho", new Color(68,175,118));
        asociacionColores.put("No hecho", new Color(253,65,65));
        this.setColor();
    }

    public void setColor()
    {
        this.color = asociacionColores.get(asociacion);
    }

    public Color getColor()
    {
        return color;
    }

    public void setAsociacion(String asociacion)
    {
        this.asociacion = asociacion;
    }

    public String getAsociacion()
    {
        return asociacion;
    }
}
