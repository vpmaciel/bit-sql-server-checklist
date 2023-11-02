package br.informacoes.unidade;

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

public class UnidadeArqCsv {

	private final String arquivo = Sis.getCaminhoDadosCsv() + "[sql-server-unidades]-" + Data.getDataHoraArquivo()
			+ ".csv";

	private BufferedWriter bufferedWriter = null;
	private final String CSV_SEPARATOR = String.format("%s", ";");
	private File file;

	public UnidadeArqCsv(final List<Unidade> listUnidade) {
		try {
			bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(arquivo), "UTF-8"));

			StringBuffer cabecalho = new StringBuffer();
			cabecalho.append("Category");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("FreeSpace_MB");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("Information");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("TotalSpace_MB");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("Vs_file_system_type");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("Vs_is_compresse");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("Vs_logical_volume_name");
			cabecalho.append(CSV_SEPARATOR);
			cabecalho.append("Vs_volume_mount_point");
			bufferedWriter.write(cabecalho.toString());
			bufferedWriter.newLine();

			for (Unidade unidade : listUnidade) {
				StringBuffer linha = new StringBuffer();
				linha.append(unidade.getCategory());
				linha.append(CSV_SEPARATOR);
				linha.append(unidade.getFreeSpace_MB());
				linha.append(CSV_SEPARATOR);
				linha.append(unidade.getInformation());
				linha.append(CSV_SEPARATOR);
				linha.append(unidade.getTotalSpace_MB());
				linha.append(CSV_SEPARATOR);
				linha.append(unidade.getVs_file_system_type());
				linha.append(CSV_SEPARATOR);
				linha.append(unidade.getVs_is_compresse());
				linha.append(CSV_SEPARATOR);
				linha.append(unidade.getVs_logical_volume_name());
				linha.append(CSV_SEPARATOR);
				linha.append(unidade.getVs_volume_mount_point());
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
