package br.informacoes.tabela;

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

public class TabelaArqCsv {

	private final String arquivo = Sis.getCaminhoDadosCsv() + "[sql-server-tabelas]-" + Data.getDataHoraArquivo()
			+ ".csv";

	private BufferedWriter bufferedWriter = null;
	private final String CSV_SEPARATOR = String.format("%s", ";");
	private File file;

	public TabelaArqCsv(final List<Tabela> listTabela) {
		try {
			bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(arquivo), "UTF-8"));

			StringBuffer cabecalho = new StringBuffer();
			cabecalho.append("Db_name");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("Obj_name");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("Total_rows");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("Total_space");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("Unused_space");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("Used_space");
			bufferedWriter.write(cabecalho.toString());
			bufferedWriter.newLine();

			for (Tabela tabela : listTabela) {
				StringBuffer linha = new StringBuffer();
				linha.append(tabela.getDb_name());
				linha.append(CSV_SEPARATOR);
				linha.append(tabela.getObj_name());
				linha.append(CSV_SEPARATOR);
				linha.append(tabela.getTotal_rows());
				linha.append(CSV_SEPARATOR);
				linha.append(tabela.getTotal_space());
				linha.append(CSV_SEPARATOR);
				linha.append(tabela.getUnused_space());
				linha.append(CSV_SEPARATOR);
				linha.append(tabela.getUsed_space());
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
