package br.sistema.main;

import java.awt.Frame;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.apache.commons.io.IOUtils;

//import com.ibatis.common.jdbc.ScriptRunner;

import br.arquitetura.gui.Msg;
import br.database.SQLServer;

public class SqlServerMonitorExec {

	public static void main(String[] args) {

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());			

			MainJan mainJan = new MainJan();
			mainJan.setState(Frame.NORMAL);
			mainJan.setVisible(true);
			mainJan.setLocationRelativeTo(null);
			mainJan.setResizable(false);
			mainJan.toFront();

		} catch (ClassNotFoundException | IllegalAccessException | InstantiationException
				| UnsupportedLookAndFeelException exception) {
			exception.printStackTrace();
			Msg.erroLookAndFeel();
		} catch (Exception exception) {
			exception.printStackTrace();
			Msg.erroGeral();
		} finally {

		}
	}

	public static void lerArquivo() throws ClassNotFoundException, SQLException {

		String aSQLScriptFilePath = "Informa o tempo desde o último reinício.sql";
		Statement stmt = null;
		Connection conn = SQLServer.getConexao();
		try {
			// Initialize object for ScripRunner
			// ScriptRunner sr = new ScriptRunner(conn, false, false);

			// Give the input file to Reader
			// Reader reader = new BufferedReader(new FileReader(aSQLScriptFilePath));

			// Exctute script
			// sr.runScript(reader);

			// String query = IOUtils.toString(new FileReader("./Lista todos os bancos de
			// dados com informações detalhadas sobre o espaço usado.sql"));
			// String query = IOUtils.toString(new FileReader("./Lista o tamanho de todas as
			// tabelas, de todos os bancos por ordem de espaço total.sql"));
			String query = IOUtils.toString(new FileReader(
					"./Lista o tamanho de todas as tabelas, de todos os bancos por ordem de espaço total.sql"));

			/*
			 * stmt = conn.prepareStatement(query); stmt.execute(); stmt.close();
			 * 
			 * conn.close();
			 */

			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				// System.out.println(rs.getString("sqlserver_start_time"));
				// System.out.println(rs.getString("SQLServerStartTime"));
				// System.out.println(rs.getString("Days"));
				// System.out.println(rs.getString("Hours"));
				// System.out.println(rs.getString("Minutes"));

				/*
				 * 
				 * System.out.print(rs.getString("dbstatus")+"\t");
				 * System.out.print(rs.getString("Recovery_Model")+"\t"+rs.getString("DBsize")+
				 * "\t"); System.out.print(rs.getString("file_Size_MB")+"\t"+rs.getString(
				 * "Space_Used_MB")+"\t");
				 * System.out.print(rs.getString("Free_Space_MB")+"\t"+rs.getString(
				 * "Log_File_Size_MB")+"\t");
				 * System.out.print(rs.getString("log_Space_Used_MB")+"\t"+rs.getString(
				 * "log_Free_Space_MB")+"\t");
				 * System.out.print(rs.getString("DB_Freespace")+"\t\t"+rs.getString("Dbname"));
				 * System.out.println();
				 */

				System.out.print(rs.getString("db_name") + "\t");
				System.out.print(rs.getString("obj_name") + "\t" + rs.getString("total_rows") + "\t");
				System.out.print(rs.getString("total_space") + "\t" + rs.getString("used_space") + "\t");
				System.out.print(rs.getString("unused_space"));
				System.out.println();
			}
			stmt.close();

			conn.close();

		} catch (Exception e) {
			System.err.println("Failed to Execute" + aSQLScriptFilePath + " The error is " + e.getMessage());
		}
	}
}