
import java.io.File;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Will
 */
public class CodigoDiagramaDeFlujo {

    private JLabel objeto;
    private String Codigo = "";
    private boolean x = true;
    private ArrayList nombres = new ArrayList();

    public CodigoDiagramaDeFlujo() {
    }

    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String Codigo) {
        this.Codigo = Codigo;
    }

    public JLabel getObjeto() {
        return objeto;
    }

    public void setObjeto(JLabel objeto) {
        this.objeto = objeto;
    }

    public void Inicio() {
        x = true;
        this.Codigo += "#include <string> \n" + "#include <iostream> \n"
                + "using namespace std;\n\n"
                + "int main() {\n";
    }

    public void Datos() {
        System.out.println(objeto.getName());
        try {
            if (objeto.getName().contains("ª")) {
                String x = objeto.getName().split("ª")[0];
                String[] S = x.split(";");
                String variables = "";
                for (int i = 0; i < S.length; i++) {
                    String[] X = S[i].split(" ");
                    String tipo = X[0];
                    String nombre = X[1];
                    nombres.add(nombre);
                    variables +="    " + tipo + " " + nombre + ";\n";
                }
                Codigo += variables;
            } else {
                String x = objeto.getName();
                String[] S = x.split(";");
                String variables = "";
                for (int i = 0; i < S.length; i++) {
                    String[] X = S[i].split(" ");
                    String tipo = X[0];
                    String nombre = X[1];
                    nombres.add(nombre);
                    variables += "    " + tipo + " " + nombre + ";\n";
                }
                Codigo += variables;
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error en los datos!", "Error", 0);
            x = false;
        }
    }

    public void Final() {
        Codigo += "return 0;\n};\n";
    }

    public void Generar() {
        if (objeto.getDisplayedMnemonic() == 2) {
            Datos();
        } else if (objeto.getDisplayedMnemonic() == 3) {
            Proceso();
        } else if (objeto.getDisplayedMnemonic() == 4) {
            Desicion();
        } else if (objeto.getDisplayedMnemonic() == 5) {
            While();
        } else if (objeto.getDisplayedMnemonic() == 6) {
            Impresion();
        }
    }

    public void Desicion() {
        String X = objeto.getName();
        String decision ="    " + "if " + X + "\n    " + "{\n";
        Codigo += decision;
    }

    public boolean isX() {
        return x;
    }

    public void setX(boolean x) {
        this.x = x;
    }

    public void Impresion() {
        if (objeto.getName().contains("_")) {
            String[] Y = objeto.getName().toString().split("_");
            String X = "    cout";
            for (int i = 0; i < Y.length; i++) {
                if (i == Y.length - 1) {
                    if (nombres.contains(Y[i])) {
                        X += "<<" + Y[i] + "<<endl;\n";
                    } else {
                        if (Y[i].length() > 1) {
                            X += "<<" + "“" + Y[i] + "”<<endl;\n";
                        } else {
                            X += "”<<endl;\n";
                        }
                    }
                } else {
                    if (nombres.contains(Y[i])) {
                        X += "<<" + Y[i];
                    } else {
                        if (Y[i].length() > 1) {
                            X += "<<" + "“" + Y[i] + "”";
                        } else {
                            X += "";
                        }
                    }
                }
            }
            Codigo += X;
            System.out.println(X);
        } else {
            String X ="    cout<<“" + objeto.getName() + "”<<endl;\n";
            Codigo += X;
        }
    }

    public void While() {
        String x = objeto.getName();
        String mientras ="    " + "while" + x + "\n" + "    {\n";
        Codigo += mientras;
    }

    public void Proceso() {
        String X ="    " + objeto.getName() + ";\n";
        Codigo += X;
    }
}
