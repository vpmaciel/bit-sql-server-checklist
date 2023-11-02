package br.arquitetura.gui;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JToolBar;

public class ToolBar {

	JButton buttonAnalise = new JButton(Imagem.getAnalise());
	JButton buttonCsv = new JButton(Imagem.getCsv());
	JButton buttonExclui = new JButton(Imagem.getExclui());
	JButton buttonExportar = new JButton(Imagem.getExportar());
	JButton buttonXls = new JButton(Imagem.getXls());
	JButton buttonGrafico = new JButton(Imagem.getGrafico());
	JButton buttonHome = new JButton(Imagem.getHome());
	JButton buttonImportar = new JButton(Imagem.getImportar());
	JButton buttonImprime = new JButton(Imagem.getImprime());
	JButton buttonJson = new JButton(Imagem.getJson());
	JButton buttonNegocios = new JButton(Imagem.getNegocios());
	JButton buttonNovo = new JButton(Imagem.getNovo());
	JButton buttonPesquisa = new JButton(Imagem.getPesquisar());
	JButton buttonPlanilha = new JButton(Imagem.getPlanilha());
	JButton buttonRegistros = new JButton(Imagem.getRegistros());
	JButton buttonRelatorio = new JButton(Imagem.getRelatorio());
	JButton buttonSalvar = new JButton(Imagem.getSalva());
	JButton buttonXml = new JButton(Imagem.getXml());

	JToolBar toolBar = new JToolBar();

	public ToolBar() {
		Dimension tamanhoToolBar = new Dimension(720, 40);
		toolBar.setPreferredSize(tamanhoToolBar);
		toolBar.setMinimumSize(tamanhoToolBar);
		toolBar.setSize(tamanhoToolBar);
		toolBar.setMaximumSize(tamanhoToolBar);
		toolBar.setFloatable(false);
		toolBar.setOpaque(false);
		buttonHome.setToolTipText("Home");
		toolBar.add(buttonHome);

		buttonNovo.setToolTipText("Novo");
		// toolBar.add(buttonNovo);

		buttonExclui.setToolTipText("Excluir");
		// toolBar.add(buttonExclui);

		buttonSalvar.setToolTipText("Salvar");
		// toolBar.add(buttonSalvar);

		buttonPesquisa.setToolTipText("Pesquisar");
		// toolBar.add(buttonPesquisa);

		buttonRegistros.setToolTipText("Registros");
		// toolBar.add(buttonRegistros);

		buttonImprime.setToolTipText("Imprimir");
		// toolBar.add(buttonImprime);

		buttonPlanilha.setToolTipText("Planilha");
		// toolBar.add(buttonPlanilha);

		buttonCsv.setToolTipText("Arquivo csv");
		// toolBar.add(buttonCsv);

		buttonXml.setToolTipText("Arquivo xml");
		// toolBar.add(buttonXml);

		buttonJson.setToolTipText("Arquivo json");
		// toolBar.add(buttonJson);

		buttonRelatorio.setToolTipText("Relatório");
		toolBar.add(buttonRelatorio);

		buttonXls.setToolTipText("Arquivo xls");
		// toolBar.add(buttonXls);

		buttonAnalise.setToolTipText("Análise");
		// toolBar.add(buttonAnalise);

		buttonNegocios.setToolTipText("Negócios");
		// toolBar.add(buttonNegocios);

		buttonGrafico.setToolTipText("Gráfico");
		// toolBar.add(buttonGrafico);

		buttonExportar.setToolTipText("Exportar");
		// toolBar.add(buttonExportar);

		buttonImportar.setToolTipText("Importar");
		// toolBar.add(buttonImportar);
	}

	public JButton getAnaliseBtn() {
		return buttonAnalise;
	}

	public JButton getCsvBtn() {
		return buttonCsv;
	}

	public JButton getExcluirBtn() {
		return buttonExclui;
	}

	public JButton getExportarBtn() {
		return buttonExportar;
	}

	public JButton getXlsBtn() {
		return buttonXls;
	}

	public JButton getGraficoBtn() {
		return buttonGrafico;
	}

	public JButton getHomeBtn() {
		return buttonHome;
	}

	public JButton getImportarBtn() {
		return buttonImportar;
	}

	public JButton getImprimirBtn() {
		return buttonImprime;
	}

	public JButton getJsonBtn() {
		return buttonJson;
	}

	public JButton getNegociosBtn() {
		return buttonNegocios;
	}

	public JButton getNovoBtn() {
		return buttonNovo;
	}

	public JButton getOdsBtn() {
		return buttonPlanilha;
	}

	public JButton getPesquisarBtn() {
		return buttonPesquisa;
	}

	public JButton getRegistrosBtn() {
		return buttonRegistros;
	}

	public JButton getRelatorioBtn() {
		return buttonRelatorio;
	}

	public JButton getSalvarBtn() {
		return buttonSalvar;
	}

	public JToolBar getTB() {
		return toolBar;
	}

	public JButton getXmlBtn() {
		return buttonXml;
	}
}