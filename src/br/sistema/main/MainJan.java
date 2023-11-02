package br.sistema.main;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.Timer;
import javax.swing.WindowConstants;

import br.arquitetura.Sis;
import br.arquitetura.gui.Imagem;

@SuppressWarnings("serial")
public class MainJan extends JFrame {

	private static MainControl mainControl;

	public static MainJan getFrameMain() {
		return MainControl.getMainJanCad();
	}

	public static MainControl getMainControl() {
		return mainControl;
	}

	static void mostrarFrame(JFrame frame) {
		MainControl.mostrarFrame(frame);
	}

	private final JMenuBar menuBar = new JMenuBar();
	private JMenu menuAjuda;
	private JMenu menuArquivo;
	private JMenu menuControle;
	private JMenuItem menuItemAjudaSobreSistema;
	private JMenuItem menuItemArquivoSair;
	private JMenuItem menuItemControleSegurancaConfiguracoes;
	private JMenuItem menuItemControleUnidades;
	private JMenuItem menuItemControleSQL;
	private JMenuItem menuItemControleTabelas;
	private JMenuItem menuItemControleBancos;
	private JMenuItem menuItemControlePermissoes;

	public MainJan() {
		iniciarGui();
		iniciarCont();
	}

	public JMenu getMenuAjuda() {
		return menuAjuda;
	}

	public JMenu getMenuArquivo() {
		return menuArquivo;
	}

	public JMenu getMenuControle() {
		return menuControle;
	}

	public JMenuItem getMenuItemAjudaSobreSistema() {
		return menuItemAjudaSobreSistema;
	}

	public JMenuItem getMenuItemArquivoSair() {
		return menuItemArquivoSair;
	}

	public JMenuItem getMenuItemControleSegurancaConfiguracoes() {
		return menuItemControleSegurancaConfiguracoes;
	}

	public JMenuItem getMenuItemControleBancos() {
		return menuItemControleBancos;
	}

	public JMenuItem getMenuItemControlePermissoes() {
		return menuItemControlePermissoes;
	}

	public JMenuItem getMenuItemControleSQL() {
		return menuItemControleSQL;
	}

	public JMenuItem getMenuItemControleTabelas() {
		return menuItemControleTabelas;
	}

	public JMenuItem getMenuItemControleUnidades() {
		return menuItemControleUnidades;
	}

	public void iniciarCont() {
		mainControl = MainControl.getInstance(this);
		Timer timer = new Timer(1280, mainControl.new Relogio());
		timer.setInitialDelay(0);
		timer.setRepeats(true);
		timer.start();
		addWindowListener(mainControl.new FrameGerenteEventos());
		menuItemArquivoSair.addActionListener(mainControl.new MenuArquivoGerenteEventos());
		menuItemControleBancos.addActionListener(mainControl.new MenuCadastroGerenteEventos());
		menuItemControlePermissoes.addActionListener(mainControl.new MenuCadastroGerenteEventos());
		menuItemControleSegurancaConfiguracoes.addActionListener(mainControl.new MenuCadastroGerenteEventos());
		menuItemControleSQL.addActionListener(mainControl.new MenuCadastroGerenteEventos());
		menuItemControleTabelas.addActionListener(mainControl.new MenuCadastroGerenteEventos());
		menuItemControleUnidades.addActionListener(mainControl.new MenuCadastroGerenteEventos());
		menuItemAjudaSobreSistema.addActionListener(mainControl.new MenuAjudaGerenteEventos());
	}

	private void iniciarGui() {
		setIconImage(Imagem.getLogoTipoImage());
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		setMinimumSize(Sis.getTamanhoJanela());
		setSize(Sis.getTamanhoJanela());
		setPreferredSize(Sis.getTamanhoJanela());
		setMaximumSize(Sis.getTamanhoJanela());

		menuArquivo = new JMenu("Arquivo");
		menuArquivo.setMnemonic('A');

		menuItemArquivoSair = new JMenuItem("Sair");
		menuArquivo.add(menuItemArquivoSair);

		menuBar.add(menuArquivo);

		menuControle = new JMenu("Controle");
		menuControle.setMnemonic('C');

		menuItemControleSegurancaConfiguracoes = new JMenuItem("Segurança | Configurações");
		menuControle.add(menuItemControleSegurancaConfiguracoes);

		menuItemControleBancos = new JMenuItem("Bancos");
		menuControle.add(menuItemControleBancos);

		menuItemControlePermissoes = new JMenuItem("Permissões");
		menuControle.add(menuItemControlePermissoes);

		menuItemControleSQL = new JMenuItem("SQL");
		menuControle.add(menuItemControleSQL);

		menuItemControleTabelas = new JMenuItem("Tabelas");
		menuControle.add(menuItemControleTabelas);

		menuItemControleUnidades = new JMenuItem("Unidades");
		menuControle.add(menuItemControleUnidades);

		menuBar.add(menuControle);

		menuAjuda = new JMenu("Ajuda");
		menuAjuda.setMnemonic('u');

		menuItemAjudaSobreSistema = new JMenuItem("Sobre o Sistema");
		menuAjuda.add(menuItemAjudaSobreSistema);

		menuBar.add(menuAjuda);

		setJMenuBar(menuBar);
	}
}