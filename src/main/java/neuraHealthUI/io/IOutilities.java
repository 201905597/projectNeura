package neuraHealthUI.io;

import javax.swing.JTextArea;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class IOutilities
{
    public static void TextFromFile(JTextArea txtArea)
    {
        try
        {
            //String path = "ficheros/InfoSaludMental.txt";
            //File file = new File(path);
            //File file = new File(IOutilities.class.getClassLoader().getResource("InfoSaludMental.txt").getFile());
            InputStream inputStream = IOutilities.class.getClassLoader().getResourceAsStream("InfoSaludMental.txt");
            //Files.copy(inputStream, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
            //String fileName = file1.getName();
            //File compiledJar = file1.getParentFile();
            //File file = new File(compiledJar.getParentFile(), fileName);

            File file = new File("temporal.txt");
            Files.copy(inputStream, file.toPath(), StandardCopyOption.REPLACE_EXISTING);

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