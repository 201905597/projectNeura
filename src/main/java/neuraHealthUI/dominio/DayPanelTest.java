package main.java.neuraHealthUI.dominio;

import neuraHealthUI.dominio.DayPanel;
import neuraHealthUI.dominio.MonthPanel;
import neuraHealthUI.ui.JVentana;

import java.time.Month;

import static org.junit.jupiter.api.Assertions.*;

class DayPanelTest {

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        JVentana ventana = new JVentana();
        MonthPanel monthPanel = new MonthPanel();
        DayPanel dayPanel = new DayPanel(4,monthPanel,2021,ventana,"Animo");

    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }

    @org.junit.jupiter.api.Test
    void isColoreadoOK() {
        boolean coloreado = dayPanel.isColoreado();
    }
}