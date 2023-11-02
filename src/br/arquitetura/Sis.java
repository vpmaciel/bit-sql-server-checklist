package br.arquitetura;

import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.Format;
import java.text.NumberFormat;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.border.Border;

import br.arquitetura.gui.Msg;

public class Sis {
	private static final String AppSeparador = System.getProperty("file.separator");
	private static String caminhoAppRaiz = System.getProperty("user.dir");;
	private static final String caminhoAppSistema = caminhoAppRaiz + AppSeparador + "app" + AppSeparador;
	private static final String caminhoDiretorioApp = caminhoAppSistema + "sqlserver" + AppSeparador;
	private static final String caminhoDiretorioAppDados = caminhoDiretorioApp + "dados" + AppSeparador;
	private static final String caminhoDiretorioDadosCsv = caminhoDiretorioAppDados + "csv" + AppSeparador;
	private static final String caminhoDiretorioDadosJson = caminhoDiretorioAppDados + "json" + AppSeparador;
	private static final String caminhoDiretorioDadosPdf = caminhoDiretorioAppDados + "pdf" + AppSeparador;
	private static final String caminhoDiretorioDadosTxt = caminhoDiretorioAppDados + "txt" + AppSeparador;
	private static final String caminhoDiretorioDadosXml = caminhoDiretorioAppDados + "xml" + AppSeparador;
	private static final String caminhoDiretorioLog = caminhoDiretorioApp + "logs";
	private static final String caminhoDiretorioOds = caminhoDiretorioAppDados + "ods" + AppSeparador;
	private static final String caminhoDiretorioVideos = caminhoDiretorioApp + "videos";
	private static final String csvSeparador = String.format("%s", ";");
	private static Sis instancia;
	private static Locale locale;
	private static Dimension tamanhoTela;

	static {		
		tamanhoTela = new Dimension(800, 585);
		criarDiretorios();
	}

	public static void abrirDiretorio(String URL) {
		File arquivo = new File(URL);
		try {
			Desktop.getDesktop().open(arquivo);
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}

	}

	public static void criarDiretorios() {
		try {
			File arquivo = new File(caminhoAppRaiz);
			arquivo.mkdir();
			arquivo = new File(caminhoAppSistema);
			arquivo.mkdir();
			arquivo = new File(caminhoDiretorioApp);
			arquivo.mkdir();
			arquivo = new File(caminhoDiretorioAppDados);
			arquivo.mkdir();
			arquivo = new File(caminhoDiretorioLog);
			arquivo.mkdir();
			arquivo = new File(caminhoDiretorioOds);
			arquivo.mkdir();
			arquivo = new File(caminhoDiretorioDadosCsv);
			arquivo.mkdir();
			arquivo = new File(caminhoDiretorioDadosJson);
			arquivo.mkdir();
			arquivo = new File(caminhoDiretorioDadosPdf);
			arquivo.mkdir();
			arquivo = new File(caminhoDiretorioDadosTxt);
			arquivo.mkdir();
			arquivo = new File(caminhoDiretorioDadosXml);
			arquivo.mkdir();
			arquivo = new File(caminhoDiretorioVideos);
			arquivo.mkdir();
		} catch (Exception exception) {
			exception.printStackTrace();
			Msg.erroCriarPasta();
		}
	}

	public static Border getBordaPainel() {
		return BorderFactory.createTitledBorder("DADOS");
	}

	public static String getCaminhoDadosCsv() {
		return caminhoDiretorioDadosCsv;
	}

	public static String getCaminhoDadosJson() {
		return caminhoDiretorioDadosJson;
	}

	public static String getCaminhoDadosPdf() {
		return caminhoDiretorioDadosPdf;
	}

	public static String getCaminhoDadosTxt() {
		return caminhoDiretorioDadosTxt;
	}

	public static String getCaminhoDadosXml() {
		return caminhoDiretorioDadosXml;
	}

	public static String getCaminhoDiretoriolog() {
		return caminhoDiretorioLog;
	}

	public static String getCaminhoDiretorioOds() {
		return caminhoDiretorioOds;
	}

	public static String getCaminhoDiretoriovideos() {
		return caminhoDiretorioVideos;
	}

	public static String getCsvSeparador() {
		return csvSeparador;
	}

	public static synchronized Sis getInstancia() {
		return instancia == null ? new Sis() : instancia;
	}

	public static Locale getLocale() {
		return Locale.getDefault();
	}

	public static String getNomeHost() {
		try {
			return InetAddress.getLocalHost().getCanonicalHostName();
		} catch (UnknownHostException e) {
			return null;
		}
	}

	public static String getNomeSistema() {
		return "SQL SERVER CHECK LIST";
	}

	public static Cursor getNovaJanelaCursor() {
		return Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
	}

	public static Format getNumeroFormatado() {

		return NumberFormat.getNumberInstance(Sis.locale);
	}

	public static String getSeparador() {
		return AppSeparador;
	}

	public static Dimension getTamanhoJanela() {
		return tamanhoTela;
	}
}
