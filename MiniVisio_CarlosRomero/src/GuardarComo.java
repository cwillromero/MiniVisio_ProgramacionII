
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class GuardarComo {

    private JPanel base = new JPanel();
    private File archivo = null;
    private BufferedImage Imagen;
    private String codigo;
    private int seleccion;
    private JFileChooser jfc = new JFileChooser();

    public GuardarComo() {
    }

    public JPanel getBase() {
        return base;
    }

    public void setBase(JPanel Base) {
        this.base = Base;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
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
        try {
            archivo = null;
            jfc = new JFileChooser();
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
            } else if (jfc.getFileFilter().getDescription().equals("Imagen JPG-JPEG")) {
                formato = "JPEG";
            } else if (jfc.getFileFilter().getDescription().equals("Imagen GIF")) {
                formato = "GIF";
            } else if (jfc.getFileFilter().getDescription().equals("Archivo PDF")) {
                GenerarPDF();
                return false;
            } else {
                formato = "PNG";
            }
            if (seleccion == JFileChooser.APPROVE_OPTION) {
                archivo = new File(jfc.getSelectedFile().getPath() + "." + formato);
                try {
                    //ESTE GUARDA LA IMAGEN
                    ImageIO.write(Imagen, formato, archivo);
                    JOptionPane.showMessageDialog(null, "Archivo " + formato + " guardado correctamente.", "Guardar", 1);
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "Error al guardar Archivo.", "Error", 0);
                }
            }
        } catch (Exception e) {
        }
        return true;
    }

    public void GenerarPDF() {
        String Path = jfc.getSelectedFile().getPath();
        try {
            try {
                ImageIO.write(Imagen, "png", new File(Path + "1" + ".png"));
            } catch (IOException ex) {
                Logger.getLogger(base.getName()).log(Level.SEVERE, null, ex);
            }
            Image imagen = Image.getInstance(Path + "1" + ".png");
            imagen.scaleAbsolute(500, 352);
            imagen.setAlignment(Element.ALIGN_CENTER);
            FileOutputStream archivo = new FileOutputStream(Path + ".PDF");
            Document doc = new Document();
            PdfWriter.getInstance(doc, archivo);
            CrearPDF pdf=new CrearPDF();
            doc.open();
            doc.setPageSize(PageSize.LETTER);
            doc.add(pdf.getTitulo("Proyecto Programación II  -  Mini Visio"));
            doc.add(new Paragraph("                                                  Carlos Wilfredo Romero Maradiaga \n"));
            doc.add(imagen);
            doc.add(pdf.getCuerpo("\nCódigo Generado:\n"));
            doc.add(pdf.getCuerpo(codigo));
            doc.close();
            JOptionPane.showMessageDialog(null, "Archivo " + "PDF" + " guardado correctamente.", "Guardar", 1);
        } catch (Exception e) {
        }
    }
}
