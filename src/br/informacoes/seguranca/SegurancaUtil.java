package br.informacoes.seguranca;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.ibatis.common.jdbc.ScriptRunner;

import br.arquitetura.util.Arquivo;
import br.database.SQLServer;

public class SegurancaUtil {

	public static void lerArquivo() throws ClassNotFoundException, SQLException {

		String aSQLScriptFilePath = "stp_checklist_seguranca.sql";
		Connection conn = SQLServer.getConexao();
		try {
			Arquivo.gravarArquivo();

			// Initialize object for ScripRunner
			ScriptRunner sr = new ScriptRunner(conn, false, false);

			// Give the input file to Reader
			Reader reader = new BufferedReader(new FileReader(aSQLScriptFilePath));

			// Exctute script
			sr.runScript(reader);

			conn.close();

		} catch (Exception e) {
			System.err.println("Failed to Execute" + aSQLScriptFilePath + " The error is " + e.getMessage());
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
}
