package main.java.neuraHealthUI.dominio;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashSet;

import static org.junit.Assert.*;

public class PsicologoTest {

    private static HashSet<Usuario> pacientes;
    private static Psicologo psicologo;
    @BeforeClass
    public static void testInitialization(){


        psicologo=new Psicologo("777","Marina","NeuraClinic");
        pacientes = new HashSet<Usuario>();

        Usuario p1 = new Usuario("222", "Jose");
        Usuario p2 = new Usuario("000", "Clara");
        Usuario p3 = new Usuario("111", "Teresa" );
        pacientes.add(p1);
        pacientes.add(p2);
        pacientes.add(p3);
        psicologo.setPacientes(pacientes);

    }

    @Test
    public void getPacientes() {
        assertEquals(pacientes, psicologo.getPacientes());
    }
}