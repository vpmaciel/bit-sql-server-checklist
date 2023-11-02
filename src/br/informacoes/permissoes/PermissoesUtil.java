package br.informacoes.permissoes;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import org.apache.commons.io.IOUtils;

import br.database.SQLServer;

public class PermissoesUtil {

	private LinkedList<Permissoes1> listPermissoes1 = new LinkedList<Permissoes1>();
	private LinkedList<Permissoes2> listPermissoes2 = new LinkedList<Permissoes2>();
	private LinkedList<Permissoes3> listPermissoes3 = new LinkedList<Permissoes3>();

	public void lerArquivo() throws ClassNotFoundException, SQLException {

		listPermissoes1 = new LinkedList<Permissoes1>();
		listPermissoes2 = new LinkedList<Permissoes2>();
		listPermissoes3 = new LinkedList<Permissoes3>();

		Statement statement = null;
		Connection conn = SQLServer.getConexao();
		try {

			ResultSet resultSet = null;
			String query = IOUtils.toString(new FileReader("./relatorio_completo_de_permissoes_1.sql"));

			statement = conn.createStatement();
			
			statement.execute("SET ANSI_WARNINGS OFF;");
			statement = conn.createStatement();			

			resultSet = statement.executeQuery(query);

			Permissoes1 permissoes1 = new Permissoes1();
			while (resultSet.next()) {
				permissoes1 = new Permissoes1();
				permissoes1.setCreate_date(resultSet.getString("create_date"));
				permissoes1.setIs_disabled(resultSet.getString("is_disabled"));
				permissoes1.setLogin_Name(resultSet.getString("Login_Name"));
				permissoes1.setLogin_Type(resultSet.getString("Login_Type"));
				permissoes1.setModify_date(resultSet.getString("modify_date"));
				permissoes1.setPermission_Name(resultSet.getString("Permission_Name"));
				permissoes1.setPermission_Type(resultSet.getString("Permission_Type"));
				listPermissoes1.add(permissoes1);
			}

			statement.close();

			query = IOUtils.toString(new FileReader("./relatorio_completo_de_permissoes_2.sql"));

			statement = conn.createStatement();

			resultSet = statement.executeQuery(query);

			Permissoes2 permissoes2 = new Permissoes2();
			while (resultSet.next()) {
				permissoes2 = new Permissoes2();
				permissoes2.setPrincipal_Name(resultSet.getString("Principal_Name"));
				permissoes2.setLogin_Name(resultSet.getString("Login_Name"));
				permissoes2.setDatabaseName(resultSet.getString("DatabaseName"));
				permissoes2.setPermission_Name(resultSet.getString("Permission_Name"));
				permissoes2.setPermission_Type(resultSet.getString("Permission_Type"));

				listPermissoes2.add(permissoes2);
			}

			statement.close();

			query = IOUtils.toString(new FileReader("./relatorio_completo_de_permissoes_3.sql"));

			statement = conn.createStatement();

			resultSet = statement.executeQuery(query);

			Permissoes3 permissoes3 = new Permissoes3();
			while (resultSet.next()) {
				permissoes3 = new Permissoes3();
				permissoes3.setPrincipal_Name(resultSet.getString("Principal_Name"));
				permissoes3.setLogin_Name(resultSet.getString("Login_Name"));
				permissoes3.setDatabaseName(resultSet.getString("DatabaseName"));
				permissoes3.setPermission_Name(resultSet.getString("Permission_Name"));
				permissoes3.setPermission_Type(resultSet.getString("Permission_Type"));
				listPermissoes3.add(permissoes3);
			}
			statement = conn.createStatement();
			statement.execute("SET ANSI_WARNINGS ON;");

			statement.close();

			conn.close();

			gerarRelatorio();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}
	}

	public void gerarRelatorio() {
		PermissoesRel1 PermissoesRel1 = new PermissoesRel1(listPermissoes1);
		PermissoesRel1.retornarRelatorio(true);
		Permissoes1ArqCsv Permissoes1ArqCsv = new Permissoes1ArqCsv(listPermissoes1);
		Permissoes1ArqCsv.retornarArquivo(true);
		PermissoesRel2 PermissoesRel2 = new PermissoesRel2(listPermissoes2);
		PermissoesRel2.retornarRelatorio(true);
		Permissoes2ArqCsv Permissoes2ArqCsv = new Permissoes2ArqCsv(listPermissoes2);
		Permissoes2ArqCsv.retornarArquivo(true);
		PermissoesRel3 PermissoesRel3 = new PermissoesRel3(listPermissoes3);
		PermissoesRel3.retornarRelatorio(true);
		Permissoes3ArqCsv Permissoes3ArqCsv = new Permissoes3ArqCsv(listPermissoes3);
		Permissoes3ArqCsv.retornarArquivo(true);
	}

	public LinkedList<Permissoes1> getListPermissoes() {
		return listPermissoes1;
	}

	public void setListPermissoes(LinkedList<Permissoes1> listPermissoes) {
		this.listPermissoes1 = listPermissoes;
	}
}

class Permissoes1 {

	String Login_Name;
	String Login_Type;
	String is_disabled;
	String Permission_Name;
	String Permission_Type;
	String create_date;
	String modify_date;

	public String getLogin_Name() {
		return Login_Name;
	}

	public void setLogin_Name(String login_Name) {
		Login_Name = login_Name;
	}

	public String getLogin_Type() {
		return Login_Type;
	}

	public void setLogin_Type(String login_Type) {
		Login_Type = login_Type;
	}

	public String getIs_disabled() {
		return is_disabled;
	}

	public void setIs_disabled(String is_disabled) {
		this.is_disabled = is_disabled;
	}

	public String getPermission_Name() {
		return Permission_Name;
	}

	public void setPermission_Name(String permission_Name) {
		Permission_Name = permission_Name;
	}

	public String getPermission_Type() {
		return Permission_Type;
	}

	public void setPermission_Type(String permission_Type) {
		Permission_Type = permission_Type;
	}

	public String getCreate_date() {
		return create_date;
	}

	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}

	public String getModify_date() {
		return modify_date;
	}

	public void setModify_date(String modify_date) {
		this.modify_date = modify_date;
	}
}

class Permissoes2 {

	String Principal_Name;
	String Login_Name;
	String DatabaseName;
	String Permission_Name;
	String Permission_Type;

	public String getPrincipal_Name() {
		return Principal_Name;
	}

	public void setPrincipal_Name(String principal_Name) {
		Principal_Name = principal_Name;
	}

	public String getLogin_Name() {
		return Login_Name;
	}

	public void setLogin_Name(String login_Name) {
		Login_Name = login_Name;
	}

	public String getDatabaseName() {
		return DatabaseName;
	}

	public void setDatabaseName(String databaseName) {
		DatabaseName = databaseName;
	}

	public String getPermission_Name() {
		return Permission_Name;
	}

	public void setPermission_Name(String permission_Name) {
		Permission_Name = permission_Name;
	}

	public String getPermission_Type() {
		return Permission_Type;
	}

	public void setPermission_Type(String permission_Type) {
		Permission_Type = permission_Type;
	}

}

class Permissoes3 {
	String Principal_Name;
	String Login_Name;
	String DatabaseName;
	String Permission_Name;
	String Permission_Type;

	public String getPrincipal_Name() {
		return Principal_Name;
	}

	public void setPrincipal_Name(String principal_Name) {
		Principal_Name = principal_Name;
	}

	public String getLogin_Name() {
		return Login_Name;
	}

	public void setLogin_Name(String login_Name) {
		Login_Name = login_Name;
	}

	public String getDatabaseName() {
		return DatabaseName;
	}

	public void setDatabaseName(String databaseName) {
		DatabaseName = databaseName;
	}

	public String getPermission_Name() {
		return Permission_Name;
	}

	public void setPermission_Name(String permission_Name) {
		Permission_Name = permission_Name;
	}

	public String getPermission_Type() {
		return Permission_Type;
	}

	public void setPermission_Type(String permission_Type) {
		Permission_Type = permission_Type;
	}

}