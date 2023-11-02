package br.informacoes.seguranca;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.Statement;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import com.ibatis.common.jdbc.ScriptRunner;

import br.arquitetura.ArquivoJson;
import br.arquitetura.Internet;
import br.configuracao.Configuracao;
import br.database.HSQLDB;
import br.database.SQLServer;
import br.sistema.main.MainControl;
import br.sistema.main.MainJan;

final class SegurancaControl {

	public class FechaJanela implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			getBancoJanCad().setVisible(false);
		}
	}

	public class FormatoJson implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {

			String endereco = "http://www.oi.com.br?&var=";
			String stringJson = null;

			try {
				List<Seguranca> listSeguranca = new LinkedList<Seguranca>(SegurancaFac.getRegistro());

				ArquivoJson<Seguranca> arquivoJson = new ArquivoJson<Seguranca>(seguranca, "seguranca");
				arquivoJson.gravarArquivo(listSeguranca);
				arquivoJson.retornarArquivo(true);
				stringJson = arquivoJson.toJson(listSeguranca);
				stringJson = String.valueOf(java.net.URLEncoder.encode(stringJson, "UTF-8"));
				new Internet().new LinkSistema(endereco + stringJson);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public class Frame extends WindowAdapter {

		@Override
		public void windowActivated(WindowEvent e) {
			getBancoJanCad().reiniciarGui();
			try {
				atualizarGui();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

		@Override
		public void windowClosing(WindowEvent e) {
			getBancoJanCad().setVisible(false);
		}

		@Override
		public void windowOpened(WindowEvent e) {
			seguranca = new Seguranca();
			getSegurancaPainelCad().getGuiBanco().requestFocus();
		}
	}

	public class Home implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			MainControl.mostrarFrame(MainJan.getFrameMain());
		}
	}

	public class ConfigurarDataBase implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			HSQLDB hsqldb = new HSQLDB();
			atualizarObjeto();
			hsqldb.inserirConfiguracao(configuracao);

		}
	}

	public class Relatorio implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {

			List<Seguranca> listSeguranca = new LinkedList<>();

			try {
				Statement statement = null;
				listSeguranca = new LinkedList<>(SegurancaFac.getRegistro());
				Connection con = SQLServer.getConexao();
				HSQLDB hsqldb = new HSQLDB();
				LinkedList<Configuracao> listConfiguracao = (LinkedList<Configuracao>) hsqldb.getConfiguracao();
				Configuracao configuracao = new Configuracao();
				
				
				for(Configuracao c: listConfiguracao) {
					configuracao.setBanco(c.getBanco());
				}
				
				statement = con.createStatement();
				statement.execute("USE " + configuracao.getBanco() +";");
				
				
			    //Initialize the script runner
			      ScriptRunner sr = new ScriptRunner(con, false, false);
			      //Creating a reader object
			      Reader reader = new BufferedReader(new FileReader("stp_checklist_seguranca.sql"));
			      //Running the script
			      sr.runScript(reader);
			} catch (Exception e) {
				e.printStackTrace();
			}

			SegurancaRel segurancaRel = new SegurancaRel(listSeguranca);
			segurancaRel.retornarRelatorio(true);

			SegurancaArqCsv segurancaArqCsv = new SegurancaArqCsv(listSeguranca);
			segurancaArqCsv.retornarArquivo(true);

		}
	}

	private Seguranca seguranca;
	private Configuracao configuracao = new Configuracao();;

	public SegurancaControl() {

	}

	public void atualizarGui() {

		if (configuracao == null) {
			return;
		}

		HSQLDB hsqldb = new HSQLDB();
		LinkedList<Configuracao> listConfiguracao = (LinkedList<Configuracao>) hsqldb.getConfiguracao();

		if (listConfiguracao.size() > 0) {
			configuracao = listConfiguracao.get(0);
		}

		getSegurancaPainelCad().getGuiServidor().setText(configuracao.getServidor());
		getSegurancaPainelCad().getGuiBanco().setText(configuracao.getBanco());
		getSegurancaPainelCad().getGuiPorta().setText(configuracao.getPorta());
		getSegurancaPainelCad().getGuiSenha().setText(configuracao.getSenha());
		getSegurancaPainelCad().getGuiUsuario().setText(configuracao.getUsuario());
	}

	public void atualizarObjeto() {

		configuracao.setServidor(getSegurancaPainelCad().getGuiServidor().getText());
		configuracao.setBanco(getSegurancaPainelCad().getGuiBanco().getText());
		configuracao.setPorta(getSegurancaPainelCad().getGuiPorta().getText());
		configuracao.setSenha(getSegurancaPainelCad().getGuiSenha().getText());
		configuracao.setUsuario(getSegurancaPainelCad().getGuiUsuario().getText());
		atualizarSQLServer();
	}

	public void atualizarSQLServer() {
		SQLServer.setBanco(configuracao.getBanco());
		SQLServer.setSenha(configuracao.getSenha());
		SQLServer.setPorta(configuracao.getPorta());
		SQLServer.setUsuario(configuracao.getUsuario());
		SQLServer.setServidor(configuracao.getServidor());
	}

	public Seguranca getSeguranca() {
		return seguranca;
	}

	public SegurancaJanCad getBancoJanCad() {
		return MainControl.getSegurancaJanCad();
	}

	public SegurancaPainelCad getSegurancaPainelCad() {
		return MainControl.getSegurancaJanCad().getSegurancaPainelCad();
	}

	public void setBanco(Seguranca seguranca) {
		this.seguranca = seguranca;
	}
}