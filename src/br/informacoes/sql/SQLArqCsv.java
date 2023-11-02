package br.informacoes.sql;

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

public class SQLArqCsv {

	private final String arquivo = Sis.getCaminhoDadosCsv() + "[sql-server]-" + Data.getDataHoraArquivo() + ".csv";

	private BufferedWriter bufferedWriter = null;
	private final String CSV_SEPARATOR = String.format("%s", ";");
	private File file;

	public SQLArqCsv(final List<SQL_1> listSQL_1, final List<SQL_2> listSQL_2, final List<SQL_3> listSQL_3) {
		try {
			bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(arquivo), "UTF-8"));

			StringBuffer cabecalho = new StringBuffer();
			cabecalho.append("Days");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("Hours");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("Minutes");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("SQLServerStartTime");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("Sqlserver_start_time");
			bufferedWriter.write(cabecalho.toString());
			bufferedWriter.newLine();

			for (SQL_1 sql_1 : listSQL_1) {
				StringBuffer linha = new StringBuffer();
				linha.append(sql_1.getDays());
				linha.append(CSV_SEPARATOR);
				linha.append(sql_1.getHours());
				linha.append(CSV_SEPARATOR);
				linha.append(sql_1.getMinutes());
				bufferedWriter.write(linha.toString());
				// bufferedWriter.newLine();
			}

			for (SQL_2 sql_2 : listSQL_2) {
				StringBuffer linha = new StringBuffer();
				linha.append(CSV_SEPARATOR);
				linha.append(sql_2.getSQLServerStartTime());
				bufferedWriter.write(linha.toString());
				// bufferedWriter.newLine();
			}

			for (SQL_3 sql_3 : listSQL_3) {
				StringBuffer linha = new StringBuffer();
				linha.append(CSV_SEPARATOR);
				linha.append(sql_3.getSqlserver_start_time());
				bufferedWriter.write(linha.toString());
				// bufferedWriter.newLine();
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
