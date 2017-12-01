
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTree;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Will
 */
public class AdministracionDiagramaClases {

    private ArrayList<Object> clases = new ArrayList();
    private File archivo = null;

    public AdministracionDiagramaClases(String path) {
        archivo = new File(path);
    }

    public AdministracionDiagramaClases() {
    }

    public void AgregarClase(JTree Clase) {
        clases.add(Clase);
    }

    public ArrayList<Object> getClases() {
        return clases;
    }

    public void setClases(ArrayList<Object> clases) {
        this.clases = clases;
    }

    public File getArchivo() {
        return archivo;
    }

    public void setArchivo(File archivo) {
        this.archivo = archivo;
    }

    public void CargarArchivo() {
        try {
            clases = new ArrayList();
            JTree arbol;
            if (archivo.exists()) {
                FileInputStream entrada = new FileInputStream(archivo);
                ObjectInputStream objeto = new ObjectInputStream(entrada);
                try {
                    while ((arbol = (JTree) objeto.readObject()) != null) {
                        clases.add(arbol);
                    }
                } catch (Exception e) {
                }
                objeto.close();
                entrada.close();
            }
        } catch (Exception e) {
        }
    }

    public void EscribirArchivo() throws IOException {
        FileOutputStream fw = null;
        ObjectOutputStream bw = null;
        try {
            fw = new FileOutputStream(archivo);
            bw = new ObjectOutputStream(fw);
            for (Object T : clases) {
                bw.writeObject(T);
            }
            bw.flush();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocurrió un error durante el guardado!", "Error", 0);
        }
        bw.close();
        fw.close();
    }

}
