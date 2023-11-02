package br.arquitetura.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.JOptionPane;

import br.database.SQLServer;

public class Arquivo {
	private static File file = new File("configuracao.txt");
	private static final String path = "configuracao.txt";
	private static String texto = "";

	public static final String lerArquivo() {

		FileReader fileReader;
		try {
			fileReader = new FileReader(file);
			BufferedReader reader = new BufferedReader(fileReader);
			String data = null;
			while ((data = reader.readLine()) != null) {
				System.out.println(data);
				texto += data;
			}
			fileReader.close();
			reader.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return texto;
	}

	public static void gravarArquivo() {
		File file = new File(path);

		BufferedWriter writer;
		try {
			writer = new BufferedWriter(new FileWriter(file));
			writer.write("use " + SQLServer.getBanco());
			System.out.println("use " + SQLServer.getBanco());
			writer.flush();
			// Fechando conexão e escrita do arquivo.
			writer.close();
			JOptionPane.showMessageDialog(null, file.getAbsolutePath());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
}