package br.sistema.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import br.arquitetura.Data;
import br.arquitetura.Sis;
import br.arquitetura.gui.Msg;
import br.database.SQLServer;
import br.informacoes.banco.BancoUtil;
import br.informacoes.permissoes.PermissoesUtil;
import br.informacoes.seguranca.SegurancaJanCad;
import br.informacoes.sql.SQLUtil;
import br.informacoes.tabela.TabelaUtil;
import br.informacoes.unidade.UnidadeUtil;

public final class MainControl {

	public class FrameGerenteEventos extends WindowAdapter {

		@Override
		public void windowActivated(WindowEvent e) {

		}

		@Override
		public void windowClosing(WindowEvent e) {
			if (Msg.confirmarSairDoSistema() == JOptionPane.OK_OPTION) {
				System.exit(0);
			}
		}

		@Override
		public void windowOpened(WindowEvent e) {
			mostrarFrame(segurancaJanCad);

		}
	}

	public class MenuAjudaGerenteEventos implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {

			if (actionEvent.getSource() == mainJan.getMenuItemAjudaSobreSistema()) {
				Msg.ajuda();
			}

		}
	}

	public class MenuArquivoGerenteEventos implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			if (actionEvent.getSource() == mainJan.getMenuItemArquivoSair()) {
				if (Msg.confirmarSairDoSistema() == 0) {
					SQLServer.fecharConexao();
					System.exit(0);
				}
			}
		}
	}

	public class MenuCadastroGerenteEventos implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			if (actionEvent.getSource() == mainJan.getMenuItemControleSegurancaConfiguracoes()) {
				mostrarFrame(segurancaJanCad);
			}
			if (actionEvent.getSource() == mainJan.getMenuItemControleBancos()) {
				BancoUtil bancoUtil = new BancoUtil();
				try {
					bancoUtil.lerArquivo();
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
			if (actionEvent.getSource() == mainJan.getMenuItemControlePermissoes()) {
				PermissoesUtil permissoesUtil = new PermissoesUtil();
				try {
					permissoesUtil.lerArquivo();
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
			if (actionEvent.getSource() == mainJan.getMenuItemControleSQL()) {
				SQLUtil sqlInformacoes = new SQLUtil();
				try {
					sqlInformacoes.lerArquivo();
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
			if (actionEvent.getSource() == mainJan.getMenuItemControleTabelas()) {
				TabelaUtil tabelaUtil = new TabelaUtil();
				try {
					tabelaUtil.lerArquivo();
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
			if (actionEvent.getSource() == mainJan.getMenuItemControleUnidades()) {
				UnidadeUtil unidadeUtil = new UnidadeUtil();
				try {
					unidadeUtil.lerArquivo();
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
		}
	}

	public class Relogio implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			MainControl.getSegurancaJanCad().setTitle(getTitulo("CHECK LIST"));
			MainControl.getMainJanCad().setTitle(getTitulo("SQL SERVER MONITOR"));
		}
	}

	private static SegurancaJanCad segurancaJanCad;
	private static MainControl mainControl;
	private static MainJan mainJan;
	private static int totalPrincipalCont;
	static {
		totalPrincipalCont = 0;
	}

	public static SegurancaJanCad getSegurancaJanCad() {
		return segurancaJanCad;
	}

	public static synchronized MainControl getInstance(MainJan mainJan) {
		if (totalPrincipalCont > 1) {
			JOptionPane.showMessageDialog(null, "Foi instanciado mais de uma Objeto:" + SqlServerMonitorExec.class);
			System.exit(0);
		}
		if (mainControl == null) {
			++totalPrincipalCont;
			return new MainControl(mainJan);
		}
		return mainControl;
	}

	public static MainJan getMainJanCad() {
		return mainJan;
	}

	public static void mostrarFrame(JFrame frame) {
		frame.setVisible(true);
		frame.toFront();
		frame.setLocationRelativeTo(null);
		mainJan.setLocationRelativeTo(null);
		frame.setResizable(false);
	}

	private MainControl(MainJan mainJan) {
		MainControl.mainJan = mainJan;
		criarFrames();
	}

	private void criarFrame(JFrame frame) {
		frame.pack();
		frame.setVisible(false);
	}

	private void criarFrames() {
		segurancaJanCad = new SegurancaJanCad();
		criarFrame(segurancaJanCad);
	}

	private String getTitulo(String titulo) {
		return Sis.getNomeSistema() + " - " + titulo + " " + Data.getDataHoraSimples();
	}
}