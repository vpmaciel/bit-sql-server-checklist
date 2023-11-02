package br.informacoes.banco;

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

public class BancoArqCsv {

	private final String arquivo = Sis.getCaminhoDadosCsv() + "[sql-server-bancos]-" + Data.getDataHoraArquivo()
			+ ".csv";

	private BufferedWriter bufferedWriter = null;
	private final String CSV_SEPARATOR = String.format("%s", ";");
	private File file;

	public BancoArqCsv(final List<Banco> listBanco) {
		try {
			bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(arquivo), "UTF-8"));

			StringBuffer cabecalho = new StringBuffer();
			cabecalho.append("DB_Freespace");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("Dbname");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("DBsize");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("Dbstatus");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("File_Size_MB");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("FreeSpace_MB");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("Log_File_Size_MB");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("Log_Free_Space_MB");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("Log_Space_Used_MB");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("Recovery_Model");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("Space_Used_MB");
			bufferedWriter.write(cabecalho.toString());
			bufferedWriter.newLine();

			for (Banco banco : listBanco) {
				StringBuffer linha = new StringBuffer();
				linha.append(banco.getDB_Freespace());
				linha.append(CSV_SEPARATOR);
				linha.append(banco.getDbname());
				linha.append(CSV_SEPARATOR);
				linha.append(banco.getDBsize());
				linha.append(CSV_SEPARATOR);
				linha.append(banco.getDbstatus());
				linha.append(CSV_SEPARATOR);
				linha.append(banco.getFile_Size_MB());
				linha.append(CSV_SEPARATOR);
				linha.append(banco.getFree_Space_MB());
				linha.append(CSV_SEPARATOR);
				linha.append(banco.getLog_File_Size_MB());
				linha.append(CSV_SEPARATOR);
				linha.append(banco.getLog_Free_Space_MB());
				linha.append(CSV_SEPARATOR);
				linha.append(banco.getLog_Space_Used_MB());
				linha.append(CSV_SEPARATOR);
				linha.append(banco.getRecovery_Model());
				linha.append(CSV_SEPARATOR);
				linha.append(banco.getSpace_Used_MB());
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
