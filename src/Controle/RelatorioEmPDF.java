package Controle;

import Modelo.ModeloVenda;
import Visao.PesquisaVendas;
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
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;

public class RelatorioEmPDF {

    private static Connection conect;
    private static PreparedStatement pst = null;
    private static ResultSet rs = null;

    public RelatorioEmPDF() {
        this.conect = new Conectar().openConnection();
    }

    public void GerarRelatorioPdf(ModeloVenda modeloVenda) throws FileNotFoundException, IOException, SQLException {
        boolean gerado = false;
        // criação do documento
        Document document;
        try {
            document = new Document(PageSize.A4, 30, 20, 20, 30);

            PdfWriter.getInstance(document, new FileOutputStream("C:\\Users\\Public\\Relatório de vendas.pdf"));
            document.open();

            Font f = new Font(FontFamily.COURIER, 20, Font.BOLD);
            Font font = new Font(FontFamily.COURIER, 16, Font.BOLD);

            // adicionando um parágrafo no documento
            Paragraph p1 = new Paragraph("Relatório de vendas", f);
            p1.setAlignment(Element.ALIGN_CENTER);
            document.add(p1);

            SimpleDateFormat formataData = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/YYYY");
            PdfPTable table = new PdfPTable(5);
            PdfPCell header = new PdfPCell(new Paragraph("Vendas realizadas no dia " + formatDate.format(modeloVenda.getData()) + "", font));
            header.setHorizontalAlignment(Element.ALIGN_CENTER);
            header.setBackgroundColor(new BaseColor(100, 150, 200));
            header.setColspan(5);
            table.addCell(header);
            table.addCell("Código");
            table.addCell("Data");
            table.addCell("Valor total");
            table.addCell("Cliente");
            table.addCell("Usuário responsável");

            conect = new Conectar().openConnection();

            String sql = "select venda.id_venda as 'Código',strftime('%d/%m/%Y',venda.data_venda)as 'Data',venda.valor_venda as 'Valor total', cliente.nome_cliente as 'Cliente', usuario.nome_login as 'Usuario responsável' from venda join cliente on venda.id_cliente = cliente.id_cliente join usuario on usuario.id_usuario = venda.id_usuario  where venda.data_venda = '" + formataData.format(modeloVenda.getData()) + "'";

            pst = conect.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                gerado = true;
                table.addCell(rs.getString(1));
                table.addCell(rs.getString(2));
                table.addCell(rs.getString(3));
                table.addCell(rs.getString(4));
                table.addCell(rs.getString(5));
            }

            document.add(table);

            document.close();

            JOptionPane.showMessageDialog(null, "Relatório gerado neste caminho 'C:\\Users\\Public\\Relatório de vendas.pdf!'");
            if (!gerado) {
                JOptionPane.showMessageDialog(null, "Erro na impressão do relatório!");
            }
        } catch (DocumentException de) {
            System.err.println(de.getMessage());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        conect.close();
    }

    public void relatorioPdf(ModeloVenda modVenda, ModeloVenda modelVenda) throws FileNotFoundException, DocumentException, SQLException {
        conect = new Conectar().openConnection();

        boolean gerado = false;
        // criação do documento
        Document document;
        //OutputStream Output = null;
        document = new Document(PageSize.A4, 30, 20, 20, 30);

        PdfWriter.getInstance(document, new FileOutputStream("C:\\Users\\Public\\Relatório de vendas.pdf"));
        document.open();

        Font f = new Font(FontFamily.COURIER, 20, Font.BOLD);
        Font font = new Font(FontFamily.COURIER, 12, Font.BOLD);

        // adicionando um parágrafo no documento
        Paragraph p1 = new Paragraph("Relatório de vendas", f);
        p1.setAlignment(Element.ALIGN_CENTER);
        document.add(p1);

        SimpleDateFormat formataData = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/YYYY");
        PdfPTable table = new PdfPTable(5);
        PdfPCell header = new PdfPCell(new Paragraph("Vendas realizadas entre o período " + formatDate.format(modVenda.getData()) + " " + "à" + " " + formatDate.format(modelVenda.getData()) + "", font));
        header.setHorizontalAlignment(Element.ALIGN_CENTER);
        header.setBackgroundColor(new BaseColor(100, 150, 200));
        header.setColspan(5);
        table.addCell(header); 
        table.addCell("Código");
        table.addCell("Data");
        table.addCell("Valor total");
        table.addCell("Cliente");
        table.addCell("Usuário responsável");

        String periodo = "select venda.id_venda as 'Código',strftime('%d/%m/%Y',venda.data_venda)as 'Data',venda.valor_venda as 'Valor total', cliente.nome_cliente as 'Cliente', usuario.nome_login as 'Usuario responsável' from venda join cliente on venda.id_cliente = cliente.id_cliente join usuario on usuario.id_usuario = venda.id_usuario  where venda.data_venda between '" + formataData.format(modVenda.getData()) + "' and '" + formataData.format(modelVenda.getData()) + "'";
        pst = conect.prepareStatement(periodo);
        rs = pst.executeQuery();
        while (rs.next()) {
            gerado = true;
            table.addCell(rs.getString(1));
            table.addCell(rs.getString(2));
            table.addCell(rs.getString(3));
            table.addCell(rs.getString(4));
            table.addCell(rs.getString(5));
        }
        document.add(table);
        document.close();
        JOptionPane.showMessageDialog(null, "Relatório gerado neste caminho 'C:\\Users\\Public\\Relatório de vendas.pdf!'");
        if (!gerado) {
            JOptionPane.showMessageDialog(null, "Erro na impressão do relatório!");
        }
        conect.close();
    }
}
