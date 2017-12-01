
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.swing.JLabel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Will
 */
public class AdministracionDiaramaDeFlujo {

    private ArrayList<JLabel> componentes = new ArrayList();
    private File archivo = null;

    public AdministracionDiaramaDeFlujo(String path) {
        archivo = new File(path);
    }

    public AdministracionDiaramaDeFlujo() {
    }

    
    public void CargarArchivo() {
        try {
            componentes = new ArrayList();
            JLabel temp;
            if (archivo.exists()) {
                FileInputStream entrada = new FileInputStream(archivo);
                ObjectInputStream objeto = new ObjectInputStream(entrada);
                try {
                    while ((temp = (JLabel) objeto.readObject()) != null) {
                        componentes.add(temp);
                    }
                } catch (Exception e) {
                }
                objeto.close();
                entrada.close();
            }
        } catch (Exception e) {
        }
    }

    public void escribirArchivo() throws IOException {
        FileOutputStream fw = null;
        ObjectOutputStream bw = null;
        try {
            fw = new FileOutputStream(archivo);
            bw = new ObjectOutputStream(fw);
            for (JLabel T : componentes) {
                bw.writeObject(T);
            }
            bw.flush();
        } catch (Exception e) {

        }
        bw.close();
        fw.close();
    }
}
