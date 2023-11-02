package br.informacoes.banco;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import org.apache.commons.io.IOUtils;

import br.database.SQLServer;

public class BancoUtil {

	private LinkedList<Banco> listBanco = new LinkedList<Banco>();

	public void lerArquivo() throws ClassNotFoundException, SQLException {

		listBanco = new LinkedList<Banco>();

		Statement statement = null;
		Connection conn = SQLServer.getConexao();
		try {

			String query = IOUtils.toString(new FileReader(
					"./lista_todos_os_bancos_de_dados_com_informacoes_detalhadas_sobre_o_espaco_usado.sql"));

			statement = conn.createStatement();
			
			statement.execute("SET ANSI_WARNINGS OFF;");
			statement = conn.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			statement = conn.createStatement();
			statement.execute("SET ANSI_WARNINGS ON;");

			Banco banco = new Banco();
			while (resultSet.next()) {
				banco = new Banco();
				banco.setDB_Freespace(resultSet.getString("DB_Freespace"));
				banco.setDbname(resultSet.getString("Dbname"));
				banco.setDBsize(resultSet.getString("DBsize"));
				banco.setDbstatus(resultSet.getString("dbstatus"));
				banco.setFile_Size_MB(resultSet.getString("file_Size_MB"));
				banco.setFree_Space_MB(resultSet.getString("Free_Space_MB"));
				banco.setLog_File_Size_MB(resultSet.getString("Log_File_Size_MB"));
				banco.setLog_Free_Space_MB(resultSet.getString("log_Free_Space_MB"));
				banco.setLog_Space_Used_MB(resultSet.getString("log_Space_Used_MB"));
				banco.setRecovery_Model(resultSet.getString("Recovery_Model"));
				banco.setSpace_Used_MB(resultSet.getString("Space_Used_MB"));
				listBanco.add(banco);
			}

			statement.close();

			conn.close();

			gerarRelatorio();

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	public void gerarRelatorio() {
		BancoRel bancoRel = new BancoRel(listBanco);
		bancoRel.retornarRelatorio(true);
		BancoArqCsv bancoArqCsv = new BancoArqCsv(listBanco);
		bancoArqCsv.retornarArquivo(true);
	}

	public LinkedList<Banco> getListBanco() {
		return listBanco;
	}

	public void setListBanco(LinkedList<Banco> listBanco) {
		this.listBanco = listBanco;
	}
}

class Banco {

	String Dbname;
	String dbstatus;
	String Recovery_Model;
	String DBsize;
	String file_Size_MB;
	String Space_Used_MB;
	String Free_Space_MB;
	String Log_File_Size_MB;
	String log_Space_Used_MB;
	String log_Free_Space_MB;
	String DB_Freespace;

	public String getDbname() {
		return Dbname;
	}

	public void setDbname(String dbname) {
		Dbname = dbname;
	}

	public String getDbstatus() {
		return dbstatus;
	}

	public void setDbstatus(String dbstatus) {
		this.dbstatus = dbstatus;
	}

	public String getRecovery_Model() {
		return Recovery_Model;
	}

	public void setRecovery_Model(String recovery_Model) {
		Recovery_Model = recovery_Model;
	}

	public String getDBsize() {
		return DBsize;
	}

	public void setDBsize(String dBsize) {
		DBsize = dBsize;
	}

	public String getFile_Size_MB() {
		return file_Size_MB;
	}

	public void setFile_Size_MB(String file_Size_MB) {
		this.file_Size_MB = file_Size_MB;
	}

	public String getSpace_Used_MB() {
		return Space_Used_MB;
	}

	public void setSpace_Used_MB(String space_Used_MB) {
		Space_Used_MB = space_Used_MB;
	}

	public String getFree_Space_MB() {
		return Free_Space_MB;
	}

	public void setFree_Space_MB(String free_Space_MB) {
		Free_Space_MB = free_Space_MB;
	}

	public String getLog_File_Size_MB() {
		return Log_File_Size_MB;
	}

	public void setLog_File_Size_MB(String log_File_Size_MB) {
		Log_File_Size_MB = log_File_Size_MB;
	}

	public String getLog_Space_Used_MB() {
		return log_Space_Used_MB;
	}

	public void setLog_Space_Used_MB(String log_Space_Used_MB) {
		this.log_Space_Used_MB = log_Space_Used_MB;
	}

	public String getLog_Free_Space_MB() {
		return log_Free_Space_MB;
	}

	public void setLog_Free_Space_MB(String log_Free_Space_MB) {
		this.log_Free_Space_MB = log_Free_Space_MB;
	}

	public String getDB_Freespace() {
		return DB_Freespace;
	}

	public void setDB_Freespace(String dB_Freespace) {
		DB_Freespace = dB_Freespace;
	}
}