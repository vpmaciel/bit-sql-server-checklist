package br.informacoes.tabela;

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

public class TabelaRel {

	private final String arquivo = Sis.getCaminhoDadosPdf() + "[sql-server-tabelas]-" + Data.getDataHoraArquivo()
			+ ".pdf";
	private final Document document = new Document();
	private final Relatorio relatorio = new Relatorio();
	private final String titulo = "INFORMAÇÕES NAS TABELAS";
	private PdfWriter writer = null;

	public TabelaRel(List<Tabela> listTabela) {

		try {
			writer = PdfWriter.getInstance(document, new FileOutputStream(arquivo));
			relatorio.criarRelatorio(writer, document, titulo);

			for (Tabela tabela : listTabela) {
				document.add(new Paragraph("db_name: " + tabela.getDb_name()));
				document.add(new Paragraph("obj_name: " + tabela.getObj_name()));
				document.add(new Paragraph("total_rows: " + tabela.getTotal_rows()));
				document.add(new Paragraph("total_space: " + tabela.getTotal_space()));
				document.add(new Paragraph("used_space: " + tabela.getUsed_space()));
				document.add(new Paragraph("unused_space: " + tabela.getUnused_space()));
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
