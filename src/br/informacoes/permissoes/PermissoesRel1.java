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

public class PermissoesRel1 {

	private final String arquivo = Sis.getCaminhoDadosPdf() + "[SQL1]-" + Data.getDataHoraArquivo() + ".pdf";
	private final Document document = new Document();
	private final Relatorio relatorio = new Relatorio();
	private final String titulo = "RELATÓRIO DE PERMISSÕES 1";
	private PdfWriter writer = null;

	public PermissoesRel1(List<Permissoes1> listPermissoes1) {

		try {
			writer = PdfWriter.getInstance(document, new FileOutputStream(arquivo));
			relatorio.criarRelatorio(writer, document, titulo);

			for (Permissoes1 permissoes1 : listPermissoes1) {
				document.add(new Paragraph("Login_Name: " + permissoes1.getLogin_Name()));
				document.add(new Paragraph("Login_Type: " + permissoes1.getLogin_Type()));
				document.add(new Paragraph("Permission_Name: " + permissoes1.getPermission_Name()));
				document.add(new Paragraph("Permission_Type: " + permissoes1.getPermission_Type()));
				document.add(new Paragraph("create_date: " + permissoes1.getCreate_date()));
				document.add(new Paragraph("is_disabled: " + permissoes1.getIs_disabled()));
				document.add(new Paragraph("modify_date: " + permissoes1.getModify_date()));
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
