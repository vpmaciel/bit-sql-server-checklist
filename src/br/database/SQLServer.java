package br.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;

import br.configuracao.Configuracao;

public class SQLServer {
	private static Connection conexao = null;
	private static String servidor = null;
	private static String porta = null;
	private static String banco = null;
	private static String usuario = null;
	private static String senha = null;
	private static Configuracao configuracao = null;
	

	public static Connection getConexao() {

		HSQLDB hsqldb = new HSQLDB();
		LinkedList<Configuracao> listConfiguracao = (LinkedList<Configuracao>) hsqldb.getConfiguracao();

		if (listConfiguracao.size() > 0) {
			configuracao = listConfiguracao.get(0);
		}

		servidor = configuracao.getServidor();
		banco = configuracao.getBanco();
		porta = configuracao.getPorta();
		senha = configuracao.getSenha();
		usuario = configuracao.getUsuario();

		String connectionUrl = "jdbc:sqlserver://" + servidor + ":" + porta + ";databaseName=" + banco + ";user="
				+ usuario + ";password=" + senha;

		try {
			try {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(servidor + porta + banco + usuario + senha);
			conexao = DriverManager.getConnection(connectionUrl);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conexao;
	}

	public static void fecharConexao() {
		if (conexao != null) {
			try {
				conexao.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static String getPorta() {
		return porta;
	}

	public static void setPorta(String porta) {
		SQLServer.porta = porta;
	}

	public static String getBanco() {
		return banco;
	}

	public static void setBanco(String banco) {
		SQLServer.banco = banco;
	}

	public static String getUsuario() {
		return usuario;
	}

	public static void setUsuario(String usuario) {
		SQLServer.usuario = usuario;
	}

	public static String getSenha() {
		return senha;
	}

	public static void setSenha(String password) {
		SQLServer.senha = password;
	}

	public static void setConexao(Connection conexao) {
		SQLServer.conexao = conexao;
	}

	public static String getServidor() {
		return servidor;
	}

	public static void setServidor(String servidor) {
		SQLServer.servidor = servidor;
	}

}