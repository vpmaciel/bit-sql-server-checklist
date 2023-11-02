package br.arquitetura.gui;

import javax.swing.JOptionPane;

import br.sistema.ajuda.sobre.SobrePainelCad;
import br.sistema.main.MainJan;

public final class Msg {

	private static Object[] botoesSimNao = new Object[] { "Sim", "Nao" };

	public static void ajuda() {
		JOptionPane.showMessageDialog(MainJan.getFrameMain(), new SobrePainelCad(), "Sobre o Sistema",
				JOptionPane.INFORMATION_MESSAGE);
	}

	public static int confirmarSairDoSistema() {
		return JOptionPane.showOptionDialog(null, "Sair do Sistema ?", "Informação", JOptionPane.INFORMATION_MESSAGE,
				JOptionPane.YES_NO_OPTION, null, botoesSimNao, botoesSimNao[JOptionPane.YES_NO_OPTION]);
	}

	public static int confirmarSalvarRegistro() {
		return JOptionPane.showOptionDialog(null, "Salvar o registro ?", "Informação", JOptionPane.INFORMATION_MESSAGE,
				JOptionPane.YES_NO_OPTION, null, botoesSimNao, botoesSimNao[JOptionPane.INFORMATION_MESSAGE]);
	}

	public static void erroAbrirArquivo() {
		JOptionPane.showMessageDialog(null, "Não foi possível abrir arquivo !", "Erro", JOptionPane.ERROR_MESSAGE);
	}

	public static void erroArquivo() {
		JOptionPane.showMessageDialog(null, "Erro de arquivo !", "Erro", JOptionPane.ERROR_MESSAGE);
	}

	public static void erroArquivoNaoEncontrado() {
		JOptionPane.showMessageDialog(null, "Não foi possível encontrar arquivo !", "Erro", JOptionPane.ERROR_MESSAGE);
	}

	public static void erroCriarArquivo() {
		JOptionPane.showMessageDialog(null, "Não foi possível criar arquivo !", "Erro", JOptionPane.ERROR_MESSAGE);
	}

	public static void erroCriarPasta() {
		JOptionPane.showMessageDialog(null, "Não foi possível criar pasta !", "Erro", JOptionPane.ERROR_MESSAGE);
	}

	public static void erroGeral() {
		JOptionPane.showMessageDialog(null, "Foi encontrado um erro !", "Erro", JOptionPane.ERROR_MESSAGE);
	}

	public static void erroLookAndFeel() {
		JOptionPane.showMessageDialog(null, "Não foi possível utilizar Look and Feel !", "Erro",
				JOptionPane.ERROR_MESSAGE);
	}

	public static void erroCodificacao() {
		JOptionPane.showMessageDialog(null, "Erro de cofificação !", "Erro", JOptionPane.ERROR_MESSAGE);
	}
}
