package br.informacoes.banco;

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

public class BancoRel {

	private final String arquivo = Sis.getCaminhoDadosPdf() + "[BANCO]-" + Data.getDataHoraArquivo() + ".pdf";
	private final Document document = new Document();
	private final Relatorio relatorio = new Relatorio();
	private final String titulo = "INFORMAÇÕES DOS BANCOS DE DADOS";
	private PdfWriter writer = null;

	public BancoRel(List<Banco> listBanco) {

		try {
			writer = PdfWriter.getInstance(document, new FileOutputStream(arquivo));
			relatorio.criarRelatorio(writer, document, titulo);

			for (Banco banco : listBanco) {
				document.add(new Paragraph("Dbname: " + banco.getDbname()));
				document.add(new Paragraph("DB_Freespace: " + banco.getDB_Freespace()));
				document.add(new Paragraph("DBsize: " + banco.getDBsize()));
				document.add(new Paragraph("dbstatus: " + banco.getDbstatus()));
				document.add(new Paragraph("file_Size_MB: " + banco.getFile_Size_MB()));
				document.add(new Paragraph("FreeSpace_MB: " + banco.getFree_Space_MB()));
				document.add(new Paragraph("Log_File_Size_MB: " + banco.getLog_File_Size_MB()));
				document.add(new Paragraph("log_Free_Space_MB: " + banco.getLog_Free_Space_MB()));
				document.add(new Paragraph("log_Space_Used_MB: " + banco.getLog_Space_Used_MB()));
				document.add(new Paragraph("Recovery_Model: " + banco.getRecovery_Model()));
				document.add(new Paragraph("Space_Used_MB: " + banco.getSpace_Used_MB()));
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
