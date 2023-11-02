package br.informacoes.sql;

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

public final class SQLRel {

	private final String arquivo = Sis.getCaminhoDadosPdf() + "[sql-server-bancos]-" + Data.getDataHoraArquivo()
			+ ".pdf";
	private final Document document = new Document();
	private final Relatorio relatorio = new Relatorio();
	private final String titulo = "SQL SERVER DATA DESDE A ÚLTIMA REINICIALIZAÇÃO";
	private PdfWriter writer = null;

	public SQLRel(List<SQL_1> listSQL_1, List<SQL_2> listSQL_2, List<SQL_3> listSQL_3) {

		try {
			writer = PdfWriter.getInstance(document, new FileOutputStream(arquivo));
			relatorio.criarRelatorio(writer, document, titulo);

			for (SQL_1 sql_1 : listSQL_1) {
				document.add(new Paragraph("DAYS: " + sql_1.getDays()));
				document.add(new Paragraph("HOURS: " + sql_1.getHours()));
				document.add(new Paragraph("MINUTES: " + sql_1.getMinutes()));
				document.add(new Paragraph("\n"));
			}
			for (SQL_2 sql_2 : listSQL_2) {
				document.add(new Paragraph("SQLServerStartTime: " + sql_2.getSQLServerStartTime()));
				document.add(new Paragraph("\n"));
			}
			for (SQL_3 sql_3 : listSQL_3) {
				document.add(new Paragraph("Sqlserver_start_time: " + sql_3.getSqlserver_start_time()));
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
