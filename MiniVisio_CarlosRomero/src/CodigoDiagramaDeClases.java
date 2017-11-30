
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class CodigoDiagramaDeClases {

    private JTree arbol;
    private String Codigo = "";

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
        String atributo;
        int x = 0;
        for (int i = 0; i < root.getChildCount(); i++) {
            if (root.getChildAt(i).toString().equals("<Atributos>")) {
                for (int j = 0; j < root.getChildAt(i).getChildCount(); j++) {
                    atributo = "" + (DefaultMutableTreeNode) root.getChildAt(i).getChildAt(j);
                    String[] componentes = atributo.split(" ");
                    String tipo = componentes[1];
                    if (tipo.equals("string")) {
                        x++;
                    }
                }
            }
        }
        String inicio;
        if (x != 0) {
            inicio = "#include <string> \n" + "#include <iostream> \n"
                    + "using namespace std;\n\n" + "class " + model.getRoot().toString() + " \n"
                    + "{ \n";
        } else {
            inicio = "#include <iostream> \n"
                    + "using namespace std;\n\n" + "class " + model.getRoot().toString() + " \n"
                    + "{ \n";
        }
        this.Codigo += inicio;
    }

    public void Atributos() {
        DefaultTreeModel model = (DefaultTreeModel) arbol.getModel();
        DefaultMutableTreeNode root = (DefaultMutableTreeNode) model.getRoot();
        String atributo;
        String ListaAtributos = "";
        String publico="   public:\n";
        String privado ="   private:\n";
        String protecte="   protected:\n";
        String demas="";
        for (int i = 0; i < root.getChildCount(); i++) {
            if (root.getChildAt(i).toString().equals("<Atributos>")) {
                for (int j = 0; j < root.getChildAt(i).getChildCount(); j++) {
                    atributo = "" + (DefaultMutableTreeNode) root.getChildAt(i).getChildAt(j);
                    String[] componentes = atributo.split(" ");
                    String alcance = componentes[0];
                    String tipo = componentes[1];
                    String identificador = componentes[2];
                    if(alcance.equals("public:")){
                        publico+="      "+tipo + " " + identificador + ";\n";
                    }else if(alcance.equals("private:")){
                        privado+="      "+tipo + " " + identificador + ";\n";
                    }else if(alcance.equals("protected:")){
                        protecte+="      "+tipo + " " + identificador + ";\n";
                    }else{
                        demas+="   "+alcance+" "+tipo + " " + identificador + ";\n";
                    }
                }
            }
        }
        ListaAtributos = publico+privado+protecte;
        Codigo += ListaAtributos;
    }

    public void Final() {
        Codigo += "};\n_____________________________________________________\n";
    }

    public void Constructor() {
        DefaultTreeModel model = (DefaultTreeModel) arbol.getModel();
        DefaultMutableTreeNode root = (DefaultMutableTreeNode) model.getRoot();
        String atributo;
        String ListaAtributos = "";
        for (int i = 0; i < root.getChildCount(); i++) {
            if (root.getChildAt(i).toString().equals("<Atributos>")) {
                for (int j = 0; j < root.getChildAt(i).getChildCount(); j++) {
                    if (j == root.getChildAt(i).getChildCount() - 1) {
                        atributo = "" + (DefaultMutableTreeNode) root.getChildAt(i).getChildAt(j);
                        String[] componentes = atributo.split(" ");
                        String alcance = componentes[0];
                        String tipo = componentes[1];
                        String identificador = componentes[2];
                        ListaAtributos += tipo + " " + identificador;
                    } else {
                        atributo = "" + (DefaultMutableTreeNode) root.getChildAt(i).getChildAt(j);
                        String[] componentes = atributo.split(" ");
                        String alcance = componentes[0];
                        String tipo = componentes[1];
                        String identificador = componentes[2];
                        ListaAtributos += tipo + " " + identificador + ", ";
                    }
                }
            }
        }
        String NombreConstructor = "   public:\n"
                + "      //Constructor\n" + "      "
                + model.getRoot().toString()
                + "(" + ListaAtributos + ");\n";
        String ConstructorporDefecto = "   public:\n"
                + "      //Constructor por Defecto\n" + "      "
                + model.getRoot().toString()
                + "(" + " " + ");\n";
        Codigo += NombreConstructor + ConstructorporDefecto;
    }
}
