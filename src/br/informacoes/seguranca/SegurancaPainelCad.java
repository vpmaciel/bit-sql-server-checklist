package br.informacoes.seguranca;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import br.arquitetura.Sis;
import br.arquitetura.gui.ConfiguracaoGui;
import br.arquitetura.gui.FocoEvento;
import br.arquitetura.gui.Gui;
import br.arquitetura.gui.ToolBar;

@SuppressWarnings("serial")
public final class SegurancaPainelCad extends JPanel implements Gui {

	private SegurancaControl segurancaControl;
	private ConfiguracaoGui configuracaoGui;
	private JTextField fieldServidor;
	private JTextField fieldPorta;
	private JTextField fieldDataBase;
	private JTextField fieldUsuario;
	private JTextField fieldSenha;
	private JButton buttonConfirmar;
	private ToolBar toolBar;

	public SegurancaPainelCad() {
		iniciarLayout();
		iniciarGui();
		iniciarFocoControlador();
		iniciarGuiControlador();
	}

	@Override
	public void atualizarTable() {
	}

	public SegurancaControl getBancoCont() {
		return segurancaControl;
	}

	@Override
	public ConfiguracaoGui getConfiguracaoGui() {
		return configuracaoGui;
	}

	public JTextField getGuiPorta() {
		return fieldPorta;
	}

	public JTextField getGuiBanco() {
		return fieldDataBase;
	}

	public JTextField getGuiUsuario() {
		return fieldUsuario;
	}

	public JTextField getGuiSenha() {
		return fieldSenha;
	}

	public JTextField getGuiServidor() {
		return fieldServidor;
	}

	public JButton getGuiConfirmar() {
		return buttonConfirmar;
	}

	public ToolBar getTB() {
		return toolBar;
	}

	@Override
	public void iniciarControlador() {
	}

	@Override
	public void iniciarFocoControlador() {
		new FocoEvento(this);
	}

	@Override
	public void iniciarGui() {
		toolBar = new ToolBar();

		add(toolBar.getTB());

		add(new JLabel("SERVIDOR"));

		fieldServidor = new JTextField("localhost");
		add(fieldServidor);

		add(new JLabel("BANCO DE DADOS"));

		fieldDataBase = new JTextField("tempdb");
		add(fieldDataBase);

		add(new JLabel("PORTA"));

		fieldPorta = new JTextField("1433");
		add(fieldPorta);

		add(new JLabel("USUÁRIO"));

		fieldUsuario = new JTextField("sa");
		add(fieldUsuario);

		add(new JLabel("SENHA"));

		fieldSenha = new JTextField("integrator1981");
		add(fieldSenha);

		add(new JLabel(""));

		buttonConfirmar = new JButton("Confirmar Dados");
		add(buttonConfirmar);

	}

	@Override
	public void iniciarGuiControlador() {
		configuracaoGui = new ConfiguracaoGui(this);
	}

	@Override
	public void iniciarLayout() {
		setBorder(Sis.getBordaPainel());
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	}

	@Override
	public void iniciarTabela() {
	}

	@Override
	public void limparGui() {
		configuracaoGui.limparGui();
	}

	@Override
	public void reiniciarGui() {

	}
}
