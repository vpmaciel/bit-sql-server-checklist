package br.informacoes.tabela;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import org.apache.commons.io.IOUtils;

import br.database.SQLServer;

public class TabelaUtil {

	private LinkedList<Tabela> listTabela = new LinkedList<Tabela>();

	public void lerArquivo() throws ClassNotFoundException, SQLException {

		listTabela = new LinkedList<Tabela>();

		Statement statement = null;
		Connection conn = SQLServer.getConexao();
		try {

			String query = IOUtils
					.toString(new FileReader("./lista_o_tamanho_das_tabelas_dos_bancos_por_ordem_de_espaco_total.sql"));

			statement = conn.createStatement();
			statement.execute("SET ANSI_WARNINGS ON;");
			statement = conn.createStatement();
			
			ResultSet resultSet = statement.executeQuery(query);

			Tabela Tabela = new Tabela();
			while (resultSet.next()) {
				Tabela = new Tabela();
				Tabela.setDb_name(resultSet.getString("db_name"));
				Tabela.setObj_name(resultSet.getString("obj_name"));
				Tabela.setTotal_rows(resultSet.getString("total_rows"));
				Tabela.setTotal_space(resultSet.getString("total_space"));
				Tabela.setUsed_space(resultSet.getString("used_space"));
				Tabela.setUnused_space(resultSet.getString("unused_space"));

				listTabela.add(Tabela);
			}



			conn.close();

			gerarRelatorio();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}
	}

	public void gerarRelatorio() {
		TabelaRel TabelaRel = new TabelaRel(listTabela);
		TabelaRel.retornarRelatorio(true);
		TabelaArqCsv tabelaArqCsv = new TabelaArqCsv(listTabela);
		tabelaArqCsv.retornarArquivo(true);
	}

	public LinkedList<Tabela> getListTabela() {
		return listTabela;
	}

	public void setListTabela(LinkedList<Tabela> listTabela) {
		this.listTabela = listTabela;
	}
}

class Tabela {

	String db_name;
	String obj_name;
	String total_rows;
	String total_space;
	String used_space;
	String unused_space;

	public String getDb_name() {
		return db_name;
	}

	public void setDb_name(String db_name) {
		this.db_name = db_name;
	}

	public String getObj_name() {
		return obj_name;
	}

	public void setObj_name(String obj_name) {
		this.obj_name = obj_name;
	}

	public String getTotal_rows() {
		return total_rows;
	}

	public void setTotal_rows(String total_rows) {
		this.total_rows = total_rows;
	}

	public String getTotal_space() {
		return total_space;
	}

	public void setTotal_space(String total_space) {
		this.total_space = total_space;
	}

	public String getUsed_space() {
		return used_space;
	}

	public void setUsed_space(String used_space) {
		this.used_space = used_space;
	}

	public String getUnused_space() {
		return unused_space;
	}

	public void setUnused_space(String unused_space) {
		this.unused_space = unused_space;
	}

}