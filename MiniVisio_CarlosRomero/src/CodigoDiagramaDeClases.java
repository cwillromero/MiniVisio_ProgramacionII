
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class CodigoDiagramaDeClases {

    private JTree arbol;
    private String Codigo="";

    public CodigoDiagramaDeClases() {
    }

    public CodigoDiagramaDeClases(JTree arbol) {
        this.arbol = arbol;
    }

    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String Codigo) {
        this.Codigo = Codigo;
    }

    public void Inicio() {
        DefaultTreeModel model = (DefaultTreeModel) arbol.getModel();
        DefaultMutableTreeNode root = (DefaultMutableTreeNode) model.getRoot();
        String inicio = "class " + model.getRoot().toString() + " \n"
                + "{ \n";
        this.Codigo += inicio;
    }

    public void Atributos() {
        DefaultTreeModel model = (DefaultTreeModel) arbol.getModel();
        DefaultMutableTreeNode root = (DefaultMutableTreeNode) model.getRoot();
        String atributo;
        String ListaAtributos="";
        for (int i = 0; i < root.getChildCount(); i++) {
            if (root.getChildAt(i).toString().equals("<Atributos>")) {
                for (int j = 0; j < root.getChildAt(i).getChildCount(); j++) {
                    atributo = "" + (DefaultMutableTreeNode) root.getChildAt(i).getChildAt(j);
                    String[] componentes=atributo.split("_");
                    String alcance=componentes[0];
                    String tipo=componentes[1];
                    String identificador=componentes[2];
                    ListaAtributos+=alcance+" "+tipo+" "+identificador+";\n";
                }
            }
        }
        Codigo+=ListaAtributos;
    }
    
    public void Final(){
        Codigo+="};\n\n\n";
    }
    
    public void Metodos(){
        
    }
}
