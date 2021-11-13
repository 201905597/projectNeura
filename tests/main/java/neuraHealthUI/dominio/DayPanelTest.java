package main.java.neuraHealthUI.dominio;

import neuraHealthUI.dominio.DayPanel;
import neuraHealthUI.dominio.MonthPanel;
import neuraHealthUI.ui.JVentana;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class DayPanelTest {
    private static DayPanel dp;
    private static MonthPanel mp;
    private static JVentana ventana;

    @BeforeClass
    public static void testInitialization(){
        ventana = new JVentana();
        mp = new MonthPanel("Noviembre",2021,ventana,"Animo");
        dp = new DayPanel(11,mp,2021,ventana,"Animo");
        dp.setColoreado(1);
    }

    @Test
    public void isColoreado() {
        assertEquals(true, dp.isColoreado());

    }
}