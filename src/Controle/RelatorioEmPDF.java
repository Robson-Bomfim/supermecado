package Controle;

import Modelo.ModeloVenda;
import com.itextpdf.text.BaseColor;
import java.io.FileOutputStream;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import dao.Conectar;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;

public class RelatorioEmPDF {

    ModeloVenda modeloVenda = new ModeloVenda();
    private static Connection conect;
    private static PreparedStatement pst = null;
    private static ResultSet rs = null;

    public RelatorioEmPDF() {
        this.conect = new Conectar().openConnection();
    }

    public void GerarRelatorioPdf(ModeloVenda modeloVenda) throws FileNotFoundException {
        boolean gerado = false;
        // criação do documento
        Document document = null;
        //OutputStream Output = null;
        try {
            document = new Document(PageSize.A4, 30, 20, 20, 30);

            PdfWriter.getInstance(document, new FileOutputStream("C:\\Users\\Dell\\Documents\\Nova pasta\\Relatório de vendas.pdf"));
            document.open();

            Font f = new Font(FontFamily.COURIER, 20, Font.BOLD);
            Font font = new Font(FontFamily.COURIER, 16, Font.BOLD);

            // adicionando um parágrafo no documento
            Paragraph p1 = new Paragraph("Relatório de vendas", f);
            p1.setAlignment(Element.ALIGN_CENTER);
            document.add(p1);

            PdfPTable table = new PdfPTable(5);
            PdfPCell header = new PdfPCell(new Paragraph("Vendas realizadas", font));
            header.setHorizontalAlignment(Element.ALIGN_CENTER);
            header.setBackgroundColor(new BaseColor(100, 150, 200));
            header.setColspan(5);
            table.addCell(header);
            table.addCell("Código");
            table.addCell("Data");
            table.addCell("Valor");
            table.addCell("Cliente");
            table.addCell("Usuário responsável");

            conect = new Conectar().openConnection();
            SimpleDateFormat formataData = new SimpleDateFormat("yyyy-MM-dd");
            String sql = "select * from venda join cliente on venda.id_cliente = cliente.id_cliente join usuario on usuario.id_usuario = venda.id_usuario  where venda.data_venda = '" + formataData.format(modeloVenda.getData()) + "'";

            pst = conect.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                gerado = true;
                table.addCell(rs.getString("id_venda"));
                table.addCell(rs.getString("data_venda"));
                table.addCell(rs.getString("valor_venda"));
                table.addCell(rs.getString("nome_cliente"));
                table.addCell(rs.getString("nome_login"));
            }
            document.add(table);
            document.close();
            JOptionPane.showMessageDialog(null, "Relatório gerado na pasta documentos!");
            if (!gerado) {
                JOptionPane.showMessageDialog(null, "Erro na impressão do relatório!");
            }
        } catch (DocumentException de) {
            System.err.println(de.getMessage());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
}
