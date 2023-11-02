package br.informacoes.seguranca;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import br.database.SQLServer;

final class SegurancaImp implements SegurancaDao {

	@Override
	public Collection<Seguranca> getRegistro() {

		Collection<Seguranca> listSeguranca = new LinkedList<Seguranca>();
		Connection con = null;
		try {
			con = SQLServer.getConexao();

			CallableStatement cs = con.prepareCall("{call dbo.stpSecurity_Checklist}");
			ResultSet rs = cs.executeQuery();

			Seguranca seguranca = null;

			// Iterate through the data in the result set and display it.
			while (rs.next()) {
				seguranca = new Seguranca();
				seguranca.setAvaliacao(String.valueOf(rs.getString("Avaliação")));
				seguranca.setCategoria(String.valueOf(rs.getString("Categoria")));
				seguranca.setCodigo(String.valueOf(rs.getString("Código")));
				seguranca.setDescricaoDoProblema(String.valueOf(rs.getString("Descrição do Problema")));
				seguranca.setDetalhamentoDaVerificacao(String.valueOf(rs.getString("Detalhamento da Verificação")));
				seguranca.setResultadosDaValidacao(String.valueOf(rs.getString("Resultados da Validação")));
				seguranca.setSugestaoDeCorrecao(String.valueOf(rs.getString("Sugestão de Correção")));
				seguranca.setUrlDeReferencia(String.valueOf(rs.getString("URL de Referência")));
				seguranca.setVerificado(String.valueOf(rs.getString("O que é verificado")));
				listSeguranca.add(seguranca);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return listSeguranca;
	}

}
