package neuraHealthUI.dominio;

import neuraHealthUI.ui.ColoresDialog;
import neuraHealthUI.ui.JVentana;


import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.awt.Color;

public class DayPanel extends JPanel
{
    JVentana ventanaOwner;
    MonthPanel mes;
    String tipoDia;
    String diaDosDigitos;
    JButton btnDia;
    String asociacion;
    int coloreado;

    public DayPanel(int numero, MonthPanel mes, int anio, JVentana ventanaOwner, String tipoDia)
    {
        this.ventanaOwner = ventanaOwner;
        this.setTipoDia(tipoDia);
        this.mes = mes;
        this.setAsociacion(" ");
        coloreado = 0;

        //si solo tiene un digito, añadir un 0 delante
        if (numero<10)
            diaDosDigitos = "0" + String.valueOf(numero);
        else
            diaDosDigitos = String.valueOf(numero);

        btnDia = new JButton(diaDosDigitos);

        btnDia.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                //AnimoDialog animoDlg = new AnimoDialog(DayPanel.this.getFecha(),ventanaOwner,true, DayPanel.this,ventanaOwner.getIdConectado());
                ColoresDialog coloresDlg = new ColoresDialog(DayPanel.this.getFecha(),ventanaOwner,true, DayPanel.this,ventanaOwner.getIdConectado(), tipoDia);
            }
        });
        this.add(btnDia);

        this.setVisible(true);
    }

    public void setBtnColor(Color color)
    {
        this.btnDia.setBackground(color);
        DayPanel.this.updateUI();
        coloreado = 1;
    }

    public void setTipoDia(String tipoDia)
    {
        this.tipoDia = tipoDia;
    }

    public String getFecha()
    {
        return diaDosDigitos + mes.getMesYAnio();
    }

    public void setAsociacion(String asociacion)
    {
        this.asociacion = asociacion;
    }
    public String getAsociacion()
    {
        return asociacion;
    }

    public String getDiaDosDigitos()
    {
        return diaDosDigitos;
    }

    public boolean isColoreado()
    {
        if (coloreado == 1)
            return true;
        else
            return false;
    }

    public String getMesYAnio()
    {
        return mes.getMesYAnio();
    }
}