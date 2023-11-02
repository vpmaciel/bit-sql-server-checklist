package br.informacoes.unidade;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import org.apache.commons.io.IOUtils;

import br.database.SQLServer;

public class UnidadeUtil {

	private LinkedList<Unidade> listUnidade = new LinkedList<Unidade>();

	public void lerArquivo() throws ClassNotFoundException, SQLException {

		listUnidade = new LinkedList<Unidade>();

		Statement statement = null;
		Connection conn = SQLServer.getConexao();
		try {

			String query = IOUtils.toString(new FileReader("./informa_o_espaco_total_e_livre_de_cada_unidade.sql"));

			statement = conn.createStatement();
			statement.execute("SET ANSI_WARNINGS  OFF;");
			statement = conn.createStatement();
			
			ResultSet resultSet = statement.executeQuery(query);

			Unidade unidade = new Unidade();
			while (resultSet.next()) {
				unidade = new Unidade();
				unidade.setCategory(resultSet.getString("Category"));
				unidade.setInformation(resultSet.getString("Information"));
				unidade.setVs_logical_volume_name(resultSet.getString("logical_volume_name"));
				unidade.setVs_volume_mount_point(resultSet.getString("volume_mount_point"));
				unidade.setVs_file_system_type(resultSet.getString("file_system_type"));
				unidade.setTotalSpace_MB(resultSet.getString("TotalSpace_MB"));
				unidade.setFreeSpace_MB(resultSet.getString("FreeSpace_MB"));
				unidade.setVs_is_compresse(resultSet.getString("Compressed"));

				listUnidade.add(unidade);
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
		UnidadeRel unidadeRel = new UnidadeRel(listUnidade);
		unidadeRel.retornarRelatorio(true);
		UnidadeArqCsv unidadeArqCsv = new UnidadeArqCsv(listUnidade);
		unidadeArqCsv.retornarArquivo(true);
	}

	public LinkedList<Unidade> getListUnidade() {
		return listUnidade;
	}

	public void setListUnidade(LinkedList<Unidade> listUnidade) {
		this.listUnidade = listUnidade;
	}
}

class Unidade {

	String category;
	String information;
	String vs_logical_volume_name;
	String vs_volume_mount_point;
	String vs_file_system_type;
	String totalSpace_MB;
	String freeSpace_MB;
	String vs_is_compresse;

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getInformation() {
		return information;
	}

	public void setInformation(String information) {
		this.information = information;
	}

	public String getVs_logical_volume_name() {
		return vs_logical_volume_name;
	}

	public void setVs_logical_volume_name(String vs_logical_volume_name) {
		this.vs_logical_volume_name = vs_logical_volume_name;
	}

	public String getVs_volume_mount_point() {
		return vs_volume_mount_point;
	}

	public void setVs_volume_mount_point(String vs_volume_mount_point) {
		this.vs_volume_mount_point = vs_volume_mount_point;
	}

	public String getVs_file_system_type() {
		return vs_file_system_type;
	}

	public void setVs_file_system_type(String vs_file_system_type) {
		this.vs_file_system_type = vs_file_system_type;
	}

	public String getTotalSpace_MB() {
		return totalSpace_MB;
	}

	public void setTotalSpace_MB(String totalSpace_MB) {
		this.totalSpace_MB = totalSpace_MB;
	}

	public String getFreeSpace_MB() {
		return freeSpace_MB;
	}

	public void setFreeSpace_MB(String freeSpace_MB) {
		this.freeSpace_MB = freeSpace_MB;
	}

	public String getVs_is_compresse() {
		return vs_is_compresse;
	}

	public void setVs_is_compresse(String vs_is_compresse) {
		this.vs_is_compresse = vs_is_compresse;
	}
}