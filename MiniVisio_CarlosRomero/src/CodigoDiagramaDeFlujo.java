
import java.io.File;
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
public class CodigoDiagramaDeFlujo {

    private JLabel objeto;
    private String Codigo = "";

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
        this.Codigo += "#include <string> \n" + "#include <iostream> \n"
                + "using namespace std;\n\n"
                + "int main() {\n";
    }
    
    public void Datos(){
        if(objeto.getDisplayedMnemonic()==2){
            System.out.println("Cosas");
        }
    }
    
    public void Final() {
        Codigo += "return 0;\n};\n";
    }
}
