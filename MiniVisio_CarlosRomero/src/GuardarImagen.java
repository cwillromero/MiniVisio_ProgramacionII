
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Will
 */
public class GuardarImagen {

    private JPanel base = new JPanel();
    private File archivo = null;
    BufferedImage Imagen;
    int seleccion;

    public GuardarImagen() {
    }

    public JPanel getBase() {
        return base;
    }

    public void setBase(JPanel Base) {
        this.base = Base;
    }

    public void CrearImagen() {
        //ESTO CREA LA IMAGEN SOLO CON EL TAMAÑO DEL PANEL
        this.Imagen = new BufferedImage(base.getWidth(), base.getHeight(), BufferedImage.TYPE_INT_RGB);
        //ESTO TOMA LOS COMPONENTES DE LA IMAGEN(EN CUANTO AL DIAGRAMA DE CLASES, PARA
        //PODER VER SUS COMPONENTE SE NECESITA QUE EL ARBOL ESTE DESPLEGAD0)
        Graphics G = Imagen.getGraphics();
        base.paintAll(G);
    }

    public boolean EscribirImagen() {
        archivo = null;
        JFileChooser jfc = new JFileChooser();
        //Extensiones
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Imagen PNG", "PNG");
        jfc.addChoosableFileFilter(filtro);
        FileNameExtensionFilter filtro1 = new FileNameExtensionFilter("Imagen JPG-JPEG", "JPEG");
        jfc.addChoosableFileFilter(filtro1);
        FileNameExtensionFilter filtro2 = new FileNameExtensionFilter("Imagen GIF", "GIF");
        jfc.addChoosableFileFilter(filtro2);
        FileNameExtensionFilter filtro3 = new FileNameExtensionFilter("Archivo PDF", "PDF");
        jfc.addChoosableFileFilter(filtro3);
        //FORMATO ELEGIDO
        String formato;
        //ESTO TOMA LA RUTA ELEGIDA POR EL USUARIO
        seleccion = jfc.showSaveDialog(base);
        if (jfc.getFileFilter().getDescription().equals("Imagen PNG")) {
            formato = "PNG";
        }else if (jfc.getFileFilter().getDescription().equals("Imagen JPG-JPEG")) {
            formato = "JPEG";
        }else if (jfc.getFileFilter().getDescription().equals("Imagen GIF")) {
            formato = "GIF";
        }else if (jfc.getFileFilter().getDescription().equals("Archivo PDF")) {
            JOptionPane.showMessageDialog(null, "Todavía en Desarrollo", "En Desarrollo", 0);
            return false;
        }else{
            formato = "PNG";
        }
        archivo = new File(jfc.getSelectedFile().getPath() + "." + formato);
        try {
            //ESTE GUARDA LA IMAGEN
            ImageIO.write(Imagen, formato, archivo);
            JOptionPane.showMessageDialog(null, "Archivo " + formato + " guardado correctamente.", "Guardar", 3);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar Archivo-", "Error", 0);
        }
        return true;
    }
}
