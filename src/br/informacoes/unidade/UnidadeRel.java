package br.informacoes.unidade;

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

public class UnidadeRel {

	private final String arquivo = Sis.getCaminhoDadosPdf() + "[sql-server-unidades]-" + Data.getDataHoraArquivo()
			+ ".pdf";
	private final Document document = new Document();
	private final Relatorio relatorio = new Relatorio();
	private final String titulo = "INFORMAÇÕES NAS UNIDADES";
	private PdfWriter writer = null;

	public UnidadeRel(List<Unidade> listUnidade) {

		try {
			writer = PdfWriter.getInstance(document, new FileOutputStream(arquivo));
			relatorio.criarRelatorio(writer, document, titulo);

			for (Unidade unidade : listUnidade) {
				document.add(new Paragraph("Category: " + unidade.getCategory()));
				document.add(new Paragraph("Information: " + unidade.getInformation()));
				document.add(new Paragraph("vs_logical_volume_name: " + unidade.getVs_logical_volume_name()));
				document.add(new Paragraph("volume_mount_point: " + unidade.getVs_volume_mount_point()));
				document.add(new Paragraph("vs_file_system_type: " + unidade.getVs_file_system_type()));
				document.add(new Paragraph("TotalSpace_MB: " + unidade.getTotalSpace_MB()));
				document.add(new Paragraph("FreeSpace_MB: " + unidade.getFreeSpace_MB()));
				document.add(new Paragraph("vs_is_compresse(): " + unidade.getVs_is_compresse()));
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
