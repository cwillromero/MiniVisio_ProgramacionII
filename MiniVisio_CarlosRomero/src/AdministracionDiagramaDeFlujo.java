
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.swing.JLabel;

public class AdministracionDiagramaDeFlujo {

    private ArrayList<JLabel> componentes = new ArrayList();
    private File archivo = null;

    public AdministracionDiagramaDeFlujo(String path) {
        archivo = new File(path);
    }

    public AdministracionDiagramaDeFlujo() {
    }

    public File getArchivo() {
        return archivo;
    }

    public void setArchivo(File archivo) {
        this.archivo = archivo;
    }

    public ArrayList<JLabel> getComponentes() {
        return componentes;
    }

    public void setComponentes(ArrayList<JLabel> componentes) {
        this.componentes = componentes;
    }

    public void AgregarComponente(JLabel componente) {
        componentes.add(componente);
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

    public void EscribirArchivo() throws IOException {
        FileOutputStream fw = null;
        ObjectOutputStream bw = null;
        try {
            fw = new FileOutputStream(archivo);
            bw = new ObjectOutputStream(fw);
            for (Object t : componentes) {
                bw.writeObject(t);
            }
            bw.flush();
        } catch (Exception e) {

        }
        bw.close();
        fw.close();
    }
}
