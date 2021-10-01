package neuraHealthUI.io;

import javax.swing.JTextArea;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.File;
import java.nio.charset.StandardCharsets;

public class IOutilities
{
    public static void TextFromFile(JTextArea txtArea)
    {
        try
        {
            String path = "ficheros/InfoSaludMental.txt";
            File file = new File(path);
            FileReader fr = new FileReader(file, StandardCharsets.UTF_8);
            BufferedReader br = new BufferedReader(fr);
            String lineaActual = null;
            StringBuilder contentBuilder = new StringBuilder();
            while ((lineaActual = br.readLine()) != null)
            {
                contentBuilder.append(lineaActual).append("\n");
            }
            txtArea.append(contentBuilder.toString());
        }
        catch(IOException ioe)
        {
            ioe.printStackTrace();
        }
    }
}