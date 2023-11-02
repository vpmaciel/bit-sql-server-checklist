package br.arquitetura;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JOptionPane;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import br.arquitetura.gui.Msg;

public class Relatorio {

	public void criarRelatorio(PdfWriter writer, Document document, String titulo) {
		try {

			document.open();
			document.setPageSize(PageSize.A4);

			Font font = new Font(Font.FontFamily.COURIER, 20, Font.BOLD);
			Paragraph paragraph = new Paragraph(titulo, font);

			paragraph.setAlignment(Element.ALIGN_CENTER);
			document.add(paragraph);
			document.add(new Paragraph("\n"));
			// document.newPage();
			paragraph.setAlignment(Element.ALIGN_JUSTIFIED);

		} catch (Exception exception) {
			exception.printStackTrace();
			JOptionPane.showMessageDialog(null, "Erro ao criar o relatório", "Erro", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void getRodape(PdfWriter writer, Document document) {
		try {
			Font font = new Font(Font.FontFamily.COURIER, 8, Font.BOLD);
			Rectangle page = document.getPageSize();
			PdfPTable foot = new PdfPTable(1);
			PdfPCell cell = new PdfPCell(new Paragraph(Data.getDataHoraCompleta(), font));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
			cell.setBorder(0);
			foot.addCell(cell);
			foot.setTotalWidth(page.getWidth() - document.leftMargin() - document.rightMargin());
			foot.writeSelectedRows(0, -1, document.leftMargin(), document.bottomMargin(), writer.getDirectContent());
		} catch (Exception exception) {
			exception.printStackTrace();
			JOptionPane.showMessageDialog(null, "Erro ao definir o rodapé do relatório", "Erro",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public File retornarRelatorio(String caminho, boolean abrirArquivo) {

		File arquivo = new File(caminho);

		if (!abrirArquivo) {
			return arquivo;
		}

		try {
			Desktop.getDesktop().open(arquivo);
		} catch (FileNotFoundException fileNotFoundException) {
			fileNotFoundException.printStackTrace();
			Msg.erroArquivoNaoEncontrado();
		} catch (IOException ioException) {
			ioException.printStackTrace();
			Msg.erroAbrirArquivo();
		} catch (Exception exception) {
			exception.printStackTrace();
			Msg.erroArquivo();
		}

		return arquivo;
	}
}
