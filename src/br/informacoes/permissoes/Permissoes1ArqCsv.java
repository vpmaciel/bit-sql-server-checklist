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

public class Permissoes1ArqCsv {

	private final String arquivo = Sis.getCaminhoDadosCsv() + "[sql-server-permissoes1]-" + Data.getDataHoraArquivo()
			+ ".csv";

	private BufferedWriter bufferedWriter = null;
	private final String CSV_SEPARATOR = String.format("%s", ";");
	private File file;

	public Permissoes1ArqCsv(final List<Permissoes1> listPermissoes1) {
		try {
			bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(arquivo), "UTF-8"));

			StringBuffer cabecalho = new StringBuffer();
			cabecalho.append("Create_date");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("Is_disabled");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("Login_Name");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("Login_Type");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("Modify_date");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("Permission_Name");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("Permission_Type");
			bufferedWriter.write(cabecalho.toString());
			bufferedWriter.newLine();

			for (Permissoes1 permissoes1 : listPermissoes1) {
				StringBuffer linha = new StringBuffer();
				linha.append(permissoes1.getCreate_date());
				linha.append(CSV_SEPARATOR);
				linha.append(permissoes1.getIs_disabled());
				linha.append(CSV_SEPARATOR);
				linha.append(permissoes1.getLogin_Name());
				linha.append(CSV_SEPARATOR);
				linha.append(permissoes1.getLogin_Type());
				linha.append(CSV_SEPARATOR);
				linha.append(permissoes1.getModify_date());
				linha.append(CSV_SEPARATOR);
				linha.append(permissoes1.getPermission_Name());
				linha.append(CSV_SEPARATOR);
				linha.append(permissoes1.getPermission_Type());
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
