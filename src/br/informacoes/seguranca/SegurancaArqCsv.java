package br.informacoes.seguranca;

import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

import br.arquitetura.Data;
import br.arquitetura.Sis;
import br.arquitetura.gui.Msg;

public class SegurancaArqCsv {

	private final String arquivo = Sis.getCaminhoDadosCsv() + "[sql-server-seguranca]-" + Data.getDataHoraArquivo()
			+ ".csv";

	private BufferedWriter bufferedWriter = null;
	private final String CSV_SEPARATOR = String.format("%s", ";");
	private File file;

	public SegurancaArqCsv(final List<Seguranca> listSeguranca) {
		try {
			bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(arquivo), "UTF-8"));

			StringBuffer cabecalho = new StringBuffer();
			cabecalho.append("Codigo");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("Avaliacao");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("Categoria");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("DescricaoDoProblema");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("DetalhamentoDaVerificacao");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("ResultadosDaValidacao");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("SugestaoDeCorrecao");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("UrlDeReferencia");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("Verificado");
			bufferedWriter.write(cabecalho.toString());
			bufferedWriter.newLine();

			for (Seguranca seguranca : listSeguranca) {
				StringBuffer linha = new StringBuffer();
				linha.append(seguranca.getCodigo());
				linha.append(CSV_SEPARATOR);
				linha.append(seguranca.getAvaliacao());
				linha.append(CSV_SEPARATOR);
				linha.append(seguranca.getCategoria());
				linha.append(CSV_SEPARATOR);
				linha.append(seguranca.getDescricaoDoProblema());
				linha.append(CSV_SEPARATOR);
				linha.append(seguranca.getDetalhamentoDaVerificacao());
				linha.append(CSV_SEPARATOR);
				linha.append(seguranca.getResultadosDaValidacao());
				linha.append(CSV_SEPARATOR);
				linha.append(seguranca.getSugestaoDeCorrecao());
				linha.append(CSV_SEPARATOR);
				linha.append(seguranca.getUrlDeReferencia());
				linha.append(CSV_SEPARATOR);
				linha.append(seguranca.getVerificado());
				bufferedWriter.write(linha.toString());
				bufferedWriter.newLine();
			}
			bufferedWriter.flush();
			bufferedWriter.close();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			Msg.erroCodificacao();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			Msg.erroArquivoNaoEncontrado();
		} catch (IOException e) {
			e.printStackTrace();
			Msg.erroAbrirArquivo();
		}
	}

	public File retornarArquivo(boolean abrirArquivo) {

		try {
			Sis.abrirDiretorio(Sis.getCaminhoDadosCsv());
			file = new File(arquivo);
			if (abrirArquivo) {
				Desktop.getDesktop().open(file);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return file;
	}
}
