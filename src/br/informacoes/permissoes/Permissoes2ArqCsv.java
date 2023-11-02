package br.informacoes.permissoes;

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

public class Permissoes2ArqCsv {

	private final String arquivo = Sis.getCaminhoDadosCsv() + "[sql-server-permissoes2]-" + Data.getDataHoraArquivo()
			+ ".csv";

	private BufferedWriter bufferedWriter = null;
	private final String CSV_SEPARATOR = String.format("%s", ";");
	private File file;

	public Permissoes2ArqCsv(final List<Permissoes2> listPermissoes2) {
		try {
			bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(arquivo), "UTF-8"));

			StringBuffer cabecalho = new StringBuffer();
			cabecalho.append("DatabaseName");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("Login_Name");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("Permission_Name");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("Permission_Type");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("Principal_Name");
			bufferedWriter.write(cabecalho.toString());
			bufferedWriter.newLine();

			for (Permissoes2 permissoes2 : listPermissoes2) {
				StringBuffer linha = new StringBuffer();
				linha.append(permissoes2.getDatabaseName());
				linha.append(CSV_SEPARATOR);
				linha.append(permissoes2.getLogin_Name());
				linha.append(CSV_SEPARATOR);
				linha.append(permissoes2.getPermission_Name());
				linha.append(CSV_SEPARATOR);
				linha.append(permissoes2.getPermission_Type());
				linha.append(CSV_SEPARATOR);
				linha.append(permissoes2.getPrincipal_Name());
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
