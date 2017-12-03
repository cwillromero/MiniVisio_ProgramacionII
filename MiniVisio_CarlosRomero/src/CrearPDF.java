
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import javafx.scene.paint.Color;

/**
 *
 * @author Will
 */
public class CrearPDF {

    public CrearPDF() {
    }
    private Font fuente1 = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.UNDERLINE);
    private Font fuente2 = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL);
    private Font fuente3 = new Font(Font.FontFamily.COURIER, 10, Font.ITALIC);

    public Paragraph getTitulo(String Texto) {
        Paragraph p = new Paragraph();
        Chunk c = new Chunk();
        p.setAlignment(Element.ALIGN_CENTER);
        c.append(Texto);
        c.setFont(fuente1);
        c.setUnderline(1, -1);
        p.add(c);
        return p;
    }

    public Paragraph getCuerpo(String Texto) {
        Paragraph p = new Paragraph();
        Chunk c = new Chunk();
        p.setAlignment(Element.ALIGN_JUSTIFIED);
        c.append(Texto);
        c.setFont(fuente2);
        p.add(c);
        return p;
    }

}
