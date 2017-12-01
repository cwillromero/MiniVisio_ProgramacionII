
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
    JFileChooser jfc = new JFileChooser();
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

    public void CrearImagen(){
        //ESTO CREA LA IMAGEN SOLO CON EL TAMAÑO DEL PANEL
        this.Imagen=new BufferedImage(base.getWidth(), base.getHeight(), BufferedImage.TYPE_INT_RGB);
        //ESTO TOMA LOS COMPONENTES DE LA IMAGEN(EN CUANTO AL DIAGRAMA DE CLASES, PARA
        //PODER VER SUS COMPONENTE SE NECESITA QUE EL ARBOL ESTE DESPLEGAD0)
        Graphics G = Imagen.getGraphics();
        base.paintAll(G);
    }
    
    public void EscribirImagen(){
        //ESTO TOMA LA RUTA ELEGIDA POR EL USUARIO
        seleccion = jfc.showSaveDialog(base);
        //LA LISTA DE LOS FORMATOS EN QUE SE PUEDE GUARDAR
        String[] S={"JPEG","JPG","PNG","BMP","GIF","PDF"};
        //ESTE PONE LA EXTENSION
        String formato=(String)JOptionPane.showInputDialog(null, "Seleccione un Formato:", "Formato", 3, null, S, S[0]);
        archivo = new File(jfc.getSelectedFile().getPath()+"."+formato);
        try {
            //ESTE GUARDA LA IMAGEN
            ImageIO.write(Imagen, formato, archivo);
            JOptionPane.showMessageDialog(null, "Archivo "+formato+" guardado correctamente.", "Guardar", 3);
        } catch (IOException e) {
            System.out.println("Error de escritura");
        }
//        String formato;
//        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Imagen PNG", "PNG");
//        jfc.addChoosableFileFilter(filtro);
//        FileNameExtensionFilter filtro1 = new FileNameExtensionFilter("Imagen JPG-JPEG", "JPEG");
//        jfc.addChoosableFileFilter(filtro1);
//        FileNameExtensionFilter filtro2 = new FileNameExtensionFilter("Imagen BPM", "BPM");
//        jfc.addChoosableFileFilter(filtro2);
//        FileNameExtensionFilter filtro3 = new FileNameExtensionFilter("Archivo PDF", "PDF");
//        jfc.addChoosableFileFilter(filtro3);
//        //ESTO TOMA LA RUTA ELEGIDA POR EL USUARIO
//        seleccion = jfc.showSaveDialog(base);
//        //IMPLEMENTA LAS EXTENSIONES
//        if (jfc.getFileFilter().getDescription().equals("Imagen PNG")) {
//            formato = "PNG";
//        }else if (jfc.getFileFilter().getDescription().equals("Imagen JPEG")) {
//            formato = "JPEG";
//        }else if (jfc.getFileFilter().getDescription().equals("Imagen BPM")) {
//            formato = "BPM";
//        }else if (jfc.getFileFilter().getDescription().equals("Archivo PDF")) {
//            formato = "PDF";
//        }else{
//            formato = "PNG";
//        }
    }
}
