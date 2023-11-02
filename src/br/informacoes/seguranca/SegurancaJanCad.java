package br.informacoes.seguranca;

import java.awt.FlowLayout;
import java.awt.KeyboardFocusManager;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.WindowConstants;

import br.arquitetura.Sis;
import br.arquitetura.gui.ConfiguracaoGui;
import br.arquitetura.gui.FocoEvento;
import br.arquitetura.gui.Gui;
import br.arquitetura.gui.Imagem;

@SuppressWarnings("serial")
public final class SegurancaJanCad extends JFrame implements Gui {

	private SegurancaControl segurancaControl;
	private SegurancaPainelCad segurancaPainelCad;
	private ConfiguracaoGui configuracaoGui;

	public SegurancaJanCad() {
		iniciarLayout();
		iniciarGui();
		iniciarFocoControlador();
		iniciarGuiControlador();
		iniciarControlador();
	}

	@Override
	public void atualizarTable() {
	}

	public void desabilitarGui() {
	}

	public SegurancaControl getBancoCont() {
		return segurancaControl;
	}

	public SegurancaPainelCad getSegurancaPainelCad() {
		return segurancaPainelCad;
	}

	@Override
	public ConfiguracaoGui getConfiguracaoGui() {
		return configuracaoGui;
	}

	@Override
	public void iniciarControlador() {
		setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		segurancaControl = new SegurancaControl();
		addWindowListener(segurancaControl.new Frame());
		segurancaPainelCad.getTB().getRelatorioBtn().addActionListener(segurancaControl.new Relatorio());
		segurancaPainelCad.getTB().getXlsBtn().addActionListener(segurancaControl.new FechaJanela());
		segurancaPainelCad.getTB().getHomeBtn().addActionListener(segurancaControl.new Home());
		segurancaPainelCad.getTB().getJsonBtn().addActionListener(segurancaControl.new FormatoJson());
		segurancaPainelCad.getGuiConfirmar().addActionListener(segurancaControl.new ConfigurarDataBase());
	}

	@Override
	public void iniciarFocoControlador() {
		new FocoEvento(this);
	}

	@Override
	public void iniciarGui() {
		setIconImage(Imagem.getLogoTipoImage());
		segurancaPainelCad = new SegurancaPainelCad();

		final JScrollPane scrollPane = new JScrollPane(segurancaPainelCad);

		KeyboardFocusManager.getCurrentKeyboardFocusManager().addPropertyChangeListener("focusOwner",
				new PropertyChangeListener() {

					@Override
					public void propertyChange(PropertyChangeEvent evt) {
						if (!(evt.getNewValue() instanceof JComponent)) {
							return;
						}
						JComponent focused = (JComponent) evt.getNewValue();
						if (segurancaPainelCad.isAncestorOf(focused)) {
							segurancaPainelCad.scrollRectToVisible(focused.getBounds());
						}
					}
				});
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		add(scrollPane);
		setContentPane(scrollPane);
		pack();
	}

	@Override
	public void iniciarGuiControlador() {
		configuracaoGui = new ConfiguracaoGui(this);
	}

	@Override
	public void iniciarLayout() {
		setLayout(new FlowLayout(FlowLayout.CENTER));
		setPreferredSize(Sis.getTamanhoJanela());
		setMinimumSize(Sis.getTamanhoJanela());
		setSize(Sis.getTamanhoJanela());
		setMaximumSize(Sis.getTamanhoJanela());
	}

	@Override
	public void iniciarTabela() {
	}

	@Override
	public void limparGui() {
		segurancaPainelCad.limparGui();
	}

	@Override
	public void reiniciarGui() {
		segurancaPainelCad.reiniciarGui();
	}
}