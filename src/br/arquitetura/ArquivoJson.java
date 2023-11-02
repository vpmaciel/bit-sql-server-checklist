package br.arquitetura;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import br.arquitetura.gui.Msg;
import br.informacoes.seguranca.Seguranca;

public class ArquivoJson<Tipo> {

	private String arquivo;
	private List<Tipo> listTipo = new LinkedList<Tipo>();
	private Tipo tipo;
	private File file;

	public ArquivoJson(Tipo tipo, String nome) {
		this.tipo = tipo;
		arquivo = Sis.getCaminhoDadosJson() + "[" + nome + "]-" + Data.getDataHoraArquivo() + ".json";
	}

	public List<Tipo> getListTipo() {
		return listTipo;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void gravarArquivo(Collection<?> collection) {
		Gson gson = new Gson();
		String json = gson.toJson(collection);
		try {
			FileWriter writer = new FileWriter(arquivo);
			writer.write(json);
			writer.close();

		} catch (Exception e) {
			e.printStackTrace();
			Msg.erroArquivo();
		}
	}

	public Collection<?> lerArquivo() {
		List<?> listTipo = null;
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(arquivo));
			Type listType = new TypeToken<ArrayList<Seguranca>>() {
			}.getType();
			listTipo = new Gson().fromJson(bufferedReader, listType);
		} catch (Exception e) {
			e.printStackTrace();
			Msg.erroArquivo();
		}
		return listTipo;
	}

	public String toJson(Collection<?> collection) {
		Gson gson = new Gson();
		String json = gson.toJson(collection);
		return json;
	}

	public void setListTipo(List<Tipo> listTipo) {
		this.listTipo = listTipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
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