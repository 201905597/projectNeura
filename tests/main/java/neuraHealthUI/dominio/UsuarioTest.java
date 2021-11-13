package main.java.neuraHealthUI.dominio;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class UsuarioTest {

    private static HashMap<String,String> hmFechaEmoc;
    private static Usuario usuario;
    @BeforeClass
    public static void testInitialization(){

        usuario=new Usuario("555","Pepe");
        hmFechaEmoc= new HashMap<String,String>();
        hmFechaEmoc.put("01Octubre2021", "Feliz");
        usuario.setHmFechaEmocion(hmFechaEmoc);

    }


    @Test
    public void gethmFechaEmoc() {
        assertEquals(hmFechaEmoc, usuario.gethmFechaEmoc());
    }
}