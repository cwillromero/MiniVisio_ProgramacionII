
import java.util.ArrayList;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Will
 */
public class CodigoUML {
    String Codigo;
    ArrayList<JTree> clases=new ArrayList();
    int I=0;
    
    public CodigoUML() {
    }
    
    public void Arboles(ArrayList ar) {
        this.clases=ar;
    }

    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String Codigo) {
        this.Codigo = Codigo;
    }

    public int getI() {
        return I;
    }

    public void setI(int I) {
        this.I = I;
    }

    
    public void Atributos(){
        String x;
        DefaultTreeModel model =(DefaultTreeModel) clases.get(I).getModel();
            DefaultMutableTreeNode root = (DefaultMutableTreeNode) model.getRoot();
            for (int i = 0; i < root.getChildCount(); i++) { 
                if (root.getChildAt(i).toString().equals("<Atributos>")) {
                    x=""+(DefaultMutableTreeNode) root.getChildAt(i).getChildAt(0);
                }
            }
        String tipo,variable;
        String[] Split=Codigo.split(";");
        for (int i = 0; i < Split.length; i++) {
            String[] S=Split[i].split(":");
            variable=S[0];tipo=S[1];
            this.Codigo+=tipo+" "+variable+";";
        }
    }
    
    public void InicioNormal() {
        DefaultTreeModel model =(DefaultTreeModel) clases.get(I).getModel();
        DefaultMutableTreeNode root = (DefaultMutableTreeNode) model.getRoot();
        Codigo+= "#include <iostream>\n" +
                 "using namespace std;\n"+
                 "class "+model.getRoot()+" {\n"
                 ;
    }
    
    public void Final(){
        this.Codigo="}";
    }
}
