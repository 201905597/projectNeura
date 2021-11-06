package neuraHealthUI.dominio;

import neuraHealthUI.ui.AnimoDialog;
import neuraHealthUI.ui.JVentana;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.BorderFactory;

import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Color;

import java.util.ArrayList;
import java.util.HashMap;

public class MonthPanel extends JPanel
{
    private ArrayList<DayPanel> diasArrayList;
    private String nombreMes;
    private int anio;
    private JVentana ventanaOwner;
    //org
    String meses[] = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
    int diasMeses[] = {31,28,31,30,31,30,31,31,30,31,30,31};
    HashMap<String,Integer> mesDias = new HashMap<String,Integer>();

    public MonthPanel(String nombreMes, int anio, JVentana ventanaOwner) //add attribute tipoMes
    {
        this.setLayout(new BorderLayout());
        this.diasArrayList = new ArrayList<DayPanel>();
        this.setAnio(anio);
        this.setNombreMes(nombreMes);
        this.ventanaOwner = ventanaOwner;
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK,4));
        //org
        for (int i=0;i<12;i++)
        {
            mesDias.put(meses[i],diasMeses[i]);
        }
        if (nombreMes == "Febrero")
        {
            if ((anio % 4 == 0) && ((anio % 100 != 0) || (anio % 400 == 0)))
                mesDias.replace("Febrero",28,29);
        }

        //PANEL NORTE
        JPanel pnlNorte = new JPanel();
        JLabel lblMes = new JLabel(nombreMes + " ");
        JLabel lblAnio = new JLabel(String.valueOf(anio));
        pnlNorte.add(lblMes);
        pnlNorte.add(lblAnio);
        this.add(pnlNorte, BorderLayout.NORTH);

        //PANEL CENTRO
        JPanel pnlCentro = new JPanel();
        pnlCentro.setLayout(new GridLayout(7,5));

        for (int i = 1;i<mesDias.get(nombreMes)+1;i++)
        {
            diasArrayList.add(new DayPanel(i,MonthPanel.this,(MonthPanel.this).getAnio(), ventanaOwner));
            pnlCentro.add(diasArrayList.get(i-1)); //day panel
        }

        this.add(pnlCentro, BorderLayout.CENTER);


        this.setVisible(false);
    }

    public void setNombreMes(String nombreMes)
    {
        this.nombreMes = nombreMes;
    }

    public void setAnio(int anio)
    {
        this.anio = anio;
    }

    public int getAnio()
    {
        return anio;
    }

    public String getNombreMes()
    {
        return nombreMes;
    }

    public String getMesYAnio()
    {
        return nombreMes + String.valueOf(anio);
    }

    //añadido 2 oct
    public ArrayList<DayPanel> getDayArray()
    {
        return diasArrayList;
    }


}