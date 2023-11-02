package br.informacoes.permissoes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import br.arquitetura.Data;
import br.arquitetura.Relatorio;
import br.arquitetura.Sis;

public class PermissoesRel2 {

	private final String arquivo = Sis.getCaminhoDadosPdf() + "[SQL2]-" + Data.getDataHoraArquivo() + ".pdf";
	private final Document document = new Document();
	private final Relatorio relatorio = new Relatorio();
	private final String titulo = "RELATÓRIO DE PERMISSÕES 2";
	private PdfWriter writer = null;

	public PermissoesRel2(List<Permissoes2> listPermissoes2) {

		try {
			writer = PdfWriter.getInstance(document, new FileOutputStream(arquivo));
			relatorio.criarRelatorio(writer, document, titulo);

			for (Permissoes2 permissoes2 : listPermissoes2) {
				document.add(new Paragraph("Principal_Name: " + permissoes2.getPrincipal_Name()));
				document.add(new Paragraph("Login_Name: " + permissoes2.getLogin_Name()));
				document.add(new Paragraph("DatabaseName: " + permissoes2.getDatabaseName()));
				document.add(new Paragraph("Permission_Name: " + permissoes2.getPermission_Name()));
				document.add(new Paragraph("Permission_Type: " + permissoes2.getPermission_Type()));

				document.add(new Paragraph("\n"));
			}

		} catch (DocumentException | FileNotFoundException exception) {
			exception.printStackTrace();
		}
		relatorio.getRodape(writer, document);
		document.close();
		Sis.abrirDiretorio(Sis.getCaminhoDadosPdf());
		relatorio.retornarRelatorio(arquivo, false);
	}

	public File retornarRelatorio(boolean abrirArquivo) {
		return relatorio.retornarRelatorio(arquivo, abrirArquivo);
	}
}
