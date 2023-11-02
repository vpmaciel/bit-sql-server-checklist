package br.informacoes.sql;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import org.apache.commons.io.IOUtils;

import br.database.SQLServer;

public class SQLUtil {

	private LinkedList<SQL_1> listSQL_1 = new LinkedList<SQL_1>();
	private LinkedList<SQL_2> listSQL_2 = new LinkedList<SQL_2>();
	private LinkedList<SQL_3> listSQL_3 = new LinkedList<SQL_3>();

	public void lerArquivo() throws ClassNotFoundException, SQLException {

		listSQL_1 = new LinkedList<SQL_1>();
		listSQL_2 = new LinkedList<SQL_2>();
		listSQL_3 = new LinkedList<SQL_3>();

		Statement statement = null;
		Connection conn = SQLServer.getConexao();
		try {

			String query = IOUtils.toString(new FileReader("./informa_o_tempo_desde_o_ultimo_reinicio_3.sql"));

			statement = conn.createStatement();
			statement.execute("SET ANSI_WARNINGS OFF;");
			statement = conn.createStatement();
			
			ResultSet resultSet = statement.executeQuery(query);

			SQL_1 sql_1 = new SQL_1();
			while (resultSet.next()) {
				sql_1 = new SQL_1();
				sql_1.setDays(resultSet.getString("Days"));
				sql_1.setHours(resultSet.getString("Hours"));
				sql_1.setMinutes(resultSet.getString("Minutes"));
				listSQL_1.add(sql_1);
			}

			query = IOUtils.toString(new FileReader("./informa_o_tempo_desde_o_ultimo_reinicio_2.sql"));

			statement = conn.createStatement();
			resultSet = statement.executeQuery(query);

			SQL_2 sql_2 = new SQL_2();
			while (resultSet.next()) {
				sql_2 = new SQL_2();
				sql_2.setSQLServerStartTime(resultSet.getString("SQLServerStartTime"));
				listSQL_2.add(sql_2);
			}

			query = IOUtils.toString(new FileReader("./informa_o_tempo_desde_o_ultimo_reinicio_1.sql"));

			statement = conn.createStatement();
			resultSet = statement.executeQuery(query);

			SQL_3 sql_3 = new SQL_3();
			while (resultSet.next()) {
				sql_3 = new SQL_3();
				sql_3.setSqlserver_start_time(resultSet.getString("sqlserver_start_time"));
				listSQL_3.add(sql_3);
			}
			
			statement = conn.createStatement();
			statement.execute("SET ANSI_WARNINGS ON;");

			statement.close();

			conn.close();

			gerarRelatorio();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	public void gerarRelatorio() {
		SQLRel sqlRel = new SQLRel(listSQL_1, listSQL_2, listSQL_3);
		sqlRel.retornarRelatorio(true);
		SQLArqCsv sqlArqCsv = new SQLArqCsv(listSQL_1, listSQL_2, listSQL_3);
		sqlArqCsv.retornarArquivo(true);
	}

	public LinkedList<SQL_1> getListSQL_1() {
		return listSQL_1;
	}

	public void setListSQL_1(LinkedList<SQL_1> listSQL_1) {
		this.listSQL_1 = listSQL_1;
	}

	public LinkedList<SQL_2> getListSQL_2() {
		return listSQL_2;
	}

	public void setListSQL_2(LinkedList<SQL_2> listSQL_2) {
		this.listSQL_2 = listSQL_2;
	}

	public LinkedList<SQL_3> getListSQL_3() {
		return listSQL_3;
	}

	public void setListSQL_3(LinkedList<SQL_3> listSQL_3) {
		this.listSQL_3 = listSQL_3;
	}

}

class SQL_1 {
	String days;
	String hours;
	String minutes;

	public String getDays() {
		return days;
	}

	public void setDays(String days) {
		this.days = days;
	}

	public String getHours() {
		return hours;
	}

	public void setHours(String hours) {
		this.hours = hours;
	}

	public String getMinutes() {
		return minutes;
	}

	public void setMinutes(String minutes) {
		this.minutes = minutes;
	}
}

class SQL_2 {
	String SQLServerStartTime;

	public String getSQLServerStartTime() {
		return SQLServerStartTime;
	}

	public void setSQLServerStartTime(String sQLServerStartTime) {
		SQLServerStartTime = sQLServerStartTime;
	}
}

class SQL_3 {
	String sqlserver_start_time;

	public String getSqlserver_start_time() {
		return sqlserver_start_time;
	}

	public void setSqlserver_start_time(String sqlserver_start_time) {
		this.sqlserver_start_time = sqlserver_start_time;
	}
}