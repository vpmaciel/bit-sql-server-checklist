package br.informacoes.seguranca;

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

public final class SegurancaRel {

	private final String arquivo = Sis.getCaminhoDadosPdf() + "[seguranca]-" + Data.getDataHoraArquivo() + ".pdf";
	private final Document document = new Document();
	private final Relatorio relatorio = new Relatorio();
	private final String titulo = "RELATÓRIO DE SEGURANÇA";
	private PdfWriter writer = null;

	public SegurancaRel(List<Seguranca> listSeguranca) {

		try {
			writer = PdfWriter.getInstance(document, new FileOutputStream(arquivo));
			relatorio.criarRelatorio(writer, document, titulo);

			for (Seguranca seguranca : listSeguranca) {
				document.add(new Paragraph("CÓDIGO: " + seguranca.getCodigo()));
				document.add(new Paragraph("CATEGORIA: " + seguranca.getCategoria()));
				document.add(new Paragraph("O QUE É VERIFICADO: " + seguranca.getVerificado()));
				document.add(new Paragraph("AVALIAÇÃO: " + seguranca.getAvaliacao()));
				document.add(new Paragraph("DESCRIÇÃO DO PROBLEMA: " + seguranca.getDescricaoDoProblema()));
				document.add(new Paragraph("DETALHAMENTO DA VERIFICAÇÃO: " + seguranca.getDetalhamentoDaVerificacao()));
				document.add(new Paragraph("SUGESTÃO DE CORREÇÃO: " + seguranca.getSugestaoDeCorrecao()));
				document.add(new Paragraph("RESULTADOS DA VALIDAÇÃO: " + seguranca.getResultadosDaValidacao()));
				document.add(new Paragraph("URL DE REFERÊNCIA: " + seguranca.getUrlDeReferencia()));
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
